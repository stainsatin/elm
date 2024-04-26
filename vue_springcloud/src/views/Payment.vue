<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>在线支付</p>
    </header>
    <div style="height: 2vw"></div>

    <!-- 订单信息部分 -->
    <div class="order">
      <h3>订单信息：</h3>
      <div
        style="height: 1.5px; background-color: #ddd; margin-bottom: 1vw"
      ></div>
      <div class="order-info">
        <p>
          {{ orders.business.businessName }}
          <i class="fa fa-caret-down" @click="detailetShow"></i>
        </p>
        <p>&#165;{{ orders.orderTotal }}</p>
      </div>
      <!-- 订单明细部分 -->
      <ul class="order-detailet" v-show="isShowDetailet">
        <li v-for="item in orders.list">
          <p>{{ item.food.foodName }} x {{ item.quantity }}</p>
          <p>&#165;{{ item.food.foodPrice * item.quantity }}</p>
        </li>
        <li>
          <p>配送费</p>
          <p>&#165;{{ orders.business.deliveryPrice }}</p>
        </li>
      </ul>
    </div>

    <!-- 积分部分 -->
    <div class="credit">
      <h3>积分信息：</h3>
      <div
        style="height: 1.5px; background-color: #ddd; margin-bottom: 1vw"
      ></div>
      <div class="credit-info">
        <p>您当前可用的积分总额为{{ "  " + this.creditNum + " " }}</p>
        <p>消耗{{ " " + this.consumeCreditNum + " 积分 " }}</p>
        <p>可抵扣{{ "  " + this.deductionMoney + " 元 " }}</p>
      </div>
      <div class="buttonofcredit">
        使用积分<input
          type="radio"
          name="radios"
          value="1"
          v-model="useCreditOrNot"
          style="width: 6vw; height: 3.2vw"
        />
      </div>
      <div class="buttonofcredit">
        不使用积分<input
          type="radio"
          name="radios"
          value="0"
          v-model="useCreditOrNot"
          style="width: 6vw; height: 3.2vw"
        />
      </div>
    </div>

    <!-- 支付方式部分 -->
    <ul class="payment-type">
      <h3>支付方式：</h3>
      <div
        style="height: 1.5px; background-color: #ddd; margin-bottom: 1vw"
      ></div>
      <li>
        <img src="../assets/alipay.png" />
        <input
          type="radio"
          name="method"
          checked
          style="width: 6vw; height: 3.2vw"
        />
      </li>
      <li>
        <img src="../assets/wechat.png" />
        <input type="radio" name="method" style="width: 6vw; height: 3.2vw" />
      </li>
    </ul>
    <div class="payment-button" @click="payBusiness(orders.orderTotal)">
      <button>确认支付</button>
    </div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";
import { onMounted, onBeforeUnmount } from "vue";
export default {
  name: "Payment",
  data() {
    return {
      orderId: this.$route.query.orderId,
      orders: {
        business: {},
      },
      useCreditOrNot: 1,
      deductionMoney: 0,
      consumeCreditNum: 0,
      creditNum: 0,
      isShowDetailet: false,
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    //获取订单
    let url_1 = "OrdersController/getOrdersById/" + this.orderId;
    this.$axios
      .get(url_1)
      .then((response) => {
        this.orders = response.data.result;
        // 有了订单信息后，再查询可用积分，
		//否则异步请求（如axios.get()）不会阻塞代码的执行。当您发起一个异步请求时，代码会继续执行，而不是等待请求完成。
        let url_2 = "CreditController/queryAvailableCredit/" + this.user.userId;
        return this.$axios.get(url_2); // 返回这个请求，以便我们可以链式调用.then()
      })
      .then((response) => {
        this.creditNum = response.data.result;
        let money = Math.floor(this.orders.orderTotal);
        console.log("money:", money);
        this.queryConsumeCreditByPaying(this.user.userId, money, this.creditNum);
      })
      .catch((error) => {
        console.error(error);
      });
    //查询可用积分
    // let url_2 = "CreditController/queryAvailableCredit/" + this.user.userId;
    // this.$axios
    //   .get(url_2)
    //   .then((response) => {
    //     this.creditNum = response.data.result;
    //     console.log("Before floor:", this.orders.orderTotal);
    //     let money = Math.floor(this.orders.orderTotal);
    //     console.log("money:",money)
    //     this.queryConsumeCreditByPaying(
    //       this.user.userId,
    //       money,
    //       this.creditNum
    //     );
    //   })
    //   .catch((error) => {
    //     console.error(error);
    //   });
  },
  setup() {
    onMounted(() => {
      // 这里的代码是实现：一旦路由到在线支付组件，就不能回到订单确认组件。
      // 先将当前url添加到history对象中
      history.pushState(null, null, document.URL);
      // popstate事件能够监听history对象的变化
      window.onpopstate = () => {
        router.push({
          path: "/index",
        });
      };
    });

    onBeforeUnmount(() => {
      window.onpopstate = null;
    });
  },
  methods: {
    detailetShow() {
      this.isShowDetailet = !this.isShowDetailet;
    },
    queryConsumeCreditByPaying(userId, money, creditNum) {
      //查询消耗积分和抵扣的钱
      let url =
        "CreditController/consumeCreditByPaying/" +
        userId +
        "/" +
        Math.floor(money) +
        "/" +
        creditNum;
      this.$axios
        .get(url)
        .then((response) => {
          console.log(response.data.result);
          this.consumeCreditNum = Math.round(response.data.result.creditNum);
          this.deductionMoney =
            Math.floor(response.data.result.deductionMoney * 100) / 100.0;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    payBusiness(moneyNum) {
      //支付
      let businessWalletId = 1; //this.orders.business.walletId;
      //console.log('this.orders',this.orders);
      let userWalletId = this.user.walletId;

      if (userWalletId == null) {
        alert("您未开通虚拟钱包!请开通!");
      } else if (businessWalletId == null) {
        alert("商家未开通虚拟钱包!请通知商家开通!");
      } else {
        if (this.useCreditOrNot == 0) {
          //不用积分
          let url =
            "VirtualWalletController/transferMoney/" +
            userWalletId +
            "/" +
            businessWalletId +
            "/" +
            moneyNum +
            "/" +
            this.orderId;
          this.$axios
            .post(url)
            .then((response) => {
              let res = response.data.result;
              //返回1成功，说明余额有钱
              if (res == 1) {
                this.$router.push({
                  path: "/paymentDone",
                });
              } else {
                //返回0失败，说明没钱
                alert("您没钱了，请去充值!");
                //跳转到充值页面
                this.$router.push({
                  path: "/wallet",
                });
              }
            })
            .catch((error) => {
              console.error(error);
            });
        } else {
          //使用积分
          let url =
            "CreditController/transferMoneyWithCreditConsume/" +
            userWalletId +
            "/" +
            businessWalletId +
            "/" +
            moneyNum +
            "/" +
            this.orderId +
            "/" +
            this.deductionMoney +
            "/" +
            this.consumeCreditNum +
            "/" +
            this.user.userId;
          this.$axios
            .post(url)
            .then((response) => {
              let res = response.data.result;
              //返回1成功，说明余额有钱
              if (res == 1) {
                this.$router.push({
                  path: "/paymentDone",
                });
              } else {
                //返回0失败，说明没钱
                alert("您没钱了，请去充值!");
                //跳转到充值页面
                this.$router.push({
                  path: "/wallet",
                });
              }
            })
            .catch((error) => {
              console.error(error);
            });
        }
      }
    },
  },
  components: {
    Footer,
  },
};
</script>
<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
}

/******************* 头部 ******************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: deepskyblue;

  position: fixed;
  top: 0;
  left: 0;

  display: flex;
  justify-content: center;
  align-items: center;

  color: white;
  font-size: 5vw;
  font-weight: 700;
}

/****************** 订单信息部分 ******************/
.wrapper .order {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 12vw 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .order h3 {
  padding-bottom: 2vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .order .order-info {
  box-sizing: border-box;
  padding: 1vw 0;
  font-size: 3.5vw;
  color: darkslategray;

  display: flex;
  justify-content: space-between;
}

.wrapper .order .order-info .fa-caret-down {
  user-select: none;
  cursor: pointer;
}

.wrapper .order .order-info p:last-child {
  color: orangered;
  font-size: 3.5vw;
}

/****************** 订单明细部分 ******************/
.wrapper .order .order-detailet {
  width: 100%;
}

.wrapper .order .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 0;
  color: darkslategray;
  font-size: 2.5vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/****************** 积分部分 ******************/
.wrapper .credit {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .credit h3 {
  padding-bottom: 2vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .credit .credit-info {
  box-sizing: border-box;
  padding: 1vw 0;
  font-size: 3.5vw;
  color: darkslategray;

  display: flex;
  flex-direction: column;
}

.wrapper .credit .credit-info p {
  margin: 1vw 0;
}

.wrapper .credit .buttonofcredit {
  box-sizing: border-box;
  padding: 1vw 0;
  font-size: 3.5vw;
  color: darkslategray;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/****************** 支付方式部分 ******************/
.wrapper .payment-type {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .payment-type h3 {
  padding-bottom: 2vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .payment-type li {
  width: 100%;
  box-sizing: border-box;
  padding: 3vw 0;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .payment-type li img {
  width: 33vw;
  height: 8.9vw;
}

.wrapper .payment-button {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 10vw 20vw;
  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .payment-button button {
  width: 100%;
  height: 10vw;
  font-size: 3.5vw;
  color: white;
  background-color: mediumseagreen;
  border: none;
  outline: none;
  border-radius: 4vw;

  cursor: pointer;
}
</style>
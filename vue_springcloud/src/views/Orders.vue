<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>确认订单</p>
    </header>
    <!-- 订单信息部分 -->
    <div class="order-info">
      <h5>订单配送至：</h5>
      <div class="order-info-address" @click="toUserAddress">
        <p>
          {{
            deliveryaddress != null ? deliveryaddress.address : "请选择送货地址"
          }}
        </p>
        <i class="fa fa-angle-right"></i>
      </div>
      <p>
        {{
          deliveryaddress != null
            ? deliveryaddress.contactName +
              " " +
              sexFilter(deliveryaddress.contactSex) +
              " " +
              deliveryaddress.contactTel
            : ""
        }}
      </p>
      <!-- <p>{{ user.userName }}{{ sexFilter(user.userSex) }} {{ user.userId }}</p> -->
    </div>

    <!-- 订单明细部分 -->
    <div class="order-box">
      <h3>{{ business.businessName }}</h3>
      <ul class="order-detailed">
        <li v-for="item in cartArr">
          <div class="order-detailed-left">
            <img :src="item.food.foodImg" />
            <p>{{ item.food.foodName }} x {{ item.quantity }}</p>
          </div>
          <p>&#165;{{ item.food.foodPrice * item.quantity }}</p>
        </li>
      </ul>

      <div class="order-deliveryfee">
        <p>配送费</p>
        <p>&#165;{{ business.deliveryPrice }}</p>
      </div>
    </div>
    <div style="height: 18vw"></div>
    <!-- 合计部分 -->
    <div class="total">
      <div class="total-left">&#165;{{ totalPrice }}</div>
      <div class="total-right" @click="toPayment">去支付</div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Orders",
  data() {
    return {
      businessId: this.$route.query.businessId,
      business: {},
      user: {},
      cartArr: [],
      deliveryaddress: {},
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.deliveryaddress = this.$getLocalStorage(this.user.userId);

    //查询当前商家
    let businessUrl = "BusinessController/getBusinessById/" + this.businessId;
    this.$axios
      .get(businessUrl)
      .then((response) => {
        this.business = response.data.result;
      })
      .catch((error) => {
        console.error(error);
      });

    //查询当前用户在购物车中的当前商家食品列表
    let cartUrl =
      "CartController/listCart/" + this.user.userId + "/" + this.businessId;
    this.$axios
      .get(cartUrl)
      .then((response) => {
        this.cartArr = response.data.result;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  computed: {
    totalPrice() {
      let totalPrice = 0;
      for (let cartItem of this.cartArr) {
        totalPrice += cartItem.food.foodPrice * cartItem.quantity;
      }
      totalPrice += this.business.deliveryPrice;
      return totalPrice;
    },
  },
  methods: {
    sexFilter(userSex) {
      return userSex == 1 ? "先生" : "女士";
    },
    toUserAddress() {
      this.$router.push({
        path: "/userAddress",
        query: {
          businessId: this.businessId,
        },
      });
    },
    toPayment() {
      if (this.deliveryaddress == null) {
        alert("请选择送货地址！");
        return;
      }

      //创建订单
      let url = `OrdersController/createOrders/${this.user.userId}/${this.businessId}/${this.deliveryaddress.daId}/${this.totalPrice}`;
      this.$axios
        .post(url)
        .then((response) => {
          let orderId = response.data.result;
          if (orderId > 0) {
            this.$router.push({
              path: "/payment",
              query: {
                orderId: orderId,
              },
            });
          } else {
            alert("创建订单失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f2f2f2;
}

/****************** header部分 ******************/
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
.wrapper .order-info {
  /*注意这里，不设置高，靠内容撑开。因为地址有可能折行*/
  width: 100%;
  box-sizing: border-box;
  padding: 12vw 4vw 4vw;

  background-color: deepskyblue;
  color: white;
  border-bottom-right-radius: 6vw;
  border-bottom-left-radius: 6vw;

  user-select: none;
  cursor: pointer;
}

.wrapper .order-info h5 {
  font-size: 3vw;
  font-weight: 400;
}

.wrapper .order-info .order-info-address {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: 700;
  user-select: none;
  cursor: pointer;
  margin: 1vw 0;
}

.wrapper .order-info .order-info-address p {
  width: 90%;
  font-size: 5vw;
}

.wrapper .order-info .order-info-address i {
  font-size: 6vw;
}

.wrapper .order-info p {
  font-size: 3.5vw;
}

.wrapper h3 {
  box-sizing: border-box;
  padding: 3vw;
  font-size: 4vw;
  color: #666;
  border-bottom: solid 2px #ddd;
}

/****************** 订单明细部分 ******************/
.wrapper .order-box {
  width: 95%;
  margin: 2vw 2.5vw;
  padding-bottom: 4vw;

  display: flex;
  flex-direction: column;

  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .order-box .order-detailed li {
  width: 100%;
  height: 16vw;
  box-sizing: border-box;
  padding: 3vw;
  margin-top: 2vw;
  margin-bottom: 2vw;

  color: gray;
  font-size: 3.5vw;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order-box .order-detailed li .order-detailed-left {
  display: flex;
  align-items: center;
}

.wrapper .order-box .order-detailed li .order-detailed-left img {
  width: 12vw;
  height: 12vw;
  margin-right: 3vw;
}

.wrapper .order-box .order-detailed li .order-detailed-left p {
  font-size: 3.5vw;
  margin-left: 3vw;
}

.wrapper .order-box .order-detailed li p {
  font-size: 3.5vw;
}

.wrapper .order-box .order-deliveryfee {
  width: 100%;
  height: 10vw;
  box-sizing: border-box;
  padding: 3vw;
  color: gray;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 3.5vw;
}

/****************** 订单合计部分 ******************/
.wrapper .total {
  width: 95%;
  height: 14vw;
  position: fixed;
  bottom: 3vw;
  left: 2.5vw;
  display: flex;
}

.wrapper .total .total-left {
  background-color: dimgray;
  color: white;
  font-size: 4.7vw;
  font-weight: 700;
  border-top-left-radius: 5vw;
  border-bottom-left-radius: 5vw;

  display: flex;
  flex: 2;
  align-items: center;
  justify-content: center;
}

.wrapper .total .total-right {
  background-color: mediumseagreen;
  color: white;
  font-size: 4.7vw;
  font-weight: 700;
  border-top-right-radius: 5vw;
  border-bottom-right-radius: 5vw;

  display: flex;
  flex: 1;
  align-items: center;
  justify-content: center;

  cursor: pointer;
  user-select: none;
}
</style>

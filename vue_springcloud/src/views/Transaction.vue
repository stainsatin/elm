<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>积分明细</p>
    </header>
    <div style="height: 2vw"></div>

    <!--积分获取记录-->
    <ul class="in-credit">
      <h3>获取积分记录：</h3>
      <div
        v-show="creditArr.filter((credit) => credit.credit > 0).length > 0"
        style="height: 1.5px; background-color: #ddd; margin-bottom: 1vw"
      ></div>
      <li v-for="item in creditArr">
        <div class="credit-info" v-if="item.credit > 0">
          <p>积分 {{ item.credit }}</p>
          <i class="fa fa-caret-down" @click="detailetShow(item)"></i>
        </div>
        <ul
          class="credit-detailet"
          v-if="item.credit > 0"
          v-show="item.isShowDetailet"
        >
          <li>
            <p>积分规则代码</p>
            <p>{{ item.ruleCode }}</p>
          </li>
          <li>
            <p>相关事件</p>
            <p>
              {{ item.eventId == null ? "不与其他事件相关" : item.eventId }}
            </p>
          </li>
          <li>
            <p>创建时间</p>
            <p>{{ item.createTime }}</p>
          </li>
          <li>
            <p>过期时间</p>
            <p>{{ item.expiredTime }}</p>
          </li>
        </ul>
      </li>
    </ul>
    <!-- <div class="padding-bottom:20vw ;"> -->

    <!--积分消费记录-->
    <ul class="out-credit">
      <h3>消费积分记录：</h3>
      <div
        v-show="creditArr.filter((credit) => credit.credit < 0).length > 0"
        style="height: 1.5px; background-color: #ddd; margin-bottom: 1vw"
      ></div>
      <li v-for="item in creditArr">
        <div class="credit-info" v-if="item.credit < 0">
          <p>积分 {{ -1 * item.credit }}</p>
          <i class="fa fa-caret-down" @click="detailetShow(item)"></i>
        </div>
        <ul
          class="credit-detailet"
          v-if="item.credit < 0"
          v-show="item.isShowDetailet"
        >
          <li>
            <p>积分规则代码</p>
            <p>{{ item.ruleCode }}</p>
          </li>
          <li>
            <p>相关事件</p>
            <p>
              {{ item.eventId == null ? "不与其他事件相关" : item.eventId }}
            </p>
          </li>
          <li>
            <p>创建时间</p>
            <p>{{ item.createTime }}</p>
          </li>
          <li>
            <p>过期时间</p>
            <p>{{ item.expiredTime }}</p>
          </li>
        </ul>
      </li>
    </ul>
    <!-- </div> -->
    <h3 class="bottom">-------------------我是有底线的-------------------</h3>
    <div style="height: 16vw"></div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";

export default {
  name: "OrderList",
  data() {
    return {
      transactionArr: [],
      user: {},
      creditArr: [],
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    let url = "TransactionController/queryTransaction/" + this.user.walletId;
    this.$axios
      .get(url)
      .then((response) => {
        let result = response.data.result;
        console.log("array", result);
        for (let orders of result) {
          orders.isShowDetailet = false;
          if (orders.type == 2) {
            orders.businessName = this.getBusinessNameByWalletId(
              orders.inputwalletId,
              orders
            );
            // console.log(orders.businessName);
          }
        }
        this.transactionArr = result;
      })
      .catch((error) => {
        console.error(error);
      });

    this.queryAllCredit(this.user.userId);
  },
  methods: {
    queryAllCredit(userId) {
      let url = "CreditController/queryAllCredit/" + userId;
      this.$axios
        .get(url)
        .then((response) => {
          let res = response.data.result;
          for (let orders of res) {
            orders.isShowDetailet = false;
          }
          this.creditArr = res;
          console.log(this.creditArr);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    detailetShow(orders) {
      orders.isShowDetailet = !orders.isShowDetailet;
    },
    getBusinessNameByWalletId(walletId, orders) {
      let url = "BusinessController/queryBusinessNameByWalletId/" + walletId;
      this.$axios
        .get(url)
        .then((response) => {
          orders.businessName = response.data.result;
        })
        .catch((error) => {
          console.error(error);
        });
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
  height: 100%;
}

/****************** header部分 ******************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: deepskyblue;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: center;
}

/****************** 历史订单列表部分 ******************/
.wrapper .in-credit {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 12vw 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .in-credit h3 {
  padding-bottom: 2vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .in-credit li {
  width: 100%;
}

.wrapper .in-credit li .credit-info {
  box-sizing: border-box;
  padding: 1vw 0;
  font-size: 3.5vw;
  color: darkslategray;

  display: flex;
  justify-content: space-between;
}

.wrapper .in-credit li .credit-info i {
  cursor: pointer;
}

.wrapper .in-credit li .credit-info .credit-info-right {
  display: flex;
}

.wrapper .in-credit li .credit-info .credit-info-right .credit-info-right-icon {
  margin-left: 2vw;
  padding: 0 1vw;
  background-color: darkorange;
  color: white;
  border-radius: 1vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .in-credit li .credit-detailet {
  width: 100%;
}

.wrapper .in-credit li .credit-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 0;
  color: darkslategray;
  font-size: 2.5vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .out-credit h3 {
  margin-bottom: 2vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .out-credit {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 4vw 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .out-credit li {
  width: 100%;
}

.wrapper .out-credit li .credit-info {
  box-sizing: border-box;
  padding: 1vw 0;
  font-size: 3.5vw;
  color: darkslategray;

  display: flex;
  justify-content: space-between;
}

.wrapper .out-credit li .credit-info i {
  cursor: pointer;
}

.wrapper .out-credit li .credit-info .credit-info-right {
  display: flex;
}

.wrapper
  .out-credit
  li
  .credit-info
  .credit-info-right
  .credit-info-right-icon {
  margin-left: 2vw;
  padding: 0 1vw;
  background-color: darkorange;
  color: white;
  border-radius: 1vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .out-credit li .credit-detailet {
  width: 100%;
}

.wrapper .out-credit li .credit-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 0;
  color: darkslategray;
  font-size: 2.5vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .bottom {
  box-sizing: border-box;
  padding: 2vw 4vw 4vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: #999;
  display: flex;
  justify-content: center;
}
</style>

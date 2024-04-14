<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>我的订单</p>
    </header>
    <div style="height: 2vw"></div>

    <!--未支付订单列表-->
    <ul class="unpaid-order">
      <h3>未支付订单信息：</h3>
      <div
        v-show="orderArr.length > 0"
        style="height: 1.5px; background-color: #ddd; margin-bottom: 1vw"
      ></div>
      <li v-for="item in orderArr">
        <div class="order-info" v-if="item.orderState == 0">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="detailetShow1(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal }}</p>
            <div class="order-info-right-icon" @click="toPay(item.orderId)">
              去支付
            </div>
          </div>
        </div>
        <!--未支付订单明细-->
        <ul
          class="order-detailet"
          v-if="item.orderState == 0"
          v-show="item.isShowDetailet"
        >
          <li v-for="odItem in item.list">
            <p>{{ odItem.food.foodName }} x {{ odItem.quantity }}</p>
            <p>&#165;{{ odItem.food.foodPrice * odItem.quantity }}</p>
          </li>
          <li>
            <p>配送费</p>
            <p>&#165;{{ item.business.deliveryPrice }}</p>
          </li>
        </ul>
      </li>
    </ul>

    <!--已支付订单列表-->
    <ul class="paid-order">
      <h3>已支付订单信息：</h3>
      <li v-for="item in orderArr">
        <div class="order-info" v-if="item.orderState == 1">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="detailetShow2(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal }}</p>
          </div>
        </div>
        <!--已支付订单明细-->
        <ul
          class="order-detailet"
          v-if="item.orderState == 1"
          v-show="item.isShowDetailet"
        >
          <li v-for="odItem in item.list">
            <p>{{ odItem.food.foodName }} x {{ odItem.quantity }}</p>
            <p>&#165;{{ odItem.food.foodPrice * odItem.quantity }}</p>
          </li>
          <li>
            <p>配送费</p>
            <p>&#165;{{ item.business.deliveryPrice }}</p>
          </li>
        </ul>
      </li>
    </ul>
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
      orderArr: [],
      user: {},
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    let url = "OrdersController/listOrdersByUserId/" + this.user.userId;
    this.$axios
      .get(url)
      .then((response) => {
        let result = response.data.result;
        //console.log(result);
        for (let orders of result) {
          orders.isShowDetailet = false;
        }
        this.orderArr = result;
        console.log(result);
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    detailetShow1(orders) {
      orders.isShowDetailet = !orders.isShowDetailet;
    },
    detailetShow2(orders) {
      orders.isShowDetailet = !orders.isShowDetailet;
    },
    toPay(orderId) {
      this.$router.push({
        path: "/payment",
        query: {
          orderId: orderId,
        },
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

  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;

  display: flex;
  justify-content: center;
  align-items: center;

  color: white;
  font-size: 5vw;
  font-weight: 700;
}

/****************** 历史订单列表部分 ******************/
.wrapper .unpaid-order {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 12vw 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .unpaid-order h3 {
  padding-bottom: 2vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .unpaid-order li {
  width: 100%;
}

.wrapper .unpaid-order li .order-info {
  box-sizing: border-box;
  padding: 1vw 0;
  font-size: 3.5vw;
  color: darkslategray;

  display: flex;
  justify-content: space-between;
}

.wrapper .unpaid-order li .order-info p i {
  cursor: pointer;
}

.wrapper .unpaid-order li .order-info .order-info-right {
  display: flex;
}

.wrapper .unpaid-order li .order-info .order-info-right .order-info-right-icon {
  margin-left: 2vw;
  padding: 0 1vw;
  background-color: darkorange;
  color: white;
  border-radius: 1vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .unpaid-order li .order-detailet {
  width: 100%;
}

.wrapper .unpaid-order li .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 0;
  color: darkslategray;
  font-size: 2.5vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .paid-order h3 {
  margin-bottom: 2vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .paid-order {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 4vw 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .paid-order li {
  width: 100%;
}

.wrapper .paid-order li .order-info {
  box-sizing: border-box;
  padding: 1vw 0;
  font-size: 3.5vw;
  color: darkslategray;

  display: flex;
  justify-content: space-between;
}

.wrapper .paid-order li .order-info .order-info-right {
  display: flex;
}

.wrapper .paid-order li .order-info .order-info-right .order-info-right-icon {
  margin-left: 2vw;
  padding: 0 1vw;
  background-color: darkorange;
  color: white;
  border-radius: 1vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .paid-order li .order-detailet {
  width: 100%;
}

.wrapper .paid-order li .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 0;
  color: darkslategray;
  font-size: 2.5vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>

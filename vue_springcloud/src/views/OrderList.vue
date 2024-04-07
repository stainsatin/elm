<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>我的订单</p>
    </header>
    <!-- 订单列表部分 -->
    <div style="height: 2vw"></div>
    <ul class="unpaid-order">
      <h3>未支付订单信息：</h3>
      <li v-for="item in orderArr">
        <div class="order-info" v-if="item.orderState == 0">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="detailetShow(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal }}</p>
            <div class="order-info-right-icon" @click="toPay(item.orderId)">
              去支付
            </div>
          </div>
        </div>
        <ul class="order-detailet" v-show="item.isShowDetailet">
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

    <ul class="paid-order">
      <h3>已支付订单信息：</h3>
      <li v-for="item in orderArr">
        <div class="order-info" v-if="item.orderState == 1">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="detailetShow(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal }}</p>
          </div>
        </div>
        <ul class="order-detailet" v-show="item.isShowDetailet">
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
    detailetShow(orders) {
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
  background-color: #f2f2f2;
  box-sizing: border-box;
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

/****************** 历史订单列表部分 ******************/
.wrapper .unpaid-order h3 {
  margin-bottom: 3vw;
  font-size: 3.5vw;
  font-weight: 300;
  color: gray;
}

.wrapper .unpaid-order {
  box-sizing: border-box;
  padding: 3vw 3vw 2vw;
  margin: 12vw 2.5vw 2vw;
  user-select: none;
  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;
}

.wrapper .unpaid-order li {
  width: 100%;
}

.wrapper .unpaid-order li .order-info {
  box-sizing: border-box;
  padding: 2vw 4vw;
  font-size: 3.5vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .unpaid-order li .order-info .order-info-right {
  display: flex;
}

.wrapper .unpaid-order li .order-info .order-info-right .order-info-right-icon {
  background-color: #f90;
  color: #fff;
  border-radius: 3px;
  margin-left: 2vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .unpaid-order li .order-detailet {
  width: 100%;
}

.wrapper .unpaid-order li .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;
  color: #666;
  font-size: 2.5vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .paid-order h3 {
  margin-bottom: 3vw;
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
  padding: 2vw 4vw;
  font-size: 3.5vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .paid-order li .order-info .order-info-right {
  display: flex;
}

.wrapper .paid-order li .order-info .order-info-right .order-info-right-icon {
  background-color: #f90;
  color: #fff;
  border-radius: 3px;
  margin-left: 2vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .paid-order li .order-detailet {
  width: 100%;
}

.wrapper .paid-order li .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;
  color: #666;
  font-size: 2.5vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>

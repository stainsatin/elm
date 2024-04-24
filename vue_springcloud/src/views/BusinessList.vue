<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>商家列表</p>
    </header>
    <!-- 商家列表部分 -->
    <ul class="merchant">
      <li v-for="item in businessArr" @click="toBusinessInfo(item.businessId)">
        <div class="merchant-img">
          <img :src="item.businessImg" />
          <div class="quantity" v-show="item.quantity > 0">
            {{ item.quantity }}
          </div>
        </div>
        <div class="merchant-info">
          <h3>{{ item.businessName }}</h3>
          <p>
            &#165;{{ item.starPrice }}起送 | &#165;{{ item.deliveryPrice }}配送
          </p>
          <p>{{ item.businessExplain }}</p>
        </div>
      </li>
    </ul>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";

export default {
  name: "BusinessList",
  data() {
    return {
      orderTypeId: this.$route.query.orderTypeId,
      businessName: this.$route.query.businessName,
      businessArr: [],
      user: {},
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    //根据orderTypeId查询商家信息
    if (this.orderTypeId !== undefined) {
      let url_1 =
        "BusinessController/listBusinessByOrderTypeId/" + this.orderTypeId;
      this.$axios
        .get(url_1)
        .then((response) => {
          this.businessArr = response.data.result;
          // console.log(response.data);
          //判断是否登录
          if (this.user != null) {
            this.listCart();
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }

    //根据businessName查询商家信息
    if (this.businessName !== undefined) {
      let url_2 = "BusinessController/listBusinessByKeyWords/" + this.businessName;
      this.$axios
        .get(url_2)
        .then((response) => {
          this.businessArr = response.data.result;
          //判断是否登录
          if (this.user != null) {
            this.listCart();
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }
  },
  components: {
    Footer,
  },
  methods: {
    listCart() {
      let url = "CartController/listCart/" + this.user.userId;
      this.$axios
        .get(url)
        .then((response) => {
          let cartArr = response.data.result;
          //遍历所有食品列表
          for (let businessItem of this.businessArr) {
            businessItem.quantity = 0; //某个商家
            for (let cartItem of cartArr) {
              if (cartItem.businessId == businessItem.businessId) {
                businessItem.quantity += cartItem.quantity;
              }
            }
          }
          this.businessArr.sort();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    toBusinessInfo(businessId) {
      this.$router.push({
        path: "/businessInfo",
        query: {
          businessId: businessId,
        },
      });
    },
  },
};
</script>
<style scoped>
/********* 总容器 *********/
.wrapper {
  width: 100%;
  background-color: #f2f2f2;
}

/********* 头部 *********/
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

/*********商家列表*********/
.wrapper .merchant {
  width: 100%;
  padding-top: 12vw;
  padding-bottom: 20vw;
}

.wrapper .merchant li {
  display: flex;
  padding: 2.5vw;
  margin: 2vw;
  user-select: none;
  cursor: pointer;
  background-color: white;
  border-radius: 5vw;
}

.wrapper .merchant li .merchant-img {
  position: relative;
}

.wrapper .merchant li img {
  width: 20vw;
  height: 20vw;
  border-radius: 6vw;
}

.wrapper .merchant li .merchant-img .quantity {
  width: 4vw;
  height: 4vw;
  background-color: red;
  border-radius: 3vw;

  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 3vw;
  color: white;

  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}

.wrapper .merchant li .merchant-info {
  width: 100%;
  margin-left: 3vw;
}

.wrapper .merchant li .merchant-info h3 {
  margin-bottom: 2vw;
  color: darkslategrey;
  font-size: 4vw;
}

.wrapper .merchant li .merchant-info p {
  margin-bottom: 2vw;
  font-size: 3vw;
  color: gray;
}
</style>

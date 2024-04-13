<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>地址管理</p>
    </header>
    <!-- 地址列表部分 -->
    <ul class="addresslist">
      <li v-for="item in deliveryAddressArr">
        <div class="addresslist-left" @click="setDeliveryAddress(item)">
          <h3>
            {{
              item.contactName +
              " " +
              sexFilter(item.contactSex) +
              " " +
              item.contactTel
            }}
          </h3>
          <p>{{ item.address }}</p>
        </div>
        <div class="addresslist-right">
          <i class="fa fa-edit" @click="editUserAddress(item.daId)"></i>
          <i class="fa fa-remove" @click="removeUserAddress(item.daId)"></i>
        </div>
      </li>
    </ul>
    <!-- 新增地址部分 -->
    <div class="addbtn" @click="toAddUserAddress">
      <button>
        <i class="fa fa-plus-circle"></i>
        新增收货地址
      </button>
    </div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";
export default {
  name: "UserAddress",
  data() {
    return {
      businessId: this.$route.query.businessId,
      user: {},
      deliveryAddressArr: [],
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");

    this.listDeliveryAddressByUserId();
  },
  components: {
    Footer,
  },
  methods: {
    sexFilter(userSex) {
      return userSex == 1 ? "先生" : "女士";
    },
    listDeliveryAddressByUserId() {
      //查询送货地址
      let url =
        "DeliveryAddressController/listDeliveryAddressByUserId/" +
        this.user.userId;
      this.$axios
        .get(url)
        .then((response) => {
          this.deliveryAddressArr = response.data.result;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    setDeliveryAddress(deliveryAddress) {
      //把用户选择的默认送货地址存储到localStorage中
      this.$setLocalStorage(this.user.userId, deliveryAddress);
      this.$router.push({
        path: "/orders",
        query: {
          businessId: this.businessId,
        },
      });
    },
    toAddUserAddress() {
      this.$router.push({
        path: "/addUserAddress",
        query: {
          businessId: this.businessId,
        },
      });
    },
    editUserAddress(daId) {
      this.$router.push({
        path: "/editUserAddress",
        query: {
          businessId: this.businessId,
          daId: daId,
        },
      });
    },
    removeUserAddress(daId) {
      if (!confirm("确认要删除此送货地址吗？")) {
        return;
      }

      let url = "DeliveryAddressController/removeDeliveryAddress/" + daId;
      this.$axios
        .delete(url)
        .then((response) => {
          if (response.data.result > 0) {
            let deliveryAddress = this.$getLocalStorage(this.user.userId);
            if (deliveryAddress != null && deliveryAddress.daId == daId) {
              this.$removeLocalStorage(this.user.userId);
            }
            this.listDeliveryAddressByUserId();
          } else {
            alert("删除地址失败！");
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
/*************** 总容器 ***************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f2f2f2;
}

/*************** header ***************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: deepskyblue;
  display: flex;
  justify-content: space-around;
  align-items: center;
  color: white;
  font-size: 5vw;
  position: fixed;
  left: 0;
  top: 0;
  /*保证在最上层*/
  z-index: 1000;
}

/*************** addresslist ***************/
.wrapper .addresslist {
  width: 100%;
  box-sizing: border-box;
  padding-top: 15vw;
}

.wrapper .addresslist li {
  width: 95%;
  padding: 5vw 3vw;
  box-sizing: border-box;
  margin: 0 2.5vw 3vw;

  background-color: white;
  border-radius: 5vw;
  border: solid 1.5px #ddd;

  display: flex;
  justify-content: space-between;
}

.wrapper .addresslist li .addresslist-left {
  flex: 5;
  /*左边这块区域是可以点击的*/
  user-select: none;
  cursor: pointer;
}

.wrapper .addresslist li .addresslist-left h3 {
  color: darkslategray;
  font-size: 3.5vw;
  font-weight: 500;
  margin-bottom: 1vw;
}

.wrapper .addresslist li .addresslist-left p {
  font-size: 3vw;
  color: darkslategray;
}

.wrapper .addresslist li .addresslist-right {
  font-size: 5vw;
  color: gray;
  display: flex;
  align-items: center;
  user-select: none;
}
.wrapper .addresslist li .addresslist-right .fa-remove {
  margin-left: 3vw;
  cursor: pointer;
}
.wrapper .addresslist li .addresslist-right .fa-edit {
  cursor: pointer;
}

/*************** 新增地址部分 ***************/
.wrapper .addbtn {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 10vw 18vw;
  display: flex;
  justify-content: center;
}
.wrapper .addbtn button {
  width: 100%;
  height: 10vw;
  background-color: deepskyblue;
  border: none;
  outline: none;
  border-radius: 4vw;

  font-size: 3.5vw;
  color: white;

  cursor: pointer;
}
.wrapper .addbtn button .fa-plus-circle {
  margin-right: 2vw;
}
</style>

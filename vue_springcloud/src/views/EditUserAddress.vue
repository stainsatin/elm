<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>编辑送货地址</p>
    </header>
    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div class="title">联系人：</div>
        <div class="content">
          <input
            type="text"
            v-model="deliveryAddress.contactName"
            placeholder="联系人
姓名"
          />
        </div>
      </li>
      <li>
        <div class="title">性别：</div>
        <div class="content" style="font-size: 3vw">
          <input
            type="radio"
            v-model="deliveryAddress.contactSex"
            value="1"
            style="width: 6vw; height: 3.2vw"
            checked
          />男
          <input
            type="radio"
            v-model="deliveryAddress.contactSex"
            value="0"
            style="width: 6vw; height: 3.2vw"
          />女
        </div>
      </li>
      <li>
        <div class="title">电话：</div>
        <div class="content">
          <input
            type="tel"
            v-model="deliveryAddress.contactTel"
            placeholder="电话"
          />
        </div>
      </li>
      <li>
        <div class="title">收货地址：</div>
        <div class="content">
          <input
            type="text"
            v-model="deliveryAddress.address"
            placeholder="收货地址"
          />
        </div>
      </li>
    </ul>
    <div class="button-add">
      <button @click="editUserAddress">更新</button>
    </div>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";
export default {
  name: "EditUserAddress",
  data() {
    return {
      businessId: this.$route.query.businessId,
      daId: this.$route.query.daId,
      user: {},
      deliveryAddress: {},
    };
  },
  created() {
    this.user = this.$getSessionStorage("user");
    let url = "DeliveryAddressController/getDeliveryAddressById/" + this.daId;
    this.$axios
      .get(url)
      .then((response) => {
        this.deliveryAddress = response.data.result;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  components: {
    Footer,
  },
  methods: {
    editUserAddress() {
      if (this.deliveryAddress.contactName == "") {
        alert("联系人姓名不能为空！");
        return;
      }
      if (this.deliveryAddress.contactTel == "") {
        alert("联系人电话不能为空！");
        return;
      }
      if (this.deliveryAddress.address == "") {
        alert("联系人地址不能为空！");
        return;
      }
      let url =
        "DeliveryAddressController/updateDeliveryAddress/" +
        this.daId +
        "/" +
        this.deliveryAddress.contactName +
        "/" +
        this.deliveryAddress.contactSex +
        "/" +
        this.deliveryAddress.contactTel +
        "/" +
        this.deliveryAddress.address;
      this.$axios
        .put(url)
        .then((response) => {
          if (response.data.result > 0) {
            this.$router.push({
              path: "/userAddress",
              query: {
                businessId: this.businessId,
              },
            });
          } else {
            alert("更新地址失败！");
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

/*************** （表单信息） ***************/
.wrapper .form-box {
  width: 100%;
  padding-top: 12vw;
}

.wrapper .form-box li {
  display: flex;
  align-items: center;
  box-sizing: border-box;
  padding: 3vw 3vw 0;
}

.wrapper .form-box li .title {
  flex: 0 0 18vw;
  font-size: 3.5vw;
  font-weight: 700;
  color: darkslategray;
}

.wrapper .form-box li .content {
  flex: 1;
  display: flex;
  align-items: center;
}

.wrapper .form-box li .content input {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 2vw;
  border-radius: 3vw;

  font-size: 3vw;
  border: none;
  outline: none;
}

.wrapper .button-add {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 10vw 20vw;
}

.wrapper .button-add button {
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

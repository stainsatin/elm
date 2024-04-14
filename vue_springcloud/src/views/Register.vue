<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>用户注册</p>
    </header>
    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div class="title">手机号码：</div>
        <div class="content">
          <input
            type="text"
            @blur="checkUserId"
            v-model="user.userId"
            placeholder="手
机号码"
          />
        </div>
      </li>
      <li>
        <div class="title">密码：</div>
        <div class="content">
          <input type="password" v-model="user.password" placeholder="密码" />
        </div>
      </li>
      <li>
        <div class="title">确认密码：</div>
        <div class="content">
          <input
            type="password"
            v-model="confirmPassword"
            placeholder="确认密码"
          />
        </div>
      </li>
      <li>
        <div class="title">用户名称：</div>
        <div class="content">
          <input type="text" v-model="user.userName" placeholder="用户名称" />
        </div>
      </li>
      <li>
        <div class="title">性别：</div>
        <div class="content" style="font-size: 3vw">
          <input
            type="radio"
            v-model="user.userSex"
            value="1"
            style="width: 6vw; height: 3.2vw"
          />男
          <input
            type="radio"
            v-model="user.userSex"
            value="0"
            style="width: 6vw; height: 3.2vw"
          />女
        </div>
      </li>
    </ul>
    <div class="button-register">
      <button @click="register">注册</button>
    </div>
    <div class="button-login">
      <button @click="login">去登录</button>
    </div>
  </div>
</template>
<script>
export default {
  name: "Register",
  data() {
    return {
      user: {
        userId: "",
        password: "",
        userName: "",
        userSex: 1,
      },
      confirmPassword: "",
    };
  },
  methods: {
    checkUserId() {
      let url = "UserController/getUserById/" + this.userId;
      this.$axios
        .get(url)
        .then((response) => {
          if (response.data.result == 1) {
            this.user.userId = "";
            alert("此手机号码已存在！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    register() {
      if (this.user.userId == "") {
        alert("手机号码不能为空！");
        return;
      }
      if (this.user.password == "") {
        alert("密码不能为空！");
        return;
      }
      if (this.user.password != this.confirmPassword) {
        alert("两次输入的密码不一致！");
        return;
      }
      if (this.user.userName == "") {
        alert("用户名不能为空！");
        return;
      }
      // 在发送数据之前转换性别值为整数（十进制）
      // this.user.userSex = parseInt(this.user.userSex, 10);
      console.log(this.user.userSex);
      //注册请求
      let url = `UserController/saveUser/${this.user.userId}/${this.user.password}/${this.user.userName}/${this.user.userSex}`;
      this.$axios
        .post(url)
        .then((response) => {
          // console.log(response.data);
          if (response.data.result > 0) {
            alert("注册成功！");
            this.$router.push("/login");
          } else {
            alert("注册失败！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    login() {
      this.$router.push({
        path: "login",
      });
    },
  },
};
</script>
<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
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

/****************** 表单部分 ******************/
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

.wrapper .button-register {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 10vw 0;
}

.wrapper .button-register button {
  width: 100%;
  height: 10vw;
  background-color: mediumseagreen;
  border: none;
  outline: none;
  border-radius: 4vw;

  font-size: 3.5vw;
  color: white;

  cursor: pointer;
}

.wrapper .button-login {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 10vw;
  display: flex;
  justify-content: center;
}

.wrapper .button-login button {
  width: 100%;
  height: 10vw;
  background-color: white;
  border: none;
  outline: none;
  border-radius: 4vw;
  border: solid 1.5px #ddd;

  font-size: 3.5vw;
  color: darkslategray;

  cursor: pointer;
}
</style>

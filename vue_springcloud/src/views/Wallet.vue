<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>{{ user.userName }}{{ user.userSex ? "先生" : "女士" }}</p>
    </header>
    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div class="title">您的余额：</div>
        <div class="content">
          <p v-if="balance != null">{{ balance }}</p>
          <div v-else class="createWallet" @click="createWallet">创建钱包</div>
        </div>
      </li>
      <li>
        <div class="title">充值或提现：</div>
        <div class="content">
          <input v-model="money" placeholder="操作金额" />
        </div>
      </li>
      <li>
        <div class="title">您的积分：</div>
        <div class="content">
          <p>{{ creditNum }}</p>
        </div>
      </li>
    </ul>
    <div class="button-login">
      <button @click="recharge">{{(creditEarningByRecharge==0)?'充值':'充值您可获得'+creditEarningByRecharge+'积分'}}</button>
    </div>
    
    <div class="button-register">
      <button @click="withdrawMoney">提现</button>
    </div>
    <div class="button-register">
      <button @click="router2tans">积分明细</button>
    </div>
    <div class="button-login" v-if="creditEarningBySign!=0">
      <button @click="sign(user.userId,creditEarningBySign)">今天签到可以拿{{creditEarningBySign}}积分</button>
    </div>
    <div class="button-register" v-else>
      <button >您今天已签到</button>
    </div> 

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";

export default {
  name: "Me",
  data() {
    return {
      creditNum: 0,
      creditEarningBySign:0,
      creditEarningByRecharge:0,
      balance: 0,
      userId: "",
      money: "",
      reg: /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/,
    };
  },
  watch:{
    money:function(val){
      // this.queryEarnCreditByRecharge(this.user.userId,this.money);
      if(this.reg.test(val)){
        this.queryEarnCreditByRecharge(this.user.userId,val);
      }
      if(val==""){
        this.creditEarningByRecharge=0;
      }
        
    },
  },
  created() {
    this.user = this.$getSessionStorage("user");
    this.queryBalance();  
    this.queryAvailableCredit(this.user.userId);
    this.queryEarningCreditBySign(this.user.userId);
  },
  methods: {
	queryBalance() {  //查询余额
	  // console.log("查询余额",this.user);
	  if (this.user.walletId == null) {
	    this.balance = null;
	  } else {
		let url="VirtualWalletController/queryBalance/"+this.user.walletId;
	    this.$axios.get(url).then((response) => {
	        this.balance = response.data.result;
	      })
	      .catch((error) => {
	        console.error(error);
	      });
	  }
	},
	queryAvailableCredit(userId) {  //查询可用积分
		let url="CreditController/queryAvailableCredit/"+userId;
		this.$axios.get(url).then((response) => {
	      this.creditNum=response.data.result;
	    })
	    .catch((error) => {
	      console.error(error);
	    });
	
	},
	queryEarningCreditBySign(userId){ //查询签到积分
		let url="CreditController/queryEarningCreditBySign/"+userId;
	    this.$axios.get(url).then((response) => {
	      this.creditEarningBySign=response.data.result;
	    })
	    .catch((error) => {
	      console.error(error);
	    });
	},
    queryEarnCreditByRecharge(userId,money){ //查询充值所获积分
      money=Math.floor((money));
	  let url="CreditController/queryEarnCreditByRecharge/"+userId+'/'+money;
      this.$axios.get(url).then((response) => {
          this.creditEarningByRecharge=Math.floor(response.data.result);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    sign(userId,creditNum){   //签到后更新积分
		let url="CreditController/earnCreditBySign/"+userId+'/'+creditNum;
		this.$axios.put(url).then((response) => {
          if(response.data.result==1){
            this.queryEarningCreditBySign(userId);
            this.queryAvailableCredit(userId);
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    createWallet() { //创建钱包
		let url_1="VirtualWalletController/userCreateVirtualWallet/"+this.user.userId;
		this.$axios.post(url_1).then((response) => {
          this.user.walletId = response.data.result;
          this.$setSessionStorage("user", this.user);
          // 钱包创建成功，更新余额
          // console.log("after create wallet")
          // console.log("创建钱包",this.user)
		  let url_2="VirtualWalletController/queryBalance/"+this.user.walletId;
          this.$axios.get(url_2).then((response) => {
              this.balance = response.data.result;
            })
            .catch((error) => {
              console.error(error);
            });
        })
        .catch((error) => {
          console.error(error);
        });
    },
    router2tans() { //查看流水
      if (this.user.walletId == null || this.balance == null) {
        alert("您未开通虚拟钱包!请开通!");
      } else {
        this.$router.push({ path: "/transaction" });
      }
    },
    recharge() {  //充值
      if (!this.reg.test(this.money)) {
        alert("充值金额错误!");
      } else if (this.user.walletId == null || this.balance == null) {
        alert("您未开通虚拟钱包!请开通!");
      } else {
        if(this.creditEarningByRecharge==0){
			let url_1="VirtualWalletController/recharge/"+this.user.walletId+'/'+this.money;
			this.$axios.put(url_1).then((response) => {
            if (response.data.result == 1) {
              alert("充值成功");
              this.queryBalance();
            } else {
              alert("充值失败");
            }
            this.money = 0;
          })
          .catch((error) => {
            console.error(error);
          });
        }else{
		  let url_2="CreditController/earnCreditBySignAndRecharge/"+this.user.userId+'/'+this.user.walletId+'/'+Math.floor(this.money)+'/'+this.creditEarningByRecharge;
          this.$axios.put(url_2).then((response) => {
            if (response.data.result == 1) {
              alert("充值成功");
              this.queryBalance();
              this.queryAvailableCredit(this.user.userId);
            } else {
              alert("充值失败");
            }
            this.money = 0;
          })
          .catch((error) => {
            alert("充值失败,最大余额十万");
            console.error(error);
          });
        }
        
      }
    },
    withdrawMoney() {  //提现
      if (!this.reg.test(this.money)) {
        alert("提现金额错误!");
      } else if (this.user.walletId == null || this.balance == null) {
        alert("您未开通虚拟钱包!请开通!");
      } else if (this.money > this.balance) {
        alert("余额不足");
      } else {
			let url="VirtualWalletController/withdrawMoney/"+this.user.walletId+'/'+this.money;
			this.$axios.put(url).then((response) => {
            if (response.data.result == 1) {
              alert("提现成功");
              this.queryBalance();
            } else {
              alert("提现失败");
            }
            this.money = 0;
          })
          .catch((error) => {
            console.error(error);
          });
      }
    }
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

/****************** 表单部分 ******************/
.wrapper .form-box {
  width: 100%;
  margin-top: 12vw;
}

.wrapper .form-box li {
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
  display: flex;
  align-items: center;
}

.wrapper .form-box li .title {
  flex: 0 0 25vw;
  font-size: 3vw;
  font-weight: 700;
  color: #666;
}

.wrapper .form-box li .content {
  flex: 1;
  font-size: 3vw;
}

.wrapper .form-box li .content input {
  border: none;
  outline: none;
  width: 100%;
  height: 4vw;
  font-size: 3vw;
}

.wrapper .form-box li .content .createWallet {
  width: 26%;
  background-color: #f90;
  color: #fff;
  border-radius: 4vw;
  margin-left: 2vw;
  user-select: none;
  cursor: pointer;
}

.wrapper .button-login {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}

.wrapper .button-login button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  color: #fff;
  background-color: #38ca73;
  border-radius: 4vw;
  border: none;
  outline: none;
}

.wrapper .button-register {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}

.wrapper .button-register button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  /*与上面登陆按钮不同的只有颜色、背景色、边框不同*/
  color: #666;
  background-color: #eee;
  border: solid 1px #ddd;
  border-radius: 4vw;
  border: none;
  outline: none;
}
</style>

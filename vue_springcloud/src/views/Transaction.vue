<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>积分明细</p>
		</header>
		<!-- 订单列表部分 -->
		<h3>获取积分记录：</h3>

		<ul class="order">
			<li v-for="item in creditArr">
				<div class="order-info" v-if="item.credit > 0">
					<p>积分{{ item.credit }}</p>
					<i class="fa fa-caret-down" @click="detailetShow(item)"></i>
				</div>

				<ul class="order-detailet" v-show="item.isShowDetailet">
					<!-- time -->
					<li>
						<p>积分规则代码</p>
						<p>{{ item.ruleCode }}</p>
					</li>

					<li>
						<p>相关事件</p>
						<p>{{ item.eventId==null?"不与其他事件相关":item.eventId}}</p>
					</li>

					<li>
						<p>创建时间</p>
						<p>{{ item.createTime}}</p>
					</li>

					<!-- id -->
					<li>
						<p>过期时间</p>
						<p>{{ item.expiredTime }}</p>
					</li>
				</ul>
			</li>
		</ul>
		<!-- <div class="padding-bottom:20vw ;"> -->
		<h3>消费积分记录：</h3>

		<ul class="order">
			<li v-for="item in creditArr">
				<div class="order-info" v-if="item.credit < 0">
					<p>积分{{ -1*item.credit }}</p>
					<i class="fa fa-caret-down" @click="detailetShow(item)"></i>
				</div>

				<ul class="order-detailet" v-show="item.isShowDetailet">
					<!-- time -->
					<li>
						<p>积分规则代码</p>
						<p>{{ item.ruleCode }}</p>
					</li>

					<li>
						<p>相关事件</p>
						<p>{{ item.eventId==null?"不与其他事件相关":item.eventId}}</p>
					</li>

					<li>
						<p>创建时间</p>
						<p>{{ item.createTime}}</p>
					</li>

					<!-- id -->
					<li>
						<p>过期时间</p>
						<p>{{ item.expiredTime }}</p>
					</li>
				</ul>
			</li>
		</ul>
		<!-- </div> -->
		<h3 class="bottom">-------------------我是有底线的-------------------</h3>
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
			let url="TransactionController/queryTransaction/${this.user.walletId}";
			this.$axios.get(url).then((response) => {
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
				let url="CreditController/queryAllCredit/${userId}";
				this.$axios.get(url).then((response) => {
						let res = response.data.result;
						for (let orders of res) {
							orders.isShowDetailet = false;
						}
						this.creditArr = res;
					})
					.catch((error) => {
						console.error(error);
					});
			},
			detailetShow(orders) {
				orders.isShowDetailet = !orders.isShowDetailet;
			},
			getBusinessNameByWalletId(walletId, orders) {
				let url="BusinessController/queryBusinessNameByWalletId/${walletId}";
				this.$axios.get(url).then((response) => {
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
	.wrapper h3 {
		margin-top: 12vw;
		box-sizing: border-box;
		padding: 4vw;
		font-size: 4vw;
		font-weight: 300;
		color: #999;
	}

	.wrapper .order {
		width: 100%;
	}

	.wrapper .order li {
		width: 100%;
	}

	.wrapper .order li .order-info {

		box-sizing: border-box;
		padding: 2vw 4vw;
		font-size: 4vw;
		color: #666;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order li .order-info .order-info-right {
		display: flex;
	}

	.wrapper .order li .order-info .order-info-right .order-info-right-icon {
		background-color: #f90;
		color: #fff;
		border-radius: 3px;
		margin-left: 2vw;
		user-select: none;
		cursor: pointer;
	}

	.wrapper .order li .order-detailet {
		width: 100%;
	}

	.wrapper .order li .order-detailet li {
		width: 100%;
		box-sizing: border-box;
		padding: 1vw 4vw;
		color: #666;
		font-size: 3vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.wrapper .bottom {
	margin-bottom: 20vw;
	  margin-top: 12vw;
	  box-sizing: border-box;
	  padding: 4vw;
	  font-size: 4vw;
	  font-weight: 300;
	  color: #999;
	 }
</style>
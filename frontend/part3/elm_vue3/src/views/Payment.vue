<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>在线支付</p>
		</header>
		<!-- 订单信息部分 -->
		<h3>订单信息：</h3>
		<div class="order-info">
			<p>
				{{orders.business.businessName}}
				<i class="fa fa-caret-down" @click="detailetShow"></i>
			</p>
			<p>&#165;{{orders.orderTotal}}</p>
		</div>
		<!-- 订单明细部分 -->
		<ul class="order-detailet" v-show="isShowDetailet">
			<li v-for="item in orders.list">
				<p>{{item.food.foodName}} x {{item.quantity}}</p>
				<p>&#165;{{item.food.foodPrice*item.quantity}}</p>
			</li>
			<li>
				<p>配送费</p>
				<p>&#165;{{orders.business.deliveryPrice}}</p>
			</li>
		</ul>
		<!-- 支付方式部分 -->
		<ul class="payment-type">
			<li>
				<img src="../assets/alipay.png">
				<i class="fa fa-check-circle"></i>
			</li>
			<li>
				<img src="../assets/wechat.png">
			</li>
		</ul>
		<div class="payment-button" @click="toPaymentDone">
			<button>确认支付</button>
		</div>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>
<script>
	import Footer from '../components/Footer.vue';
	import { onMounted, onBeforeUnmount } from 'vue';
	export default {
		name: 'Payment',
		data() {
			return {
				orderId: this.$route.query.orderId,
				orders: {
					business: {}
				},
				isShowDetailet: false
			}
		},
		created() {
			this.$axios.post('OrdersController/getOrdersById', this.$qs.stringify({
				orderId: this.orderId
			})).then(response => {
				this.orders = response.data;
			}).catch(error => {
				console.error(error);
			});
		},
		setup() {
		    onMounted(() => {
		      // 这里的代码是实现：一旦路由到在线支付组件，就不能回到订单确认组件。
		      // 先将当前url添加到history对象中
		      history.pushState(null, null, document.URL);
		      // popstate事件能够监听history对象的变化
		      window.onpopstate = () => {
		        router.push({
		          path: '/index'
		        });
		      };
		    });
		
		    onBeforeUnmount(() => {
		      window.onpopstate = null;
		    });
		  },
		methods: {
			detailetShow() {
				this.isShowDetailet = !this.isShowDetailet;
			},
			toPaymentDone() {
				this.$router.push({
					path: '/paymentDone'
				});
			}
		},
		components: {
			Footer
		}
	}
</script>
<style scoped>
	body{
		background-color: #f2f2f2;
	}
	/****************** 总容器 ******************/
	.wrapper{
		width: 100%;
	}
	
	/******************* 头部 ******************/
	.wrapper header{
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
	.wrapper h3 {
		width: 100%;
		box-sizing: border-box;
		padding: 15vw 3vw 1vw;
		
		font-size: 3.5vw;
		font-weight: 300;
		color: gray;
	}

	.wrapper .order-info {
		width: 100%;
		box-sizing: border-box;
		padding: 2vw 3vw;
		font-size: 3.5vw;
		color: darkslategray;
		
		display: flex;
		align-items: center;
		justify-content: space-between;
	}
	.wrapper .order-info .fa-caret-down{
		user-select: none;
		cursor: pointer;
	}
	.wrapper .order-info p:last-child {
		color: orangered;
	}

	/****************** 订单明细部分 ******************/
	.wrapper .order-detailet {
		width: 100;
		margin-bottom: 8vw;
	}

	.wrapper .order-detailet li {
		width: 100%;
		box-sizing: border-box;
		padding: 1vw 3vw 0vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order-detailet li p {
		font-size: 3vw;
		color: darkslategray;
	}

	/****************** 支付方式部分 ******************/
	.wrapper .payment-type {
		width: 95%;
		margin: 2vw 2.5vw;
		
		background-color: white;
		border-radius: 5vw;
	}

	.wrapper .payment-type li {
		width: 100%;
		box-sizing: border-box;
		padding: 3vw;
		
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .payment-type li img {
		width: 33vw;
		height: 8.9vw;
	}

	.wrapper .payment-type li .fa-check-circle {
		color: mediumseagreen;
		font-size: 4.5vw;
	}

	.wrapper .payment-button {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 10vw 20vw;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .payment-button button {
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
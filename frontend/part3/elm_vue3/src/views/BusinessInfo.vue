<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>商家信息</p>
		</header>
		<!-- 商家logo部分 -->
		<div class="merchant-logo">
			<img :src="business.businessImg">
		</div>
		<!-- 商家信息部分 -->
		<div class="merchant-info">
			<h1>{{business.businessName}}</h1>
			<p>&#165;{{business.starPrice}}起送 &#165;{{business.deliveryPrice}}配送</p>
			<p>{{business.businessExplain}}</p>
		</div>
		<!-- 食品列表部分 -->
		<ul class="food">
			<li v-for="(item,index) in foodArr">
				<div class="left">
					<img :src="item.foodImg">
					<div class="left-info">
						<h3>{{item.foodName}}</h3>
						<p>{{item.foodExplain}}</p>
						<p>&#165;{{item.foodPrice}}</p>
					</div>
				</div>
				<div class="right">
					<div>
						<i class="fa fa-minus-circle" @click="minus(index)" v-show="item.quantity!=0"></i>
					</div>
					<p><span v-show="item.quantity!=0">{{item.quantity}}</span></p>
					<div>
						<i class="fa fa-plus-circle" @click="add(index)"></i>
					</div>
				</div>
			</li>
		</ul>
		<!-- 购物车部分 -->
		<div class="cart">
			<div class="cart-left">
				<div class="cart-left-icon"
					:style="totalQuantity==0?'background-color:#505051;':'background-color:#3190E8;'">
					<i class="fa fa-shopping-cart"></i>
					<div class="cart-left-icon-quantity" v-show="totalQuantity!=0">
						{{totalQuantity}}
					</div>
				</div>
				<div class="cart-left-info">
					<p>&#165;{{totalPrice}}</p>
					<p>另需配送费{{business.deliveryPrice}}元</p>
				</div>
			</div>
			<div class="cart-right">
				<!-- 不够起送费 -->
				<div class="cart-right-item" v-show="totalSettle<business.starPrice"
					style="background-color: #535356;cursor: default;">
					&#165;{{business.starPrice}}起送
				</div>
				<!-- 达到起送费 -->
				<div class="cart-right-item" @click="toOrder" v-show="totalSettle>=business.starPrice">
					去结算
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		name: 'BusinessInfo',
		data() {
			return {
				businessId: this.$route.query.businessId,
				business: {},
				foodArr: [],
				user: {}
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
			//根据businessId查询商家信息
			this.$axios.post('BusinessController/getBusinessById', this.$qs.stringify({
				businessId: this.businessId
			})).then(response => {
				this.business = response.data;
				// console.log(this.business);
			}).catch(error => {
				console.error(error);
			});
			//根据businessId查询所属食品信息
			this.$axios.post('FoodController/listFoodByBusinessId', this.$qs.stringify({
				businessId: this.businessId
			})).then(response => {
				this.foodArr = response.data;
				for (let i = 0; i < this.foodArr.length; i++) {
					this.foodArr[i].quantity = 0;
				}
				//如果已登录，那么需要去查询购物车中是否已经选购了某个食品
				if (this.user != null) {
					this.listCart();
				}
			}).catch(error => {
				console.error(error);
			});
		},
		methods: {
			listCart() {
				this.$axios.post('CartController/listCart', this.$qs.stringify({
					businessId: this.businessId,
					userId: this.user.userId
				})).then(response => {
					let cartArr = response.data;
					//遍历所有食品列表
					for (let foodItem of this.foodArr) {
						foodItem.quantity = 0;
						for (let cartItem of cartArr) {
							if (cartItem.foodId == foodItem.foodId) {
								foodItem.quantity = cartItem.quantity;
							}
						}
					}
					this.foodArr.sort();
				}).catch(error => {
					console.error(error);
				});
			},
			add(index) { //商品数量++
				//首先做登录验证
				if (this.user == null) {
					this.$router.push({
						path: '/login'
					});
					return;
				}
				if (this.foodArr[index].quantity == 0) {
					//做insert
					this.savaCart(index);
				} else {
					//做update
					this.updateCart(index, 1);
				}
			},
			minus(index) {
				//首先做登录验证
				if (this.user == null) {
					this.$router.push({
						path: '/login'
					});
					return;
				}
				if (this.foodArr[index].quantity > 1) {
					//做update
					this.updateCart(index, -1);
				} else {
					//做delete
					this.removeCart(index);
				}
			},
			savaCart(index) {
				this.$axios.post('CartController/saveCart', this.$qs.stringify({
					businessId: this.businessId,
					userId: this.user.userId,
					foodId: this.foodArr[index].foodId
				})).then(response => {
					if (response.data == 1) {
						//此食品数量要更新为1；
						this.foodArr[index].quantity = 1;
						this.foodArr.sort();
					} else {
						alert('向购物车中添加食品失败！');
					}
				}).catch(error => {
					console.error(error);
				});
			},
			updateCart(index, num) {
				this.$axios.post('CartController/updateCart', this.$qs.stringify({
					businessId: this.businessId,
					userId: this.user.userId,
					foodId: this.foodArr[index].foodId,
					quantity: this.foodArr[index].quantity + num
				})).then(response => {
					if (response.data == 1) {
						//此食品数量要更新为1或-1；
						this.foodArr[index].quantity += num;
						this.foodArr.sort();
					} else {
						alert('向购物车中更新食品失败！');
					}
				}).catch(error => {
					console.error(error);
				});
			},
			removeCart(index) {
				this.$axios.post('CartController/removeCart', this.$qs.stringify({
					businessId: this.businessId,
					userId: this.user.userId,
					foodId: this.foodArr[index].foodId
				})).then(response => {
					if (response.data == 1) {
						//此食品数量要更新为0；视图的减号和数量要消失
						this.foodArr[index].quantity = 0;
						this.foodArr.sort();
					} else {
						alert('从购物车中删除食品失败！');
					}
				}).catch(error => {
					console.error(error);
				});
			},
			toOrder() {
				this.$router.push({
					path: '/orders',
					query: {
						businessId: this.business.businessId
					}
				});
			}
		},
		computed: {
			//食品总价格
			totalPrice() {
				let total = 0;
				for (let item of this.foodArr) {
					total += item.foodPrice * item.quantity;
				}
				return total;
			},
			//食品总数量
			totalQuantity() {
				let quantity = 0;
				for (let item of this.foodArr) {
					quantity += item.quantity;
				}
				return quantity;
			},
			//结算总价格
			totalSettle() {
				return this.totalPrice + this.business.deliveryPrice;
			}
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
		height: 100%;
	}

	/****************** header部分 ******************/
	.wrapper header{
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

	/****************** 商家logo部分 ******************/
	.wrapper .merchant-logo{
		width: 100%;
		padding-top: 15vw;
		display: flex;
		justify-content: center;
		margin-bottom: 3vw;
	}
	.wrapper .merchant-logo img{
		height: 35vw;
		width: 35vw;
		border-radius: 6vw;
	}
	


	/****************** 商家信息部分 ******************/
	.wrapper .merchant-info{
		width: 100%;
		height: 20vw;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.wrapper .merchant-info h1{
		color: black;
		font-size: 5.5vw;
		margin-bottom: 1.5vw;
	}
	.wrapper .merchant-info p{
		color: darkslategray;
		font-size: 3vw;
		margin-bottom: 1.5vw;
	}
	/****************** 食品列表部分 ******************/
	.wrapper .food {
		width: 100%;
		/*使用下外边距避开footer部分*/
		margin-bottom: 14vw;
	}

	.wrapper .food li{
		padding: 3vw;
		margin: 2vw;
		user-select:none;
		background-color: white;
		border-radius: 5vw;
		
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.wrapper .food li .left{
		display: flex;
		align-items: center;
	}
	.wrapper .food li .left img{
		width: 20vw;
		height: 20vw;
	}
	.wrapper .food li .left .left-info{
		margin-left: 3vw;
	}
	.wrapper .food li .left .left-info h3{
		margin-bottom: 2vw;
		font-size: 4vw;
		color: darkslategray;
	}
	.wrapper .food li .left .left-info p{
		margin-bottom: 2vw;
		font-size: 3.5vw;
		color: gray;
	}
	.wrapper .food li .right{
		width: 16vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.wrapper .food li .right .fa-minus-circle{
		font-size: 4.5vw;
		color: gray;
		cursor: pointer;
	}
	.wrapper .food li .right .fa-plus-circle{
		font-size: 4.5vw;
		color: deepskyblue;
		cursor: pointer;
	}
	.wrapper .food li .right p{
		font-size: 3.5vw;
		color: darkslategray;
	}

	/****************** 购物车部分 ******************/
	.wrapper .cart {
		width: 100%;
		height: 14vw;
		position: fixed;
		left: 0;
		bottom: 0;
		display: flex;
	}

	.wrapper .cart .cart-left {
		flex: 2;
		background-color: #505051;
		display: flex;
	}

	.wrapper .cart .cart-left .cart-left-icon {
		width: 16vw;
		height: 16vw;
		box-sizing: border-box;
		border: solid 1.6vw #444;
		border-radius: 8vw;
		background-color: #3190E8;
		font-size: 7vw;
		color: #fff;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: -4vw;
		margin-left: 3vw;
		position: relative;
	}

	.wrapper .cart .cart-left .cart-left-icon-quantity {
		width: 5vw;
		height: 5vw;
		border-radius: 2.5vw;
		background-color: red;
		color: #fff;
		font-size: 3.6vw;
		display: flex;
		justify-content: center;
		align-items: center;
		position: absolute;
		right: -1.5vw;
		top: -1.5vw;
	}

	.wrapper .cart .cart-left .cart-left-info p:first-child {
		font-size: 4.5vw;
		color: #fff;
		margin-top: 1vw;
	}

	.wrapper .cart .cart-left .cart-left-info p:last-child {
		font-size: 2.8vw;
		color: #AAA;
	}

	.wrapper .cart .cart-right {
		flex: 1;
	}

	/*达到起送费时的样式*/
	.wrapper .cart .cart-right .cart-right-item {
		width: 100%;
		height: 100%;
		background-color: #38CA73;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;
		cursor: pointer;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	/*不够起送费时的样式（只有背景色和鼠标样式的区别）*/
	/*
	.wrapper .cart .cart-right .cart-right-item{
		width: 100%;
		height: 100%;
		background-color: #535356;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;

		display: flex;
		justify-content: center;
		align-items: center;
	}
 */
</style>
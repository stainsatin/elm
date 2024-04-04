<template scoped>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<div class="header1" @click="toUserAddress">
				<div class="location-icon"><i class="fa fa-map-marker"></i></div>
				<div class="location-box">
					<div class="location-text">
						天津大学北洋园校区
						<i class="fa fa-caret-down"></i>
					</div>
					<div class="weather">晴 30°C</div>
				</div>
			</div>
			<div class="header2">
				<div class="recommend">推荐 |</div>
				<div class="ordinary"> 常点</div>
			</div>
		</header>
		<!-- search部分 -->
		<div class="search">
			<!-- 当滚动条超过上面的定位块时，search-fixed块变成固定在顶部。 -->
			<div class="search-fix" ref="fixedBox">
				<!-- 搜索框部分中间的白框 -->
				<div class="search-box">
					<div class="search-text">
						<i class="fa fa-search"></i>
						<input type="text" v-model="businessName" placeholder="搜索饿了么商家、商品名称">
					</div>
					<div class="search-icon" @click="listBusinessByName()">搜索</div>
				</div>
			</div>
		</div>
		<!-- 点餐分类部分 -->
		<ul class="foodtype">
			<li @click="toBusinessList(1)">
				<img src="../assets/dcfl01.png">
				<p>美食</p>
			</li>
			<li @click="toBusinessList(2)">
				<img src="../assets/dcfl02.png">
				<p>早餐</p>
			</li>
			<li @click="toBusinessList(3)">
				<img src="../assets/dcfl03.png">
				<p>跑腿代购</p>
			</li>
			<li @click="toBusinessList(4)">
				<img src="../assets/dcfl04.png">
				<p>汉堡披萨</p>
			</li>
			<li @click="toBusinessList(5)">
				<img src="../assets/dcfl05.png">
				<p>甜品饮品</p>
			</li>
			<li @click="toBusinessList(6)">
				<img src="../assets/dcfl06.png">
				<p>速食简餐</p>
			</li>
			<li @click="toBusinessList(7)">
				<img src="../assets/dcfl07.png">
				<p>地方小吃</p>
			</li>
			<li @click="toBusinessList(8)">
				<img src="../assets/dcfl08.png">
				<p>米粉面馆</p>
			</li>
			<li @click="toBusinessList(9)">
				<img src="../assets/dcfl09.png">
				<p>包子粥铺</p>
			</li>
			<li @click="toBusinessList(10)">
				<img src="../assets/dcfl10.png">
				<p>炸鸡炸串</p>
			</li>
		</ul>
		<!-- 横幅广告部分（注意：此处有背景图片） -->
		<div class="banner">
			<h3>品质套餐</h3>
			<p>搭配齐全吃得好</p>
			<a>立即抢购 &gt;</a>
		</div>
		<!-- 超级会员部分 -->
		<div class="supermember">
			<div class="super-left">
				<img src="../assets/super_member.png">
				<h3>超级会员</h3>
				<p>&#8226; 每月享超值权益</p>
			</div>
			<div class="super-right">
				立即开通 &gt;
			</div>
		</div>
		<!-- 推荐商家部分 -->
		<div class="recommend-merchant">
			<div>——</div>
			<p>推荐商家</p>
			<div>——</div>
		</div>
		<!-- 推荐方式部分 -->
		<ul class="recommend-line">
			<li>综合排序<i class="fa fa-caret-down"></i></li>
			<li>距离最近</li>
			<li>销量最高</li>
			<li>筛选<i class="fa fa-filter"></i></li>
		</ul>
		<!-- 推荐商家列表部分 -->
		<ul class="merchant">
			<li @click="toBusinessInfo(10001)">
				<img src="../assets/sj01.png">
				<div class="merchant-info">
					<div class="merchant-info-h">
						<h3>万家饺子（软件园E18店）</h3>
						<div class="more-info">&#8226;</div>
					</div>
					<div class="merchant-info-rating">
						<div class="left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="right">蜂鸟专送</div>
					</div>
					<div class="merchant-info-delivery">
						<p>&#165;15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="merchant-info-label">
						<p>各种饺子</p>
					</div>
					<div class="merchant-info-promotion">
						<div class="left">
							<div class="icon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="right">
							两个活动<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="merchant-info-promotion2">
						<div class="icon">特</div>
						<p>特价商品5元起</p>
					</div>
				</div>
			</li>
			<li @click="toBusinessInfo(10002)">
				<img src="../assets/sj02.png">
				<div class="merchant-info">
					<div class="merchant-info-h">
						<h3>小锅饭豆腐馆（全运店）</h3>
						<div class="more-info">&#8226;</div>
					</div>
					<div class="merchant-info-rating">
						<div class="left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="right">蜂鸟专送</div>
					</div>
					<div class="merchant-info-delivery">
						<p>&#165;15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="merchant-info-label">
						<p>各种饺子</p>
					</div>
					<div class="merchant-info-promotion">
						<div class="left">
							<div class="icon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="right">
							两个活动<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="merchant-info-promotion2">
						<div class="icon">特</div>
						<p>特价商品5元起</p>
					</div>
				</div>
			</li>
			<li @click="toBusinessInfo(10003)">
				<img src="../assets/sj03.png">
				<div class="merchant-info">
					<div class="merchant-info-h">
						<h3>麦当劳麦乐送（全运路店）</h3>
						<div class="more-info">&#8226;</div>
					</div>
					<div class="merchant-info-rating">
						<div class="left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="right">蜂鸟专送</div>
					</div>
					<div class="merchant-info-delivery">
						<p>&#165;15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="merchant-info-label">
						<p>各种饺子</p>
					</div>
					<div class="merchant-info-promotion">
						<div class="left">
							<div class="icon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="right">
							两个活动<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="merchant-info-promotion2">
						<div class="icon">特</div>
						<p>特价商品5元起</p>
					</div>
				</div>
			</li>
			<li @click="toBusinessInfo(10004)">
				<img src="../assets/sj04.png">
				<div class="merchant-info">
					<div class="merchant-info-h" @click="toBusinessInfo(item.businessId)">
						<h3>米村拌饭（浑南店）</h3>
						<div class="more-info">&#8226;</div>
					</div>
					<div class="merchant-info-rating">
						<div class="left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="right">蜂鸟专送</div>
					</div>
					<div class="merchant-info-delivery">
						<p>&#165;15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="merchant-info-label">
						<p>各种饺子</p>
					</div>
					<div class="merchant-info-promotion">
						<div class="left">
							<div class="icon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="right">
							两个活动<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="merchant-info-promotion2">
						<div class="icon">特</div>
						<p>特价商品5元起</p>
					</div>
				</div>
			</li>
			<li @click="toBusinessInfo(10005)">
				<img src="../assets/sj05.png">
				<div class="merchant-info">
					<div class="merchant-info-h">
						<h3>申计串道（中海康城店）</h3>
						<div class="more-info">&#8226;</div>
					</div>
					<div class="merchant-info-rating">
						<div class="left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="right">蜂鸟专送</div>
					</div>
					<div class="merchant-info-delivery">
						<p>&#165;15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="merchant-info-label">
						<p>各种饺子</p>
					</div>
					<div class="merchant-info-promotion">
						<div class="left">
							<div class="icon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="right">
							两个活动<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="merchant-info-promotion2">
						<div class="icon">特</div>
						<p>特价商品5元起</p>
					</div>
				</div>
			</li>
		</ul>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	//导入共通组件
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Index',
		data() {
			return {
				businessName: ''
			}
		},
		mounted() {
			document.onscroll = () => {
				//获取滚动条位置
				let s1 = document.documentElement.scrollTop;
				let s2 = document.body.scrollTop;
				let scroll = s1 == 0 ? s2 : s1;
				//获取视口宽度
				let width = document.documentElement.clientWidth;

				//获取顶部固定块
				let search = this.$refs.fixedBox;

				//判断滚动条超过视口宽度的12%时，搜索块变固定定位
				if (scroll > width * 0.12) {
					search.style.position = 'fixed';
					search.style.left = '0';
					search.style.top = '0';
				} else {
					search.style.position = 'static';
				}
			}
		},
		unmounted() {
			//当切换到其他组件时，就不需要document滚动条事件，所以将此事件去掉
			document.onscroll = null;
		},
		components: {
			Footer
		},
		methods: {
			toUserAddress(){
				this.$router.push({
					path: '/userAddress'
				});
			},
			toBusinessList(orderTypeId) {
				this.$router.push({
					path: '/businessList',
					query: {
						orderTypeId: orderTypeId
					}
				});
			},
			toBusinessInfo(businessId) {
				this.$router.push({
					path: '/businessInfo',
					query: {
						businessId: businessId
					}
				});
			},
			listBusinessByName() {
				this.$router.push({
					path: '/businessList',
					query: {
						businessName: this.businessName
					}
				});
			}
		}
	}
</script>
<style scoped>
	/********* 总容器 *********/
	.wrapper {
		width: 100%;
		background-color: #f2f2f2;
	}

	/********* 头部 ********/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: deepskyblue;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}

	/********* 头部左侧 ********/
	.wrapper header .header1 {
		display: flex;
		user-select: none;
		cursor: pointer;
	}

	.wrapper header .location-icon {
		font-size: 5vw;
		color: white;
		height: 3.5vw;
		width: 3.5vw;
		margin: 1vw 1vw 0 3vw;
	}

	.wrapper header .location-box {
		color: white;
		margin-left: 1vw;
	}

	.wrapper header .location-text {
		font-size: 3.5vw;
		font-weight: 700;
		margin-top: 1.5vw;
	}

	.wrapper header .weather {
		font-size: 3vw;
		font-weight: 500;
	}

	/********* 头部右侧 ********/
	.wrapper header .header2 {
		display: flex;
		align-items: center;
		font-size: 4.5vw;
		color: white;
		margin: 2vw 2.5vw;
		user-select: none;
	}

	.wrapper header .recommend {
		font-weight: 700;
		margin-right: 1vw;
		cursor: pointer;
	}

	.wrapper header .ordinary {
		cursor: pointer;
	}

	/****************** search ******************/
	.wrapper .search {
		width: 100%;
		height: 13vw;
	}

	.wrapper .search .search-fix {
		width: 100%;
		height: 13vw;
		background-color: #f2f2f2;
		border-bottom-right-radius: 6vw;
		border-bottom-left-radius: 6vw;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .search .search-fix .search-box {
		width: 80%;
		height: 7.5vw;
		background-color: white;
		border: solid 1.5px deepskyblue;
		border-radius: 4vw;

		display: flex;
		justify-content: space-between;
		align-items: center;

		font-size: 3vw;
		color: lightslategrey;
		font-family: "宋体";
		user-select: none;
		/**取消文本选中状态**/
	}

	.wrapper .search .search-fix .search-box .fa-search {
		margin-right: 1vw;
		margin-left: 3vw;
	}

	.wrapper .search .search-fix .search-box input {
		font-size: 3vw;
		border: none;
		outline: none;
	}

	.wrapper .search .search-fix .search-box .search-icon {
		width: 17%;
		height: 100%;
		background-color: deepskyblue;
		border-radius: 4vw;

		display: flex;
		justify-content: center;
		align-items: center;

		font-size: 3.5vw;
		color: white;
		font-family: "微软雅黑";

		cursor: pointer;
	}

	/*********点餐分类*********/
	.wrapper .foodtype {
		width: 95%;
		height: 40vw;
		padding-top: 2vw;
		margin: 2vw 2.5vw;

		display: flex;
		flex-wrap: wrap;
		justify-content: center;

		background-color: white;
		border-radius: 5vw;
	}

	.wrapper .foodtype li {
		width: 18vw;
		height: 20vw;
		display: flex;
		flex-wrap: wrap;
		flex-direction: column;
		align-content: center;
		align-items: center;
		user-select: none;
		/**取消文本选中状态**/
		cursor: pointer;
		/**文本点击状态**/
	}

	.wrapper .foodtype li img {
		width: 12.5vw;
		height: 12.5vw;
	}

	.wrapper .foodtype li p {
		font-size: 3vw;
		color: darkslategray;
	}

	/****************** 横幅广告部分 ******************/

	.wrapper .banner {
		/**
	 * 设置容器宽度95%，然后水平居中，这样两边留白; 
	 * 这里不能用padding，因为背景图片也会覆盖padding
	 */
		width: 95%;
		margin: 0 auto;
		height: 29vw;
		/*此三个样式组合，可以保证背景图片充满整个容器*/
		background-image: url(../assets/index_banner.png);
		background-repeat: no-repeat;
		background-size: cover;
		box-sizing: border-box;
		padding: 2vw 6vw;
	}

	.wrapper .banner h3 {
		font-size: 4.2vw;
		margin-bottom: 1.2vw;
	}

	.wrapper .banner p {
		font-size: 3.4vw;
		color: #666;
		margin-bottom: 2.4vw;
	}

	.wrapper .banner a {
		font-size: 3vw;
		color: #C79060;
		font-weight: 700;
	}

	/*********超级会员*********/
	.wrapper .supermember {
		width: 95%;
		height: 15vw;
		margin: 2vw 2.5vw 0;
		background-color: lightgoldenrodyellow;
		border-radius: 5vw;

		display: flex;
		justify-content: space-between;
		align-items: center;

		color: saddlebrown;
	}

	.wrapper .supermember .super-left {
		font-size: 3.5vw;
		margin-left: 4vw;
		display: flex;
		flex-wrap: wrap;
		align-items: center;
		user-select: none;
	}

	.wrapper .supermember .super-left img {
		width: 10vw;
		height: 10vw;
		margin-right: 3vw;
	}

	.wrapper .supermember .super-right {
		font-size: 3.5vw;
		margin-right: 4vw;
		cursor: pointer;
	}

	/*********推荐商家*********/
	.wrapper .recommend-merchant {
		width: 100%;
		height: 10vw;
		margin-top: 2vw;

		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;

		font-size: 4vw;
		color: darkslategrey;
	}

	.wrapper .recommend-merchant p {
		margin: 0 4vw 0 4vw;
	}

	/*********推荐方式*********/
	.wrapper .recommend-line {
		width: 100%;
		height: 7vw;
		margin: 0 auto;

		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
		align-items: center;
	}

	.wrapper .recommend-line li {
		font-size: 3.5vw;
		color: darkslategrey;
		user-select: none;
		cursor: pointer;
	}

	.wrapper .recommend-line .fa-caret-down {
		margin-left: 1vw;
	}

	.wrapper .recommend-line .fa-filter {
		margin-left: 1vw;
	}

	/*********推荐商家列表*********/
	.wrapper .merchant {
		width: 100%;
		padding-bottom: 20vw;
	}

	.wrapper .merchant li {
		display: flex;
		padding: 2.5vw;
		margin: 2vw;
		background-color: white;
		user-select: none;
		cursor: pointer;
		border-radius: 5vw;
	}

	.wrapper .merchant li img {
		width: 20vw;
		height: 20vw;
		border-radius: 6vw;
	}

	.wrapper .merchant li .merchant-info {
		width: 100%;
		margin-left: 2.5vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-h {
		display: flex;
		justify-content: space-between;
		margin-bottom: 2vw;
		color: darkslategrey;
	}

	.wrapper .merchant li .merchant-info .merchant-info-h h3 {
		font-size: 4vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-h .more-info {
		background-color: gray;
		color: white;
		font-size: 4vw;
		margin-right: 2vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-rating {
		display: flex;
		justify-content: space-between;
		margin-bottom: 1vw;
		font-size: 3vw;
		color: darkslategrey;
	}

	.wrapper .merchant li .merchant-info .merchant-info-rating .left {
		display: flex;
	}

	.wrapper .merchant li .merchant-info .merchant-info-rating .left p {
		margin-left: 1vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-rating .left .fa-star {
		display: flex;
		align-items: center;
		font-size: 2.5vw;
		color: gold;
		margin-right: 0.5vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-rating .right {
		display: flex;
		background-color: deepskyblue;
		color: white;
		font-size: 2.8vw;
		border-radius: 1vw;
		padding: 0 0.6vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-delivery {
		display: flex;
		justify-content: space-between;
		margin-bottom: 1vw;
		font-size: 3vw;
		color: darkslategrey;
	}

	.wrapper .merchant li .merchant-info .merchant-info-label {
		display: flex;
		align-items: center;
		margin: 1vw 0 1vw 0;
	}

	.wrapper .merchant li .merchant-info .merchant-info-label p {
		font-size: 2.8vw;
		color: gray;
		border: solid 1px gray;
		border-radius: 1vw;
		padding: 0 0.6vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 1.8vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion .left {
		display: flex;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion .left .icon {
		font-size: 3vw;
		margin-right: 2vw;
		color: white;
		background-color: limegreen;
		padding: 0 0.6vw;
		border-radius: 1vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion .left p {
		font-size: 3vw;
		color: darkslategray;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion .right {
		font-size: 3vw;
		color: darkslategray;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion .right .fa-caret-down {
		margin-left: 1vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion2 {
		display: flex;
		align-items: center;
		margin-bottom: 1.8vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion2 {
		display: flex;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion2 .icon {
		font-size: 3vw;
		margin-right: 2vw;
		color: white;
		background-color: indianred;
		padding: 0 0.6vw;
		border-radius: 1vw;
	}

	.wrapper .merchant li .merchant-info .merchant-info-promotion2 p {
		font-size: 3vw;
		color: darkslategray;
	}
</style>
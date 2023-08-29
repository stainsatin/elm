<template>
	<ul class="footer">
		<li @click="toIndex">
			<i class="fa fa-home"></i>
			<p>首页</p>
		</li>
		<li>
			<i class="fa fa-compass"></i>
			<p>发现</p>
		</li>
		<li @click="toOrderList">
			<i class="fa fa-file-text"></i>
			<p>订单</p>
		</li>
		<li>
			<i class="fa fa-user"></i>
			<p>我的</p>
		</li>
	</ul>
</template>
<script>
	export default {
		name: 'Footer',
		methods: {
			toIndex() {
				this.$router.push({
					path: '/index'
				});
			},
			toOrderList() {
				this.$router.push({
					path: '/orderList'
				});
			}
		}
	}
</script>
<style>
	/*********底部菜单*********/
	.wrapper .footer{
		width: 95%;
		height: 14vw;
		border: solid 1.5px darkgray;
		background-color: white;
		border-radius: 5vw;
		
		position: fixed;
		bottom: 3vw;
		left: 2.5%;
		
		display:flex;
		justify-content: space-around;
		align-items: center;
	}
	.wrapper .footer li{
		display:flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		font-size:5vw;
		
		color: gray;
		user-select: none;
		cursor: pointer;
	}
	.wrapper .footer li p{
		font-size:2.8vw;
	}
</style>
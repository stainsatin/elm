<template>
	<div class="wrapper">
		<!--头部-->
		<header>
			<p>支付成功</p>
		</header>
		<!--提示-->
		<h2>支付成功！</h2>
		<!--返回首页按钮-->
		<div class="back-button" @click="toIndex">
			<button>返回首页</button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'PaymentDone',
		methods: {
			toIndex() {
				this.$router.push({
					path: '/index'
				});
			}
		}
	}
</script>

<style>
	/********* 总容器 *********/
	.wrapper {
		width: 100%;
	}

	/********* 头部 *********/
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

	/********* 订单信息 *********/
	.wrapper h2 {
		padding-top: 40vw;
		padding-bottom: 20vw;
		display: flex;
		justify-content: center;
		color: darkslategray;
		font-size: 8vw;

	}

	/********* 返回首页按钮 *********/
	.wrapper .back-button {
		width: 100%;
		box-sizing: border-box;
		padding: 5vw 10vw;
		display: flex;
		justify-content: center;
		align-items: center;
		position: fixed;
		bottom: 0vw;
	}

	.wrapper .back-button button {
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
<template>
	<!-- 总容器 -->
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>个人信息</p>
		</header>
		<!-- 用户信息部分 -->
		<div class="user-info">
			<img :src=" user.userImg " @error="setDefaultImage">
			<p>{{user.userName}}</p>
			<i class="fa fa-edit" @click="toUpdateUserName"></i>
		</div>
		<!-- 修改用户密码部分 -->
		<ul class="settings-button">
			<li @click="toUpdateUserPassword">
				<button><i class="fa fa-lock"></i>修改密码</button>
			</li>
			<li @click="toUserAddress">
				<button><i class="fa fa-map-marker"></i>我的地址</button>
			</li>
			<li >
				<button><i class="fa fa-info"></i>我的积分</button>
			</li>
			<li @click="toLogin">
				<button><i class="fa fa-info"></i>退出登录</button>
			</li>
			
		</ul>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import defaultImage from '../assets/profile_photo.png'
	import Footer from '../components/Footer.vue';
	export default {
		name: 'Profile',
		data() {
			return {
				defaultImage,
				user: {}
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
		},
		components: {
			Footer
		},
		methods: { 
			setDefaultImage(e) {
			      e.target.src = defaultImage
			},
			toUserAddress() {
				this.$router.push({
					path: '/userAddress'
				});
			},
			toUpdateUserName() {
				this.$router.push({
					path: '/updateUserName'
				});
			},
			toUpdateUserPassword() {
				this.$router.push({
					path: '/updateUserPassword'
				});
			},
			toLogin(){
				this.$removeSessionStorage('user')
				this.$router.push({
					path: '/login'
				});
			}
		}
	}
</script>

<style scoped>
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/****************** header ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: deepskyblue;
		color: white;
		font-size: 5vw;
		font-weight: 700;
		position: fixed;
		left: 0;
		top: 0;
		z-index: 1000;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	/****************** 用户信息部分 ******************/
	.wrapper .user-info {
		width: 100%;
		box-sizing: border-box;
		padding: 18vw 6vw 6vw;
		display: flex;
		align-items: center;
		border-bottom: solid 1.5px #DDD;
	}

	.wrapper .user-info img {
		width: 25vw;
		height: 25vw;
		border-radius: 5vw;
	}

	.wrapper .user-info p {
		font-size: 4vw;
		margin-right: 3vw;
		margin-left: 3vw;
	}

	.wrapper .user-info .fa-edit {
		font-size: 4vw;
		cursor: pointer;
		user-select: none;
	}

	/********* 设置按钮部分 *********/
	.wrapper .settings-button {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 10vw 0;
	}

	.wrapper .settings-button li {
		margin-bottom: 3vw;
	}

	.wrapper .settings-button li button {
		width: 100%;
		height: 10vw;
		background-color: white;
		border: none;
		outline: none;
		border-radius: 4vw;
		border: solid 1.5px #DDD;

		font-size: 3.5vw;
		color: dimgray;

		cursor: pointer;

		display: flex;
		align-items: center;
	}

	.wrapper .settings-button li button i {
		margin: 0 3vw;
	}
</style>
<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>用户登陆</p>
		</header>
		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					手机号码：
				</div>
				<div class="content">
					<input type="text" v-model="userId" placeholder="手机号码">
				</div>
			</li>
			<li>
				<div class="title">
					密码：
				</div>
				<div class="content">
					<input type="password" v-model="password" placeholder="密码">
				</div>
			</li>
		</ul>
		<div class="button-login">
			<button @click="login">登录</button>
		</div>
		<div class="button-register">
			<button @click="register">去注册</button>
		</div>
	</div>
</template>
<script>
	export default {
		name: 'Login',
		data() {
			return {
				userId: '',
				password: '',
				frompath: ''
			}
		},
		beforeRouteEnter(to, from, next) {
			next(vm => {
				vm.frompath = from.path;
			})
		},
		methods: {
			login() {
				if (this.userId == '') {
					alert('手机号码不能为空！');
					return;
				}
				if (this.password == '') {
					alert('密码不能为空！');
					return;
				}

				//登录请求
				let url="UserController/getUserByIdByPass/${this.userId}/${this.password}";
				this.$axios.get(url).then(response => {
					let user = response.data.result;
					console.log('user',user);
					if (user == null) {
						alert('用户名或密码不正确！');
					} else {
						//sessionstorage有容量限制，为了防止数据溢出，所以不将userImg数据放入session中						
						user.userImg = '';
						this.$setSessionStorage('user', user);
						if (this.frompath == '/register') {
							this.$router.push({
								path: "/index"
							})
						} else {
							this.$router.go(-1);
						}
					}
				}).catch(error => {
					console.error(error);
				});
			},
			register() {
				this.$router.push({
					path: 'register'
				});
			}
		}
	}
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
		margin-top: 12vw;
	}

	.wrapper .form-box li {
		box-sizing: border-box;
		padding: 3vw 3vw 0;
		display: flex;
		align-items: center;
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

	.wrapper .button-login {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 10vw 0;
	}

	.wrapper .button-login button {
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

	.wrapper .button-register {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 10vw 20vw;
		display: flex;
		justify-content: center;
	}

	.wrapper .button-register button {
		width: 100%;
		height: 10vw;
		background-color: white;
		border: none;
		outline: none;
		border-radius: 4vw;

		font-size: 3.5vw;
		color: darkslategray;

		cursor: pointer;
	}
</style>
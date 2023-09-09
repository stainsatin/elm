<template scoped>
	<!-- 总容器 -->
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>更新密码</p>
		</header>
		<!-- 更新用户名部分 -->
		<div class="form-box">
			<li>
				<div class="title">
					旧密码:
				</div>
				<div class="content">
					<input type="password" v-model="oldPass" placeholder="旧密码">
				</div>
			</li>
			<li>
				<div class="title">
					新密码:
				</div>
				<div class="content">
					<input type="password" v-model="newPass" placeholder="新密码">
				</div>
			</li>
		</div>
		<!--保存按钮-->
		<div class="save-button" @click="toUpdateUserPassword()">
			<button>保存</button>
		</div>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';
	
	export default {
		name: 'UpdateUserPassword',
		data() {
			return {
				oldPass: '',
				newPass: '',
				user: {}
			}
		},
		components: {
			Footer
		},
		created() {
			this.user = this.$getSessionStorage('user');
		},
		methods: {
			toUpdateUserPassword() {
				//根据userId更改password
				if (this.oldPass == '') {
					alert('请输入旧密码！');
					return;
				}
				if (this.newPass == '') {
					alert('新密码不能为空！');
					return;
				}
				this.$axios.post('UserController/updateUserPassword', this.$qs.stringify({
					userId: this.user.userId,
					oldPass: this.oldPass,
					newPass:this.newPass
				})).then(response => {
					if (response.data > 0) {
						this.user.password=this.newPass;
						this.$setSessionStorage('user', this.user);
						alert('修改密码成功！');
					} else {
						alert('修改密码失败！');
					}
				}).catch(error => {
					console.error(error);
				});
			}
		
		}
	}
</script>

<style scoped>
	body{
		background-color: #f2f2f2;
	}
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}
	
	/****************** header ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
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
	
	/****************** 修改密码部分 ******************/
	.wrapper .form-box{
		width: 100%;
		padding-top: 12vw;
	}
	.wrapper .form-box li{
		display: flex;
		align-items: center;
		box-sizing: border-box;
		padding: 3vw 3vw 0;
	}
	.wrapper .form-box li .title{
		flex: 0 0 18vw;
		font-size: 3.5vw;
		font-weight: 700;
		color: darkslategray;
	}
	.wrapper .form-box li .content{
		flex: 1;
	}
	.wrapper .form-box li .content input{
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 2vw;
		border-radius: 3vw;
		background-color: white;
		
		font-size: 3vw;
		border: none;
		outline: none;
	}
	
	/****************** 保存按钮部分 ******************/
	.wrapper .save-button{
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 10vw 0;
	}
	.wrapper .save-button button{
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

</style>
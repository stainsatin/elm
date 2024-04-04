import { createRouter, createWebHistory} from "vue-router";//createWebHashHistory
import { createApp } from 'vue'
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'
import Orders from '../views/Orders.vue'
import UserAddress from '../views/UserAddress.vue'
import Payment from '../views/Payment.vue'
import OrderList from '../views/OrderList.vue'
import AddUserAddress from '../views/AddUserAddress.vue'
import EditUserAddress from '../views/EditUserAddress.vue'
import Register from '../views/Register.vue'
import Profile from '../views/Profile.vue'
import UpdateUserName from '../views/UpdateUserName.vue'
import UpdateUserPassword from '../views/UpdateUserPassword.vue'
import PaymentDone from '../views/PaymentDone.vue'
import Error403 from '../components/Error403.vue'
const routes = [{
	 path: '/',
	 name: 'Home',
	 component: Index
 }, {
	 path: '/index',
	 name: 'Index',
	 component: Index
 }, {
	 path: '/businessList',
	 name: 'BusinessList',
	 component: BusinessList
 }, {
	 path: '/businessInfo',
	 name: 'BusinessInfo',
	 component: BusinessInfo
 }, {
	 path: '/login',
	 name: 'Login',
	 component: Login
 }, {
	 path: '/orders',
	 name: 'Orders',
	 component: Orders
 }, {
	 path: '/userAddress',
	 name: 'UserAddress',
	 component: UserAddress
 }, {
	 path: '/payment',
	 name: 'Payment',
	 component: Payment
 }, {
	 path: '/orderList',
	 name: 'OrderList',
	 component: OrderList
 }, {
	 path: '/addUserAddress',
	 name: 'AddUserAddress',
	 component: AddUserAddress
 }, {
	 path: '/editUserAddress',
	 name: 'EditUserAddress',
	 component: EditUserAddress
 }, {
	 path: '/register',
	 name: 'Register',
	 component: Register
 },{
 	 path: '/profile',
 	 name: 'Profile',
 	 component: Profile
 },{
 	 path: '/updateUserName',
 	 name: 'UpdateUserName',
 	 component: UpdateUserName
 },{
 	 path: '/updateUserPassword',
 	 name: 'UpdateUserPassword',
 	 component: UpdateUserPassword
 },{
 	 path: '/paymentDone',
 	 name: 'PaymentDone',
 	 component: PaymentDone
 },{
 	 path: '/error403',
 	 name: 'Error403',
 	 component: Error403
 }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});
// base: process.env.BASE_URL,
//解决重复路由报异常问题
const originalPush = router.push
router.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default router
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/login', 
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: 'Dashboard' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员列表' }, component: () => import('../views/manager/Admin') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告列表' }, component: () => import('../views/manager/Notice') },
      { path: 'user', name: 'User', meta: { name: '用户列表' }, component: () => import('../views/manager/User') },
      { path: 'category', name: 'Category', meta: { name: '分类列表' }, component: () => import('../views/manager/Category') },
      { path: 'goods', name: 'Goods', meta: { name: '物品列表' }, component: () => import('../views/manager/Goods') },
      { path: 'circles', name: 'Circles', meta: { name: '社区分类' }, component: () => import('../views/manager/Circles') },
      { path: 'posts', name: 'Posts', meta: { name: '帖子列表' }, component: () => import('../views/manager/Posts') },
      { path: 'feedback', name: 'Feedback', meta: { name: '留言列表' }, component: () => import('../views/manager/Feedback') },
      { path: 'comment', name: 'Comment', meta: { name: '评论列表' }, component: () => import('../views/manager/Comment') },
      { path: 'orders', name: 'Orders', meta: { name: '订单列表' }, component: () => import('../views/manager/Orders') },
    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', component: () => import('../views/front/Home') },
      { path: 'person', component: () => import('../views/front/Person') },
      { path: 'goodsDetail', component: () => import('../views/front/GoodsDetail') },
      { path: 'collect', component: () => import('../views/front/Collect') },
      { path: 'notice', component: () => import('../views/front/Notice') },
      { path: 'addGoods', component: () => import('../views/front/AddGoods') },
      { path: 'goods', component: () => import('../views/front/Goods') },
      { path: 'orders', component: () => import('../views/front/Orders') },
      { path: 'posts', component: () => import('../views/front/Posts') },
      { path: 'userPosts', component: () => import('../views/front/UserPosts') },
      { path: 'postsDetail', component: () => import('../views/front/PostsDetail') },
      { path: 'feedback', component: () => import('../views/front/Feedback') },
      { path: 'userFeedback', component: () => import('../views/front/UserFeedback') },
      { path: 'search', component: () => import('../views/front/Search') },
    ]
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

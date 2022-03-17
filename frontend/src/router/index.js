import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../components/auth/Login.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../components/auth/Register.vue')
  },
  {
    path: '/cart',
    name: 'cart',
    component: () => import('../components/cart/Cart.vue')
  },
  {
    path: '/:slug',
    name: 'product-detail',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/product/ProductDetail.vue')
  },
  {
    path: 'search',
    name: 'search',
    component: () => import('../views/SearchPage.vue'),
    props: route => ({ query: route.query.q })
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

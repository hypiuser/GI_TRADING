<template>
  <div style="background-color: white; min-height: 100vh">

    <div class="front-header">
      <a href="/front/home">
        <div class="front-header-left">
          <div class="title">GI_TRADING</div></div>
      </a>
      <div class="front-header-center">
        <div @click="$router.push(item.path)" class="menu" v-for="item in menus" :key="item.path"
             :class="{'menu-active' : item.path === $route.path }">{{ item.text }}</div>
      </div>
      
      <div class="front-header-right">
        <div v-if="!user.username">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>

        <div v-else>
          <el-dropdown>
            <div class="front-header-dropdown">
              <img :src="user.avatar" alt="" style="border-radius: 30%">
              <div style="margin-left: 10px; color: #eee; cursor: pointer">
                <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              </div>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div @click="$router.push('/front/person')">个人信息</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/goods')">我的库存</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/collect')">我的收藏</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/orders')">我的订单</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/userPosts')">我的帖子</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/userFeedback')">我的留言</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div style="text-decoration: none" @click="logout">退出登录</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <!--主体-->
    <div class="main-body">
      <router-view ref="child" @update:user="updateUser" />
    </div>
  </div>
</template>

<script>
export default {
  name: "FrontLayout",
  data () {
    return {
      notice: [],
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
      menus: [
        { text: '首页', path: '/front/home' },
        { text: '社区', path: '/front/posts' },
        { text: '留言板', path: '/front/feedback' },
        { text: '公告', path: '/front/notice' },
      ]
    }
  },

  mounted() {

  },
  methods: {
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    // 退出登录
    logout() {
      localStorage.removeItem("xm-user");
      this.$router.push("/login");
    },
  }

}
</script>

<style scoped>
  @import "@/assets/css/front.css";

  .menu {
    color: #eee;
    font-size: 16px;
    padding: 0 20px;
    cursor: pointer;
  }
  .menu:hover {
    color: orange;
  }
  .menu-active {
    color: orange;
  }
</style>
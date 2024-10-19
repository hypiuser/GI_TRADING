<template>
  <div style="margin: 0 auto; padding: 10px 0; width: 50%">
    <div style="display: flex; grid-gap: 20px; margin-bottom: 40px">
      <img :src="goods.img" alt="" style="width: 50%; height: 400px; display: block">
      <div style="flex: 1; width: 0" >
        <el-tooltip :content="goods.name" placement="top-start">
          <div style="width: 100%; font-weight: bold; font-size: 26px; margin: 20px 0" class="line1">{{ goods.name }}</div>
        </el-tooltip>
        <div style="color: #464646; font-size: 16px;">
          <span>浏览 {{ goods.readCount }}</span>
          <span style="margin-left: 20px">点赞 {{ goods.likesCount }}</span>
          <span style="margin-left: 20px">收藏 {{ goods.collectCount }}</span>
        </div>
        <div style="color: red; font-size: 24px; margin: 40px 0">￥{{ goods.price }}</div>
        <div style="margin-bottom: 20px"><span style="color: #666">种类：</span> {{ goods.category}}</div>
        <div style="margin-bottom: 40px"><span style="color: #666">发布日期：</span> {{ goods.date }}</div>
        <div>
          <el-button v-if="!goods.userLikes" @click="addLikes" size="medium" >点赞</el-button>
          <el-button v-if="goods.userLikes" @click="addLikes" size="medium" ><i class="el-icon-caret-top"></i>已点赞</el-button>
          <el-button v-if="!goods.userCollect" @click="addCollect" size="medium"><i class="el-icon-star-off"></i>收藏</el-button>
          <el-button v-if="goods.userCollect" @click="addCollect" size="medium"><i class="el-icon-star-on"></i>已收藏</el-button>
          <el-button size="medium"  @click="addOrder">立即购买</el-button>
        </div>
      </div>
    </div>

    <div>
      <div style="display: flex; border-bottom: 1px solid #464646; margin-bottom: 10px">
        <div style="padding: 10px 20px; cursor: pointer" :class="{ 'active' : current === '物品详情' }" @click="changeItem('物品详情')">物品详情</div>
        <div style="padding: 10px 20px; cursor: pointer" :class="{ 'active' : current === '物品评论' }" @click="changeItem('物品评论')">物品评论</div>
      </div>

      <div v-if="current === '物品详情'">
        <div v-html="goods.content"></div>
      </div>

      <div v-if="current === '物品评论'" class="card">
        <Comment :fid="id" module="goods" />
      </div>

    </div>
  </div>
</template>

<script>
import Comment from "@/components/Comment";
export default {
  name: "GoodsDetail",
  components: {Comment},
  data() {
    return {
      id: this.$route.query.id,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      goods: {},
      current: '物品详情',
      form: {},
      fromVisible: false,
      addressList: []
    }
  },
  created() {
    this.$request.put('/goods/updateReadCount/' + this.id).then(res => {
      this.load()
    })

    this.loadAddress()
  },
  methods: {
    addOrder() {
      this.form.goodsId = this.id
      this.form.phone= this.user.phone
      this.form.address=null
      this.form.userName=this.user.name
      this.$request.post('/orders/add', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('下单成功')
          this.$router.push('/front/orders')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadAddress() {
      this.$request.get('/address/selectAll').then(res => {
        this.addressList = res.data || []
      })
    },
    addCollect() {
      this.$request.post('/collect/add', { fid: this.goods.id, module: 'goods' }).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    addLikes() {
      this.$request.post('/likes/add', { fid: this.goods.id, module: 'goods' }).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    changeItem(current) {
      this.current = current
    },
    load() {
      this.$request.get('/goods/selectById/' + this.id).then(res => {
        this.goods = res.data || {}
      })
    }
  }
}
</script>

<style scoped>
.active {
  background-color: #464646;
  color: #eee;
}
</style>
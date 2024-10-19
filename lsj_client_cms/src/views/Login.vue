<template>
  <div class="container">
    <div style="flex: 1; background-color: #f8f8f8">
      <div style="height: 50px; display: flex; align-items: center; background-color: white; padding-left: 20px">
        <a href =/login>
        <span style="font-size: 24px; margin-left: 5px">登录页</span>
      </a>
      </div>
      <div class="bg">
      <div style="height: calc(100vh - 60px); display: flex; align-items: center; justify-content: center">
        <div style="width: 500px; padding: 50px; background-color: white; border-radius: 5px;">
          <div style="display: flex; margin-bottom: 50px; font-size: 24px">
            <div style="border-bottom: 2px solid #52D8FF; padding-bottom: 10px">登录</div>
            <a style="color: #333" href="/register"><div style="margin-left: 10px;">用户注册</div></a>
          </div>
          <el-form :model="form" :rules="rules" ref="formRef">
            <el-form-item prop="username">
              <el-input size="medium" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="medium" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item prop="role">
              <el-radio-group v-model="form.role" style="width: 100%;display: flex;justify-content: center;" size="medium">
                <el-radio-button label="ADMIN">后台</el-radio-button>
                <el-radio-button label="USER">前台</el-radio-button>
              </el-radio-group>
            </el-form-item>
            <el-form-item>
              <el-button size="medium" style="width: 100%; background-color: #52D8FF; border-color: #52D8FF; color: white" @click="login">登 录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: { role: 'ADMIN' },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      }
    }
  },
  created() {

  },
  methods: {
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {

          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))
              if (res.data.role === 'ADMIN') {
                this.$router.push('/home')
              } else {
                this.$router.push('/front/home')
              }
              this.$message.success('登录成功')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  display: flex;
}
a {
  color: #2a60c9;
}
.bg {
  height: 1000px;
  background-image: url("@/assets/imgs/lrbg.png");
  background-size: cover;
  background-position-y: center;
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>
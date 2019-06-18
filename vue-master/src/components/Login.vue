<template>
    <div>
        <el-form :rules="loginRules" class="login-container" label-position="left" label-width="0px" v-loading="loading">
            <h3 class="login-title">系统登录</h3>
            <el-form-item prop="account">
                <el-input type="text" v-model="loginForm.username" autocomplete="off" placeholder="账号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginForm.password" autocomplete="off" placeholder="密码"></el-input>
            </el-form-item>
            <el-checkbox class="login-remember" v-model="checked" label-position="left">记住密码</el-checkbox>
            <el-form-item style="width: 100%">
                <el-button type="primary" style="width: 40%" @click="showRegist">注册</el-button>
                <el-button type="primary" style="width: 40%" @click="submit">登录</el-button>
            </el-form-item>
        </el-form>
        <el-form :rules="registRules"  label-position="left" label-width="0px" >
            <div style="text-align: left">
                <el-dialog
                    :title="dialogTitle"
                    style="padding: 0px;"
                    :close-on-click-modal="false"
                    :visible.sync="dialogVisible"
                    width="30%"
                    center 
                    >
                        <el-form-item prop="account">
                                姓名:<el-input type="text" v-model="registForm.name" autocomplete="off" placeholder="姓名"></el-input>
                        </el-form-item>
                        <el-form-item prop="account">
                                用户名:<el-input type="text" v-model="registForm.username" autocomplete="off" placeholder="账号"></el-input>
                        </el-form-item>
                        <el-form-item prop="password">
                                密码:<el-input type="password" v-model="registForm.password" autocomplete="off" placeholder="密码"></el-input>
                        </el-form-item>
                        <span slot="footer" class="dialog-footer" >
                                <el-button size="primary" style="width: 40%" @click="cancelRegist">取 消</el-button>
                                <el-button size="primary" style="width: 40%" @click="doRegist">确 定</el-button>
                        </span>
                </el-dialog>       
            </div>     
        </el-form>
    </div>
</template>

<script>
export default {
    data(){
        return {
            dialogVisible: false,
            dialogTitle: "",
            registForm:{
                name: "",
                username: "",
                password: "",
            },
            loginForm: {
                username: "admin",
                password: "1234",
            },
            loginRules: {
                account: {required: true, message: '请输入用户名', trigger: 'blur'},
                password: {required: true, message: '请输入密码', trigger: 'blur'}
            },
            registRules: {
                name: {required: true, message: '请输入姓名', trigger: 'blur'},
                account: {required: true, message: '请输入用户名', trigger: 'blur'},
                password: {required: true, message: '请输入密码', trigger: 'blur'}
            },
            loading: false,
            checked: true,
        }
    },
    methods: {
        doRegist: function(){
            this.postRequest("/auth/regist",this.registForm).then(response =>{
                if(resp && resp.status == 200){
                    let data = resp.data;
                    this.$message("注册成功");
                }else {
                    this.$message("注册失败");
                }
                this.dialogVisible=false;
            })
        },

        showRegist: function(){
            this.dialogTitle="注册";
            this.dialogVisible=true;
        },
        cancelRegist: function(){
            this.dialogVisible=false;
        },
        submit: function(){
            var _this = this;
            this.loading = true;
            _this.postRequest('/auth/login' , {
                username: _this.loginForm.username,
                password: _this.loginForm.password,
            }).then(resp => {
                _this.loading = false;
                if(resp && resp.status == 200) {
                    _this.$store.commit('login', resp.data);
                    var path = _this.$route.query.redirect;
                    _this.$message("登录成功");
                    _this.$router.replace({path: '/Home'});
                }else {
                    _this.$message(resp.data);
                }
            });
        }
    }
}
</script>

<style>

    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .login-title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }
    .login-remember {
        margin: 0px 0px 35px 0px;
        text-align: left;
    }
</style>
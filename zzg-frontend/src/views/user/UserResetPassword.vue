<script setup>  
import { ref } from 'vue'  
import useUserInfoStore from '@/stores/userInfo.js'  
import { userpasswordUpdateService } from '@/api/user.js'  
import { ElMessage } from 'element-plus'  
  
const userInfoStore = useUserInfoStore();  
  

//添加表单数据模型
const passwordModel = ref({
    old_pwd: '',
    new_pwd: '',
    re_pwd: '',
})
  
function checkRePassword(rule, value, callback) {  
    if (value === '') {  
        callback(new Error('请再次输入新密码'));  
    } else if (value !== newPwd.value) {  
        callback(new Error('两次输入的新密码不一致！'));  
    } else {  
        callback();  
    }  
}  
  
const rules = {  
    old_pwd: [  
        { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }  
    ],  
    new_pwd: [  
        { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }  
    ],  
    re_pwd: [  
        { validator: checkRePassword, trigger: 'blur' }  
    ]  
}  
  
const updatepassword = async () => {  
    let result = await userpasswordUpdateService(passwordModel.value);  
    ElMessage.success(result.message ? result.message : '修改成功');  
}  
</script>  
  
<template>  
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>重置密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="passwordModel" :rules="rules" label-width="100px" size="large"></el-form>
                    <el-form-item label="原密码" prop="old_pwd">  
                        <el-input type="password" v-model="passwordModel.old_pwd"></el-input>  
                    </el-form-item>  
                    <el-form-item label="新密码" prop="new_pwd">  
                        <el-input type="password" v-model="passwordModel.new_pwd"></el-input>  
                    </el-form-item>  
                    <el-form-item label="确认新密码" prop="re_pwd">  
                        <el-input type="password" v-model="passwordModel.re_pwd"></el-input>  
                    </el-form-item>  
                    <el-form-item>
                        <el-button type="primary" @click="updatepassword()">提交修改</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
       </el-card>
</template>
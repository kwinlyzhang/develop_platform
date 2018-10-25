<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类别" prop="type">
      <el-input v-model="dataForm.type" placeholder="类别"></el-input>
    </el-form-item>
    <el-form-item label="名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="名称"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="描述"></el-input>
    </el-form-item>
    <el-form-item label="单价" prop="amount">
      <el-input v-model="dataForm.amount" placeholder="单价"></el-input>
    </el-form-item>
    <el-form-item label="数量" prop="number">
      <el-input v-model="dataForm.number" placeholder="数量"></el-input>
    </el-form-item>
    <el-form-item label="总计" prop="total">
      <el-input v-model="dataForm.total" placeholder="总计"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          detailId: 0,
          type: '',
          name: '',
          remark: '',
          amount: '',
          number: '',
          total: ''
        },
        dataRule: {
          type: [
            { required: true, message: '类别不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          remark: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
          ],
          amount: [
            { required: true, message: '单价不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '数量不能为空', trigger: 'blur' }
          ],
          total: [
            { required: true, message: '总计不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.detailId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.detailId) {
            this.$http({
              url: this.$http.adornUrl(`/business/consumptiondetail/info/${this.dataForm.detailId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.type = data.consumptiondetail.type
                this.dataForm.name = data.consumptiondetail.name
                this.dataForm.remark = data.consumptiondetail.remark
                this.dataForm.amount = data.consumptiondetail.amount
                this.dataForm.number = data.consumptiondetail.number
                this.dataForm.total = data.consumptiondetail.total
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/business/consumptiondetail/${!this.dataForm.detailId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'detailId': this.dataForm.detailId || undefined,
                'type': this.dataForm.type,
                'name': this.dataForm.name,
                'remark': this.dataForm.remark,
                'amount': this.dataForm.amount,
                'number': this.dataForm.number,
                'total': this.dataForm.total
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>

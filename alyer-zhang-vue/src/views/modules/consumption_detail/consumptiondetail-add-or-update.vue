<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="消费日期" prop="consumeDate">
      <el-date-picker
        v-model="dataForm.consumeDate"
        type="date"
        value-format="yyyy-MM-dd"
        placeholder="选择消费日期">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="类别" prop="type">
      <el-select v-model="dataForm.type" placeholder="请选择">
        <el-option
          v-for="item in consumeTypes"
          :key="item.type"
          :label="item.name"
          :value="item.type">
        </el-option>
      </el-select>
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
          id: 0,
          consumeDate: '',
          type: '',
          name: '',
          remark: '',
          amount: '',
          number: '1',
          total: ''
        },
        consumeTypes: [],
        dataRule: {
          consumeDate: [
            { required: true, message: '消费日期不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '类别不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          amount: [
            { required: true, message: '单价不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '数量不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    created () {
      this.getDataList()
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/consumption_detail/consumptiondetail/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.consumeDate = data.consumptionDetail.consumeDate
                this.dataForm.type = data.consumptionDetail.type
                this.dataForm.name = data.consumptionDetail.name
                this.dataForm.remark = data.consumptionDetail.remark
                this.dataForm.amount = data.consumptionDetail.amount
                this.dataForm.number = data.consumptionDetail.number
                this.dataForm.total = data.consumptionDetail.total
              }
            })
          }
        })
      },
      // 获取类型列表
      getDataList () {
        this.$http({
          url: this.$http.adornUrl('/consume_type/consumetype/list/all'),
          method: 'get'
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.consumeTypes = data.list
          } else {
            this.consumeTypes = []
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/consumption_detail/consumptiondetail/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'consumeDate': this.dataForm.consumeDate,
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

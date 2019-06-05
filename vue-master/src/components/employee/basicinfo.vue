<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过员工名搜索员工,记得回车哦..."
            clearable
            @change="keywordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchEmp"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchEmp">搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddEmpView">
            添加员工
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="emps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              type="selection"
              align="left"
              width="30">
            </el-table-column>
            <el-table-column
              prop="name"
              align="left"
              fixed
              label="姓名"
              width="90">
            </el-table-column>
            <el-table-column
              prop="gender"
              label="性别"
              width="50">
            </el-table-column>
            <el-table-column
              width="85"
              align="left"
              label="出生日期">
              <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template>
            </el-table-column>
            <el-table-column
              prop="idCard"
              width="150"
              align="left"
              label="身份证号码">
            </el-table-column>
            <el-table-column
              prop="wedlock"
              width="70"
              label="婚姻状况">
            </el-table-column>
            <el-table-column
              width="80"
              prop="nation"
              label="民族">
            </el-table-column>
            <el-table-column
              width="80"
              align="left"
              label="部门">
              <template slot-scope="scope">{{ scope.row.department | formatDep}}</template>
            </el-table-column>
            <el-table-column
              prop="politic"
              label="政治面貌">
            </el-table-column>
            <el-table-column
              prop="email"
              width="160"
              align="left"
              label="电子邮件">
            </el-table-column>
            <el-table-column
              prop="phone"
              width="140"
              label="电话号码">
            </el-table-column>
            <el-table-column
              prop="address"
              width="180"
              align="left"
              label="联系地址">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="140">
              <template slot-scope="scope">
                <el-button @click="showEditEmpView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteEmp(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="emps.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyEmps">批量删除
            </el-button>
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount">
            </el-pagination>
          </div>
        </div>
      </el-main>
    </el-container>
        <el-form :model="emp" :rules="rules" ref="EmpForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="77%">
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="姓名:" prop="name">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.name" size="mini" style="width: 150px"
                            placeholder="请输入员工姓名"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="性别:" prop="gender">
                  <el-radio-group v-model="emp.gender">
                    <el-radio label="男">男</el-radio>
                    <el-radio style="margin-left: 15px" label="女">女</el-radio>
                  </el-radio-group>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="出生日期:" prop="birthday">
                  <el-date-picker
                    v-model="emp.birthday"
                    size="mini"
                    value-format="yyyy-MM-dd"
                    style="width: 150px"
                    type="date"
                    placeholder="出生日期">
                  </el-date-picker>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="政治面貌:" prop="politic">
                  <el-select v-model="emp.politic" style="width: 200px" size="mini" placeholder="政治面貌">
                    <el-option
                      v-for="item in politics"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="民族:" prop="nation">
                  <el-select v-model="emp.nation" style="width: 150px" size="mini" placeholder="请选择民族">
                    <el-option
                      v-for="item in nations"
                      :key="item.id"
                      :label="item.name"
                      :value="item.name">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="部门:" prop="department">
                  <el-select v-model="emp.department" style="width: 150px" size="mini" placeholder="请选择部门">
                    <el-option
                      v-for="item in departments"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                 </el-form-item> 
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="电子邮箱:" prop="email">
                  <el-input prefix-icon="el-icon-message" v-model="emp.email" size="mini" style="width: 150px"
                            placeholder="电子邮箱地址..."></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="婚姻状况:" prop="wedlock">
                  <el-radio-group v-model="emp.wedlock">
                    <el-radio label="已婚">已婚</el-radio>
                    <el-radio style="margin-left: 15px" label="未婚">未婚</el-radio>
                    <el-radio style="margin-left: 15px" label="离异">离异</el-radio>
                  </el-radio-group>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="7">
              <div>
                <el-form-item label="电话号码:" prop="phone">
                  <el-input prefix-icon="el-icon-phone" v-model="emp.phone" size="mini" style="width: 200px"
                            placeholder="电话号码..."></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="联系地址:" prop="address">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.address" size="mini" style="width: 200px"
                            placeholder="联系地址..."></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="身份证:" prop="idCard">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.idCard" size="mini" style="width: 200px"
                            placeholder="身份证号码..." :disabled="disable"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
    <el-button size="mini" @click="cancelEidt">取 消</el-button>
    <el-button size="mini" type="primary" @click="Emp('EmpForm')">确 定</el-button>
  </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        emps: [],
        keywords: '',
        disable: false,
        dialogTitle: '',
        departments: [{
          "id": 10,
          "name": "人事部"
        },{
          "id": 9,
          "name": "市场部"
        },{
          "id": 11,
          "name": "运维部"
        }],
        nations: [],
        politics: [],
        multipleSelection: [],
        totalCount: -1,
        currentPage: 1,
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,

        edit: {
          name: '',
          gender: '',
          birthday: '',
          wedlock: '',
          nation: '',
          department: '',
          politic: '',
          email: '',
          phone: '',
          address: ''
        },

        emp: {
          name: '',
          gender: '',
          birthday: '',
          wedlock: '',
          nation: '',
          department: '',
          politic: '',
          email: '',
          phone: '',
          address: ''
        },
        rules: {
          name: [{required: true, message: '必填:姓名', trigger: 'blur'}],
          gender: [{required: true, message: '必填:性别', trigger: 'blur'}],
          birthday: [{required: true, message: '必填:出生日期', trigger: 'blur'}],
          wedlock: [{required: true, message: '必填:婚姻状况', trigger: 'blur'}],
          nation: [{required: true, message: '必填:民族', trigger: 'blur'}],
          departments: [{required: true, message: '必填:部门', trigger: 'blur'}],
          politic: [{required: true, message: '必填:政治面貌', trigger: 'blur'}],
          email: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
          phone: [{required: true, message: '必填:电话号码', trigger: 'blur'}],
          address: [{required: true, message: '必填:联系地址', trigger: 'blur'}],
          idCard: [{required: true, message: '必填:身份证', trigger: 'blur'}]
        }
      };
    },
    mounted: function () {
      this.loadEmps();
      this.loadData();
    },
    
    methods: {
      loadData(){
        this.getRequest("/info/nations").then(resp=> {
          if(resp && resp.status ==200){
            let data=resp.data;
            this.nations = data.obj;
          }
        })
        this.getRequest("/info/politics").then(resp=> {
          if(resp && resp.status ==200){
            let data=resp.data;
            this.politics = data.obj;
          }
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteManyEmps(){
        this.$confirm('此操作将删除 ' + this.multipleSelection.length + ' 条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var id = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            id += this.multipleSelection[i].id + ",";
          }
          this.doDelete(id)
        }).catch(() => {
        });
      },
      deleteEmp(row){
        this.$confirm('此操作将永久删除 '+ row.name +", 是否继续?", '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.id);
        })
      },
      doDelete(id){
        this.tableLoading = true;
        var _this = this;
        _this.postRequest("/employee/delete",{
          "id": id
        }).then(resp=> {
          if(resp && resp.status == 200){
            let data = resp.data;
            if(data.status == 200){
              _this.$message("操作成功");
            }else {
              _this.$message("操作失败,请重试");
            }
          }else {
            return false;
          }
          _this.loadEmps();
          tableLoading = false;
        })
      },
      keywordsChange(val){
        if (val == '') {
          this.loadEmps();
        }
      },
      searchEmp(){
        this.loadEmps();
      },
      currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadEmps();
      },
      loadEmps(){
        var _this = this;
        this.tableLoading = true;
        this.getRequest("/employee/basinfo?page=" + this.currentPage + "&size=10&keywords=" + this.keywords).then(resp=> {
          this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.emps = data.emps;
            _this.totalCount = data.count;
          }
        })
        this.tableLoading = false;
      },
      Emp(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if(valid){
            if(_this.dialogTitle == '编辑员工'){
              for(var i in _this.emp){
                _this.edit[i] = (_this.emp[i] == _this.edit[i] ? '' : _this.emp[i]);
              }
              _this.edit.idCard = _this.emp.idCard;
              _this.postRequest("/employee/edit", _this.edit).then(resp=> {
                if(resp && resp.status ==200){
                  let data = resp.data;
                  if(data.status == 200){
                    this.$message("操作成功");
                  }else {
                    this.$message("操作失败,请重试");
                  }
                }
              })
            }else {
              _this.postRequest("/employee/add", _this.emp).then(resp=> {
                if(resp && resp.status ==200){
                  let data = resp.data;
                  if(data.status == 200){
                    this.$message("操作成功");
                  }else {
                    this.$message("操作失败,请重试");
                  }
                }
              })
            }
            this.loadEmps();
            this.dialogVisible=false;
          }
        });
      },
      cancelEidt(){
        this.dialogVisible = false;
        this.emptyData();
      },
      showEditEmpView(row){
        this.emptyData();
        this.dialogTitle = "编辑员工";
        for(var i in row){
          this.emp[i] = row[i];
        }
        this.emp.birthday = this.formatDate(row.birthday);
        this.disable = true;
        this.dialogVisible = true;
        for(let i in this.emp){
          this.edit[i] = this.emp[i];
        }
      },
      showAddEmpView(){
        this.emptyData();
        this.dialogTitle = "添加员工";
        this.disable = false;
        this.dialogVisible = true;
      },
      emptyData(){
        this.emp = {
          name: '',
          gender: '',
          birthday: '',
          wedlock: '',
          nation: '',
          nativePlace: '',
          politic: '',
          email: '',
          phone: '',
          address: ''
        }
      }
    }
  };
</script>
<style>
  .el-dialog__body {
    padding-top: 0px;
    padding-bottom: 0px;
  }

  .slide-fade-enter-active {
    transition: all .8s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
</style>




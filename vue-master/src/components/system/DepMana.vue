<template>
  <div>
    <div style="text-align: left">
      <el-input
        placeholder="输入部门名称搜索部门..."
        style="width: 500px;margin: 0px;padding: 0px;"
        size="mini"
        prefix-icon="el-icon-search"
        v-model="keywords">
      </el-input>
    </div>
    <div>
      <el-tree
        :props="defaultProps"
        :data="treeData"
        ref="tree"
        v-loading="treeLoading"
        :render-content="renderContent"
        style="width: 500px;margin-top: 10px"
        >
      </el-tree>
      <div style="text-align: left">
        <el-dialog
          title="添加部门"
          :visible.sync="dialogVisible"
          width="25%">
          <div>
            <span>上级部门</span>
            <el-select v-model="dep" style="width: 200px" placeholder="请选择" size="mini">
              <el-option
                v-for="item in allDeps"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </div>
          <div style="margin-top: 10px">
            <span>部门名称</span>
            <el-input size="mini" style="width: 200px;" v-model="depName" placeholder="请输入部门名称..." @keyup.enter.native="addDep"></el-input>
          </div>
          <span slot="footer" class="dialog-footer">
    <el-button size="small" @click="dialogVisible = false">取消</el-button>
    <el-button size="small" type="primary" @click="addDep">添加</el-button>
  </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        keywords: '',
        depName: '',
        treeLoading: false,
        dialogVisible: false,
        allDeps: [],
        dep: '',
        treeData: [],
        defaultProps: {
          label: 'name',
          children: 'children'
        }
      }
    },
    mounted: function () {
      this.treeLoading = true;
      this.loadTreeData();
      this.getAllDeps();
    },
    watch: {
      keywords(val) {
        this.$refs.tree.filter(val);
      }
    },
    methods: {
      filterNode(value, data) {
        if (!value) return true;
        return data.name.indexOf(value) !== -1;
      },
      getAllDeps(){
        var _this = this;
        this.getRequest("/info/deps").then(resp=> {
          if (resp && resp.status == 200) {
            _this.allDeps = resp.data;
          }
        })
      },
      loadTreeData(){
        var _this = this;
        this.getRequest("/info/dep?id=-1").then(resp=> {
          _this.treeLoading = false;
          if (resp && resp.status == 200) {
            _this.treeData = resp.data;
          }
        })
      },
      addDep(){
        var _this = this;
        this.dialogVisible = false;
        this.treeLoading = true;
        this.postRequest("/info/dep", {
          name: this.depName,
          parentid: this.dep
        }).then(resp=> {
          _this.treeLoading = false;
          if (resp && resp.status == 200) {
            _this.$message("操作成功");
          }else{
            _this.$message("操作失败,请重试");
          }
        })
        _this.loadTreeData();
      },
      showAddDepView(data, event){
        this.dialogVisible = true;
        this.dep = data.id;
        event.stopPropagation()
      },
      deleteDep(data, event){
        var _this = this;
        if (data.children.length>0) {
          this.$message({
            message: '该部门下尚有其他部门，不能被删除!',
            type: 'warning'
          });
        } else {
          this.$confirm('删除 ' + data.name + ' 部门, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.treeLoading = true;
            _this.deleteRequest("/info/dep?id=" + data.id).then(resp=> {
              _this.treeLoading = false;
              if (resp && resp.status == 200) {
                _this.$message("操作成功");
              }else{
                _this.$message("操作失败,请重试");
              }
            });
            _this.loadTreeData();
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }
        event.stopPropagation()
      },
      renderContent(h, {node, data, store}) {
        return (
        <span style="flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px;">
        <span>
          <span>{node.label}</span>
        </span>
        <span>
        <el-button style="font-size: 12px;" type="primary" size="mini" style="padding:3px" on-click={ () => this.showAddDepView(data,event) }>添加部门</el-button>
        <el-button style="font-size: 12px;" type="danger" size="mini" style="padding:3px" on-click={ () => this.deleteDep(data,event) }>删除部门</el-button>
        </span>
        </span>);
      }
    }
  };
</script>

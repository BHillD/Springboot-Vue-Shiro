<template>
  <div>
    <div style="text-align: left" v-loading="loading">
      <el-input
        placeholder="请输入角色名称..."
        size="mini"
        style="width: 250px"
        v-model="newRole">
      </el-input>
      <el-button type="primary" size="mini" style="margin-left: 5px" @click="doAddRole">添加角色</el-button>
    </div>
    <div style="margin-top: 10px;text-align: left">
      <el-collapse v-model="activeColItem" accordion style="width: 500px;" @change="collapseChange">
        <el-collapse-item v-for="(item,index) in roles" :title="item.name" :name="item.id" :key="item.name">
          <el-card class="box-card">
            <div slot="header">
              <span>可访问的资源</span>
              <el-button type="text"
                         style="color: #f6061b;margin: 0px;float: right; padding: 3px 0;width: 15px;height:15px"
                         icon="el-icon-delete" @click="deleteRole(item.id,item.name)"></el-button>
            </div>
            <div>
              <el-tree :props="props"
                       :key="item.id"
                       :data="treeData"
                       :default-checked-keys="checkedKeys"
                       node-key="id"
                       ref="tree"
                       show-checkbox
                       highlight-current>
              </el-tree>
            </div>
            <div style="display: flex;justify-content: flex-end;margin-right: 10px">
              <el-button size="mini" @click="cancelUpdateRoleMenu">取消修改</el-button>
              <el-button type="primary" size="mini" @click="updateRoleMenu(index)">确认修改</el-button>
            </div>
          </el-card>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>
<script>
  export default {
    mounted: function () {
      this.loading = true;
      this.initRoles();
    },
    methods: {
      deleteRole(rid, rname){
        var _this = this;
        this.$confirm('删除角色[' + rname + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.loading = true;
          _this.deleteRequest("/system/role/" + rid).then(resp=> {
            if (resp && resp.status == 200) {
              _this.initRoles();
            } else {
              _this.loading = false;
            }
          })
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      doAddRole(){
          this.loading = true;
          var _this = this;
          this.postRequest("/system/role", {
            name: _this.newRole,
          }).then(resp=> {
            if (resp && resp.status == 200) {
              var data = resp.data;
              _this.initRoles();
              _this.newRole = '';
              _this.loading = false;
            } else {
              _this.loading = false;
            }
          })
      },
      updateRoleMenu(index){
        var checkedKeys = this.$refs.tree[index].getCheckedKeys(true);
        var _this = this;
        this.putRequest("/system/basic/updateMenuRole", {
          rid: this.activeColItem,
          mids: checkedKeys
        }).then(resp=> {
          if (resp && resp.status == 200) {
            _this.activeColItem = -1;
          }
        })
      },
      collapseChange(activeName){
        if (activeName == '') {
          return;
        }
        var _this = this;
        this.getRequest("/system/menutree/" + activeName).then(resp=> {
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.treeData = data.tree;
            _this.checkedKeys = data.menus;
          }
        })
      },
      initRoles(){
        var _this = this;
        this.getRequest("/system/roles").then(resp=> {
          _this.loading = false;
          if (resp && resp.status == 200) {
            _this.roles = resp.data.obj;
            _this.activeColItem=-1
          }
        })
      },
      cancelUpdateRoleMenu(){
        this.activeColItem = -1;
      }
    },
    data(){
      return {
        props: {
          label: 'name',
          children: 'children'
        },
        newRole: '',
        roles: [],
        treeData: [],
        checkedKeys: [],
        loading: false,
        activeColItem: -1
      };
    }
  }
</script>

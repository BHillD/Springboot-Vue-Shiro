<template>
  <div style="margin-top: 10px">
    <el-tabs v-model="defaultTab" type="card">
      <el-tab-pane label="部门管理" name="depMana">
        <dep-mana></dep-mana>
      </el-tab-pane>
      <el-tab-pane label="角色管理" name="menuRole">
        <menu-role></menu-role>
      </el-tab-pane>
      <el-tab-pane label="用户管理" name="userrole">
        <user-role></user-role>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import MenuRole from './MenuRole.vue'
  import DepMana from './DepMana.vue'
  import UserRole from './UserRole.vue'
  export default {
    data() {
      return {
        defaultTab: 'depMana'
      };
    },
    methods: {},
    components: {
      'menu-role': MenuRole,
      'dep-mana': DepMana,
      'user-role': UserRole
    }
  };
</script>

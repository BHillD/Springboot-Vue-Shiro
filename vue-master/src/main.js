// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index.js'
import 'font-awesome/css/font-awesome.min.css'
import {getRequest} from './utils/axios'
import {postRequest} from './utils/axios'
import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import {initMenu} from './utils/utils';
import './utils/filter'

Vue.config.productionTip = false;
Vue.use(ElementUi);

Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;

router.beforeEach((to,from,next) => {
  if(to.name == 'Login'){
    next();
    return;
  }
  let name = store.state.user.name;
  if(name == '未登录'){
    if(to.meta.requireAuth ||to.name == null){
      next({path: '/', query: {redirect: to.path}})
    } else {
      next();
    }
  } else {
    initMenu(router, store);
    next();
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

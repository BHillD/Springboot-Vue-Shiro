import Vue from 'vue'
Vue.filter("formatDate", formatDate);
Vue.filter("formatDep",formatDep);
Vue.prototype.formatDate = formatDate;
Vue.prototype.formatDep = formatDep;
function formatDate(value) {
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day;
}

function formatDep(value) {
  if(value == 9){
    return "人事部";
  } else if(value == 10){
    return "市场部";
  } else if(value == 11){
    return "运维部";
  }
}
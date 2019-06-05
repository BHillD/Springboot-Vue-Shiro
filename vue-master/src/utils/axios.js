import axios from 'axios'

let base = '';

export const postRequest = (url, params) => {
    return axios({
      method: 'post',
      url: `${base}${url}`,
      data: params,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    });
  }

export const getRequest = (url) => {
    return axios({
      method: 'get',
      url: `${base}${url}`
    });
  }
  
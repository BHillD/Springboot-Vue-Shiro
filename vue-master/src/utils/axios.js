import axios from 'axios'

let base = '/api';

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

export const putRequest = (url, params) => {
    return axios({
      method: 'put',
      url: `${base}${url}`,
      data: params,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    })
}

export const deleteRequest = (url) => {
    return axios({
      method: 'delete',
      url: `${base}${url}`
    })
}
  
import axios from 'axios';

import * as Paths from '../../services/api/paths';

export default class AuthServices {
  
    login(login) {
       
      return new Promise((resolve, reject) => {
        axios.post(Paths.login,login, {
          headers: { 'Accept-Language': 'tr'},
        })
          .then((res => {
            resolve(res.data);
          }))
          .catch(err => {
            reject(err);
          });
      });
    }
    
    getUserInformation(token) {
      return new Promise((resolve, reject) => {
        axios.get(Paths.user, {
          headers: { 'Accept-Language': 'tr', 'Authorization': `Bearer ${token}`},
        })
          .then((res => {
            resolve(res.data);
          }))
          .catch(err => {
            reject(err);
          });
      });
    }

    createUser(user) {
      return new Promise((resolve, reject) => {
        axios.post(Paths.createUser, user,{
          headers: { 'Accept-Language': 'tr'},
        })
          .then((res => {
            resolve(res.data);
          }))
          .catch(err => {
            reject(err);
          });
      });
    }

}
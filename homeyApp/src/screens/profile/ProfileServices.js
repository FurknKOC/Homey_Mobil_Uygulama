import axios from 'axios';

import * as Paths from '../../services/api/paths';

export default class AuthServices {
    
    getUserInformation(token) {
        console.log("getiresnee ");
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

}
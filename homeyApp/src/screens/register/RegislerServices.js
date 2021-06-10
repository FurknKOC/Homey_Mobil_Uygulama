import axios from 'axios';

import * as Paths from '../../services/api/paths';


export default class RegisterServices {

    registerApart(apart,token) {
        console.log("buraya geliyor muuuu????");
      return new Promise((resolve, reject) => {
        axios.post(Paths.registerUserHouse, apart, {
          headers: { 'Accept-Language': 'tr', 'Authorization': `Bearer ${token}` },
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
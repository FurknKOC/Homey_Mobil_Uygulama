import axios from 'axios';

import * as Paths from '../../services/api/paths';


export default class HomeServices {

    createSmartDevice(smartDevice,token) {
        console.log("buraya geliyor muuuu????");
      return new Promise((resolve, reject) => {
        axios.post(Paths.smartDevice, smartDevice, {
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
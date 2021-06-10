import axios from 'axios';

import * as Paths from '../../services/api/paths';


export default class ApartServices {

    createApart(apart,token) {
      return new Promise((resolve, reject) => {
        axios.post(Paths.apart, apart, {
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

    getAllApart(user,token) {
        return new Promise((resolve, reject) => {
          axios.post(Paths.getAllApart, user, {
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

      sendUuidForApart(mail,token) {
        return new Promise((resolve, reject) => {
          axios.post(Paths.sendUuidForApart, mail, {
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

      createTool(tool,token) {
        return new Promise((resolve, reject) => {
          axios.post(Paths.createTool, tool, {
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
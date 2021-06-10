import axios from 'axios';

import * as Paths from '../../services/api/paths';

export default class ReservationServices {

    getTools(token) {
        return new Promise((resolve, reject) => {
          axios
            .post(Paths.getTools, null, {
              headers: {'Accept-Language': 'tr', Authorization: `Bearer ${token}`},
            })
            .then(res => {
              resolve(res.data);
            })
            .catch(err => {
              reject(err);
            });
        });
      }

  getReservations(reservation, token) {
    return new Promise((resolve, reject) => {
      axios
        .post(Paths.getReservations, reservation, {
          headers: {'Accept-Language': 'tr', Authorization: `Bearer ${token}`},
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  createReservation(reservation, token) {
    return new Promise((resolve, reject) => {
      axios
        .post(Paths.createReservation, reservation, {
          headers: {'Accept-Language': 'tr', Authorization: `Bearer ${token}`},
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}

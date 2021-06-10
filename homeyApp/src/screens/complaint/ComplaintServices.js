import axios from 'axios';

import * as Paths from '../../services/api/paths';


export default class ComplaintServices {

    createComplaint(comp,token) {
      return new Promise((resolve, reject) => {
        axios.post(Paths.createComplaint, comp, {
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

    getAllComplaint(token) {
        return new Promise((resolve, reject) => {
          axios.post(Paths.getAllComplaint, null, {
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

      getAllComplaintManager(token) {
        return new Promise((resolve, reject) => {
          axios.post(Paths.getAllComplaintManager, null, {
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

      setComplaintTypeWorkingOn(comp,token) {
      return new Promise((resolve, reject) => {
        axios.post(Paths.complaintTypeWorkingOn, comp, {
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

    setComplaintTypeSolved(comp,token) {
      return new Promise((resolve, reject) => {
        axios.post(Paths.complaintTypeSolved, comp, {
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
// import AsyncStorage from '@react-native-async-storage/async-storage';
// import axios from 'axios';

// import * as actions from '../redux/modules/auth/auth.action';
// import * as Paths from 'src/services/api/paths';

// const getAccessToken = async () => {
//     return await AsyncStorage.getItem('access_token');
// };

// const getUserInfo = async () => {
//     return await AsyncStorage.getItem('user_info');
// };

// const setAccessToken = (token) => {
//     AsyncStorage.setItem('access_token', token);
//     axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
// };

// const setUserInfo = (info) => {
//     AsyncStorage.setItem('user_info', JSON.stringify(info));
// };

// const removeAccessToken = () => {
//     AsyncStorage.removeItem('access_token');
//     delete axios.defaults.headers.common['Authorization'];
// };

// const removeUserInfo = () => {
//     AsyncStorage.removeItem('user_info');
// };

// const stateAxiosHeader = async () => {
//     const token = await getAccessToken();
//     axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
// };

// let isRefreshing = false;
// let subscribers = [];

// function onRefreshed(token) {
//     subscribers.map(cb => cb(token));
// }

// function subscribeTokenRefresh(cb) {
//     subscribers.push(cb);
// }

// const authenticationRequest = () => {
//     axios.interceptors.response.use((response) => {
//             return response
//         },
//         async function (error) {
//             let originalRequest = {...error}.config;


//                 return new Promise(resolve => {
//                     subscribeTokenRefresh(token => {
//                         originalRequest.headers.Authorization = `Bearer ${token}`;
//                         resolve(axios(originalRequest));
//                     });
//                 });

//             return Promise.reject(error);

//         });
// };


// const logout = async () => {
//     isRefreshing = false;
//     subscribers = [];

//     const user = await getUserInfo();

//     if (!user)
//         return;

//     global.dispatch(actions.clearUser());

//     removeUserInfo();
//     removeAccessToken();
//     removeRefreshToken();
// };

// export {
//     getAccessToken,
//     getUserInfo,
//     setAccessToken,
//     setUserInfo,
//     removeAccessToken,
//     removeUserInfo,
//     stateAxiosHeader,
//     authenticationRequest
// }

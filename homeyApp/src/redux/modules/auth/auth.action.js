import * as actionTypes from './auth.type';

export function saveUser(user) {
    return {
        type: actionTypes.SAVE_USER,
        payload: user
    }
}

export function clearUser() {
    return {
        type: actionTypes.CLEAR_USER
    }
}
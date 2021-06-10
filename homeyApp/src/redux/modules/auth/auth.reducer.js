import * as actionTypes from './auth.type';

const initialState = {
    user: null,
};

const reducer = (state = initialState, {type, payload}) => {
    switch (type) {
        case actionTypes.SAVE_USER:
            return { ...state, user: payload };
        case actionTypes.CLEAR_USER:
            return { ...state, user: null };
        default:
            return state;
    }
};

export default reducer;

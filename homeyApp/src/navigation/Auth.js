import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

import { SignIn } from '../screens/auth/SignIn';

function AuthNavigator() {
    return (
        <Stack.Navigator
            headerMode={'none'}
            initialRouteName={'SignIn2'}
        >
            <Stack.Screen name="SignIn" component={SignIn} />
        </Stack.Navigator>
    )
}
export default AuthNavigator;

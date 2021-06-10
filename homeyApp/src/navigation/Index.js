import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';

import Home from '../screens/home/Home';
import Apart from '../screens/apart/Apart';
import SingIn from '../screens/auth/SignIn';
import MainNavigation from './MainNavigation';
import UserNavigation from './UserNavigation';
import CreateUser from '../screens/auth/CreateUser';

const Stack = createStackNavigator();

const IndexNavigation = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="SingIn" screenOptions={{headerShown:false}}>
        <Stack.Screen name="Apart" component={Apart} />
        <Stack.Screen name="SingIn" component={SingIn} />
        <Stack.Screen name="Home" component={Home} />
        <Stack.Screen name="MainNavigation" component={MainNavigation} />
        <Stack.Screen name="UserNavigation" component={UserNavigation} />
        <Stack.Screen name="CreateUser" component={CreateUser} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default IndexNavigation;
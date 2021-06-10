import 'react-native-gesture-handler';
import * as React from 'react';
import {Appbar} from 'react-native-paper';
import { LogBox, StatusBar, View } from 'react-native';
import { Provider as StoreProvider } from 'react-redux';
import store from './src/redux/store';

import IndexNavigation from './src/navigation/Index'
LogBox.ignoreLogs(["Warning: Can't perform a"]);
const App = () => {
    return (

    //   <StoreProvider store={store}>
    //     <Appbar.Header>
    //   <Appbar.BackAction onPress={() => {}} />
    //   <Appbar.Content title="Title" subtitle="Subtitle" />
    //   <Appbar.Action icon="magnify" onPress={() => {}} />
    //   <Appbar.Action icon="dots-vertical" onPress={() => {}} />
    // </Appbar.Header>
        <IndexNavigation />
     // </StoreProvider>
            
            
    )
};

export default App;

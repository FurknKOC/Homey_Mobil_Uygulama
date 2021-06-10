import React, {useState, useEffect} from 'react';
import {View, StyleSheet, Image} from 'react-native';
import {Title, Button, HelperText, TextInput, Card} from 'react-native-paper';
import AsyncStorage from '@react-native-async-storage/async-storage';

import AuthServices from './AuthServices';

import messaging from '@react-native-firebase/messaging';
import Colors from '../../constants/Colors';
import DefaultImage from '../../assets/logo_size.jpg';

const SignIn = props => {
  const {navigation} = props;

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  //const [role, setRole] = useState('');

  clearAsyncStorage = async () => {
    AsyncStorage.clear();
  };

  useEffect(() => {
    requestUserPermission();
    messaging()
      .getToken()
      .then(token => {
        //Servise token kayıt edilecek.
        console.log('token oldu mu acep ? == ' + token);
      });
  }, []);

  async function requestUserPermission() {
    const authStatus = await messaging().requestPermission();
    const enabled =
      authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
      authStatus === messaging.AuthorizationStatus.PROVISIONAL;

    if (enabled) {
      console.log('Authorization status:', authStatus);
    }
  }

  const onLogin = async () => {
    clearAsyncStorage();

    let login = {
      username: username,
      password: password,
    };

    const authServices = new AuthServices();
    authServices
      .login(login)
      .then(res => {
        AsyncStorage.setItem('token', res.token);
        getUserInformation(res.token);
      })
      .catch(error => {
        console.log('login err : ', error);
      });
  };

  const getUserInformation = async token => {
    const authServices = new AuthServices();
    authServices
      .getUserInformation(token)
      .then(res => {
        AsyncStorage.setItem('userRole', res.role.toString());
        AsyncStorage.setItem('userId', res.id.toString());

        goToNavigate(res.role);
      })
      .catch(error => {
        console.log('user err : ', error);
      });
  };

  const goToNavigate = async role => {
    if (role === 'ADMIN') {
      navigation.navigate('MainNavigation');
    } else {
      navigation.navigate('UserNavigation');
    }
  };

  const DEFAULT_IMAGE = Image.resolveAssetSource(DefaultImage).uri;

  return (
    <View style={styles.screen}>
      <View style={styles.authContainer}>
        <Card>
          <Card.Cover source={{uri: DEFAULT_IMAGE}} />
        </Card>
        <TextInput
          mode="outlined"
          label="Kullanıcı Adı"
          value={username}
          onChangeText={text => setUsername(text)}
        />
        <HelperText type="error" visible={username ? false : true}>
          Kullanıcı Adı Zorunludur!
        </HelperText>
        <TextInput
          mode="outlined"
          secureTextEntry={true}
          label="Şifre"
          value={password}
          onChangeText={text => setPassword(text)}
        />
        <HelperText type="error" visible={password ? false : true}>
          Şifre Zorunludur!
        </HelperText>
        <View style={styles.buttonContainer}>
          <Button
            title="Login"
            mode="contained"
            color={Colors.primary}
            onPress={() => {
              onLogin();
            }}>
            Giriş Yap
          </Button>
        </View>
        <View style={styles.buttonContainer}>
          <Button
            title="Switch to Sign Up"
            mode="outlined"
            color={Colors.accent}
            onPress={() => navigation.navigate('CreateUser')}>
            {' '}
            Kayıt Ol
          </Button>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    width: '100%',
  },
  gradient: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  authContainer: {
    width: '80%',
    alignSelf: 'center',
    marginTop: 50,
    padding: 20,
  },
  buttonContainer: {
    marginTop: 10,
  },
});

export default SignIn;

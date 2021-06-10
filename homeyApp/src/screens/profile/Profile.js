import AsyncStorage from '@react-native-async-storage/async-storage';
import React, {useEffect, useState} from 'react';
import {StyleSheet} from 'react-native';
import {Card, Title, Paragraph, Subheading, Divider, Button} from 'react-native-paper';

import messaging from '@react-native-firebase/messaging';
import ProfileServices from './ProfileServices';
import HomeServices from '../home/HomeServices';

import {View, Text} from 'react-native';

const Profile = props => {
  let emptyUser = {
    name: '',
    surname: '',
    email: '',
    phone: '',
    about: '',
    username: '',
  };

  const [userInfo, setUserInfo] = useState(emptyUser);

  useEffect(() => {
    requestUserPermission();
    messaging()
      .getToken()
      .then(token => {
        //Servise token kayıt edilecek.
        createSmartDevice(token);
      });
    getUserInformation();
  }, []);

  async function requestUserPermission(fcmToken) {
    const authStatus = await messaging().requestPermission();
    const enabled =
      authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
      authStatus === messaging.AuthorizationStatus.PROVISIONAL;

    if (enabled) {
      console.log('Authorization status:', authStatus);
    }
  }

  const createSmartDevice = async fcmToken => {
    const token = await AsyncStorage.getItem('token');

    let smartDevice = {
      fcmToken: fcmToken,
    };

    const homeServices = new HomeServices();
    homeServices
      .createSmartDevice(smartDevice, token)
      .then(res => {
        console.log('Smart Device Saved');
      })
      .catch(error => {
        console.log('get apart err : ', error);
      });
  };

  const getUserInformation = async () => {

    const token = await AsyncStorage.getItem('token');

    const profileServices = new ProfileServices();
    profileServices
      .getUserInformation(token)
      .then(res => {
        console.log(JSON.stringify(res));

        setUserInfo(res);
      })
      .catch(error => {
        console.log('user err : ', error);
      });
  };

  console.log('home sayfasındayız');
  return (
    <View style={styles.container}>
      <Title>Profil</Title>
      <Card>
        <Card.Content>
          <Title>Ad: <Subheading>{userInfo.name}</Subheading></Title>
          <Divider style={[styles.dividerSettings, {backgroundColor:"red"}]} />
          <Title>Soyad: <Subheading>{userInfo.surname}</Subheading></Title>
          <Divider style={[styles.dividerSettings, {backgroundColor:"orange"}]} />
          <Title>Kullanıcı Adı: <Subheading>{userInfo.username}</Subheading></Title>
          <Divider style={[styles.dividerSettings, {backgroundColor:"yellow"}]} />
          <Title>E-Mail: <Subheading>{userInfo.email}</Subheading></Title>
          <Divider style={[styles.dividerSettings, {backgroundColor:"green"}]} />
          <Title>Telefon: <Subheading>{userInfo.phone}</Subheading></Title>
          <Divider style={[styles.dividerSettings, {backgroundColor:"blue"}]} />
          <Title>Hakkımda: <Subheading>{userInfo.about}</Subheading></Title>
          <Divider style={[styles.dividerSettings, {backgroundColor:"purple"}]} />
          <Button>Bilgileri Güncelle</Button>
        </Card.Content>
      </Card>
    </View>
  );
};

const styles = StyleSheet.create({
  main: {
    flex: 1,
  },
  container: {
    width: '100%',
    alignSelf: 'center',
  },
  dividerSettings: {
    backgroundColor: 'black',
    marginTop: 10,
    marginBottom: 10,
  },
});

export default Profile;

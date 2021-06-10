import AsyncStorage from '@react-native-async-storage/async-storage';
import React, {useEffect, useState} from 'react';
import {StyleSheet} from 'react-native';
import { Card, Title, Paragraph} from 'react-native-paper';

import ApartServices from '../apart/ApartServices';
import messaging from '@react-native-firebase/messaging';
import HomeServices from './HomeServices';

import {
    View,
    Text
  } from 'react-native';

  const Home = props => {

    const [aparts, setAparts] = useState([]);

    useEffect (()=>{
        requestUserPermission();
        messaging()
            .getToken()
            .then(token => {
                //Servise token kayıt edilecek.
                createSmartDevice(token);
            });
        getAllApart();
      },[]);

      async function requestUserPermission(fcmToken) {
        const authStatus = await messaging().requestPermission();
        const enabled =
          authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
          authStatus === messaging.AuthorizationStatus.PROVISIONAL;
      
        if (enabled) {
          console.log('Authorization status:', authStatus);
        }
      }

      const createSmartDevice = async (fcmToken) => {    

        const token = await AsyncStorage.getItem('token');
    
        let smartDevice = {
            fcmToken:fcmToken
        };
        
        const homeServices = new HomeServices();
        homeServices.createSmartDevice(smartDevice,token).then(res => {
          
            console.log("Smart Device Saved");
    
        }).catch(error => {
            console.log("get apart err : ", error)
        });
      } 

      const getAllApart = async () => {    

        const userId = await AsyncStorage.getItem('userId');
        const token = await AsyncStorage.getItem('token');
    
        let user = {
            id:userId
        };
        
        const apartServices = new ApartServices();
        apartServices.getAllApart(user,token).then(res => {
          
            setAparts(res);
    
        }).catch(error => {
            console.log("get apart err : ", error)
        });
      } 

     
      console.log("home sayfasındayız");
      return(
        
          <View style={styles.container}>
              <Title>Ana Sayfa</Title>
              <Card>
                <Card.Content>
                    <Title>Apart Sayısı: {aparts.length}</Title>
                    <Paragraph></Paragraph>
                </Card.Content>
                <Card.Content>
                    <Title>Yurt Sayısı: 7</Title>
                    <Paragraph></Paragraph>
                </Card.Content>
                <Card.Content>
                    <Title>Apartta Kalan Kişi Sayısı: 152</Title>
                    <Paragraph></Paragraph>
                </Card.Content>
                <Card.Content>
                    <Title>Yurtta Kalan Kişi Sayısı: 486</Title>
                    <Paragraph></Paragraph>
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
        width:'100%',
        alignSelf:'center'
    }
});

export default Home;

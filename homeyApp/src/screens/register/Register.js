import React, { useState, useEffect } from 'react';
import {View, Text,StyleSheet, ScrollView} from 'react-native';
import { Button, Drawer, HelperText, TextInput, Snackbar, List } from 'react-native-paper';
import RegisterServices from './RegislerServices';
import AsyncStorage from '@react-native-async-storage/async-storage';


  const Register = props => {

    const {navigation} = props;

    const [control, setControl] = useState(true);
    const [uuid, setUuid] = useState('');
    const [houseNumber, setHouseNumber] = useState();
    const [roomNumber, setRoomNumber] = useState();
    const [snackbarVisible, setSnackbarVisible] = useState(false);


    const onDismissSnackBar = () => setSnackbarVisible(false);

    const registerApart = async () => {  
        
        console.log("agaaa");

        const token = await AsyncStorage.getItem('token');
    
        let apart = {
            uuid:uuid,
            houseNumber:houseNumber
        };
        
        const registerServices = new RegisterServices();
        registerServices.registerApart(apart,token).then(res => {
          
            if(res == "1") {
                setUuid('');
                setHouseNumber('');
    
                setSnackbarVisible(true);
                navigation.navigate('Home');
            }
    
        }).catch(error => {
            console.log("apart err : ", error)
        });
      }



      return(
        <View style={styles.main}>
        <Drawer.Section title="İşlem Seçiniz">
          <Drawer.Item
              label="Aparta kayıt ol!"
              active={control === true}
              onPress={() => setControl(true)}
          />
          <Drawer.Item
              label="Yurta kayıt ol!"
              active={control === false}
              onPress={() => setControl(false)}
          />
          </Drawer.Section>
          {control ? 
        <View style={styles.container}>
            <TextInput mode="outlined" label="Uuid" value={uuid} onChangeText={(text) => setUuid(text)} />
        <HelperText type="error" visible={uuid ? false : true}>
          Uuid girmek zorunludur!
        </HelperText>
        <TextInput mode="outlined" label="Daire Numarası" keyboardType="number-pad" value={houseNumber} onChangeText={(text) => setHouseNumber(text)} />
        <HelperText type="error" visible={houseNumber ? false : true}>
          Daire numarası girmek zorunludur!
        </HelperText>   
        <Button mode="outlined" onPress={() => registerApart()}>Aparta Kayıt Ol</Button>
        </View>
    : 
    <View style={styles.container}>
    <TextInput mode="outlined" label="Uuid" value={uuid} onChangeText={(text) => setUuid(text)} />
    <HelperText type="error" visible={uuid ? false : true}>
        Uuid girmek zorunludur!
    </HelperText>
    <TextInput mode="outlined" label="Oda Numarası" keyboardType="number-pad" value={roomNumber} onChangeText={(text) => setRoomNumber(text)} />
        <HelperText type="error" visible={roomNumber ? false : true}>
          Oda numarası girmek zorunludur!
        </HelperText>   
    <Button mode="outlined" onPress={() => registerApart()}>Yurta Kayıt Ol</Button>
    </View> }
    <Snackbar
        visible={snackbarVisible}
        onDismiss={onDismissSnackBar}
        action={{
          label: 'Kapat',
          onPress: () => {
            // Do something
          },
        }}>
        Apart'a kayıt olundu!
      </Snackbar>
          </View>
      );
  };

const styles = StyleSheet.create({ 
    main: {
        flex: 1,
    },
    container: {
        width:'90%',
        alignSelf:'center'
    }
});

export default Register;

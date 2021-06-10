import React, {useState, useEffect} from 'react';
import {
  Button,
  Drawer,
  HelperText,
  TextInput,
  Snackbar,
  List,
  Provider,
  Portal,
  Modal,
  Card,
  Title,
  Paragraph,
} from 'react-native-paper';

import ApartServices from './ApartServices';
import {View, Text, StyleSheet, ScrollView} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import {apart} from '../../services/api/paths';

const Apart = props => {
  let emptyDetails = {
    id: '',
    address: '',
    name: '',
    houseCount: '',
  };

  const [control, setControl] = useState(true);
  const [address, setAddress] = useState('');
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [houseCount, setHouseCount] = useState();
  const [snackbarVisible, setSnackbarVisible] = useState(false);
  const [snackbarSendCodeVisible, setSnackbarSendCodeVisible] = useState(false);
  const [snackbarCreateToolVisible, setSnackbarCreateToolVisible] = useState(
    false,
  );
  const [aparts, setAparts] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const [codeControl, setCodeControl] = useState(false);
  const [toolControl, setToolControl] = useState(false);
  const [toolName, setToolName] = useState(false);
  const [toolAbout, setToolAbout] = useState(false);

  const [details, setDetails] = useState(emptyDetails);

  const showModal = () => setModalVisible(true);
  const hideModal = () => setModalVisible(false);

  useEffect(() => {
    getAllApart();
  }, []);

  const addressError = () => {
    return !address.includes('@');
  };

  const setDetail = item => {
    console.log('DETAIL == ' + JSON.stringify(item));
    details.name = item.name;
    details.address = item.address;
    details.houseCount = item.houseCount;
    details.id = item.id;

    setModalVisible(true);
  };

  const onDismissSnackBar = () => setSnackbarVisible(false);
  const onDismissSnackBarSendCode = () => setSnackbarSendCodeVisible(false);
  const onDismissSnackBarCreateTool = () => setSnackbarCreateToolVisible(false);

  const createApart = async () => {
    const userId = await AsyncStorage.getItem('userId');
    const token = await AsyncStorage.getItem('token');

    let apart = {
      address: address,
      name: name,
      houseCount: houseCount,
      userDto: {
        id: userId,
      },
      status: 1,
    };

    const apartServices = new ApartServices();
    apartServices
      .createApart(apart, token)
      .then(res => {
        if (res == '1') {
          setAddress('');
          setHouseCount('');
          setName('');

          setSnackbarVisible(true);
        }
      })
      .catch(error => {
        console.log('apart err : ', error);
      });
  };

  const getAllApart = async () => {
    const userId = await AsyncStorage.getItem('userId');
    const token = await AsyncStorage.getItem('token');

    let user = {
      id: userId,
    };

    const apartServices = new ApartServices();
    apartServices
      .getAllApart(user, token)
      .then(res => {
        setAparts(res);
        console.log(aparts[0]);
      })
      .catch(error => {
        console.log('get apart err : ', error);
      });
  };

  const sendUuidForApart = async apartId => {
    console.log('apart ID ==== ' + apartId);

    const userId = await AsyncStorage.getItem('userId');
    const token = await AsyncStorage.getItem('token');

    let mail = {
      to: email,
      apartId: apartId,
    };

    const apartServices = new ApartServices();
    apartServices
      .sendUuidForApart(mail, token)
      .then(res => {
        if (res == '1') {
          setEmail('');
          setSnackbarSendCodeVisible(true);
        }
      })
      .catch(error => {
        console.log('apart err : ', error);
      });
  };

  const createToolForApart = async apartId => {
    console.log('apart ID ==== ' + apartId);

    const token = await AsyncStorage.getItem('token');

    let tool = {
      name: toolName,
      about: toolAbout,
      apartDto: {
        id: apartId,
      },
    };

    const apartServices = new ApartServices();
    apartServices
      .createTool(tool, token)
      .then(res => {
        if (res == '1') {
          setToolAbout('');
          setToolName('');
          setSnackbarCreateToolVisible(true);
        }
      })
      .catch(error => {
        console.log('Create Tool err : ', error);
      });
  };

  return (
    <View style={styles.main}>
      <Drawer.Section title="İşlem Seçiniz">
        <Drawer.Item
          label="Apart Ekle"
          active={control === true}
          onPress={() => setControl(true)}
        />
        <Drawer.Item
          label="Apart Listele"
          active={control === false}
          onPress={() => setControl(false)}
        />
      </Drawer.Section>

      {control ? (
        <View style={styles.container}>
          <TextInput
            mode="outlined"
            label="Adres"
            value={address}
            onChangeText={text => setAddress(text)}
          />
          <HelperText type="error" visible={addressError()}>
            Email address is invalid!
          </HelperText>
          <TextInput
            mode="outlined"
            label="İsim"
            value={name}
            onChangeText={text => setName(text)}
          />
          <HelperText type="error" visible={name ? false : true}>
            Ev Adı Zorunludur!
          </HelperText>
          <TextInput
            mode="outlined"
            label="Ev Sayısı"
            keyboardType="number-pad"
            value={houseCount}
            onChangeText={text => setHouseCount(text)}
          />
          <HelperText type="error" visible={houseCount ? false : true}>
            Ev Sayısı alanı Zorunludur!
          </HelperText>
          <Button mode="outlined" onPress={() => createApart()}>
            Apart Oluştur
          </Button>
        </View>
      ) : (
        <ScrollView>
          {aparts.map(apart => (
            <List.Item
              title={apart.name}
              description={apart.address}
              right={props => <Text>Ev Sayısı : {apart.houseCount}</Text>}
              left={props => <List.Icon {...props} icon="home" />}
              onPress={() => setDetail(apart)}
            />
          ))}
        </ScrollView>
      )}

      <Snackbar
        visible={snackbarVisible}
        onDismiss={onDismissSnackBar}
        action={{
          label: 'Kapat',
          onPress: () => {
            // Do something
          },
        }}>
        Apart oluşturuldu!
      </Snackbar>
      <Snackbar
        visible={snackbarSendCodeVisible}
        onDismiss={onDismissSnackBarSendCode}
        action={{
          label: 'Kapat',
          onPress: () => {
            // Do something
          },
        }}>
        Kod Gönderildi!
      </Snackbar>
      <Snackbar
        visible={snackbarCreateToolVisible}
        onDismiss={onDismissSnackBarCreateTool}
        action={{
          label: 'Kapat',
          onPress: () => {
            // Do something
          },
        }}>
        Eşya oluşturuldu!
      </Snackbar>
      <Provider>
        <Portal>
          <Modal
            visible={modalVisible}
            onDismiss={hideModal}
            //contentContainerStyle={containerStyle}
          >
            <Card>
              <Card.Content>
                <Title>{details.name}</Title>
                <Paragraph>{details.address}</Paragraph>
                <Paragraph>Ev Sayısı : {details.houseCount}</Paragraph>
                {codeControl ? (
                  <View style={styles.container}>
                    <TextInput
                      mode="outlined"
                      label="Adres"
                      value={email}
                      onChangeText={text => setEmail(text)}
                    />
                    <HelperText type="error" visible={email ? false : true}>
                      Email address is invalid!
                    </HelperText>
                    <View style={styles.modalButtonView}>
                      <Button
                        mode="outlined"
                        style={styles.modalButton}
                        onPress={() => sendUuidForApart(details.id)}>
                        Gönder
                      </Button>
                      <Button
                        mode="contained"
                        style={styles.modalButton}
                        onPress={() => setCodeControl(false)}>
                        Gönderme
                      </Button>
                    </View>
                  </View>
                ) : (
                  <Button onPress={() => setCodeControl(true)}>
                    Kod Gönder
                  </Button>
                )}
                {toolControl ? (
                  <View style={styles.container}>
                    <TextInput
                      mode="outlined"
                      label="Eşya Adı"
                      value={toolName}
                      onChangeText={text => setToolName(text)}
                    />
                    <HelperText type="error" visible={toolName ? false : true}>
                      Eşya Adı Alanını Doldurmak Zorunludur!
                    </HelperText>
                    <TextInput
                      mode="outlined"
                      label="Eşya Hakkında"
                      value={toolAbout}
                      onChangeText={text => setToolAbout(text)}
                    />
                    <HelperText type="error" visible={toolAbout ? false : true}>
                      Eşya Hakkında Alanını Doldurmak Zorunludur!
                    </HelperText>
                    <View style={styles.modalButtonView}>
                      <Button
                        mode="outlined"
                        style={styles.modalButton}
                        onPress={() => createToolForApart(details.id)}>
                        Oluştur
                      </Button>
                      <Button
                        mode="contained"
                        style={styles.modalButton}
                        onPress={() => setToolControl(false)}>
                        Oluşturma
                      </Button>
                    </View>
                  </View>
                ) : (
                  <Button onPress={() => setToolControl(true)}>
                    Eşya Oluştur
                  </Button>
                )}
              </Card.Content>
            </Card>
          </Modal>
        </Portal>
      </Provider>
    </View>
  );
};

const styles = StyleSheet.create({
  main: {
    flex: 1,
  },
  container: {
    width: '90%',
    alignSelf: 'center',
  },
  modalButton: {
    width: '40%',
  },
  modalButtonView: {
    flexDirection: 'row',
    justifyContent: 'space-evenly',
  },
});

export default Apart;

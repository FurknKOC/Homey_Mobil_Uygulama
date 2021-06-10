import React, {useState, useEffect} from 'react';

import {View, Text, ScrollView, StyleSheet} from 'react-native';
import DateTimePickerModal from 'react-native-modal-datetime-picker';
import moment from 'moment';
import {
  Button,
  List,
  Provider,
  Portal,
  Modal,
  Card,
  Title,
  Paragraph,
  RadioButton,
} from 'react-native-paper';
import ReservationServices from './ReservationServices';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { createUser } from '../../services/api/paths';

const Reservation = props => {
  const [date, setDate] = useState();
  const [showDate, setShowDate] = useState(false);
  const [tools, setTools] = useState([]);
  const [toolId, setToolId] = useState('');
  const [timeOptions, setTimeOptions] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const [value, setValue] = useState('first');

  useEffect(() => {
    getTools();
  }, []);

  const showModal = () => setModalVisible(true);
  const hideModal = () => setModalVisible(false);

  const getTools = async () => {
    const token = await AsyncStorage.getItem('token');

    const reservationServices = new ReservationServices();
    reservationServices
      .getTools(token)
      .then(res => {
        setTools(res);
        console.log(JSON.stringify(res));
      })
      .catch(error => {
        console.log('get apart err : ', error);
      });
  };

  const setOptions = id => {
    setToolId(id);
    setShowDate(true);
  };

  const getReservations = async (date) => {

    console.log("tarih == " + moment(date).format('yyyy-MM-DD HH:mm') );

    const token = await AsyncStorage.getItem('token');
    console.log('tool ID = ' + toolId);

    let reservation = {
      dateTime: moment(date).format('yyyy-MM-DD HH:mm'),
      toolDto: {
        id: toolId,
      },
    };

    const reservationServices = new ReservationServices();
    reservationServices
      .getReservations(reservation, token)
      .then(res => {
        setTimeOptions(res);
      })
      .catch(error => {
        console.log('get reservation err : ', error);
      });

    setModalVisible(true);
  };

  const createReservation = async () => {
    const token = await AsyncStorage.getItem('token');

    let reservation = {
      dateTime: moment(date).format('yyyy-MM-DD HH:mm'),
      toolDto: {
        id: toolId,
      },
      reservationTime:value,
      status:1
    };

    const reservationServices = new ReservationServices();
    reservationServices
      .createReservation(reservation, token)
      .then(res => {
        console.log(JSON.stringify(res));
      })
      .catch(error => {
        console.log('create  reservation err : ', error);
      });

    setModalVisible(true);
  };

  return (
    <View style={styles.main}>
      <ScrollView>
        {tools.map(tool => (
          <List.Item
            title={tool.name}
            description={tool.about}
            //right={props => <Text>Ev Sayısı : {apart.houseCount}</Text>}
            left={props => <List.Icon {...props} icon="home" />}
            onPress={() => setOptions(tool.id)}
          />
        ))}
      </ScrollView>
      <DateTimePickerModal
        isVisible={showDate}
        mode="date"
        locale="tr_TR"
        confirmTextIOS="Kaydet"
        cancelTextIOS="Vazgeç"
        headerTextIOS="Başlangıç Zamanı"
        minimumDate={new Date()}
        onConfirm={date => {
          setDate(date);
          setShowDate(false);
          setTimeOptions([]);
          getReservations(date);
        }}
        onCancel={() => {
          setShowDate(false);
        }}
      />
      <Provider>
        <Portal>
          <Modal
            visible={modalVisible}
            onDismiss={hideModal}
            //contentContainerStyle={containerStyle}
          >
            <Card>
              <Card.Content>
                <Title>Saat Aralığı Seç</Title>
                <RadioButton.Group
                  onValueChange={value => setValue(value)}
                  value={value}>
                  <RadioButton.Item disabled={timeOptions.includes("TIME1") ? true : false} label="09:00 - 12:00" value="TIME1" onPress={()=>setValue("TIME1")} />
                  <RadioButton.Item disabled={timeOptions.includes("TIME2") ? true : false} label="12:00 - 15:00" value="TIME2" onPress={()=>setValue("TIME2")} />
                  <RadioButton.Item disabled={timeOptions.includes("TIME3") ? true : false} label="15:00 - 18:00" value="TIME3" onPress={()=>setValue("TIME3")} />
                  <RadioButton.Item disabled={timeOptions.includes("TIME4") ? true : false} label="18:00 - 21:00" value="TIME4" onPress={()=>setValue("TIME4")} />
                  <RadioButton.Item disabled={timeOptions.includes("TIME5") ? true : false} label="21:00 - 24:00" value="TIME5" onPress={()=>setValue("TIME5")} />
                </RadioButton.Group>
                <Button mode="outlined" onPress={()=>createReservation()}>Rezervasyon Oluştur</Button>
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
});

export default Reservation;

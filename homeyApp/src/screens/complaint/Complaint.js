import React, {useState, useEffect} from 'react';
import {View, Text, StyleSheet, ScrollView} from 'react-native';
import {
  Button,
  Drawer,
  HelperText,
  TextInput,
  Snackbar,
  List,
} from 'react-native-paper';
import AsyncStorage from '@react-native-async-storage/async-storage';
import ComplaintServices from './ComplaintServices';

const Complaint = props => {
  const [control, setControl] = useState(true);
  const [complaint, setComplaint] = useState('');
  const [title, setTitle] = useState('');
  const [snackbarVisible, setSnackbarVisible] = useState(false);
  const [complaints, setComplaints] = useState([]);

  useEffect(() => {
    getAllComplaint();
  }, []);

  const onDismissSnackBar = () => setSnackbarVisible(false);

  const createComplaint = async () => {
    const token = await AsyncStorage.getItem('token');

    let comp = {
      complaint: complaint,
      title: title
    };

    const complaintServices = new ComplaintServices();
    complaintServices
      .createComplaint(comp, token)
      .then(res => {
        if (res == '1') {
          setComplaint('');

          setSnackbarVisible(true);
          setControl(false);
        }
      })
      .catch(error => {
        console.log('apart err : ', error);
      });
  };

  const getAllComplaint = async () => {
    const token = await AsyncStorage.getItem('token');

    const complaintServices = new ComplaintServices();
    complaintServices
      .getAllComplaint(token)
      .then(res => {
        setComplaints(res);
        console.log(complaints[0]);
      })
      .catch(error => {
        console.log('get complaint err : ', error);
      });
  };

  return (
    <View style={styles.main}>
      <Drawer.Section title="İşlem Seçiniz">
        <Drawer.Item
          label="Şikayet Oluştur!"
          active={control === true}
          onPress={() => setControl(true)}
        />
        <Drawer.Item
          label="Oluşturulan Şikayetleri Gör"
          active={control === false}
          onPress={() => setControl(false)}
        />
      </Drawer.Section>
      {control ? (
        <View style={styles.container}>
           <TextInput mode="outlined" label="Başlık" value={title} onChangeText={(text) => setTitle(text)} />
        <HelperText type="error" visible={title ? false : true}>
          Başlık girmek zorunludur!
        </HelperText>
          <TextInput
            mode="flat"
            style={styles.complaintInput}
            multiline={true}
            numberOfLines={10}
            label="Şikayetinizi giriniz"
            value={complaint}
            onChangeText={text => setComplaint(text)}
          />
          <HelperText type="error" visible={complaint ? false : true}>
            Şikayet girmek zorunludur!
          </HelperText>
          <Button mode="outlined" onPress={() => createComplaint()}>
            Şikayet Oluştur
          </Button>
        </View>
      ) : (
        <ScrollView>
          {complaints.map(complaint => (
            <List.Item
              title={complaint.title}
              description={complaint.complaint}
              right={props => <Button mode="text">Detay</Button>}
              left={props => <List.Icon {...props} icon="alert-octagon" color="orange" />}
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
        Şikayet Oluşturuldu!
      </Snackbar>
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
  complaintInput: {
    height: 200,
    textAlignVertical: 'top',
    paddingTop: 0,
    paddingBottom: 0,
    justifyContent: 'flex-start',
  },
});

export default Complaint;

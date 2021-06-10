import React, {useState, useEffect} from 'react';
import {View, StyleSheet, ScrollView} from 'react-native';
import {
  Button,
  Modal,
  Portal,
  Text,
  Paragraph,
  Title,
  Card,
  Provider,
  Snackbar,
  List,
  Divider,
  Drawer,
} from 'react-native-paper';
import AsyncStorage from '@react-native-async-storage/async-storage';
import ComplaintServices from './ComplaintServices';

const AdminComplaint = props => {
  let emptyDetails = {
    name: '',
    complaint: '',
    email: '',
    phone: '',
    title: '',
    houseNumber: '',
    apartName: '',
    id: '',
    compliantType:''
  };

  const [snackbarVisible, setSnackbarVisible] = useState(false);
  const [complaints, setComplaints] = useState([]);
  const [visible, setVisible] = useState(false);
  const [details, setDetails] = useState(emptyDetails);
  const [control, setControl] = useState(true);

  const setDetail = item => {
    console.log('DETAIL == ' + JSON.stringify(item));
    details.name = item.userDto.name;
    details.complaint = item.complaint;
    details.title = item.title;
    details.email = item.userDto.email;
    details.phone = item.userDto.phone;
    details.houseNumber = item.houseNumber;
    details.apartName = item.apartDto.name;
    details.id = item.id;
    details.complaintType = item.complaintType;

    setVisible(true);
  };
  const showModal = () => setVisible(true);
  const hideModal = () => setVisible(false);
  const containerStyle = {backgroundColor: 'white', padding: 20};

  useEffect(() => {
    getAllComplaint();
  }, []);

  const onDismissSnackBar = () => setSnackbarVisible(false);

  const getAllComplaint = async () => {
    const token = await AsyncStorage.getItem('token');

    const complaintServices = new ComplaintServices();
    complaintServices
      .getAllComplaintManager(token)
      .then(res => {
        setComplaints(res);
        console.log(complaints[0]);
      })
      .catch(error => {
        console.log('get complaint err : ', error);
      });
  };

  const setComplaintTypeWorkingOn = async complaintId => {
    const token = await AsyncStorage.getItem('token');

    let comp = {
      id: complaintId,
    };

    const complaintServices = new ComplaintServices();
    complaintServices
      .setComplaintTypeWorkingOn(comp, token)
      .then(res => {
        if (res == '1') {
          setSnackbarVisible(true);
          setVisible(false);
        }
      })
      .catch(error => {
        console.log('apart err : ', error);
      });
  };

  const setComplaintTypeSolved = async complaintId => {
    const token = await AsyncStorage.getItem('token');

    let comp = {
      id: complaintId,
    };

    const complaintServices = new ComplaintServices();
    complaintServices
      .setComplaintTypeSolved(comp, token)
      .then(res => {
        if (res == '1') {
          setSnackbarVisible(true);
          setVisible(false);
        }
      })
      .catch(error => {
        console.log('apart err : ', error);
      });
  };

  return (
    <View style={styles.main}>
      <Drawer.Section title="İşlem Seçiniz">
        <Drawer.Item
          label="Şikayetleri Gör"
          active={control === true}
          onPress={() => setControl(true)}
        />
        <Drawer.Item
          label="Çözülmüş Şikayetleri Gör"
          active={control === false}
          onPress={() => setControl(false)}
        />
      </Drawer.Section>
      {control ? (
        <ScrollView>
          {complaints.map(complaint =>
            complaint.complaintType === 'ENTRY' ? (
              <List.Item
                title={complaint.userDto.name}
                description={complaint.title}
                right={props => (
                  <Button mode="text" onPress={() => setDetail(complaint)}>
                    Detay
                  </Button>
                )}
                left={props => (
                  <List.Icon {...props} icon="alert-octagon" color="red" />
                )}
              />
            ) : complaint.complaintType === 'WORKING_ON' && (
              <List.Item
                title={complaint.userDto.name}
                description={complaint.title}
                right={props => (
                  <Button mode="text" onPress={() => setDetail(complaint)}>
                    Detay
                  </Button>
                )}
                left={props => (
                  <List.Icon {...props} icon="alert-octagon" color="orange" />
                )}
              />
            ),
          )}
        </ScrollView>
      ) : (
        <ScrollView>
          {complaints.map(
            complaint =>
              complaint.complaintType === 'SOLVED' && (
                <List.Item
                  title={complaint.userDto.name}
                  description={complaint.title}
                  right={props => (
                    <Button mode="text" onPress={() => setDetail(complaint)}>
                      Detay
                    </Button>
                  )}
                  left={props => (
                    <List.Icon {...props} icon="alert-octagon" color="green" />
                  )}
                />
              ),
          )}
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
        İşlem Başarılı!
      </Snackbar>
      <Provider>
        <Portal>
          <Modal
            visible={visible}
            onDismiss={hideModal}
            //contentContainerStyle={containerStyle}
          >
            <Card>
              <Card.Content>
                <Title>
                  {details.name} - {details.title}
                </Title>
                <Divider style={styles.divierSettings} />
                <Paragraph>
                  <Text style={styles.titleFont}>Şikayet : </Text>
                  {details.complaint}
                </Paragraph>
                <Divider style={styles.divierSettings} />
                <Paragraph>
                  <Text style={styles.titleFont}>Apart Adı : </Text>
                  {details.apartName}
                </Paragraph>
                <Paragraph>
                  <Text style={styles.titleFont}>Daire Numarası : </Text>
                  {details.houseNumber}
                </Paragraph>
                <Divider style={styles.divierSettings} />
                <Paragraph>Email : {details.email}</Paragraph>
                <Paragraph>Telefon : {details.phone}</Paragraph>
                {details.complaintType === "ENTRY" ? <Button
                  mode="outlined"
                  onPress={() => setComplaintTypeWorkingOn(details.id)}>
                  İşleme Al
                </Button> :
                 details.complaintType === "WORKING_ON" ? <Button
                  mode="outlined"
                  onPress={() => setComplaintTypeSolved(details.id)}>
                  Şikayeti Kapat
                </Button> : null }
                
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
  complaintInput: {
    height: 200,
    textAlignVertical: 'top',
    paddingTop: 0,
    paddingBottom: 0,
    justifyContent: 'flex-start',
  },
  divierSettings: {
    backgroundColor: 'black',
    marginTop: 10,
    marginBottom: 10,
  },
  titleFont: {
    fontWeight: 'bold',
  },
});

export default AdminComplaint;

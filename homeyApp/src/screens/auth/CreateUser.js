import React, {useState, useEffect} from 'react';
import {
  Button,
  HelperText,
  TextInput,
  Snackbar,
  List,
  Title,
} from 'react-native-paper';

import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  KeyboardAvoidingView,
} from 'react-native';

import AuthServices from './AuthServices';

const CreateUser = props => {
  const {navigation} = props;

  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [about, setAbout] = useState('');
  const [email, setEmail] = useState();
  const [phone, setPhone] = useState();
  const [snackbarVisible, setSnackbarVisible] = useState(false);

  const onDismissSnackBar = () => setSnackbarVisible(false);

  const createUser = async () => {
    let user = {
      name: name,
      surname: surname,
      username: username,
      password: password,
      about: about,
      email: email,
      phone: phone,
      role: 'USER',
      status: 1,
    };

    const authServices = new AuthServices();
    authServices
      .createUser(user)
      .then(res => {
        console.log('kayıt dönüş değeri = ' + res);
        setSnackbarVisible(true);
        navigation.navigate('SingIn');
      })
      .catch(error => {
        console.log('login err : ', error);
      });
  };

  return (
    <View style={styles.main}>
      <ScrollView>
        <KeyboardAvoidingView
          behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
          style={styles.container}
          keyboardVerticalOffset={-250}>
          <Title>KAYIT OL</Title>

          <TextInput
            mode="outlined"
            label="Ad"
            value={name}
            onChangeText={text => setName(text)}
          />
          <HelperText type="error" visible={name ? false : true}>
            Ad Zorunludur!
          </HelperText>
          <TextInput
            mode="outlined"
            label="Soyad"
            value={surname}
            onChangeText={text => setSurname(text)}
          />
          <HelperText type="error" visible={surname ? false : true}>
            Soyad Zorunludur!
          </HelperText>
          <TextInput
            mode="outlined"
            label="Kullanıcı adı"
            value={username}
            onChangeText={text => setUsername(text)}
          />
          <HelperText type="error" visible={username ? false : true}>
            Kullanıcı Adı Zorunludur!
          </HelperText>
          <TextInput
            mode="outlined"
            label="Şifre"
            secureTextEntry={true}
            value={password}
            onChangeText={text => setPassword(text)}
          />
          <HelperText type="error" visible={username ? false : true}>
            Şifre Zorunludur!
          </HelperText>
          <TextInput
            mode="outlined"
            label="Hakkında"
            value={about}
            onChangeText={text => setAbout(text)}
          />
          <HelperText type="error" visible={about ? false : true}>
            Hakkında Zorunludur!
          </HelperText>
          <TextInput
            mode="outlined"
            label="Email"
            value={email}
            onChangeText={text => setEmail(text)}
          />
          <HelperText type="error" visible={email ? false : true}>
            Email Zorunludur!
          </HelperText>
          <TextInput
            mode="outlined"
            label="Telefon Numarası"
            value={phone}
            onChangeText={text => setPhone(text)}
          />
          <HelperText type="error" visible={phone ? false : true}>
            Telefon Numarası Zorunludur!
          </HelperText>
          <Button
            title="Login"
            style={styles.button}
            mode="contained"
            onPress={() => createUser()}>
            Kayıt Ol
          </Button>
        </KeyboardAvoidingView>
      </ScrollView>
      <Snackbar
        visible={snackbarVisible}
        onDismiss={onDismissSnackBar}
        action={{
          label: 'Kapat',
          onPress: () => {
            // Do something
          },
        }}>
        Kayıt Olundu!
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
  button: {
    marginTop: 50,
  },
});

export default CreateUser;

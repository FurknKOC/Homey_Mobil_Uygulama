import * as React from 'react';
import { BottomNavigation, Text } from 'react-native-paper';
import { Provider as PaperProvider } from 'react-native-paper';

import Complaint from '../screens/complaint/Complaint';
import Register from '../screens/register/Register';
import Reservation from '../screens/reservation/Reservation';
import Profile from '../screens/profile/Profile';


const ComplaintRoute = () => <Complaint />;

const RegisterRoute = () => <Register />;

const ReservationRoute = () => <Reservation />

const ProfileRoute = () => <Profile />


const UserNavigation = ({navigation}) => {
  const [index, setIndex] = React.useState(0);
  const [routes] = React.useState([
    { key: 'profile', title: 'Profil', icon: 'account', color: '#3F51B5' },
    { key: 'register', title: 'Kayıt', icon: 'archive', color: '#009688' },
    { key: 'complaint', title: 'Şikayet', icon: 'account-alert', color: '#795548' },
    { key: 'reservation', title: 'Rezervasyon', icon: 'washing-machine', color: '#8B008B' },
  ]);

  const renderScene = BottomNavigation.SceneMap({
    profile: ProfileRoute,
    register: RegisterRoute,
    complaint: ComplaintRoute,
    reservation: ReservationRoute,
  });

  return (
    <PaperProvider>
    <BottomNavigation
      navigationState={{ index, routes }}
      onIndexChange={setIndex}
      renderScene={renderScene}
    />
    </PaperProvider>
    
  );
};

export default UserNavigation;
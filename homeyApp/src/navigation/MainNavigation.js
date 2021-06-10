import * as React from 'react';
import { BottomNavigation, Text } from 'react-native-paper';
import { Provider as PaperProvider } from 'react-native-paper';

import Dormitory from '../screens/dormitory/Dormitory';
import Apart from '../screens/apart/Apart';
import Home from '../screens/home/Home';
import AdminComplaint from '../screens/complaint/AdminComplaint';


const DormitoryRoute = () => <Dormitory />;

const HomeRoute = () => <Home />;

const ApartRoute = () => <Apart />;

const AdminComplaintRoute = () => <AdminComplaint />;



const MainNavigation = ({navigation}) => {
  const [index, setIndex] = React.useState(0);
  const [routes] = React.useState([
    { key: 'home', title: 'Ana Sayfa', icon: 'home', color: '#3F51B5' },
    { key: 'apart', title: 'Apart', icon: 'home-city', color: '#009688' },
    { key: 'dormitory', title: 'Dormitory', icon: 'office-building', color: '#795548' },
    { key: 'adminComplaint', title: 'Şikayetler', icon: 'animation', color: '#8B008B' },
  ]);

  const renderScene = BottomNavigation.SceneMap({
    home: HomeRoute,
    apart: ApartRoute,
    dormitory: DormitoryRoute,
    adminComplaint: AdminComplaintRoute
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

export default MainNavigation;
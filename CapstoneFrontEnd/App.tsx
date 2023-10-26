import React, { useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import UserPage from './src/components/UserPage';
import Home from './src/components/Home';
import Trip from './src/components/Trip';

const Stack = createStackNavigator();

function App() {
  const [user, setUser] = useState({});

  console.log(user)
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home">
          {props => <Home {...props} onLogin={setUser} />}
        </Stack.Screen>
        <Stack.Screen name="UserPage">
  {props => <UserPage {...props} user={user}/>}
</Stack.Screen>
        {/* <Stack.Screen name="Trip" component={Trip} /> */}
      </Stack.Navigator>
    </NavigationContainer>
  );
}
export default App;

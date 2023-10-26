import { useState, useRef, useEffect } from 'react';
import { View, Text, StyleSheet, TextInput, Button, TouchableOpacity, Image} from 'react-native';
import LottieView from 'lottie-react-native';

const BASE_URL = "http://localhost:8080/api/";

function Home({ navigation, onLogin }) {
  const [loginUsername, setLoginUsername] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const [registrationUsername, setRegistrationUsername] = useState('');
  const [registrationPassword, setRegistrationPassword] = useState('');
  const [error, setError] = useState(null);

  const handleLogin = async () => {
    if (loginUsername && loginPassword) {
      try {
        const response = await fetch(BASE_URL + 'auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ username: loginUsername, password: loginPassword }),
        });

        if (response.status === 200) {
          const userData = await response.json();
          onLogin(userData);
          navigation.navigate('UserPage', { userData });
        } else if (response.status === 401) { // Unauthorized (not registered)
          setError('User not registered. Please register first.');
        } else {
          setError('Login failed');
        }
      } catch (error) {
        console.error('API request failed', error);
        setError('API request failed');
      }

      setLoginUsername('');
      setLoginPassword('');
    }
  };

  const handleRegistration = async () => {
    if (registrationUsername && registrationPassword) {
      try {
        const response = await fetch(BASE_URL + 'auth/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ username: registrationUsername, password: registrationPassword }),
        });

        if (response.status === 201) {
          setError('Registration successful');
          setRegistrationUsername('');
          setRegistrationPassword('');
        } else if (response.status === 409) {
          setError('Username already exists.');
        } else {
          setError('Registration failed');
        }
      } catch (error) {
        console.error('API request failed', error);
        setError('API request failed');
      }
    }
  };

  const handleRefresh = () => {
    setLoginUsername('');
    setLoginPassword('');
    setRegistrationUsername('');
    setRegistrationPassword('');
    setError(null);
  };

  const animationRef = useRef(null);
  useEffect(() => {
    animationRef.current?.play();
  }, []);

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Eco-Tracks</Text>
        <TouchableOpacity onPress={handleRefresh}>
          <Text style={styles.refreshButton}>Refresh</Text>
        </TouchableOpacity>
      </View>

      <View style={styles.contentContainer}>
        <View style={styles.loginRegistrationContainer}>
          <View style={styles.loginBox}>
            <Text style={styles.sectionTitle}>Login</Text>
            <TextInput
              style={styles.input}
              onChangeText={(text) => setLoginUsername(text)}
              value={loginUsername}
              placeholder="Username"
              placeholderTextColor="#c9b00f"
            />

            <TextInput
              style={styles.input}
              onChangeText={(text) => setLoginPassword(text)}
              value={loginPassword}
              placeholder="Password"
              secureTextEntry={true}
              placeholderTextColor="#c9b00f"
            />

            <Button title="Log in" onPress={handleLogin} color="#c9b00f" />
            <Text style={styles.errorText}>{error}</Text>
          </View>

          <View style={styles.registrationBox}>
            <Text style={styles.sectionTitle}>Registration</Text>
            <TextInput
              style={styles.input}
              onChangeText={(text) => setRegistrationUsername(text)}
              value={registrationUsername}
              placeholder="New Username"
              placeholderTextColor="#c9b00f"
            />

            <TextInput
              style={styles.input}
              onChangeText={(text) => setRegistrationPassword(text)}
              value={registrationPassword}
              placeholder="New Password"
              secureTextEntry={true}
              placeholderTextColor="#c9b00f"
            />

            <Button title="Register" onPress={handleRegistration} color="#c9b00f" />
            <Text style={styles.errorText}>{error}</Text>
          </View>
        </View>
      </View>

      <Image
        source={require('./animations/camper.jpg')}
        style={styles.image}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'center',
    backgroundColor: "#057d95",
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
    marginTop: 20,
  },
  title: {
    fontSize: 30,
    fontWeight: 'bold',
    color: "#c9b00f",
    marginTop: 20,
    alignSelf: 'center',
  },
  refreshButton: {
    fontSize: 20,
    color: '#c9b00f',
  },
  contentContainer: {
    flex: 1,
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  loginRegistrationContainer: {
    flexDirection: 'row', // Place login and registration side by side
  },
  loginBox: {
    borderWidth: 2,
    borderColor: '#c9b00f',
    borderRadius: 10,
    padding: 10,
    marginBottom: 20,
    width: 180,
    height: 250,
    marginRight: 20,
  },
  registrationBox: {
    borderWidth: 2,
    borderColor: '#c9b00f',
    borderRadius: 10,
    padding: 10,
    marginBottom: 20,
    width: 180,
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#c9b00f',
    marginBottom: 10,
  },
  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
    color: '#c9b00f',
  },
  errorText: {
    color: 'red',
    textAlign: 'center',
    marginTop: 10,
  },
  image: {
    flex: 1,
    resizeMode: 'cover',
    justifyContent: 'flex-end',
    width: '100%',
    height: 100,
    
  },
});

export default Home;

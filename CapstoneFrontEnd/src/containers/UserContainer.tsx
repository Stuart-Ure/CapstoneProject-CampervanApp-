// import React, { useState } from "react";
// import { NavigationContainer } from "@react-navigation/native";
// import { createStackNavigator } from "@react-navigation/stack";
// import { View, Text, TextInput, StyleSheet, Button } from "react-native";
// import { useNavigation } from "@react-navigation/native";
// import UserPage from "../components/UserPage";

// const UserContainer = () => {
//   const [username, setUsername] = useState("");
//   const [password, setPassword] = useState("");
//   const [error, setError] = useState<string | null>(null); // Specify the type for error

//   const navigation = useNavigation();

//   const BASE_URL = "http://localhost:8080/api/users/";

//   const handleLogin = async () => {
//     if (!username || !password) {
//       setError("Username and password are required");
//       return;
//     }

//     const requestBody = {
//       username: username,
//       password: password,
//     };

//     try {
//         const response = await fetch(BASE_URL + "login", {

//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(requestBody),
//       });

//       if (response.status === 200) {
//         // Login was successful, navigate to "UserPage" here.
//         // navigation.navigate("UserPage");
//       } else {
//         // Login failed; handle the error
//         setError("Login failed");
//       }
//     } catch (error) {
//       console.error("Login failed", error);
//       setError("Login failed");
//     }
//   };
//   const styles = StyleSheet.create({
//     container: {
//       flex: 1,
//       backgroundColor: "#fff",
//       alignItems: "center",
//       justifyContent: "center",
//     },
//     header: {
//       marginBottom: 20,
//     },
//     title: {
//       fontSize: 24,
//       fontWeight: "bold",
//       marginBottom: 20,
//     },
//     input: {
//       borderWidth: 1,
//       borderColor: "#ccc",
//       padding: 8,
//       margin: 10,
//       width: 200,
//     },
//     errorText: {
//       color: "red",
//     },
//   });

//   return (
//     <View style={styles.container}>
//       <Text style={styles.title}>Eco-Tracks</Text>
//       <UserLoginComponent
//         username={username}
//         password={password}
//         setUsername={setUsername}
//         setPassword={setPassword}
//         onLogin={handleLogin}
//         error={error}
//       />
//     </View>
//    );
   
// };

// const UserLoginComponent = ({
//   username,
//   password,
//   setUsername,
//   setPassword,
//   onLogin,
//   error,
// }: {
//   username: string;
//   password: string;
//   setUsername: (text: string) => void;
//   setPassword: (text: string) => void;
//   onLogin: () => void;
//   error: string | null;
// }) => {
//   return (
//     <View>
//       <TextInput
//         onChangeText={setUsername}
//         value={username}
//         placeholder="Username"
//       />
//       <TextInput
//         onChangeText={setPassword}
//         value={password}
//         placeholder="Password"
//         secureTextEntry={true}
//       />
//       <Button title="Log in" onPress={onLogin} />
//       {error && <Text>{error}</Text>}
//     </View>
//   );
// };

// const Stack = createStackNavigator();

// const App = () => {
//   return (
//     <NavigationContainer>
//       <Stack.Navigator>
//         <Stack.Screen name="UserContainer" component={UserContainer} />
//         <Stack.Screen name="UserPage" component={UserPage} />
//       </Stack.Navigator>
//     </NavigationContainer>
//   );
// };

// export default App;

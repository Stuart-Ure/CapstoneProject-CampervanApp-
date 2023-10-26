// import React, { useState, useEffect } from 'react';
// import { View, Text, StyleSheet, TouchableOpacity, TextInput, Button, ScrollView } from 'react-native';
// import LocationSearch from './LocationSearch';
// import apiConfig from '../../utils/api-config';
// import SustainabilityActions from './SustainabilityActions';

// const UserPage = ({ destinations = [], user, navigation }) => {
//   const [isSearching, setIsSearching] = useState(false);
//   const [selectedLocation, setSelectedLocation] = useState(null);
//   const [tripLocations, setTripLocations] = useState([]);
//   const [tripName, setTripName] = useState("");
//   const [activeTrip, setActiveTrip] = useState(null);
//   const [sustainabilityPoints, setSustainabilityPoints] = useState(0);
//   const [userTrips, setUserTrips] = useState([]);

//   const startSearching = () => {
//     setIsSearching(true);
//   };

//   const stopSearching = () => {
//     setIsSearching(false);
//   };

//   const handleLocationSelect = (locationDetails) => {
//     console.log('Selected location:', locationDetails);
//     setSelectedLocation(locationDetails);
//     stopSearching();
//   };

//   const createNewTrip = async (name) => {
//     try {
//       const response = await fetch('http://localhost:8080/api/routes/', {
//         method: 'POST',
//         headers: apiConfig.headers,
//         body: JSON.stringify({ name: name }),
//       });
//       if (response.status === 201) {
//         const trip = await response.json();
//         setActiveTrip(trip);
//         // Clear the trip name input field
//         setTripName("");
//       } else {
//         // Handle errors
//         console.error('Error creating trip:', response.status);
//       }
//     } catch (error) {
//       // Handle network errors
//       console.error('Network error:', error);
//     }
//   };

//   const handleAddToTrip = () => {
//     if (selectedLocation && activeTrip) {
      
//       const destinationDetails = {
//         name: selectedLocation.name,
//         description: selectedLocation.description,
//         route : {id: activeTrip.id}
//       };

//       addDestinationToTrip(destinationDetails);
//     } else {
//       console.log('Selected location or activeTrip is missing');
//     }
//   };

//   const addDestinationToTrip = async (destinationDetails) => {
//     // const id = destinationDetails.route.id;
   
   
//     try {
  
//       const response = await fetch(`http://localhost:8080/api/destinations/destination`, {
//         method: 'POST',
//         headers: apiConfig.headers,
//         body: JSON.stringify(destinationDetails)
        
//       });

//       if (response.status === 200) {
//         const destination = await response.json();
//         console.log('Before adding to tripLocations:', tripLocations);

//         setTripLocations([...tripLocations, destinationDetails]); 

//         console.log('After adding to tripLocations:', tripLocations);


//         setSelectedLocation(null); // Clear selectedLocation
//         console.log('Added destination:', destination);
//       } else {
//         console.error('Error adding destination:', response.status);
//       }
//     } catch (error) {
//       console.error('Network error:', error);
//     }
//   };

//   useEffect(() => {
//     const fetchUserTripsAndDestinations = async () => {
//       if (user) {
//         try {
//           const response = await fetch('http://localhost:8080/api/routes/', {
//             method: 'GET',
//             headers: apiConfig.headers,
//           });

//           if (response.status === 200) {
//             const userTrips = await response.json();

//             if (userTrips.length > 0) {
//               setActiveTrip(userTrips[0]);
//               console.log('Active Trip:', userTrips[0]);
//               const tripId = userTrips[0].id;

//               const destinationsResponse = await fetch(`http://localhost:8080/api/destinations/`, {
//                 method: 'GET',
//                 headers: apiConfig.headers,
//               });

//               if (destinationsResponse.status === 200) {
//                 const tripDestinations = await destinationsResponse.json();
//                 setTripLocations(tripDestinations);
//               } else {
//                 console.error('Error fetching destinations:', destinationsResponse.status);
//               }
//             }
//           } else {
//             console.error('Error fetching user trips:', response.status);
//           }
//         } catch (error) {
//           console.error('Network error:', error);
//         }
//       }
//     };

//     fetchUserTripsAndDestinations();
//   }, [user]);

//   return (
//     <ScrollView style={styles.scrollContainer}>
//       <View style={styles.container}>
//         <View style={styles.header}>
//           <Text style={styles.title}>Eco-Tracks</Text>
//           {user ? <Text style={styles.username}>Hello {user.username}</Text> : null}
//           {isSearching && (
//             <LocationSearch onLocationSelect={handleLocationSelect} onClose={stopSearching} />
//           )}
//           <TouchableOpacity style={styles.button} onPress={startSearching}>
//             <Text style={styles.buttonText}>New Destination</Text>
//           </TouchableOpacity>
//         </View>
  
//         <View style={styles.createTripBox}>
//           <Text style={styles.tripNameTitle}>New Trip:</Text>
//           <TextInput
//             style={styles.tripNameInput}
//             value={tripName}
//             onChangeText={(text) => setTripName(text)}
//           />
//           <Button title="Create" onPress={() => createNewTrip(tripName)} color="#c9b00f" />
//         </View>
  
//         {selectedLocation && (
//           <View>
//             <Text>Selected Location:</Text>
//             <Text>{selectedLocation.description}</Text>
//             <Button title="Add to Trip" onPress={handleAddToTrip} color="#c9b00f" />
//           </View>
//         )}

        
  
//         <View style={styles.tripList}>
//           <Text style={styles.tripListTitle}>
//             {activeTrip ? <Text>{activeTrip.name}</Text> : <Text>Your Trip</Text>}
//           </Text>
          
//           {tripLocations.map((destination) => (
//             <View key={destination.id}>
//               <Text>{destination.name}</Text>
//               <Text style={styles.destinationText}>{destination.description}</Text>
//             </View>
//           ))}
//         </View>
//       </View>
//     </ScrollView>
//   );
// }
  
// const styles = StyleSheet.create({
//   container: {
//     // flex: 1,
//     backgroundColor: "#057d95",
//     alignItems: 'center',
//     justifyContent: 'center',
//   },
//   header: {
//     marginTop: 10,
//     marginBottom: 20,
//     alignItems: 'center',
//   },
//   title: {
//     fontSize: 30,
//     fontWeight: 'bold',
//     color: "#c9b00f",
//     alignSelf: 'center',
//     marginTop: 0,
//     marginBottom: 150 // Adjust this margin
//   },
//   button: {
//     backgroundColor: "#c9b00f",
//     padding: 10,
//     alignSelf: 'center',
//     borderRadius: 10,
//     width: 150,
//     marginTop: 20,
//   },
//   buttonText: {
//     color: "#057d95",
//     fontSize: 18,
//     fontWeight: 'bold',
//     backgroundColor: "#c9b00f",
//     padding: 10,
//     alignSelf: 'center',
//     textAlign: 'center',
//   },
//   username: {
//     fontSize: 20,
//     fontWeight: 'italic',
//     fontWeight: 'bold',
//     color: "#c9b00f",
//     alignSelf: 'center',
//     marginTop: 20,
//   },
//   tripName: {
//     flexDirection: 'row',
//     alignItems: 'center',
//   },
//   tripNameTitle: {
//     fontSize: 16,
//     fontWeight: 'bold',
//     marginRight: 10,
//   },
//   tripNameInput: {
//     flex: 1,
//     borderWidth: 1,
//     borderColor: '#000000',
//     padding: 5,
//   },
//   tripList: {
//     backgroundColor: '#c9b00f',
//     padding: 10,
//     borderRadius: 10,
//     marginTop: 20,
//   },
//   tripListTitle: {
//     fontSize: 20,
//     fontWeight: 'bold',
//     color: '#057d95',
//   },
//   destinationBox: {
//     backgroundColor: '#057d95',
//     padding: 10,
//     borderRadius: 5,
//     marginBottom: 10,
//   },
//   destinationText: {
//     fontSize: 16,
//     color: '#0f66c9',
//   },
//   createTripBox: {
//     flexDirection: 'row',
//     alignItems: 'center',
//     backgroundColor: 'white',
//     padding: 10,
//     borderRadius: 10,
//     marginBottom: 60,
//   },
//   scrollContainer: {
//     flex: 1, 
//     backgroundColor: "#057d95",  },
    

//   },
// );

// export default UserPage;
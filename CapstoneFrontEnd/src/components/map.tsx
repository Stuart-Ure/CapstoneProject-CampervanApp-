import React from 'react';
import { View, StyleSheet, TextInput } from 'react-native';
import MapView from 'react-native-maps';
import {Marker} from 'react-native-maps';
import{ PROVIDER_GOOGLE }   from 'react-native-maps';

const Map = ({ destinations = [], searchResults = [] }) => {
    return (
      <View style={styles.container}>
        <MapView style={styles.map} >
          {destinations.map((destination, index) => (
            <Marker
              key={index}
              coordinate={{
                latitude: destination.latitude,
                longitude: destination.longitude,
              }}
              title={destination.name}
            />
          ))}
          {searchResults.map((result, index) => (
            <Marker
              key={index}
              coordinate={{
                latitude: result.latitude,
                longitude: result.longitude,
              }}
              title={result.name}
            />
          ))}
        </MapView>
      </View>
    );
  };
  

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    height: "40%",
    width: "180%",
    marginTop: "auto",
    marginBottom: 40,
    borderRadius: 15,
    borderWidth: 4,
    borderColor: "#000000",
    backgroundColor: "#2379ca",
    alignSelf: 'center',
  },

});

export default Map;

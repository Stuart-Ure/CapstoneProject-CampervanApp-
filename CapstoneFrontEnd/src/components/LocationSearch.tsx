import React, { useState } from 'react';
import { View, Text, StyleSheet, TextInput, FlatList } from 'react-native';
import { GooglePlacesAutocomplete } from 'react-native-google-places-autocomplete';

const LocationSearch = ({ onLocationSelect, onClose }) => {
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState([]);

  const handleSearch = async () => {
    if (!searchQuery) {
      setSearchResults([]); // Clear search results if the query is empty.
      return;
    }
  
    const apiKey = 'AIzaSyDy7i6XTngTFxdhFp9KKqWfOAj6kqeq734';
    const apiUrl = `https://maps.googleapis.com/maps/api/place/autocomplete/json?input=${searchQuery}&key=${apiKey}`;
  
    try {
      const response = await fetch(apiUrl);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      if (data.status === 'OK') {
        setSearchResults(data.predictions);
      } else {
        console.error('Google Places Autocomplete API request failed:', data.status);
      }
    } catch (error) {
      console.error('Error while fetching data:', error);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Search for a location:</Text>
      <TextInput
        style={styles.searchInput}
        value={searchQuery}
        onChangeText={(text) => setSearchQuery(text)}
        placeholder="Type a location"
        onSubmitEditing={handleSearch}
      />
      <FlatList
        data={searchResults}
        keyExtractor={(item) => item.id} // Use a unique identifier for the key
        renderItem={({ item }) => (
          <Text style={styles.resultItem} onPress={() => onLocationSelect(item)}>
            {item.description}
          </Text>
        )}
      />
      <Text style={styles.cancelButton} onPress={onClose}>
        Cancel
      </Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  searchInput: {
    borderWidth: 1,
    borderColor: '#000000',
    padding: 10,
    marginBottom: 10,
  },
  resultItem: {
    padding: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#000000',
  },
  cancelButton: {
    fontSize: 16,
    color: 'blue',
    textAlign: 'center',
  },
});

export default LocationSearch;
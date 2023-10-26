import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';


  function SustainabilityActions({ onSelectAction }) {
    const [actions, setActions] = useState([]);
  
    useEffect(() => {
      // Fetch sustainability actions from your API
      fetch('http://localhost:8080/api/sustainabilityActions/')
        .then((response) => response.json())
        .then((data) => setActions(data))
        .catch((error) => console.error('Error fetching actions:', error));
    }, []);
  
    return (
      <View>
        <Text>Select Sustainability Actions:</Text>
        {actions.map((action) => (
          <TouchableOpacity key={action.id} onPress={() => onSelectAction(action)}>
            <Text>{action.name}</Text>
          </TouchableOpacity>
        ))}
      </View>
    );
  }
  
  export default SustainabilityActions;

import React from 'react';
import {
  Button,
  Text,
  View,
} from "react-native";
import { NavigationContainer } from "@react-navigation/native";

const App = () => {
  return (
    <NavigationContainer>
      {ScreenA()}
    </NavigationContainer>
  );
};

export default App;

const ScreenA = () => {
  return (
    <View>
      <Text>Screen A</Text>
      <Button title={"Next screen"} onPress={() => {
        // TODO
      }} />
    </View>
  )
}

const ScreenB = () => {
  return (
    <View>
      <Text>Screen B</Text>
      <Button title={"Next screen"} onPress={() => {
        // TODO
      }} />
    </View>
  )
}

const ScreenC = () => {
  return (
    <View>
      <Text>Screen A</Text>
    </View>
  )
}

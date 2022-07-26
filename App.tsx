import React from 'react';
import {
  Button,
  Text,
  View,
} from "react-native";
import { NavigationContainer, useNavigation } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName={"A"}>
        <Stack.Screen name={"A"} component={ScreenA} />
        <Stack.Screen name={"B"} component={ScreenB} />
        <Stack.Screen name={"C"} component={ScreenC} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;

const ScreenA = () => {
  const navigation = useNavigation();
  return (
    <View>
      <Text>Screen A</Text>
      <Button title={"Next screen"} onPress={() => {
        navigation.navigate("B")
      }} />
    </View>
  )
}

const ScreenB = () => {
    const navigation = useNavigation();
    return (
    <View>
      <Text>Screen B</Text>
      <Button title={"Next screen"} onPress={() => {
        navigation.navigate("C")
      }} />
    </View>
  )
}

const ScreenC = () => {
  const navigation = useNavigation();
  return (
    <View>
      <Text>Screen A</Text>
    </View>
  )
}

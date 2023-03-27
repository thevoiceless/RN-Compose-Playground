import React, {useEffect, useRef} from 'react';
import {
  Button,
  requireNativeComponent,
  Text,
  View,
  UIManager,
  findNodeHandle,
} from 'react-native';
import {NavigationContainer, useNavigation} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';

const ComposeViewManager = requireNativeComponent('ComposeViewManager');

const createFragment = viewId => {
  UIManager.dispatchViewManagerCommand(
    viewId,
    // we are calling the 'create' command
    UIManager?.ComposeViewManager.Commands.create.toString(),
    [viewId],
  );
};

const ComposeView = () => {
  const ref = useRef(null);

  useEffect(() => {
    const id = requestAnimationFrame(() => {
      const viewId = findNodeHandle(ref.current);
      createFragment(viewId);
    });

    return () => {
      cancelAnimationFrame(id);
    };
  }, []);

  return (
    <ComposeViewManager
      style={{
        width: 200,
        height: 200,
      }}
      ref={ref}
    />
  );
};

const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName={'A'}>
        <Stack.Screen name={'A'} component={ScreenA} />
        <Stack.Screen name={'B'} component={ScreenB} />
        <Stack.Screen name={'C'} component={ScreenC} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;

const ScreenA = () => {
  const navigation = useNavigation();
  return (
    <View>
      <Text>Go to the next screen.</Text>
      <Button
        title={'Next screen'}
        onPress={() => {
          navigation.navigate('B');
        }}
      />
    </View>
  );
};

const ScreenB = () => {
  const navigation = useNavigation();
  return (
    <View>
      <Text>The ComposeView is green; its composeable content is magenta.</Text>
      <Text>Go to the next screen and then come back.</Text>
      <ComposeView />
      <Button
        title={'Next screen'}
        onPress={() => {
          navigation.navigate('C');
        }}
      />
    </View>
  );
};

const ScreenC = () => {
  const navigation = useNavigation();
  return (
    <View>
      <Text>
        Return to the previous screen; the composeable content will be gone.
      </Text>
    </View>
  );
};

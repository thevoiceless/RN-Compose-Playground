package com.vanilla

import android.app.Application
import android.content.Context
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.PackageList
import com.vanilla.newarchitecture.MainApplicationReactNativeHost
import com.facebook.react.config.ReactFeatureFlags
import com.facebook.soloader.SoLoader
import com.facebook.react.ReactInstanceManager
import com.vanilla.ReactNativeFlipper.initializeFlipper
import java.lang.reflect.InvocationTargetException

class MainApplication : Application(), ReactApplication {
    private val legacyArchitectureHost = object : ReactNativeHost(this) {
        override fun getUseDeveloperSupport() = BuildConfig.DEBUG

        override fun getPackages(): List<ReactPackage> {
            // Packages that cannot be autolinked yet can be added manually here, for example:
            // packages.add(new MyReactNativePackage());
            return PackageList(this).packages.apply {
                add(VanillaAppPackage())
            }
        }

        override fun getJSMainModuleName() = "index"
    }
    private val newArchitectureHost = MainApplicationReactNativeHost(this)
    override fun getReactNativeHost(): ReactNativeHost {
        return if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
            newArchitectureHost
        } else {
            legacyArchitectureHost
        }
    }

    override fun onCreate() {
        super.onCreate()
        // If you opted-in for the New Architecture, we enable the TurboModule system
        ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
        SoLoader.init(this, false)
        initializeFlipper(this, reactNativeHost.reactInstanceManager)
    }

    companion object {
        /**
         * Loads Flipper in React Native templates. Call this in the onCreate method with something like
         * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
         */
        private fun initializeFlipper(context: Context, reactInstanceManager: ReactInstanceManager) {
            if (BuildConfig.DEBUG) {
                try {
                    /*
                    We use reflection here to pick up the class that initializes Flipper,
                    since Flipper library is not available in release mode
                    */
                    val aClass = Class.forName("com.vanilla.ReactNativeFlipper")
                    aClass
                        .getMethod(
                            "initializeFlipper",
                            Context::class.java,
                            ReactInstanceManager::class.java
                        )
                        .invoke(null, context, reactInstanceManager)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
            }
        }
    }
}

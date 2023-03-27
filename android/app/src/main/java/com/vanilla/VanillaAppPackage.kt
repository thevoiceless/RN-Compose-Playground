package com.vanilla

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext

class VanillaAppPackage : ReactPackage {
    override fun createNativeModules(context: ReactApplicationContext) = emptyList<NativeModule>()
    override fun createViewManagers(context: ReactApplicationContext) = listOf(
        ComposeViewManager(context)
    )
}

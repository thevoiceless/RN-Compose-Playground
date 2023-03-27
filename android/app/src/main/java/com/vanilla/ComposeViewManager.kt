package com.vanilla

import android.view.View
import android.view.ViewGroup
import com.facebook.react.uimanager.ThemedReactContext

import android.view.Choreographer
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.uimanager.ViewGroupManager

class ComposeViewManager(
    private val reactContext: ReactApplicationContext
) : ViewGroupManager<FrameLayout>() {
    override fun getName() = REACT_CLASS

    /**
     * Return a FrameLayout which will later hold the Fragment
     */
    override fun createViewInstance(reactContext: ThemedReactContext) =
        FrameLayout(reactContext)

    /**
     * Map the "create" command to an integer
     */
    override fun getCommandsMap() = mapOf("create" to COMMAND_CREATE)

    /**
     * Handle "create" command (called from JS) and call createFragment method
     */
    override fun receiveCommand(
        root: FrameLayout,
        commandId: String,
        args: ReadableArray?
    ) {
        super.receiveCommand(root, commandId, args)
        val reactNativeViewId = requireNotNull(args).getInt(0)

        when (commandId.toInt()) {
            COMMAND_CREATE -> createFragment(root, reactNativeViewId)
        }
    }

    /**
     * Replace your React Native view with a custom fragment
     */
    fun createFragment(root: FrameLayout, reactNativeViewId: Int) {
        val parentView = root.findViewById<ViewGroup>(reactNativeViewId)
        setupLayout(parentView, root)
        val myFragment = MyFragment()
        val activity = reactContext.currentActivity as FragmentActivity
        activity.supportFragmentManager
            .beginTransaction()
            .replace(reactNativeViewId, myFragment, reactNativeViewId.toString())
            .commit()
    }

    fun setupLayout(view: View, rootView: View) {
        Choreographer.getInstance().postFrameCallback(object: Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                manuallyLayoutChildren(view, rootView)
                view.viewTreeObserver.dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    /**
     * Layout all children properly
     */
    private fun manuallyLayoutChildren(view: View, rootView: View) {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(rootView.measuredWidth, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(rootView.measuredHeight, View.MeasureSpec.EXACTLY))
        view.layout(rootView.left, rootView.top, rootView.measuredWidth, rootView.measuredHeight)
    }

    companion object {
        private const val REACT_CLASS = "ComposeViewManager"
        private const val COMMAND_CREATE = 1
    }
}
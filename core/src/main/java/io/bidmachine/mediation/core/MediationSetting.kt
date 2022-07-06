package io.bidmachine.mediation.core

import android.app.Activity
import java.lang.ref.WeakReference

object MediationSetting {

    private var weakActivity: WeakReference<Activity>? = null

    fun getActivity(): Activity? = weakActivity?.get()

    fun setActivity(activity: Activity) {
        weakActivity = WeakReference(activity)
    }

    @JvmStatic
    fun register(activity: Activity) {
        setActivity(activity)
        activity.application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks())
    }

}
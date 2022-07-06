package io.bidmachine.mediation.core

import android.app.Activity
import android.app.Application
import android.os.Bundle

internal class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // ignore
    }

    override fun onActivityStarted(activity: Activity) {
        MediationSetting.setActivity(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        MediationSetting.setActivity(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        // ignore
    }

    override fun onActivityStopped(activity: Activity) {
        // ignore
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // ignore
    }

    override fun onActivityDestroyed(activity: Activity) {
        // ignore
    }

}
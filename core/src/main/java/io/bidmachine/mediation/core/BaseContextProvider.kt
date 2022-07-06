package io.bidmachine.mediation.core

import android.app.Activity
import android.content.Context

class BaseContextProvider(override val context: Context) : ContextProvider {

    override val applicationContext: Context = context.applicationContext

    override val activity: Activity? = MediationSetting.getActivity()

}
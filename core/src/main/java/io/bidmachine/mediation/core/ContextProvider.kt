package io.bidmachine.mediation.core

import android.app.Activity
import android.content.Context

interface ContextProvider {

    val applicationContext: Context

    val context: Context

    val activity: Activity?

}
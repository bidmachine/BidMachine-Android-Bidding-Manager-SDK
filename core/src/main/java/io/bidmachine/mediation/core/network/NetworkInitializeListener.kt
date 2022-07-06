package io.bidmachine.mediation.core.network

import io.bidmachine.mediation.core.adobject.MediationError

interface NetworkInitializeListener {

    fun onInitialized()

    fun onFailToInitialize(error: MediationError)

}
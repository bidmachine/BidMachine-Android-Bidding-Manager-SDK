package io.bidmachine.mediation.core.mediationad

import io.bidmachine.mediation.core.adobject.AdInfo

interface FullscreenMediationAdListener<MediationAdType : MediationAd<*>> : MediationAdListener<MediationAdType> {

    /**
     * Called when the mediation ad has been closed.
     */
    fun onAdClosed(mediationAd: MediationAdType, adInfo: AdInfo)

}
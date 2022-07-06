package io.bidmachine.mediation.core.mediationad

import io.bidmachine.mediation.core.adobject.AdInfo

interface ViewMediationAdListener<MediationAdType : MediationAd<*>> : MediationAdListener<MediationAdType> {

    /**
     * Called when the mediation ad has been expanded to full screen.
     */
    fun onAdExpanded(mediationAd: MediationAdType, adInfo: AdInfo)

    /**
     * Called when the mediation ad has been collapsed from full screen.
     */
    fun onAdCollapsed(mediationAd: MediationAdType, adInfo: AdInfo)

}
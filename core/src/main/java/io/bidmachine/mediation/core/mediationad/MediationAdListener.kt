package io.bidmachine.mediation.core.mediationad

import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.MediationError

interface MediationAdListener<MediationAdType : MediationAd<*>> : MediationAdLoadListener<MediationAdType> {

    /**
     * Called when the mediation ad failed to show.
     */
    fun onAdFailToShow(mediationAd: MediationAdType, adError: MediationError)

    /**
     * Called when the mediation ad has been shown.
     */
    fun onAdShown(mediationAd: MediationAdType, adInfo: AdInfo)

    /**
     * Called when the mediation ad has been clicked.
     */
    fun onAdClicked(mediationAd: MediationAdType, adInfo: AdInfo)

    /**
     * Called when the mediation ad has been expired.
     */
    fun onAdExpired(mediationAd: MediationAdType, adInfo: AdInfo)

}
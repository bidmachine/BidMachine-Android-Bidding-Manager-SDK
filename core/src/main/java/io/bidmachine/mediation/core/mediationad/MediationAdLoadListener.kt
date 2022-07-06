package io.bidmachine.mediation.core.mediationad

interface MediationAdLoadListener<MediationAdType : MediationAd<*>> {

    /**
     * Called when the mediation ad has loaded.
     */
    fun onAdLoaded(mediationAd: MediationAdType)

    /**
     * Called when the mediation ad failed to load.
     */
    fun onAdFailToLoad(mediationAd: MediationAdType)

}
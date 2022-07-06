package io.bidmachine.mediation.core.mediationad

import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.Reward

interface RewardedMediationAdListener : FullscreenMediationAdListener<RewardedMediationAd> {

    /**
     * Called when the mediation ad has been rewarded.
     */
    fun onAdRewarded(mediationAd: RewardedMediationAd, adInfo: AdInfo, reward: Reward)

}
package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.Reward
import io.bidmachine.mediation.core.adobject.RewardedAdObject

interface RewardedAdBlockListener : FullscreenAdBlockListener<RewardedAdObject, RewardedAdBlock> {

    fun onAdRewarded(adBlock: RewardedAdBlock, adInfo: AdInfo, reward: Reward)

}
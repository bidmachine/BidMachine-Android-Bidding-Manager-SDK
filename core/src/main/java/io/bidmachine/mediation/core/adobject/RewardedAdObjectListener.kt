package io.bidmachine.mediation.core.adobject

interface RewardedAdObjectListener : FullscreenAdObjectListener<RewardedAdObject> {

    fun onAdRewarded(adObject: RewardedAdObject, reward: Reward)

}
package io.bidmachine.mediation.core.adblock

import io.bidmachine.mediation.core.adobject.AdInfo
import io.bidmachine.mediation.core.adobject.BannerAdObject

interface BannerAdBlockListener : AdBlockListener<BannerAdObject, BannerAdBlock> {

    fun onAdExpanded(adBlock: BannerAdBlock, adInfo: AdInfo)

    fun onAdCollapsed(adBlock: BannerAdBlock, adInfo: AdInfo)

}
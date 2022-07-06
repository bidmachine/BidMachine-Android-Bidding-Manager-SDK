package io.bidmachine.mediation.core.adobject

interface BannerAdObjectListener : AdObjectListener<BannerAdObject> {

    fun onAdExpanded(adObject: BannerAdObject)

    fun onAdCollapsed(adObject: BannerAdObject)

}
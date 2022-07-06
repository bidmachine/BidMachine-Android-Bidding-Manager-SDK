package io.bidmachine.mediation.core.adobject

interface FullscreenAdObjectListener<out AdObjectType : AdObject<*>> : AdObjectListener<AdObjectType> {

    fun onAdClosed(adObject: @UnsafeVariance AdObjectType)

}
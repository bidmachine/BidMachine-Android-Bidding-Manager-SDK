package io.bidmachine.mediation.core.adobject

interface AdObjectListener<out AdObjectType : AdObject<*>> : AdObjectLoadListener<AdObjectType> {

    fun onAdShown(adObject: @UnsafeVariance AdObjectType)

    fun onAdFailToShow(adObject: @UnsafeVariance AdObjectType, adError: MediationError)

    fun onAdClicked(adObject: @UnsafeVariance AdObjectType)

    fun onAdExpired(adObject: @UnsafeVariance AdObjectType)

}
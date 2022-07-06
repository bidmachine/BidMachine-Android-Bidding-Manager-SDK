package io.bidmachine.mediation.adapter.max

import android.view.View
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import io.bidmachine.mediation.adapter.max.MaxUtils.getCPM
import io.bidmachine.mediation.adapter.max.MaxUtils.setExtraParameters
import io.bidmachine.mediation.adapter.max.MaxUtils.setLocalExtraParameters
import io.bidmachine.mediation.adapter.max.MaxUtils.toMediationAdError
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.Utils
import io.bidmachine.mediation.core.adobject.AdObjectParameters
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.BannerAdObjectListener
import io.bidmachine.mediation.core.adobject.MediationError

internal class MaxBannerAdObject(private val networkAdUnit: MaxNetworkAdUnit) : BannerAdObject() {

    private var maxAdView: MaxAdView? = null

    override fun load(contextProvider: ContextProvider,
                      adObjectParameters: AdObjectParameters,
                      listener: BannerAdObjectListener) {
        val adUnitId = networkAdUnit.getAdUnitId()
        if (adUnitId.isNullOrEmpty()) {
            listener.onAdFailToLoad(this, MediationError.invalidParameter("AdUnitId is null or empty"))
            return
        }
        val maxAdFormat = networkAdUnit.obtainMaxAdFormat()
        maxAdView = MaxAdView(adUnitId, contextProvider.context).apply {
            layoutParams = Utils.createLayoutParams(resources, maxAdFormat.size.height)
            setListener(Listener(listener))
            setExtraParameters(networkAdUnit.getExtraParameters())
            setLocalExtraParameters(networkAdUnit.getLocalExtraParameters())
            networkAdUnit.getCustomData()?.also {
                setCustomData(it)
            }
            placement = networkAdUnit.getPlacement()
            loadAd()
        }
    }

    override fun canShow(): Boolean = maxAdView != null

    override fun getView(): View? = maxAdView

    override fun onDestroy() {
        maxAdView?.destroy()
        maxAdView = null
    }


    private inner class Listener(private val listener: BannerAdObjectListener) : MaxAdViewAdListener {

        override fun onAdLoaded(maxAd: MaxAd) {
            // Switch off auto refresh for MaxAdView
            maxAdView?.stopAutoRefresh()

            listener.onAdLoaded(this@MaxBannerAdObject, maxAd.getCPM())
        }

        override fun onAdLoadFailed(adUnitId: String, maxError: MaxError) {
            listener.onAdFailToLoad(this@MaxBannerAdObject, maxError.toMediationAdError())
        }

        override fun onAdDisplayed(maxAd: MaxAd) {
            listener.onAdShown(this@MaxBannerAdObject)
        }

        override fun onAdDisplayFailed(maxAd: MaxAd, maxError: MaxError) {
            listener.onAdFailToShow(this@MaxBannerAdObject, maxError.toMediationAdError())
        }

        override fun onAdClicked(maxAd: MaxAd) {
            listener.onAdClicked(this@MaxBannerAdObject)
        }

        override fun onAdExpanded(maxAd: MaxAd?) {
            listener.onAdExpanded(this@MaxBannerAdObject)
        }

        override fun onAdCollapsed(maxAd: MaxAd?) {
            listener.onAdCollapsed(this@MaxBannerAdObject)
        }

        override fun onAdHidden(maxAd: MaxAd) {

        }

    }

}
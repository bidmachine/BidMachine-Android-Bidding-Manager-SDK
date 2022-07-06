package io.bidmachine.mediation.adapter.admob

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.annotation.UiThread
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import io.bidmachine.mediation.adapter.admob.AdMobUtils.findLineItem
import io.bidmachine.mediation.adapter.admob.AdMobUtils.toMediationAdError
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.Utils
import io.bidmachine.mediation.core.adobject.AdObjectParameters
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.BannerAdObjectListener
import io.bidmachine.mediation.core.adobject.MediationError

/**
 * Make all calls to the Mobile Ads SDK on the main thread.
 */
internal class AdMobBannerAdObject(private val networkAdUnit: AdMobNetworkAdUnit) : BannerAdObject() {

    private var lineItem: AdMobLineItem? = null
    private var adView: AdView? = null

    /**
     * Switch off auto refresh for ad unit in the [AdMob dashboard](https://apps.admob.com) before load it.
     * Finds the first [AdMobLineItem] whose price is equal to or greater than the price floor and loads it.
     */
    override fun load(contextProvider: ContextProvider,
                      adObjectParameters: AdObjectParameters,
                      listener: BannerAdObjectListener) {
        val lineItems = networkAdUnit.getLineItems()
        if (lineItems.isNullOrEmpty()) {
            listener.onAdFailToLoad(this, MediationError.invalidParameter("LineItems is null or empty"))
            return
        }
        val priceFloor = adObjectParameters.priceFloor
        lineItems.findLineItem(priceFloor)?.also { lineItem ->
            Utils.onUiThread {
                loadAd(contextProvider.context, lineItem, listener)
            }
        } ?: listener.onAdFailToLoad(this,
                                     MediationError.invalidParameter("Can't find AdMobAdUnit at this price floor - $priceFloor"))
    }

    @UiThread
    @SuppressLint("MissingPermission")
    private fun loadAd(context: Context, lineItem: AdMobLineItem, listener: BannerAdObjectListener) {
        this.lineItem = lineItem

        try {
            adView = AdView(context).apply {
                adUnitId = lineItem.id
                adListener = Listener(listener)
                setAdSize(networkAdUnit.obtainAdSize())
                loadAd(networkAdUnit.obtainAdRequest())
            }
        } catch (e: Exception) {
            MediationLogger.throwable(e)
        }
    }

    override fun canShow(): Boolean = adView != null

    override fun getView(): View? = adView

    override fun onDestroy() {
        lineItem = null
        Utils.onUiThread {
            destroyAd()
        }
    }

    @UiThread
    private fun destroyAd() {
        adView?.destroy()
        adView = null
    }


    private inner class Listener(private val listener: BannerAdObjectListener) : AdListener() {

        override fun onAdLoaded() {
            listener.onAdLoaded(this@AdMobBannerAdObject, lineItem?.price)
        }

        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
            listener.onAdFailToLoad(this@AdMobBannerAdObject, loadAdError.toMediationAdError())
        }

        override fun onAdImpression() {
            listener.onAdShown(this@AdMobBannerAdObject)
        }

        override fun onAdClicked() {
            listener.onAdClicked(this@AdMobBannerAdObject)
        }

    }

}
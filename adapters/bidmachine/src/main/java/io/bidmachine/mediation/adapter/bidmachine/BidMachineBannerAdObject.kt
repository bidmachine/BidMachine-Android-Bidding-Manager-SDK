package io.bidmachine.mediation.adapter.bidmachine

import android.view.View
import io.bidmachine.banner.BannerListener
import io.bidmachine.banner.BannerRequest
import io.bidmachine.banner.BannerView
import io.bidmachine.mediation.adapter.bidmachine.BidMachineUtils.toMediationAdError
import io.bidmachine.mediation.adapter.bidmachine.BidMachineUtils.toPriceFloorParams
import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.adobject.AdObjectParameters
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.BannerAdObjectListener
import io.bidmachine.utils.BMError

internal class BidMachineBannerAdObject(private val networkAdUnit: BidMachineNetworkAdUnit) : BannerAdObject() {

    private var bannerView: BannerView? = null

    override fun load(contextProvider: ContextProvider,
                      adObjectParameters: AdObjectParameters,
                      listener: BannerAdObjectListener) {
        val request = BidMachineUtils.fillAdRequest(BannerRequest.Builder(), networkAdUnit)
                .setPriceFloorParams(adObjectParameters.priceFloor?.toPriceFloorParams())
                .setSize(networkAdUnit.obtainBannerSize())
                .build()
        bannerView = BannerView(contextProvider.context).apply {
            setListener(Listener(listener))
            load(request)
        }
    }

    override fun canShow(): Boolean = bannerView?.canShow() == true

    override fun getView(): View? = bannerView

    override fun onDestroy() {
        bannerView?.also {
            it.setListener(null)
            it.destroy()
        }
        bannerView = null
    }


    private inner class Listener(private val listener: BannerAdObjectListener) : BannerListener {

        override fun onAdLoaded(bannerView: BannerView) {
            listener.onAdLoaded(this@BidMachineBannerAdObject, bannerView.auctionResult?.price)
        }

        override fun onAdLoadFailed(bannerView: BannerView, bmError: BMError) {
            listener.onAdFailToLoad(this@BidMachineBannerAdObject, bmError.toMediationAdError())
        }

        override fun onAdShown(bannerView: BannerView) {
            listener.onAdShown(this@BidMachineBannerAdObject)
        }

        override fun onAdImpression(bannerView: BannerView) {

        }

        override fun onAdClicked(bannerView: BannerView) {
            listener.onAdClicked(this@BidMachineBannerAdObject)
        }

        override fun onAdExpired(bannerView: BannerView) {
            listener.onAdExpired(this@BidMachineBannerAdObject)
        }

    }

}
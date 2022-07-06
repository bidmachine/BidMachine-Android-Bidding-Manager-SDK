package io.bidmachine.mediation.core.network

import io.bidmachine.mediation.core.ContextProvider
import io.bidmachine.mediation.core.InitializeListener
import io.bidmachine.mediation.core.MediationLogger
import io.bidmachine.mediation.core.Utils
import io.bidmachine.mediation.core.adobject.BannerAdObject
import io.bidmachine.mediation.core.adobject.InterstitialAdObject
import io.bidmachine.mediation.core.adobject.MediationError
import io.bidmachine.mediation.core.adobject.RewardedAdObject
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Ad network adapter for mediation.
 *
 * @param key of ad network. Must match [NetworkAdUnit.networkKey] from the corresponding network.
 */
abstract class NetworkAdapter<NetworkAdUnitType : NetworkAdUnit>(val key: String,
                                                                 val networkVersion: String,
                                                                 val adapterVersion: String) {

    private val isInitializing: AtomicBoolean = AtomicBoolean(false)
    private val isInitialized: AtomicBoolean = AtomicBoolean(false)
    private val listenerSet: MutableSet<InitializeListener> = CopyOnWriteArraySet()

    /**
     * Returns True if ad network was already initialized.
     */
    open fun isNetworkInitialized(contextProvider: ContextProvider): Boolean {
        return false
    }

    /**
     * Creates banner ad object.
     */
    open fun createBannerAdObject(networkAdUnit: @UnsafeVariance NetworkAdUnitType): BannerAdObject? {
        return null
    }

    /**
     * Creates interstitial ad object.
     */
    open fun createInterstitialAdObject(networkAdUnit: @UnsafeVariance NetworkAdUnitType): InterstitialAdObject? {
        return null
    }

    /**
     * Creates rewarded ad object.
     */
    open fun createRewardedAdObject(networkAdUnit: @UnsafeVariance NetworkAdUnitType): RewardedAdObject? {
        return null
    }

    fun initialize(contextProvider: ContextProvider, listener: InitializeListener? = null) {
        if (isInitialized(contextProvider)) {
            MediationLogger.log("Initialization result: Network - $key ($networkVersion, $adapterVersion), status - onInitialized")

            isInitialized.compareAndSet(false, true)
            listener?.onInitialized()
            return
        }
        if (listener != null) {
            listenerSet.add(listener)
        }
        if (isInitializing.getAndSet(true)) {
            return
        }
        MediationLogger.log("Initialize network for $key")

        Utils.onBackgroundThread {
            initializeNetwork(contextProvider, object : NetworkInitializeListener {
                override fun onInitialized() {
                    MediationLogger.log("Initialization result: Network - $key ($networkVersion, $adapterVersion), status - onInitialized")

                    isInitialized.set(true)
                    sendOnInitialized()
                }

                override fun onFailToInitialize(error: MediationError) {
                    MediationLogger.error("Initialization result: Network - $key ($networkVersion, $adapterVersion), status - onFailToInitialize, error - $error")

                    sendOnInitialized()
                }
            })
        }
    }

    fun isInitialized(contextProvider: ContextProvider): Boolean =
        isInitialized.get() || isNetworkInitialized(contextProvider)

    protected abstract fun initializeNetwork(contextProvider: ContextProvider, listener: NetworkInitializeListener)

    private fun sendOnInitialized() {
        isInitializing.set(false)
        listenerSet.forEach {
            it.onInitialized()
        }
        listenerSet.clear()
    }

}
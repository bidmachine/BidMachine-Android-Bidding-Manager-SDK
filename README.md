# BidMachine Android Bidding Manager

[<img src="https://img.shields.io/badge/Mediation%20SDK%20Version-1.0.0-brightgreen">]()

* [Overview](#overview)
* [Workflow](#workflow)
* [Configure 3rd party ad network](#configure-3rd-party-ad-network)
* [Banner](#banner)
* [Interstitial](#interstitial)
* [Rewarded](#rewarded)

# Overview

Mediation SDK takes over the responsibility of loading the pre bid and the post bid blocks and provides the most
expensive ad.

# Integration

Add repository:

```groovy
repositories {
    maven {
        name 'BidMachine maven repo'
        url 'https://artifactory.bidmachine.io/bidmachine'
    }
}
```

Add dependency:

```groovy
dependencies {
    implementation "io.bidmachine.mediation:sdk:1.0.0"
}
```

# Workflow

1) Obtain configuration from own source.
2) Configure and initialize a 3rd party ad network. After the ad network has been initialized, crete mediation ad
   network adapter and registered it. Repeat if there are multiple networks.
3) Initialize the Mediation SDK.
4) Create and configure the ```MediationAd``` object.
5) Load the ```MediationAd``` object.
6) Show the ```MediationAd``` object when needed.

# Configure 3rd party ad network

```kotlin
private fun initializeBidMachine() {
    BidMachine.setLoggingEnabled(true)
    BidMachine.setTestMode(true)
    BidMachine.initialize(this, "YOUR_SOURCE_ID") {
        // Register Mediation BidMachine adapter
        MediationManager.registerAdNetwork(this, BidMachineNetworkAdapter())

        onNetworkInitialized()
    }
}

// Initialize MediationManager after initialize all 3rd party ad networks
private fun onNetworkInitialized() {
    MediationManager.setLoggingEnabled(true)
    MediationManager.initialize(this, object : InitializeListener {
        override fun onInitialized() {
            // Mediation SDK ready for work
        }
    })
}
```

# Banner

```kotlin
private var bannerMediationAdView: BannerMediationAdView? = null

private fun loadBanner() {
    bannerMediationAdView = BannerMediationAdView(this).apply {
        listener = BannerMediationListener()
        preBidTimeOutMs = 3000
        postBidTimeOutMs = 3000
        postBidPriceFloor = 1.0
        setPreBidNetworkAdUnit(listOf(MaxNetworkAdUnit("YOUR_MAX_AD_UNIT_ID")
                                              .setMaxAdFormat(MaxAdFormat.BANNER)))
        setPostBidNetworkAdUnit(listOf(AdMobNetworkAdUnit(YOUR_ADMOB_LINE_ITEMS)
                                               .setAdSize(AdSize.BANNER),
                                       BidMachineNetworkAdUnit()
                                               .setBannerSize(BannerSize.Size_320x50)))
        // Default - 15000 ms
        setAutoRefreshTime(10000)
        // Default - 2000 ms
        setRetryOnFailTime(1000)

        loadAd()
    }
}

private fun showBanner() {
    bannerMediationAdView?.takeIf {
        // Checking the possibility of showing mediation ad
        it.isLoadingCompleted() && it.canShowAd()
    }?.also {
        // Add bannerMediationAdView to container
        adContainer.addView(it)
    }
}

private fun destroyBanner() {
    // Destroy banner instance if not needed
    bannerMediationAdView?.destroy()
    bannerMediationAdView = null
}
```

# Interstitial

```kotlin
private var interstitialMediationAd: InterstitialMediationAd? = null

private fun loadInterstitial() {
    interstitialMediationAd = InterstitialMediationAd(this).apply {
        listener = InterstitialListener()
        preBidTimeOutMs = 3000
        postBidTimeOutMs = 3000
        postBidPriceFloor = 1.0
        setPreBidNetworkAdUnit(listOf(MaxNetworkAdUnit("YOUR_MAX_AD_UNIT_ID")))
        setPostBidNetworkAdUnit(listOf(AdMobNetworkAdUnit(YOUR_ADMOB_LINE_ITEMS),
                                       BidMachineNetworkAdUnit()
                                               .setAdContentType(AdContentType.All)))
        loadAd()
    }
}

private fun showInterstitial() {
    interstitialMediationAd?.takeIf {
        // Checking the possibility of showing mediation ad
        it.isLoadingCompleted() && it.canShowAd()
    }?.showAd()
}

private fun destroyInterstitial() {
    // Destroy interstitial instance if not needed
    interstitialMediationAd?.destroy()
    interstitialMediationAd = null
}
```

# Rewarded

```kotlin
private var rewardedMediationAd: RewardedMediationAd? = null

private fun loadRewarded() {
    rewardedMediationAd = RewardedMediationAd(this).apply {
        listener = RewardedMediationListener()
        preBidTimeOutMs = 3000
        postBidTimeOutMs = 3000
        postBidPriceFloor = 1.0
        setPreBidNetworkAdUnit(listOf(MaxNetworkAdUnit("YOUR_MAX_AD_UNIT_ID")))
        setPostBidNetworkAdUnit(listOf(AdMobNetworkAdUnit(YOUR_ADMOB_LINE_ITEMS),
                                       BidMachineNetworkAdUnit()))
        loadAd()
    }
}

private fun showRewarded() {
    rewardedMediationAd?.takeIf {
        // Checking the possibility of showing mediation ad
        it.isLoadingCompleted() && it.canShowAd()
    }?.showAd()
}

private fun destroyRewarded() {
    // Destroy rewarded instance if not needed
    rewardedMediationAd?.destroy()
    rewardedMediationAd = null
}
```

# AdMob adapter

[<img src="https://img.shields.io/badge/AdMob%20version-21.0.0-brightgreen">]()
[<img src="https://img.shields.io/badge/AdMob%20adapter%20version-21.0.0.0-brightgreen">]()

> **Warning** The price that will be used as the price of the loaded ad is the ```price``` from the ```AdMobLineItem```.

# Supported type

* Banner
* Interstitial
* Rewarded

# Integration

How to integrate the AdMob, see [the official documentation](https://developers.google.com/admob/android/quick-start).

Add adapter dependency:

```groovy
dependencies {
    implementation "io.bidmachine.mediation:adapter.admob:21.0.0.0"
}
```

# Network ad unit

Configure and add ```AdMobNetworkAdUnit``` to list of ad unit for the ```AdMob``` to participate in mediation.

| Parameter | Required | Type                      |
|-----------|----------|---------------------------|
| lineItems | Yes      | Collection<AdMobLineItem> |
| adRequest | No       | AdRequest                 |
| adSize    | No       | AdSize                    |

```AdMobLineItem``` contains information about the ad unit id from [AdMob dashboard](https://apps.admob.com) and the
price corresponding to it.

> **Warning** Switch off auto refresh for the banner ad unit in the [AdMob dashboard](https://apps.admob.com) before load it.
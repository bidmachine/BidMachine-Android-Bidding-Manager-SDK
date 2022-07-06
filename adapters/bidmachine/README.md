# BidMachine adapter

[<img src="https://img.shields.io/badge/BidMachine%20version-1.9.5-brightgreen">]()
[<img src="https://img.shields.io/badge/BidMachine%20adapter%20version-1.9.5.0-brightgreen">]()

# Supported type

* Banner
* Interstitial
* Rewarded

# Integration

How to integrate the BidMachine, see [the official documentation](https://docs.bidmachine.io/docs/in-house-mediation).

Add adapter dependency:

```groovy
dependencies {
    implementation "io.bidmachine.mediation:adapter.bidmachine:1.9.5.0"
}
```

# Network ad unit

Configure and add ```BidMachineNetworkAdUnit``` to list of ad unit for the ```BidMachine``` to participate in mediation.

| Parameter       | Required | Type                |
|-----------------|----------|---------------------|
| targetingParams | No       | TargetingParams     |
| networkList     | No       | List<NetworkConfig> |
| customParams    | No       | CustomParams        |
| placementId     | No       | String              |
| bannerSize      | No       | BannerSize          |
| adContentType   | No       | AdContentType       |
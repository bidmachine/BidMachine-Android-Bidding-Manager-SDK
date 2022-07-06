package io.bidmachine.mediation.core.network

/**
 * Ad unit for mediation.
 *
 * @param networkKey Key of ad network - [NetworkAdapter.key]
 */
open class NetworkAdUnit(val networkKey: String, val params: MutableMap<String, Any>)
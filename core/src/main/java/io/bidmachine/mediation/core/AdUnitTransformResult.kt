package io.bidmachine.mediation.core

import io.bidmachine.mediation.core.network.NetworkAdUnit

data class AdUnitTransformResult<T>(val networkAdUnit: NetworkAdUnit, val result: T)
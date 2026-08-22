package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;

@SinceKotlin
@ContractsDsl
@Metadata
@ExperimentalContracts
/* loaded from: classes2.dex */
public enum InvocationKind {
    AT_MOST_ONCE,
    AT_LEAST_ONCE,
    EXACTLY_ONCE,
    UNKNOWN
}

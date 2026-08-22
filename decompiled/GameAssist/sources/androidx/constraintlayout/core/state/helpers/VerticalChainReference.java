package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.State;

/* loaded from: classes.dex */
public class VerticalChainReference extends ChainReference {

    /* renamed from: androidx.constraintlayout.core.state.helpers.VerticalChainReference$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1940a;

        static {
            int[] iArr = new int[State.Chain.values().length];
            f1940a = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1940a[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1940a[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}

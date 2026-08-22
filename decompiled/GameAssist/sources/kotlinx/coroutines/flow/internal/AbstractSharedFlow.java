package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {

    /* renamed from: c, reason: collision with root package name */
    private AbstractSharedFlowSlot[] f19286c;

    /* renamed from: h, reason: collision with root package name */
    private int f19287h;

    /* renamed from: i, reason: collision with root package name */
    private int f19288i;

    /* renamed from: j, reason: collision with root package name */
    private SubscriptionCountStateFlow f19289j;

    protected final AbstractSharedFlowSlot f() {
        AbstractSharedFlowSlot abstractSharedFlowSlot;
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            try {
                AbstractSharedFlowSlot[] abstractSharedFlowSlotArr = this.f19286c;
                if (abstractSharedFlowSlotArr == null) {
                    abstractSharedFlowSlotArr = j(2);
                    this.f19286c = abstractSharedFlowSlotArr;
                } else if (this.f19287h >= abstractSharedFlowSlotArr.length) {
                    Object[] copyOf = Arrays.copyOf(abstractSharedFlowSlotArr, abstractSharedFlowSlotArr.length * 2);
                    Intrinsics.d(copyOf, "copyOf(this, newSize)");
                    this.f19286c = (AbstractSharedFlowSlot[]) copyOf;
                    abstractSharedFlowSlotArr = (AbstractSharedFlowSlot[]) copyOf;
                }
                int i2 = this.f19288i;
                do {
                    abstractSharedFlowSlot = abstractSharedFlowSlotArr[i2];
                    if (abstractSharedFlowSlot == null) {
                        abstractSharedFlowSlot = g();
                        abstractSharedFlowSlotArr[i2] = abstractSharedFlowSlot;
                    }
                    i2++;
                    if (i2 >= abstractSharedFlowSlotArr.length) {
                        i2 = 0;
                    }
                } while (!abstractSharedFlowSlot.a(this));
                this.f19288i = i2;
                this.f19287h++;
                subscriptionCountStateFlow = this.f19289j;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.Z(1);
        }
        return abstractSharedFlowSlot;
    }

    protected abstract AbstractSharedFlowSlot g();

    public final StateFlow h() {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            subscriptionCountStateFlow = this.f19289j;
            if (subscriptionCountStateFlow == null) {
                subscriptionCountStateFlow = new SubscriptionCountStateFlow(this.f19287h);
                this.f19289j = subscriptionCountStateFlow;
            }
        }
        return subscriptionCountStateFlow;
    }

    protected abstract AbstractSharedFlowSlot[] j(int i2);

    protected final void l(AbstractSharedFlowSlot abstractSharedFlowSlot) {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        int i2;
        Continuation[] b2;
        synchronized (this) {
            try {
                int i3 = this.f19287h - 1;
                this.f19287h = i3;
                subscriptionCountStateFlow = this.f19289j;
                if (i3 == 0) {
                    this.f19288i = 0;
                }
                b2 = abstractSharedFlowSlot.b(this);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (Continuation continuation : b2) {
            if (continuation != null) {
                Result.Companion companion = Result.Companion;
                continuation.g(Result.b(Unit.f18288a));
            }
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.Z(-1);
        }
    }

    protected final int m() {
        return this.f19287h;
    }

    protected final AbstractSharedFlowSlot[] n() {
        return this.f19286c;
    }
}

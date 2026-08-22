package kotlinx.coroutines.flow.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public abstract class ChannelFlow<T> implements FusibleFlow<T> {

    /* renamed from: c, reason: collision with root package name */
    public final CoroutineContext f19291c;

    /* renamed from: h, reason: collision with root package name */
    public final int f19292h;

    /* renamed from: i, reason: collision with root package name */
    public final BufferOverflow f19293i;

    public ChannelFlow(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        this.f19291c = coroutineContext;
        this.f19292h = i2;
        this.f19293i = bufferOverflow;
    }

    static /* synthetic */ Object d(ChannelFlow channelFlow, FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = CoroutineScopeKt.a(new ChannelFlow$collect$2(flowCollector, channelFlow, null), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        return d(this, flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow b(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        CoroutineContext R = coroutineContext.R(this.f19291c);
        if (bufferOverflow == BufferOverflow.SUSPEND) {
            int i3 = this.f19292h;
            if (i3 != -3) {
                if (i2 != -3) {
                    if (i3 != -2) {
                        if (i2 != -2) {
                            i2 += i3;
                            if (i2 < 0) {
                                i2 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
                            }
                        }
                    }
                }
                i2 = i3;
            }
            bufferOverflow = this.f19293i;
        }
        return (Intrinsics.a(R, this.f19291c) && i2 == this.f19292h && bufferOverflow == this.f19293i) ? this : g(R, i2, bufferOverflow);
    }

    protected String c() {
        return null;
    }

    protected abstract Object f(ProducerScope producerScope, Continuation continuation);

    protected abstract ChannelFlow g(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow);

    public final Function2 j() {
        return new ChannelFlow$collectToFun$1(this, null);
    }

    public final int k() {
        int i2 = this.f19292h;
        if (i2 == -3) {
            return -2;
        }
        return i2;
    }

    public ReceiveChannel l(CoroutineScope coroutineScope) {
        return ProduceKt.e(coroutineScope, this.f19291c, k(), this.f19293i, CoroutineStart.ATOMIC, null, j(), 16, null);
    }

    public String toString() {
        String E;
        ArrayList arrayList = new ArrayList(4);
        String c2 = c();
        if (c2 != null) {
            arrayList.add(c2);
        }
        if (this.f19291c != EmptyCoroutineContext.INSTANCE) {
            arrayList.add("context=" + this.f19291c);
        }
        if (this.f19292h != -3) {
            arrayList.add("capacity=" + this.f19292h);
        }
        if (this.f19293i != BufferOverflow.SUSPEND) {
            arrayList.add("onBufferOverflow=" + this.f19293i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(DebugStringsKt.a(this));
        sb.append('[');
        E = CollectionsKt___CollectionsKt.E(arrayList, ", ", null, null, 0, null, null, 62, null);
        sb.append(E);
        sb.append(']');
        return sb.toString();
    }
}

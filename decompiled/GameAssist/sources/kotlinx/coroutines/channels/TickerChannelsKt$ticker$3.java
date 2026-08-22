package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt$ticker$3", f = "TickerChannels.kt", l = {72, 73}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class TickerChannelsKt$ticker$3 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $delayMillis;
    final /* synthetic */ long $initialDelayMillis;
    final /* synthetic */ TickerMode $mode;
    private /* synthetic */ Object L$0;
    int label;

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f19030a;

        static {
            int[] iArr = new int[TickerMode.values().length];
            iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
            iArr[TickerMode.FIXED_DELAY.ordinal()] = 2;
            f19030a = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TickerChannelsKt$ticker$3(TickerMode tickerMode, long j2, long j3, Continuation<? super TickerChannelsKt$ticker$3> continuation) {
        super(2, continuation);
        this.$mode = tickerMode;
        this.$delayMillis = j2;
        this.$initialDelayMillis = j3;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(ProducerScope producerScope, Continuation continuation) {
        return ((TickerChannelsKt$ticker$3) o(producerScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        TickerChannelsKt$ticker$3 tickerChannelsKt$ticker$3 = new TickerChannelsKt$ticker$3(this.$mode, this.$delayMillis, this.$initialDelayMillis, continuation);
        tickerChannelsKt$ticker$3.L$0 = obj;
        return tickerChannelsKt$ticker$3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        Object d3;
        Object c2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            int i3 = WhenMappings.f19030a[this.$mode.ordinal()];
            if (i3 == 1) {
                long j2 = this.$delayMillis;
                long j3 = this.$initialDelayMillis;
                SendChannel l2 = producerScope.l();
                this.label = 1;
                d3 = TickerChannelsKt.d(j2, j3, l2, this);
                if (d3 == d2) {
                    return d2;
                }
            } else if (i3 == 2) {
                long j4 = this.$delayMillis;
                long j5 = this.$initialDelayMillis;
                SendChannel l3 = producerScope.l();
                this.label = 2;
                c2 = TickerChannelsKt.c(j4, j5, l3, this);
                if (c2 == d2) {
                    return d2;
                }
            }
        } else {
            if (i2 != 1 && i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.b(obj);
        }
        return Unit.f18288a;
    }
}

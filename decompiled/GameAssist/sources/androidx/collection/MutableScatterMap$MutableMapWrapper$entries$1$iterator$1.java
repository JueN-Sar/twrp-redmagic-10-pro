package androidx.collection;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* JADX INFO: Add missing generic type declarations: [V, K] */
@Metadata
/* loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {

    /* renamed from: c, reason: collision with root package name */
    private Iterator f1346c;

    /* renamed from: h, reason: collision with root package name */
    private int f1347h = -1;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ MutableScatterMap f1348i;

    @Metadata
    @DebugMetadata(c = "androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1", f = "ScatterMap.kt", l = {1328}, m = "invokeSuspend")
    @SourceDebugExtension
    /* renamed from: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Map.Entry<K, V>>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutableScatterMap<K, V> this$0;
        final /* synthetic */ MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 this$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MutableScatterMap mutableScatterMap, MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 mutableScatterMap$MutableMapWrapper$entries$1$iterator$1, Continuation continuation) {
            super(2, continuation);
            this.this$0 = mutableScatterMap;
            this.this$1 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation o(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.this$1, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00b2  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0072  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x00a7 -> B:5:0x00aa). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00ad -> B:6:0x00ae). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005a -> B:7:0x0070). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00bf -> B:18:0x00c0). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object v(java.lang.Object r23) {
            /*
                Method dump skipped, instructions count: 201
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1.AnonymousClass1.v(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: x, reason: merged with bridge method [inline-methods] */
        public final Object y(SequenceScope sequenceScope, Continuation continuation) {
            return ((AnonymousClass1) o(sequenceScope, continuation)).v(Unit.f18288a);
        }
    }

    MutableScatterMap$MutableMapWrapper$entries$1$iterator$1(MutableScatterMap mutableScatterMap) {
        Iterator a2;
        this.f1348i = mutableScatterMap;
        a2 = SequencesKt__SequenceBuilderKt.a(new AnonymousClass1(mutableScatterMap, this, null));
        this.f1346c = a2;
    }

    public final int b() {
        return this.f1347h;
    }

    @Override // java.util.Iterator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Map.Entry next() {
        return (Map.Entry) this.f1346c.next();
    }

    public final void d(int i2) {
        this.f1347h = i2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1346c.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        int i2 = this.f1347h;
        if (i2 != -1) {
            this.f1348i.p(i2);
            this.f1347h = -1;
        }
    }
}

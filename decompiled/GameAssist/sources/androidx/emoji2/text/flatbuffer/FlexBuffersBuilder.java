package androidx.emoji2.text.flatbuffer;

import java.util.Comparator;

/* loaded from: classes.dex */
public class FlexBuffersBuilder {

    /* renamed from: a, reason: collision with root package name */
    private final ReadWriteBuf f3828a;

    /* renamed from: androidx.emoji2.text.flatbuffer.FlexBuffersBuilder$1, reason: invalid class name */
    class AnonymousClass1 implements Comparator<Value> {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ FlexBuffersBuilder f3829c;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Value value, Value value2) {
            byte b2;
            byte b3;
            int i2 = value.f3830a;
            int i3 = value2.f3830a;
            do {
                b2 = this.f3829c.f3828a.get(i2);
                b3 = this.f3829c.f3828a.get(i3);
                if (b2 == 0) {
                    return b2 - b3;
                }
                i2++;
                i3++;
            } while (b2 == b3);
            return b2 - b3;
        }
    }

    private static class Value {

        /* renamed from: a, reason: collision with root package name */
        int f3830a;
    }
}

package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class SerializedCollection implements Externalizable {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 0;
    public static final int tagList = 0;
    public static final int tagSet = 1;

    @NotNull
    private Collection<?> collection;
    private final int tag;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SerializedCollection(@NotNull Collection<?> collection, int i2) {
        Intrinsics.e(collection, "collection");
        this.collection = collection;
        this.tag = i2;
    }

    private final Object readResolve() {
        return this.collection;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput input) {
        List d2;
        Collection<?> a2;
        Set b2;
        Intrinsics.e(input, "input");
        byte readByte = input.readByte();
        int i2 = readByte & 1;
        if ((readByte & (-2)) != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + ((int) readByte) + '.');
        }
        int readInt = input.readInt();
        if (readInt < 0) {
            throw new InvalidObjectException("Illegal size value: " + readInt + '.');
        }
        int i3 = 0;
        if (i2 == 0) {
            d2 = CollectionsKt__CollectionsJVMKt.d(readInt);
            while (i3 < readInt) {
                d2.add(input.readObject());
                i3++;
            }
            a2 = CollectionsKt__CollectionsJVMKt.a(d2);
        } else {
            if (i2 != 1) {
                throw new InvalidObjectException("Unsupported collection type tag: " + i2 + '.');
            }
            b2 = SetsKt__SetsJVMKt.b(readInt);
            while (i3 < readInt) {
                b2.add(input.readObject());
                i3++;
            }
            a2 = SetsKt__SetsJVMKt.a(b2);
        }
        this.collection = a2;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput output) {
        Intrinsics.e(output, "output");
        output.writeByte(this.tag);
        output.writeInt(this.collection.size());
        Iterator<?> it = this.collection.iterator();
        while (it.hasNext()) {
            output.writeObject(it.next());
        }
    }

    public SerializedCollection() {
        this(CollectionsKt__CollectionsKt.g(), 0);
    }
}

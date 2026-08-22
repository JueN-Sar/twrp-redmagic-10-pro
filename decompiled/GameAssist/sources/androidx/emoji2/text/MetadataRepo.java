package androidx.emoji2.text;

import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.nio.ByteBuffer;

@AnyThread
@RequiresApi
/* loaded from: classes.dex */
public final class MetadataRepo {

    /* renamed from: a, reason: collision with root package name */
    private final MetadataList f3781a;

    /* renamed from: b, reason: collision with root package name */
    private final char[] f3782b;

    /* renamed from: c, reason: collision with root package name */
    private final Node f3783c = new Node(1024);

    /* renamed from: d, reason: collision with root package name */
    private final Typeface f3784d;

    @RestrictTo
    static class Node {

        /* renamed from: a, reason: collision with root package name */
        private final SparseArray f3785a;

        /* renamed from: b, reason: collision with root package name */
        private TypefaceEmojiRasterizer f3786b;

        private Node() {
            this(1);
        }

        Node a(int i2) {
            SparseArray sparseArray = this.f3785a;
            if (sparseArray == null) {
                return null;
            }
            return (Node) sparseArray.get(i2);
        }

        final TypefaceEmojiRasterizer b() {
            return this.f3786b;
        }

        void c(TypefaceEmojiRasterizer typefaceEmojiRasterizer, int i2, int i3) {
            Node a2 = a(typefaceEmojiRasterizer.b(i2));
            if (a2 == null) {
                a2 = new Node();
                this.f3785a.put(typefaceEmojiRasterizer.b(i2), a2);
            }
            if (i3 > i2) {
                a2.c(typefaceEmojiRasterizer, i2 + 1, i3);
            } else {
                a2.f3786b = typefaceEmojiRasterizer;
            }
        }

        Node(int i2) {
            this.f3785a = new SparseArray(i2);
        }
    }

    private MetadataRepo(Typeface typeface, MetadataList metadataList) {
        this.f3784d = typeface;
        this.f3781a = metadataList;
        this.f3782b = new char[metadataList.l() * 2];
        a(metadataList);
    }

    private void a(MetadataList metadataList) {
        int l2 = metadataList.l();
        for (int i2 = 0; i2 < l2; i2++) {
            TypefaceEmojiRasterizer typefaceEmojiRasterizer = new TypefaceEmojiRasterizer(this, i2);
            Character.toChars(typefaceEmojiRasterizer.f(), this.f3782b, i2 * 2);
            put(typefaceEmojiRasterizer);
        }
    }

    public static MetadataRepo b(Typeface typeface, ByteBuffer byteBuffer) {
        try {
            TraceCompat.a("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface, MetadataListReader.b(byteBuffer));
        } finally {
            TraceCompat.b();
        }
    }

    public char[] c() {
        return this.f3782b;
    }

    public MetadataList d() {
        return this.f3781a;
    }

    int e() {
        return this.f3781a.m();
    }

    Node f() {
        return this.f3783c;
    }

    Typeface g() {
        return this.f3784d;
    }

    @RestrictTo
    @VisibleForTesting
    void put(@NonNull TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        Preconditions.i(typefaceEmojiRasterizer, "emoji metadata cannot be null");
        Preconditions.b(typefaceEmojiRasterizer.c() > 0, "invalid metadata codepoint length");
        this.f3783c.c(typefaceEmojiRasterizer, 0, typefaceEmojiRasterizer.c() - 1);
    }
}

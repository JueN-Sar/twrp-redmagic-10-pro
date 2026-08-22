package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ByteBufferReadWriteBuf implements ReadWriteBuf {

    /* renamed from: a, reason: collision with root package name */
    private final ByteBuffer f3807a;

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public String a(int i2, int i3) {
        return Utf8Safe.c(this.f3807a, i2, i3);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte get(int i2) {
        return this.f3807a.get(i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public double getDouble(int i2) {
        return this.f3807a.getDouble(i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public float getFloat(int i2) {
        return this.f3807a.getFloat(i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public int getInt(int i2) {
        return this.f3807a.getInt(i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public long getLong(int i2) {
        return this.f3807a.getLong(i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public short getShort(int i2) {
        return this.f3807a.getShort(i2);
    }
}

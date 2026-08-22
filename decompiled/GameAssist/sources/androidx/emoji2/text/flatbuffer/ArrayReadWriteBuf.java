package androidx.emoji2.text.flatbuffer;

/* loaded from: classes.dex */
public class ArrayReadWriteBuf implements ReadWriteBuf {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f3805a;

    /* renamed from: b, reason: collision with root package name */
    private int f3806b;

    public ArrayReadWriteBuf(byte[] bArr, int i2) {
        this.f3805a = bArr;
        this.f3806b = i2;
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public String a(int i2, int i3) {
        return Utf8Safe.b(this.f3805a, i2, i3);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public byte get(int i2) {
        return this.f3805a[i2];
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public double getDouble(int i2) {
        return Double.longBitsToDouble(getLong(i2));
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public float getFloat(int i2) {
        return Float.intBitsToFloat(getInt(i2));
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public int getInt(int i2) {
        byte[] bArr = this.f3805a;
        return (bArr[i2] & 255) | (bArr[i2 + 3] << 24) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public long getLong(int i2) {
        byte[] bArr = this.f3805a;
        return (bArr[i2 + 7] << 56) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48);
    }

    @Override // androidx.emoji2.text.flatbuffer.ReadBuf
    public short getShort(int i2) {
        byte[] bArr = this.f3805a;
        return (short) ((bArr[i2] & 255) | (bArr[i2 + 1] << 8));
    }
}

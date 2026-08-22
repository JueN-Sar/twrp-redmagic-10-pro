package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.versionedparcelable.VersionedParcel;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

@RestrictTo
/* loaded from: classes.dex */
class VersionedParcelStream extends VersionedParcel {

    /* renamed from: m, reason: collision with root package name */
    private static final Charset f5735m = Charset.forName("UTF-16");

    /* renamed from: d, reason: collision with root package name */
    private final DataInputStream f5736d;

    /* renamed from: e, reason: collision with root package name */
    private final DataOutputStream f5737e;

    /* renamed from: f, reason: collision with root package name */
    private DataInputStream f5738f;

    /* renamed from: g, reason: collision with root package name */
    private DataOutputStream f5739g;

    /* renamed from: h, reason: collision with root package name */
    private FieldBuffer f5740h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f5741i;

    /* renamed from: j, reason: collision with root package name */
    int f5742j;

    /* renamed from: k, reason: collision with root package name */
    private int f5743k;

    /* renamed from: l, reason: collision with root package name */
    int f5744l;

    private static class FieldBuffer {

        /* renamed from: a, reason: collision with root package name */
        final ByteArrayOutputStream f5746a;

        /* renamed from: b, reason: collision with root package name */
        final DataOutputStream f5747b;

        /* renamed from: c, reason: collision with root package name */
        private final int f5748c;

        /* renamed from: d, reason: collision with root package name */
        private final DataOutputStream f5749d;

        FieldBuffer(int i2, DataOutputStream dataOutputStream) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.f5746a = byteArrayOutputStream;
            this.f5747b = new DataOutputStream(byteArrayOutputStream);
            this.f5748c = i2;
            this.f5749d = dataOutputStream;
        }

        void a() {
            this.f5747b.flush();
            int size = this.f5746a.size();
            this.f5749d.writeInt((this.f5748c << 16) | (size >= 65535 ? 65535 : size));
            if (size >= 65535) {
                this.f5749d.writeInt(size);
            }
            this.f5746a.writeTo(this.f5749d);
        }
    }

    private VersionedParcelStream(InputStream inputStream, OutputStream outputStream, ArrayMap arrayMap, ArrayMap arrayMap2, ArrayMap arrayMap3) {
        super(arrayMap, arrayMap2, arrayMap3);
        this.f5742j = 0;
        this.f5743k = -1;
        this.f5744l = -1;
        DataInputStream dataInputStream = inputStream != null ? new DataInputStream(new FilterInputStream(inputStream) { // from class: androidx.versionedparcelable.VersionedParcelStream.1
            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() {
                VersionedParcelStream versionedParcelStream = VersionedParcelStream.this;
                int i2 = versionedParcelStream.f5744l;
                if (i2 != -1 && versionedParcelStream.f5742j >= i2) {
                    throw new IOException();
                }
                int read = super.read();
                VersionedParcelStream.this.f5742j++;
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long j2) {
                VersionedParcelStream versionedParcelStream = VersionedParcelStream.this;
                int i2 = versionedParcelStream.f5744l;
                if (i2 != -1 && versionedParcelStream.f5742j >= i2) {
                    throw new IOException();
                }
                long skip = super.skip(j2);
                if (skip > 0) {
                    VersionedParcelStream.this.f5742j += (int) skip;
                }
                return skip;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i2, int i3) {
                VersionedParcelStream versionedParcelStream = VersionedParcelStream.this;
                int i4 = versionedParcelStream.f5744l;
                if (i4 != -1 && versionedParcelStream.f5742j >= i4) {
                    throw new IOException();
                }
                int read = super.read(bArr, i2, i3);
                if (read > 0) {
                    VersionedParcelStream.this.f5742j += read;
                }
                return read;
            }
        }) : null;
        this.f5736d = dataInputStream;
        DataOutputStream dataOutputStream = outputStream != null ? new DataOutputStream(outputStream) : null;
        this.f5737e = dataOutputStream;
        this.f5738f = dataInputStream;
        this.f5739g = dataOutputStream;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void A(byte[] bArr) {
        try {
            if (bArr != null) {
                this.f5739g.writeInt(bArr.length);
                this.f5739g.write(bArr);
            } else {
                this.f5739g.writeInt(-1);
            }
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected void C(CharSequence charSequence) {
        if (!this.f5741i) {
            throw new RuntimeException("CharSequence cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void E(int i2) {
        try {
            this.f5739g.writeInt(i2);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void G(Parcelable parcelable) {
        if (!this.f5741i) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void I(String str) {
        try {
            if (str != null) {
                byte[] bytes = str.getBytes(f5735m);
                this.f5739g.writeInt(bytes.length);
                this.f5739g.write(bytes);
            } else {
                this.f5739g.writeInt(-1);
            }
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a() {
        FieldBuffer fieldBuffer = this.f5740h;
        if (fieldBuffer != null) {
            try {
                if (fieldBuffer.f5746a.size() != 0) {
                    this.f5740h.a();
                }
                this.f5740h = null;
            } catch (IOException e2) {
                throw new VersionedParcel.ParcelException(e2);
            }
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel b() {
        return new VersionedParcelStream(this.f5738f, this.f5739g, this.f5724a, this.f5725b, this.f5726c);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean f() {
        return true;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean g() {
        try {
            return this.f5738f.readBoolean();
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] i() {
        try {
            int readInt = this.f5738f.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.f5738f.readFully(bArr);
            return bArr;
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected CharSequence k() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean m(int i2) {
        while (true) {
            try {
                int i3 = this.f5743k;
                if (i3 == i2) {
                    return true;
                }
                if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                    return false;
                }
                if (this.f5742j < this.f5744l) {
                    this.f5736d.skip(r2 - r1);
                }
                this.f5744l = -1;
                int readInt = this.f5736d.readInt();
                this.f5742j = 0;
                int i4 = readInt & 65535;
                if (i4 == 65535) {
                    i4 = this.f5736d.readInt();
                }
                this.f5743k = (readInt >> 16) & 65535;
                this.f5744l = i4;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int o() {
        try {
            return this.f5738f.readInt();
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public Parcelable q() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String s() {
        try {
            int readInt = this.f5738f.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.f5738f.readFully(bArr);
            return new String(bArr, f5735m);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void w(int i2) {
        a();
        FieldBuffer fieldBuffer = new FieldBuffer(i2, this.f5737e);
        this.f5740h = fieldBuffer;
        this.f5739g = fieldBuffer.f5747b;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void x(boolean z, boolean z2) {
        if (!z) {
            throw new RuntimeException("Serialization of this object is not allowed");
        }
        this.f5741i = z2;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void y(boolean z) {
        try {
            this.f5739g.writeBoolean(z);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }
}

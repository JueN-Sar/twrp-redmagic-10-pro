package okio;

import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    private static final long serialVersionUID = 1;
    final byte[] data;
    transient int hashCode;
    transient String utf8;
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final ByteString EMPTY = l(new byte[0]);

    ByteString(byte[] bArr) {
        this.data = bArr;
    }

    static int c(String str, int i2) {
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            if (i4 == i2) {
                return i3;
            }
            int codePointAt = str.codePointAt(i3);
            if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                return -1;
            }
            i4++;
            i3 += Character.charCount(codePointAt);
        }
        return str.length();
    }

    public static ByteString e(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(str.getBytes(Util.f19653a));
        byteString.utf8 = str;
        return byteString;
    }

    public static ByteString l(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString p(InputStream inputStream, int i2) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i2);
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, i3, i2 - i3);
            if (read == -1) {
                throw new EOFException();
            }
            i3 += read;
        }
        return new ByteString(bArr);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        ByteString p2 = p(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, p2.data);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    @Override // java.lang.Comparable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(ByteString byteString) {
        int r2 = r();
        int r3 = byteString.r();
        int min = Math.min(r2, r3);
        for (int i2 = 0; i2 < min; i2++) {
            int f2 = f(i2) & 255;
            int f3 = byteString.f(i2) & 255;
            if (f2 != f3) {
                return f2 < f3 ? -1 : 1;
            }
        }
        if (r2 == r3) {
            return 0;
        }
        return r2 < r3 ? -1 : 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int r2 = byteString.r();
            byte[] bArr = this.data;
            if (r2 == bArr.length && byteString.o(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public byte f(int i2) {
        return this.data[i2];
    }

    public String h() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i2 = 0;
        for (byte b2 : bArr) {
            int i3 = i2 + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i2] = cArr2[(b2 >> 4) & 15];
            i2 += 2;
            cArr[i3] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public int hashCode() {
        int i2 = this.hashCode;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = Arrays.hashCode(this.data);
        this.hashCode = hashCode;
        return hashCode;
    }

    byte[] j() {
        return this.data;
    }

    public boolean n(int i2, ByteString byteString, int i3, int i4) {
        return byteString.o(i3, this.data, i2, i4);
    }

    public boolean o(int i2, byte[] bArr, int i3, int i4) {
        if (i2 >= 0) {
            byte[] bArr2 = this.data;
            if (i2 <= bArr2.length - i4 && i3 >= 0 && i3 <= bArr.length - i4 && Util.a(bArr2, i2, bArr, i3, i4)) {
                return true;
            }
        }
        return false;
    }

    public int r() {
        return this.data.length;
    }

    public final boolean s(ByteString byteString) {
        return n(0, byteString, 0, byteString.r());
    }

    public ByteString t(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.data;
        if (i3 > bArr.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        }
        int i4 = i3 - i2;
        if (i4 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i2 == 0 && i3 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i2, bArr2, 0, i4);
        return new ByteString(bArr2);
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String u = u();
        int c2 = c(u, 64);
        if (c2 == -1) {
            if (this.data.length <= 64) {
                return "[hex=" + h() + "]";
            }
            return "[size=" + this.data.length + " hex=" + t(0, 64).h() + "…]";
        }
        String replace = u.substring(0, c2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if (c2 >= u.length()) {
            return "[text=" + replace + "]";
        }
        return "[size=" + this.data.length + " text=" + replace + "…]";
    }

    public String u() {
        String str = this.utf8;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, Util.f19653a);
        this.utf8 = str2;
        return str2;
    }
}

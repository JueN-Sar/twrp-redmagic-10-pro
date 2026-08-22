package androidx.emoji2.text.flatbuffer;

import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;

/* loaded from: classes.dex */
public class FlexBuffers {

    /* renamed from: a, reason: collision with root package name */
    private static final ReadBuf f3810a = new ArrayReadWriteBuf(new byte[]{0}, 1);

    public static class Blob extends Sized {

        /* renamed from: e, reason: collision with root package name */
        static final Blob f3811e = new Blob(FlexBuffers.f3810a, 1, 1);

        Blob(ReadBuf readBuf, int i2, int i3) {
            super(readBuf, i2, i3);
        }

        public static Blob c() {
            return f3811e;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder a(StringBuilder sb) {
            sb.append('\"');
            sb.append(this.f3815a.a(this.f3816b, b()));
            sb.append('\"');
            return sb;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Sized
        public /* bridge */ /* synthetic */ int b() {
            return super.b();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public String toString() {
            return this.f3815a.a(this.f3816b, b());
        }
    }

    public static class FlexBufferException extends RuntimeException {
        FlexBufferException(String str) {
            super(str);
        }
    }

    public static class Key extends Object {

        /* renamed from: d, reason: collision with root package name */
        private static final Key f3812d = new Key(FlexBuffers.f3810a, 0, 0);

        Key(ReadBuf readBuf, int i2, int i3) {
            super(readBuf, i2, i3);
        }

        public static Key c() {
            return f3812d;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder a(StringBuilder sb) {
            sb.append(toString());
            return sb;
        }

        public boolean equals(java.lang.Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            return key.f3816b == this.f3816b && key.f3817c == this.f3817c;
        }

        public int hashCode() {
            return this.f3817c ^ this.f3816b;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public String toString() {
            int i2 = this.f3816b;
            while (this.f3815a.get(i2) != 0) {
                i2++;
            }
            int i3 = this.f3816b;
            return this.f3815a.a(i3, i2 - i3);
        }
    }

    public static class KeyVector {

        /* renamed from: a, reason: collision with root package name */
        private final TypedVector f3813a;

        KeyVector(TypedVector typedVector) {
            this.f3813a = typedVector;
        }

        public Key a(int i2) {
            if (i2 >= b()) {
                return Key.f3812d;
            }
            TypedVector typedVector = this.f3813a;
            int i3 = typedVector.f3816b + (i2 * typedVector.f3817c);
            TypedVector typedVector2 = this.f3813a;
            ReadBuf readBuf = typedVector2.f3815a;
            return new Key(readBuf, FlexBuffers.g(readBuf, i3, typedVector2.f3817c), 1);
        }

        public int b() {
            return this.f3813a.b();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i2 = 0; i2 < this.f3813a.b(); i2++) {
                this.f3813a.d(i2).q(sb);
                if (i2 != this.f3813a.b() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static class Map extends Vector {

        /* renamed from: f, reason: collision with root package name */
        private static final Map f3814f = new Map(FlexBuffers.f3810a, 1, 1);

        Map(ReadBuf readBuf, int i2, int i3) {
            super(readBuf, i2, i3);
        }

        public static Map e() {
            return f3814f;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector, androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder a(StringBuilder sb) {
            sb.append("{ ");
            KeyVector f2 = f();
            int b2 = b();
            Vector g2 = g();
            for (int i2 = 0; i2 < b2; i2++) {
                sb.append('\"');
                sb.append(f2.a(i2).toString());
                sb.append("\" : ");
                sb.append(g2.d(i2).toString());
                if (i2 != b2 - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" }");
            return sb;
        }

        public KeyVector f() {
            int i2 = this.f3816b - (this.f3817c * 3);
            ReadBuf readBuf = this.f3815a;
            int g2 = FlexBuffers.g(readBuf, i2, this.f3817c);
            ReadBuf readBuf2 = this.f3815a;
            int i3 = this.f3817c;
            return new KeyVector(new TypedVector(readBuf, g2, FlexBuffers.j(readBuf2, i2 + i3, i3), 4));
        }

        public Vector g() {
            return new Vector(this.f3815a, this.f3816b, this.f3817c);
        }
    }

    private static abstract class Object {

        /* renamed from: a, reason: collision with root package name */
        ReadBuf f3815a;

        /* renamed from: b, reason: collision with root package name */
        int f3816b;

        /* renamed from: c, reason: collision with root package name */
        int f3817c;

        Object(ReadBuf readBuf, int i2, int i3) {
            this.f3815a = readBuf;
            this.f3816b = i2;
            this.f3817c = i3;
        }

        public abstract StringBuilder a(StringBuilder sb);

        public String toString() {
            return a(new StringBuilder(128)).toString();
        }
    }

    public static class Reference {

        /* renamed from: f, reason: collision with root package name */
        private static final Reference f3818f = new Reference(FlexBuffers.f3810a, 0, 1, 0);

        /* renamed from: a, reason: collision with root package name */
        private ReadBuf f3819a;

        /* renamed from: b, reason: collision with root package name */
        private int f3820b;

        /* renamed from: c, reason: collision with root package name */
        private int f3821c;

        /* renamed from: d, reason: collision with root package name */
        private int f3822d;

        /* renamed from: e, reason: collision with root package name */
        private int f3823e;

        Reference(ReadBuf readBuf, int i2, int i3, int i4) {
            this(readBuf, i2, i3, 1 << (i4 & 3), i4 >> 2);
        }

        public Blob b() {
            if (!k() && !o()) {
                return Blob.c();
            }
            ReadBuf readBuf = this.f3819a;
            return new Blob(readBuf, FlexBuffers.g(readBuf, this.f3820b, this.f3821c), this.f3822d);
        }

        public boolean c() {
            return l() ? this.f3819a.get(this.f3820b) != 0 : i() != 0;
        }

        public double d() {
            int i2 = this.f3823e;
            if (i2 == 3) {
                return FlexBuffers.i(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 1) {
                return FlexBuffers.j(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 != 2) {
                if (i2 == 5) {
                    return Double.parseDouble(h());
                }
                if (i2 == 6) {
                    ReadBuf readBuf = this.f3819a;
                    return FlexBuffers.j(readBuf, FlexBuffers.g(readBuf, this.f3820b, this.f3821c), this.f3822d);
                }
                if (i2 == 7) {
                    ReadBuf readBuf2 = this.f3819a;
                    return FlexBuffers.l(readBuf2, FlexBuffers.g(readBuf2, this.f3820b, this.f3821c), this.f3822d);
                }
                if (i2 == 8) {
                    ReadBuf readBuf3 = this.f3819a;
                    return FlexBuffers.i(readBuf3, FlexBuffers.g(readBuf3, this.f3820b, this.f3821c), this.f3822d);
                }
                if (i2 == 10) {
                    return j().b();
                }
                if (i2 != 26) {
                    return 0.0d;
                }
            }
            return FlexBuffers.l(this.f3819a, this.f3820b, this.f3821c);
        }

        public Key e() {
            if (!m()) {
                return Key.c();
            }
            ReadBuf readBuf = this.f3819a;
            return new Key(readBuf, FlexBuffers.g(readBuf, this.f3820b, this.f3821c), this.f3822d);
        }

        public long f() {
            int i2 = this.f3823e;
            if (i2 == 1) {
                return FlexBuffers.k(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 2) {
                return FlexBuffers.l(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 3) {
                return (long) FlexBuffers.i(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 5) {
                try {
                    return Long.parseLong(h());
                } catch (NumberFormatException unused) {
                    return 0L;
                }
            }
            if (i2 == 6) {
                ReadBuf readBuf = this.f3819a;
                return FlexBuffers.k(readBuf, FlexBuffers.g(readBuf, this.f3820b, this.f3821c), this.f3822d);
            }
            if (i2 == 7) {
                ReadBuf readBuf2 = this.f3819a;
                return FlexBuffers.l(readBuf2, FlexBuffers.g(readBuf2, this.f3820b, this.f3821c), this.f3821c);
            }
            if (i2 == 8) {
                ReadBuf readBuf3 = this.f3819a;
                return (long) FlexBuffers.i(readBuf3, FlexBuffers.g(readBuf3, this.f3820b, this.f3821c), this.f3822d);
            }
            if (i2 == 10) {
                return j().b();
            }
            if (i2 != 26) {
                return 0L;
            }
            return FlexBuffers.j(this.f3819a, this.f3820b, this.f3821c);
        }

        public Map g() {
            if (!n()) {
                return Map.e();
            }
            ReadBuf readBuf = this.f3819a;
            return new Map(readBuf, FlexBuffers.g(readBuf, this.f3820b, this.f3821c), this.f3822d);
        }

        public String h() {
            if (o()) {
                int g2 = FlexBuffers.g(this.f3819a, this.f3820b, this.f3821c);
                ReadBuf readBuf = this.f3819a;
                int i2 = this.f3822d;
                return this.f3819a.a(g2, (int) FlexBuffers.l(readBuf, g2 - i2, i2));
            }
            if (!m()) {
                return "";
            }
            int g3 = FlexBuffers.g(this.f3819a, this.f3820b, this.f3822d);
            int i3 = g3;
            while (this.f3819a.get(i3) != 0) {
                i3++;
            }
            return this.f3819a.a(g3, i3 - g3);
        }

        public long i() {
            int i2 = this.f3823e;
            if (i2 == 2) {
                return FlexBuffers.l(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 1) {
                return FlexBuffers.k(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 3) {
                return (long) FlexBuffers.i(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 10) {
                return j().b();
            }
            if (i2 == 26) {
                return FlexBuffers.j(this.f3819a, this.f3820b, this.f3821c);
            }
            if (i2 == 5) {
                return Long.parseLong(h());
            }
            if (i2 == 6) {
                ReadBuf readBuf = this.f3819a;
                return FlexBuffers.k(readBuf, FlexBuffers.g(readBuf, this.f3820b, this.f3821c), this.f3822d);
            }
            if (i2 == 7) {
                ReadBuf readBuf2 = this.f3819a;
                return FlexBuffers.l(readBuf2, FlexBuffers.g(readBuf2, this.f3820b, this.f3821c), this.f3822d);
            }
            if (i2 != 8) {
                return 0L;
            }
            ReadBuf readBuf3 = this.f3819a;
            return (long) FlexBuffers.i(readBuf3, FlexBuffers.g(readBuf3, this.f3820b, this.f3821c), this.f3821c);
        }

        public Vector j() {
            if (p()) {
                ReadBuf readBuf = this.f3819a;
                return new Vector(readBuf, FlexBuffers.g(readBuf, this.f3820b, this.f3821c), this.f3822d);
            }
            int i2 = this.f3823e;
            if (i2 == 15) {
                ReadBuf readBuf2 = this.f3819a;
                return new TypedVector(readBuf2, FlexBuffers.g(readBuf2, this.f3820b, this.f3821c), this.f3822d, 4);
            }
            if (!FlexBuffers.h(i2)) {
                return Vector.c();
            }
            ReadBuf readBuf3 = this.f3819a;
            return new TypedVector(readBuf3, FlexBuffers.g(readBuf3, this.f3820b, this.f3821c), this.f3822d, FlexBuffers.m(this.f3823e));
        }

        public boolean k() {
            return this.f3823e == 25;
        }

        public boolean l() {
            return this.f3823e == 26;
        }

        public boolean m() {
            return this.f3823e == 4;
        }

        public boolean n() {
            return this.f3823e == 9;
        }

        public boolean o() {
            return this.f3823e == 5;
        }

        public boolean p() {
            int i2 = this.f3823e;
            return i2 == 10 || i2 == 9;
        }

        StringBuilder q(StringBuilder sb) {
            int i2 = this.f3823e;
            if (i2 != 36) {
                switch (i2) {
                    case 0:
                        sb.append("null");
                        return sb;
                    case 1:
                    case 6:
                        sb.append(f());
                        return sb;
                    case 2:
                    case 7:
                        sb.append(i());
                        return sb;
                    case 3:
                    case 8:
                        sb.append(d());
                        return sb;
                    case 4:
                        Key e2 = e();
                        sb.append('\"');
                        StringBuilder a2 = e2.a(sb);
                        a2.append('\"');
                        return a2;
                    case 5:
                        sb.append('\"');
                        sb.append(h());
                        sb.append('\"');
                        return sb;
                    case 9:
                        return g().a(sb);
                    case 10:
                        return j().a(sb);
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        break;
                    case 16:
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                    case MlKitException.UNSUPPORTED /* 18 */:
                    case 19:
                    case 20:
                    case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                    case 22:
                    case 23:
                    case 24:
                        throw new FlexBufferException("not_implemented:" + this.f3823e);
                    case 25:
                        return b().a(sb);
                    case 26:
                        sb.append(c());
                        return sb;
                    default:
                        return sb;
                }
            }
            sb.append(j());
            return sb;
        }

        public String toString() {
            return q(new StringBuilder(128)).toString();
        }

        Reference(ReadBuf readBuf, int i2, int i3, int i4, int i5) {
            this.f3819a = readBuf;
            this.f3820b = i2;
            this.f3821c = i3;
            this.f3822d = i4;
            this.f3823e = i5;
        }
    }

    private static abstract class Sized extends Object {

        /* renamed from: d, reason: collision with root package name */
        protected final int f3824d;

        Sized(ReadBuf readBuf, int i2, int i3) {
            super(readBuf, i2, i3);
            this.f3824d = FlexBuffers.j(this.f3815a, i2 - i3, i3);
        }

        public int b() {
            return this.f3824d;
        }
    }

    public static class TypedVector extends Vector {

        /* renamed from: g, reason: collision with root package name */
        private static final TypedVector f3825g = new TypedVector(FlexBuffers.f3810a, 1, 1, 1);

        /* renamed from: f, reason: collision with root package name */
        private final int f3826f;

        TypedVector(ReadBuf readBuf, int i2, int i3, int i4) {
            super(readBuf, i2, i3);
            this.f3826f = i4;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector
        public Reference d(int i2) {
            if (i2 >= b()) {
                return Reference.f3818f;
            }
            return new Reference(this.f3815a, this.f3816b + (i2 * this.f3817c), this.f3817c, 1, this.f3826f);
        }
    }

    static class Unsigned {
        static int a(byte b2) {
            return b2 & 255;
        }

        static long b(int i2) {
            return i2 & 4294967295L;
        }

        static int c(short s2) {
            return s2 & 65535;
        }
    }

    public static class Vector extends Sized {

        /* renamed from: e, reason: collision with root package name */
        private static final Vector f3827e = new Vector(FlexBuffers.f3810a, 1, 1);

        Vector(ReadBuf readBuf, int i2, int i3) {
            super(readBuf, i2, i3);
        }

        public static Vector c() {
            return f3827e;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder a(StringBuilder sb) {
            sb.append("[ ");
            int b2 = b();
            for (int i2 = 0; i2 < b2; i2++) {
                d(i2).q(sb);
                if (i2 != b2 - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            return sb;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Sized
        public /* bridge */ /* synthetic */ int b() {
            return super.b();
        }

        public Reference d(int i2) {
            long b2 = b();
            long j2 = i2;
            if (j2 >= b2) {
                return Reference.f3818f;
            }
            return new Reference(this.f3815a, this.f3816b + (i2 * this.f3817c), this.f3817c, Unsigned.a(this.f3815a.get((int) (this.f3816b + (b2 * this.f3817c) + j2))));
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int g(ReadBuf readBuf, int i2, int i3) {
        return (int) (i2 - l(readBuf, i2, i3));
    }

    static boolean h(int i2) {
        return (i2 >= 11 && i2 <= 15) || i2 == 36;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double i(ReadBuf readBuf, int i2, int i3) {
        if (i3 == 4) {
            return readBuf.getFloat(i2);
        }
        if (i3 != 8) {
            return -1.0d;
        }
        return readBuf.getDouble(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int j(ReadBuf readBuf, int i2, int i3) {
        return (int) k(readBuf, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long k(ReadBuf readBuf, int i2, int i3) {
        int i4;
        if (i3 == 1) {
            i4 = readBuf.get(i2);
        } else if (i3 == 2) {
            i4 = readBuf.getShort(i2);
        } else {
            if (i3 != 4) {
                if (i3 != 8) {
                    return -1L;
                }
                return readBuf.getLong(i2);
            }
            i4 = readBuf.getInt(i2);
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long l(ReadBuf readBuf, int i2, int i3) {
        if (i3 == 1) {
            return Unsigned.a(readBuf.get(i2));
        }
        if (i3 == 2) {
            return Unsigned.c(readBuf.getShort(i2));
        }
        if (i3 == 4) {
            return Unsigned.b(readBuf.getInt(i2));
        }
        if (i3 != 8) {
            return -1L;
        }
        return readBuf.getLong(i2);
    }

    static int m(int i2) {
        return i2 - 10;
    }
}

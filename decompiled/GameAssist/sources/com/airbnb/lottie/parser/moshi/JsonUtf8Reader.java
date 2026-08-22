package com.airbnb.lottie.parser.moshi;

import com.airbnb.lottie.parser.moshi.JsonReader;
import com.google.mlkit.common.MlKitException;
import java.io.EOFException;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* loaded from: classes.dex */
final class JsonUtf8Reader extends JsonReader {
    private static final ByteString t = ByteString.e("'\\");
    private static final ByteString u = ByteString.e("\"\\");
    private static final ByteString v = ByteString.e("{}[]:, \n\t\r\f/\\;#=");
    private static final ByteString w = ByteString.e("\n\r");
    private static final ByteString x = ByteString.e("*/");

    /* renamed from: n, reason: collision with root package name */
    private final BufferedSource f9872n;

    /* renamed from: o, reason: collision with root package name */
    private final Buffer f9873o;

    /* renamed from: p, reason: collision with root package name */
    private int f9874p = 0;

    /* renamed from: q, reason: collision with root package name */
    private long f9875q;

    /* renamed from: r, reason: collision with root package name */
    private int f9876r;

    /* renamed from: s, reason: collision with root package name */
    private String f9877s;

    JsonUtf8Reader(BufferedSource bufferedSource) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        }
        this.f9872n = bufferedSource;
        this.f9873o = bufferedSource.b();
        D(6);
    }

    private void P() {
        if (!this.f9868k) {
            throw L("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private int R() {
        int[] iArr = this.f9865h;
        int i2 = this.f9864c;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int Y = Y(true);
            this.f9873o.readByte();
            if (Y != 44) {
                if (Y != 59) {
                    if (Y != 93) {
                        throw L("Unterminated array");
                    }
                    this.f9874p = 4;
                    return 4;
                }
                P();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5) {
                    int Y2 = Y(true);
                    this.f9873o.readByte();
                    if (Y2 != 44) {
                        if (Y2 != 59) {
                            if (Y2 != 125) {
                                throw L("Unterminated object");
                            }
                            this.f9874p = 2;
                            return 2;
                        }
                        P();
                    }
                }
                int Y3 = Y(true);
                if (Y3 == 34) {
                    this.f9873o.readByte();
                    this.f9874p = 13;
                    return 13;
                }
                if (Y3 == 39) {
                    this.f9873o.readByte();
                    P();
                    this.f9874p = 12;
                    return 12;
                }
                if (Y3 != 125) {
                    P();
                    if (!W((char) Y3)) {
                        throw L("Expected name");
                    }
                    this.f9874p = 14;
                    return 14;
                }
                if (i3 == 5) {
                    throw L("Expected name");
                }
                this.f9873o.readByte();
                this.f9874p = 2;
                return 2;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int Y4 = Y(true);
                this.f9873o.readByte();
                if (Y4 != 58) {
                    if (Y4 != 61) {
                        throw L("Expected ':'");
                    }
                    P();
                    if (this.f9872n.z(1L) && this.f9873o.j(0L) == 62) {
                        this.f9873o.readByte();
                    }
                }
            } else if (i3 == 6) {
                iArr[i2 - 1] = 7;
            } else if (i3 == 7) {
                if (Y(false) == -1) {
                    this.f9874p = 18;
                    return 18;
                }
                P();
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int Y5 = Y(true);
        if (Y5 == 34) {
            this.f9873o.readByte();
            this.f9874p = 9;
            return 9;
        }
        if (Y5 == 39) {
            P();
            this.f9873o.readByte();
            this.f9874p = 8;
            return 8;
        }
        if (Y5 != 44 && Y5 != 59) {
            if (Y5 == 91) {
                this.f9873o.readByte();
                this.f9874p = 3;
                return 3;
            }
            if (Y5 != 93) {
                if (Y5 == 123) {
                    this.f9873o.readByte();
                    this.f9874p = 1;
                    return 1;
                }
                int f0 = f0();
                if (f0 != 0) {
                    return f0;
                }
                int h0 = h0();
                if (h0 != 0) {
                    return h0;
                }
                if (!W(this.f9873o.j(0L))) {
                    throw L("Expected value");
                }
                P();
                this.f9874p = 10;
                return 10;
            }
            if (i3 == 1) {
                this.f9873o.readByte();
                this.f9874p = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw L("Unexpected value");
        }
        P();
        this.f9874p = 7;
        return 7;
    }

    private int T(String str, JsonReader.Options options) {
        int length = options.f9870a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(options.f9870a[i2])) {
                this.f9874p = 0;
                this.f9866i[this.f9864c - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    private boolean W(int i2) {
        if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 13 || i2 == 32) {
            return false;
        }
        if (i2 != 35) {
            if (i2 == 44) {
                return false;
            }
            if (i2 != 47 && i2 != 61) {
                if (i2 == 123 || i2 == 125 || i2 == 58) {
                    return false;
                }
                if (i2 != 59) {
                    switch (i2) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        P();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
    
        r6.f9873o.skip(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
    
        if (r2 != 47) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0074, code lost:
    
        if (r2 != 35) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0076, code lost:
    
        P();
        m0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007d, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0037, code lost:
    
        if (r6.f9872n.z(2) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003a, code lost:
    
        P();
        r3 = r6.f9873o.j(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0047, code lost:
    
        if (r3 == 42) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005a, code lost:
    
        r6.f9873o.readByte();
        r6.f9873o.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
    
        if (l0() == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0071, code lost:
    
        throw L("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0049, code lost:
    
        if (r3 == 47) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x004c, code lost:
    
        r6.f9873o.readByte();
        r6.f9873o.readByte();
        m0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x004b, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0039, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int Y(boolean r7) {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            okio.BufferedSource r2 = r6.f9872n
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.z(r4)
            if (r2 == 0) goto L80
            okio.Buffer r2 = r6.f9873o
            long r4 = (long) r1
            byte r2 = r2.j(r4)
            r4 = 10
            if (r2 == r4) goto L7e
            r4 = 32
            if (r2 == r4) goto L7e
            r4 = 13
            if (r2 == r4) goto L7e
            r4 = 9
            if (r2 != r4) goto L25
            goto L7e
        L25:
            okio.Buffer r3 = r6.f9873o
            long r4 = (long) r1
            r3.skip(r4)
            r1 = 47
            if (r2 != r1) goto L72
            okio.BufferedSource r3 = r6.f9872n
            r4 = 2
            boolean r3 = r3.z(r4)
            if (r3 != 0) goto L3a
            return r2
        L3a:
            r6.P()
            okio.Buffer r3 = r6.f9873o
            r4 = 1
            byte r3 = r3.j(r4)
            r4 = 42
            if (r3 == r4) goto L5a
            if (r3 == r1) goto L4c
            return r2
        L4c:
            okio.Buffer r1 = r6.f9873o
            r1.readByte()
            okio.Buffer r1 = r6.f9873o
            r1.readByte()
            r6.m0()
            goto L1
        L5a:
            okio.Buffer r1 = r6.f9873o
            r1.readByte()
            okio.Buffer r1 = r6.f9873o
            r1.readByte()
            boolean r1 = r6.l0()
            if (r1 == 0) goto L6b
            goto L1
        L6b:
            java.lang.String r7 = "Unterminated comment"
            com.airbnb.lottie.parser.moshi.JsonEncodingException r6 = r6.L(r7)
            throw r6
        L72:
            r1 = 35
            if (r2 != r1) goto L7d
            r6.P()
            r6.m0()
            goto L1
        L7d:
            return r2
        L7e:
            r1 = r3
            goto L2
        L80:
            if (r7 != 0) goto L84
            r6 = -1
            return r6
        L84:
            java.io.EOFException r6 = new java.io.EOFException
            java.lang.String r7 = "End of input"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonUtf8Reader.Y(boolean):int");
    }

    private String a0(ByteString byteString) {
        StringBuilder sb = null;
        while (true) {
            long q2 = this.f9872n.q(byteString);
            if (q2 == -1) {
                throw L("Unterminated string");
            }
            if (this.f9873o.j(q2) != 92) {
                if (sb == null) {
                    String L = this.f9873o.L(q2);
                    this.f9873o.readByte();
                    return L;
                }
                sb.append(this.f9873o.L(q2));
                this.f9873o.readByte();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.f9873o.L(q2));
            this.f9873o.readByte();
            sb.append(j0());
        }
    }

    private String e0() {
        long q2 = this.f9872n.q(v);
        Buffer buffer = this.f9873o;
        return q2 != -1 ? buffer.L(q2) : buffer.I();
    }

    private int f0() {
        String str;
        String str2;
        int i2;
        byte j2 = this.f9873o.j(0L);
        if (j2 == 116 || j2 == 84) {
            str = "true";
            str2 = "TRUE";
            i2 = 5;
        } else if (j2 == 102 || j2 == 70) {
            str = "false";
            str2 = "FALSE";
            i2 = 6;
        } else {
            if (j2 != 110 && j2 != 78) {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i2 = 7;
        }
        int length = str.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.f9872n.z(i4)) {
                return 0;
            }
            byte j3 = this.f9873o.j(i3);
            if (j3 != str.charAt(i3) && j3 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.f9872n.z(length + 1) && W(this.f9873o.j(length))) {
            return 0;
        }
        this.f9873o.skip(length);
        this.f9874p = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0081, code lost:
    
        if (W(r11) != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0083, code lost:
    
        if (r6 != 2) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0085, code lost:
    
        if (r7 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x008b, code lost:
    
        if (r8 != Long.MIN_VALUE) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008d, code lost:
    
        if (r10 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0091, code lost:
    
        if (r8 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0093, code lost:
    
        if (r10 != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0095, code lost:
    
        if (r10 == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0098, code lost:
    
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0099, code lost:
    
        r16.f9875q = r8;
        r16.f9873o.skip(r5);
        r16.f9874p = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a5, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a6, code lost:
    
        if (r6 == 2) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00a9, code lost:
    
        if (r6 == 4) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00ac, code lost:
    
        if (r6 != 7) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00af, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b0, code lost:
    
        r16.f9876r = r5;
        r16.f9874p = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b6, code lost:
    
        return 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b7, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int h0() {
        /*
            Method dump skipped, instructions count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonUtf8Reader.h0():int");
    }

    private char j0() {
        int i2;
        if (!this.f9872n.z(1L)) {
            throw L("Unterminated escape sequence");
        }
        byte readByte = this.f9873o.readByte();
        if (readByte == 10 || readByte == 34 || readByte == 39 || readByte == 47 || readByte == 92) {
            return (char) readByte;
        }
        if (readByte == 98) {
            return '\b';
        }
        if (readByte == 102) {
            return '\f';
        }
        if (readByte == 110) {
            return '\n';
        }
        if (readByte == 114) {
            return '\r';
        }
        if (readByte == 116) {
            return '\t';
        }
        if (readByte != 117) {
            if (this.f9868k) {
                return (char) readByte;
            }
            throw L("Invalid escape sequence: \\" + ((char) readByte));
        }
        if (!this.f9872n.z(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + i());
        }
        char c2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            byte j2 = this.f9873o.j(i3);
            char c3 = (char) (c2 << 4);
            if (j2 >= 48 && j2 <= 57) {
                i2 = j2 - 48;
            } else if (j2 >= 97 && j2 <= 102) {
                i2 = j2 - 87;
            } else {
                if (j2 < 65 || j2 > 70) {
                    throw L("\\u" + this.f9873o.L(4L));
                }
                i2 = j2 - 55;
            }
            c2 = (char) (c3 + i2);
        }
        this.f9873o.skip(4L);
        return c2;
    }

    private void k0(ByteString byteString) {
        while (true) {
            long q2 = this.f9872n.q(byteString);
            if (q2 == -1) {
                throw L("Unterminated string");
            }
            if (this.f9873o.j(q2) != 92) {
                this.f9873o.skip(q2 + 1);
                return;
            } else {
                this.f9873o.skip(q2 + 1);
                j0();
            }
        }
    }

    private boolean l0() {
        long m2 = this.f9872n.m(x);
        boolean z = m2 != -1;
        Buffer buffer = this.f9873o;
        buffer.skip(z ? m2 + r1.r() : buffer.size());
        return z;
    }

    private void m0() {
        long q2 = this.f9872n.q(w);
        Buffer buffer = this.f9873o;
        buffer.skip(q2 != -1 ? q2 + 1 : buffer.size());
    }

    private void n0() {
        long q2 = this.f9872n.q(v);
        Buffer buffer = this.f9873o;
        if (q2 == -1) {
            q2 = buffer.size();
        }
        buffer.skip(q2);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String A() {
        String L;
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 10) {
            L = e0();
        } else if (i2 == 9) {
            L = a0(u);
        } else if (i2 == 8) {
            L = a0(t);
        } else if (i2 == 11) {
            L = this.f9877s;
            this.f9877s = null;
        } else if (i2 == 16) {
            L = Long.toString(this.f9875q);
        } else {
            if (i2 != 17) {
                throw new JsonDataException("Expected a string but was " + C() + " at path " + i());
            }
            L = this.f9873o.L(this.f9876r);
        }
        this.f9874p = 0;
        int[] iArr = this.f9867j;
        int i3 = this.f9864c - 1;
        iArr[i3] = iArr[i3] + 1;
        return L;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public JsonReader.Token C() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        switch (i2) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case MlKitException.NETWORK_ISSUE /* 17 */:
                return JsonReader.Token.NUMBER;
            case MlKitException.UNSUPPORTED /* 18 */:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int E(JsonReader.Options options) {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 < 12 || i2 > 15) {
            return -1;
        }
        if (i2 == 15) {
            return T(this.f9877s, options);
        }
        int i0 = this.f9872n.i0(options.f9871b);
        if (i0 != -1) {
            this.f9874p = 0;
            this.f9866i[this.f9864c - 1] = options.f9870a[i0];
            return i0;
        }
        String str = this.f9866i[this.f9864c - 1];
        String t2 = t();
        int T = T(t2, options);
        if (T == -1) {
            this.f9874p = 15;
            this.f9877s = t2;
            this.f9866i[this.f9864c - 1] = str;
        }
        return T;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void F() {
        if (this.f9869l) {
            throw new JsonDataException("Cannot skip unexpected " + C() + " at " + i());
        }
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 14) {
            n0();
        } else if (i2 == 13) {
            k0(u);
        } else if (i2 == 12) {
            k0(t);
        } else if (i2 != 15) {
            throw new JsonDataException("Expected a name but was " + C() + " at path " + i());
        }
        this.f9874p = 0;
        this.f9866i[this.f9864c - 1] = "null";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void G() {
        if (this.f9869l) {
            throw new JsonDataException("Cannot skip unexpected " + C() + " at " + i());
        }
        int i2 = 0;
        do {
            int i3 = this.f9874p;
            if (i3 == 0) {
                i3 = R();
            }
            if (i3 == 3) {
                D(1);
            } else if (i3 == 1) {
                D(3);
            } else {
                if (i3 == 4) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + C() + " at path " + i());
                    }
                    this.f9864c--;
                } else if (i3 == 2) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + C() + " at path " + i());
                    }
                    this.f9864c--;
                } else if (i3 == 14 || i3 == 10) {
                    n0();
                } else if (i3 == 9 || i3 == 13) {
                    k0(u);
                } else if (i3 == 8 || i3 == 12) {
                    k0(t);
                } else if (i3 == 17) {
                    this.f9873o.skip(this.f9876r);
                } else if (i3 == 18) {
                    throw new JsonDataException("Expected a value but was " + C() + " at path " + i());
                }
                this.f9874p = 0;
            }
            i2++;
            this.f9874p = 0;
        } while (i2 != 0);
        int[] iArr = this.f9867j;
        int i4 = this.f9864c;
        int i5 = i4 - 1;
        iArr[i5] = iArr[i5] + 1;
        this.f9866i[i4 - 1] = "null";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void c() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 3) {
            D(1);
            this.f9867j[this.f9864c - 1] = 0;
            this.f9874p = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + C() + " at path " + i());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f9874p = 0;
        this.f9865h[0] = 8;
        this.f9864c = 1;
        this.f9873o.a();
        this.f9872n.close();
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void d() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 1) {
            D(3);
            this.f9874p = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + C() + " at path " + i());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void e() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + C() + " at path " + i());
        }
        int i3 = this.f9864c;
        this.f9864c = i3 - 1;
        int[] iArr = this.f9867j;
        int i4 = i3 - 2;
        iArr[i4] = iArr[i4] + 1;
        this.f9874p = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void h() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + C() + " at path " + i());
        }
        int i3 = this.f9864c;
        int i4 = i3 - 1;
        this.f9864c = i4;
        this.f9866i[i4] = null;
        int[] iArr = this.f9867j;
        int i5 = i3 - 2;
        iArr[i5] = iArr[i5] + 1;
        this.f9874p = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean j() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        return (i2 == 2 || i2 == 4 || i2 == 18) ? false : true;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean k() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 5) {
            this.f9874p = 0;
            int[] iArr = this.f9867j;
            int i3 = this.f9864c - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        }
        if (i2 == 6) {
            this.f9874p = 0;
            int[] iArr2 = this.f9867j;
            int i4 = this.f9864c - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + C() + " at path " + i());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public double p() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 16) {
            this.f9874p = 0;
            int[] iArr = this.f9867j;
            int i3 = this.f9864c - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.f9875q;
        }
        if (i2 == 17) {
            this.f9877s = this.f9873o.L(this.f9876r);
        } else if (i2 == 9) {
            this.f9877s = a0(u);
        } else if (i2 == 8) {
            this.f9877s = a0(t);
        } else if (i2 == 10) {
            this.f9877s = e0();
        } else if (i2 != 11) {
            throw new JsonDataException("Expected a double but was " + C() + " at path " + i());
        }
        this.f9874p = 11;
        try {
            double parseDouble = Double.parseDouble(this.f9877s);
            if (this.f9868k || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
                this.f9877s = null;
                this.f9874p = 0;
                int[] iArr2 = this.f9867j;
                int i4 = this.f9864c - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseDouble;
            }
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + parseDouble + " at path " + i());
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.f9877s + " at path " + i());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int s() {
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 16) {
            long j2 = this.f9875q;
            int i3 = (int) j2;
            if (j2 == i3) {
                this.f9874p = 0;
                int[] iArr = this.f9867j;
                int i4 = this.f9864c - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            }
            throw new JsonDataException("Expected an int but was " + this.f9875q + " at path " + i());
        }
        if (i2 == 17) {
            this.f9877s = this.f9873o.L(this.f9876r);
        } else if (i2 == 9 || i2 == 8) {
            String a0 = i2 == 9 ? a0(u) : a0(t);
            this.f9877s = a0;
            try {
                int parseInt = Integer.parseInt(a0);
                this.f9874p = 0;
                int[] iArr2 = this.f9867j;
                int i5 = this.f9864c - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (i2 != 11) {
            throw new JsonDataException("Expected an int but was " + C() + " at path " + i());
        }
        this.f9874p = 11;
        try {
            double parseDouble = Double.parseDouble(this.f9877s);
            int i6 = (int) parseDouble;
            if (i6 == parseDouble) {
                this.f9877s = null;
                this.f9874p = 0;
                int[] iArr3 = this.f9867j;
                int i7 = this.f9864c - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new JsonDataException("Expected an int but was " + this.f9877s + " at path " + i());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.f9877s + " at path " + i());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String t() {
        String str;
        int i2 = this.f9874p;
        if (i2 == 0) {
            i2 = R();
        }
        if (i2 == 14) {
            str = e0();
        } else if (i2 == 13) {
            str = a0(u);
        } else if (i2 == 12) {
            str = a0(t);
        } else {
            if (i2 != 15) {
                throw new JsonDataException("Expected a name but was " + C() + " at path " + i());
            }
            str = this.f9877s;
        }
        this.f9874p = 0;
        this.f9866i[this.f9864c - 1] = str;
        return str;
    }

    public String toString() {
        return "JsonReader(" + this.f9872n + ")";
    }
}

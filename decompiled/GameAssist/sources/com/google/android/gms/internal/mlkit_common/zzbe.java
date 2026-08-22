package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzbe implements ObjectEncoderContext {

    /* renamed from: f, reason: collision with root package name */
    private static final Charset f11429f = Charset.forName("UTF-8");

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11430g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f11431h;

    /* renamed from: i, reason: collision with root package name */
    private static final ObjectEncoder f11432i;

    /* renamed from: a, reason: collision with root package name */
    private OutputStream f11433a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f11434b;

    /* renamed from: c, reason: collision with root package name */
    private final Map f11435c;

    /* renamed from: d, reason: collision with root package name */
    private final ObjectEncoder f11436d;

    /* renamed from: e, reason: collision with root package name */
    private final zzbi f11437e = new zzbi(this);

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("key");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        f11430g = a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("value");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        f11431h = a3.b(zzayVar2.b()).a();
        f11432i = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_common.zzbd
            @Override // com.google.firebase.encoders.ObjectEncoder
            public final void a(Object obj, Object obj2) {
                zzbe.i((Map.Entry) obj, (ObjectEncoderContext) obj2);
            }
        };
    }

    zzbe(OutputStream outputStream, Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f11433a = outputStream;
        this.f11434b = map;
        this.f11435c = map2;
        this.f11436d = objectEncoder;
    }

    static /* synthetic */ void i(Map.Entry entry, ObjectEncoderContext objectEncoderContext) {
        objectEncoderContext.c(f11430g, entry.getKey());
        objectEncoderContext.c(f11431h, entry.getValue());
    }

    private static int j(FieldDescriptor fieldDescriptor) {
        zzbc zzbcVar = (zzbc) fieldDescriptor.c(zzbc.class);
        if (zzbcVar != null) {
            return zzbcVar.zza();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final long k(ObjectEncoder objectEncoder, Object obj) {
        zzaz zzazVar = new zzaz();
        try {
            OutputStream outputStream = this.f11433a;
            this.f11433a = zzazVar;
            try {
                objectEncoder.a(obj, this);
                this.f11433a = outputStream;
                long a2 = zzazVar.a();
                zzazVar.close();
                return a2;
            } catch (Throwable th) {
                this.f11433a = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                zzazVar.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    private static zzbc l(FieldDescriptor fieldDescriptor) {
        zzbc zzbcVar = (zzbc) fieldDescriptor.c(zzbc.class);
        if (zzbcVar != null) {
            return zzbcVar;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final zzbe m(ObjectEncoder objectEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        long k2 = k(objectEncoder, obj);
        if (z && k2 == 0) {
            return this;
        }
        p((j(fieldDescriptor) << 3) | 2);
        q(k2);
        objectEncoder.a(obj, this);
        return this;
    }

    private final zzbe n(ValueEncoder valueEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        this.f11437e.a(fieldDescriptor, z);
        valueEncoder.a(obj, this.f11437e);
        return this;
    }

    private static ByteBuffer o(int i2) {
        return ByteBuffer.allocate(i2).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void p(int i2) {
        while (true) {
            int i3 = i2 & 127;
            if ((i2 & (-128)) == 0) {
                this.f11433a.write(i3);
                return;
            } else {
                this.f11433a.write(i3 | 128);
                i2 >>>= 7;
            }
        }
    }

    private final void q(long j2) {
        while (true) {
            int i2 = ((int) j2) & 127;
            if (((-128) & j2) == 0) {
                this.f11433a.write(i2);
                return;
            } else {
                this.f11433a.write(i2 | 128);
                j2 >>>= 7;
            }
        }
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final /* synthetic */ ObjectEncoderContext a(FieldDescriptor fieldDescriptor, long j2) {
        g(fieldDescriptor, j2, true);
        return this;
    }

    final ObjectEncoderContext b(FieldDescriptor fieldDescriptor, double d2, boolean z) {
        if (z && d2 == 0.0d) {
            return this;
        }
        p((j(fieldDescriptor) << 3) | 1);
        this.f11433a.write(o(8).putDouble(d2).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final ObjectEncoderContext c(FieldDescriptor fieldDescriptor, Object obj) {
        e(fieldDescriptor, obj, true);
        return this;
    }

    final ObjectEncoderContext d(FieldDescriptor fieldDescriptor, float f2, boolean z) {
        if (z && f2 == 0.0f) {
            return this;
        }
        p((j(fieldDescriptor) << 3) | 5);
        this.f11433a.write(o(4).putFloat(f2).array());
        return this;
    }

    final ObjectEncoderContext e(FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z || charSequence.length() != 0) {
                    p((j(fieldDescriptor) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(f11429f);
                    p(bytes.length);
                    this.f11433a.write(bytes);
                    return this;
                }
            } else if (obj instanceof Collection) {
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    e(fieldDescriptor, it.next(), false);
                }
            } else if (obj instanceof Map) {
                Iterator it2 = ((Map) obj).entrySet().iterator();
                while (it2.hasNext()) {
                    m(f11432i, fieldDescriptor, (Map.Entry) it2.next(), false);
                }
            } else {
                if (obj instanceof Double) {
                    b(fieldDescriptor, ((Double) obj).doubleValue(), z);
                    return this;
                }
                if (obj instanceof Float) {
                    d(fieldDescriptor, ((Float) obj).floatValue(), z);
                    return this;
                }
                if (obj instanceof Number) {
                    g(fieldDescriptor, ((Number) obj).longValue(), z);
                    return this;
                }
                if (obj instanceof Boolean) {
                    f(fieldDescriptor, ((Boolean) obj).booleanValue() ? 1 : 0, z);
                    return this;
                }
                if (!(obj instanceof byte[])) {
                    ObjectEncoder objectEncoder = (ObjectEncoder) this.f11434b.get(obj.getClass());
                    if (objectEncoder != null) {
                        m(objectEncoder, fieldDescriptor, obj, z);
                        return this;
                    }
                    ValueEncoder valueEncoder = (ValueEncoder) this.f11435c.get(obj.getClass());
                    if (valueEncoder != null) {
                        n(valueEncoder, fieldDescriptor, obj, z);
                        return this;
                    }
                    if (obj instanceof zzba) {
                        f(fieldDescriptor, ((zzba) obj).zza(), true);
                        return this;
                    }
                    if (obj instanceof Enum) {
                        f(fieldDescriptor, ((Enum) obj).ordinal(), true);
                        return this;
                    }
                    m(this.f11436d, fieldDescriptor, obj, z);
                    return this;
                }
                byte[] bArr = (byte[]) obj;
                if (!z || bArr.length != 0) {
                    p((j(fieldDescriptor) << 3) | 2);
                    p(bArr.length);
                    this.f11433a.write(bArr);
                    return this;
                }
            }
        }
        return this;
    }

    final zzbe f(FieldDescriptor fieldDescriptor, int i2, boolean z) {
        if (!z || i2 != 0) {
            zzbc l2 = l(fieldDescriptor);
            int ordinal = l2.zzb().ordinal();
            if (ordinal == 0) {
                p(l2.zza() << 3);
                p(i2);
            } else if (ordinal == 1) {
                p(l2.zza() << 3);
                p((i2 + i2) ^ (i2 >> 31));
            } else if (ordinal == 2) {
                p((l2.zza() << 3) | 5);
                this.f11433a.write(o(4).putInt(i2).array());
            }
        }
        return this;
    }

    final zzbe g(FieldDescriptor fieldDescriptor, long j2, boolean z) {
        if (!z || j2 != 0) {
            zzbc l2 = l(fieldDescriptor);
            int ordinal = l2.zzb().ordinal();
            if (ordinal == 0) {
                p(l2.zza() << 3);
                q(j2);
            } else if (ordinal == 1) {
                p(l2.zza() << 3);
                q((j2 >> 63) ^ (j2 + j2));
            } else if (ordinal == 2) {
                p((l2.zza() << 3) | 1);
                this.f11433a.write(o(8).putLong(j2).array());
            }
        }
        return this;
    }

    final zzbe h(Object obj) {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.f11434b.get(obj.getClass());
        if (objectEncoder == null) {
            throw new EncodingException("No encoder for ".concat(String.valueOf(obj.getClass())));
        }
        objectEncoder.a(obj, this);
        return this;
    }
}

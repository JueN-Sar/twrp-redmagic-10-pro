package com.google.android.gms.internal.mlkit_vision_text_common;

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
final class zzcz implements ObjectEncoderContext {

    /* renamed from: f, reason: collision with root package name */
    private static final Charset f13139f = Charset.forName("UTF-8");

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f13140g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f13141h;

    /* renamed from: i, reason: collision with root package name */
    private static final ObjectEncoder f13142i;

    /* renamed from: a, reason: collision with root package name */
    private OutputStream f13143a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f13144b;

    /* renamed from: c, reason: collision with root package name */
    private final Map f13145c;

    /* renamed from: d, reason: collision with root package name */
    private final ObjectEncoder f13146d;

    /* renamed from: e, reason: collision with root package name */
    private final zzdd f13147e = new zzdd(this);

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("key");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13140g = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("value");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13141h = a3.b(zzctVar2.b()).a();
        f13142i = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzcy
            @Override // com.google.firebase.encoders.ObjectEncoder
            public final void a(Object obj, Object obj2) {
                zzcz.i((Map.Entry) obj, (ObjectEncoderContext) obj2);
            }
        };
    }

    zzcz(OutputStream outputStream, Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f13143a = outputStream;
        this.f13144b = map;
        this.f13145c = map2;
        this.f13146d = objectEncoder;
    }

    static /* synthetic */ void i(Map.Entry entry, ObjectEncoderContext objectEncoderContext) {
        objectEncoderContext.c(f13140g, entry.getKey());
        objectEncoderContext.c(f13141h, entry.getValue());
    }

    private static int j(FieldDescriptor fieldDescriptor) {
        zzcx zzcxVar = (zzcx) fieldDescriptor.c(zzcx.class);
        if (zzcxVar != null) {
            return zzcxVar.zza();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final long k(ObjectEncoder objectEncoder, Object obj) {
        zzcu zzcuVar = new zzcu();
        try {
            OutputStream outputStream = this.f13143a;
            this.f13143a = zzcuVar;
            try {
                objectEncoder.a(obj, this);
                this.f13143a = outputStream;
                long a2 = zzcuVar.a();
                zzcuVar.close();
                return a2;
            } catch (Throwable th) {
                this.f13143a = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                zzcuVar.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    private static zzcx l(FieldDescriptor fieldDescriptor) {
        zzcx zzcxVar = (zzcx) fieldDescriptor.c(zzcx.class);
        if (zzcxVar != null) {
            return zzcxVar;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final zzcz m(ObjectEncoder objectEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        long k2 = k(objectEncoder, obj);
        if (z && k2 == 0) {
            return this;
        }
        p((j(fieldDescriptor) << 3) | 2);
        q(k2);
        objectEncoder.a(obj, this);
        return this;
    }

    private final zzcz n(ValueEncoder valueEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        this.f13147e.a(fieldDescriptor, z);
        valueEncoder.a(obj, this.f13147e);
        return this;
    }

    private static ByteBuffer o(int i2) {
        return ByteBuffer.allocate(i2).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void p(int i2) {
        while (true) {
            int i3 = i2 & 127;
            if ((i2 & (-128)) == 0) {
                this.f13143a.write(i3);
                return;
            } else {
                this.f13143a.write(i3 | 128);
                i2 >>>= 7;
            }
        }
    }

    private final void q(long j2) {
        while (true) {
            int i2 = ((int) j2) & 127;
            if (((-128) & j2) == 0) {
                this.f13143a.write(i2);
                return;
            } else {
                this.f13143a.write(i2 | 128);
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
        this.f13143a.write(o(8).putDouble(d2).array());
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
        this.f13143a.write(o(4).putFloat(f2).array());
        return this;
    }

    final ObjectEncoderContext e(FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z || charSequence.length() != 0) {
                    p((j(fieldDescriptor) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(f13139f);
                    p(bytes.length);
                    this.f13143a.write(bytes);
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
                    m(f13142i, fieldDescriptor, (Map.Entry) it2.next(), false);
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
                    ObjectEncoder objectEncoder = (ObjectEncoder) this.f13144b.get(obj.getClass());
                    if (objectEncoder != null) {
                        m(objectEncoder, fieldDescriptor, obj, z);
                        return this;
                    }
                    ValueEncoder valueEncoder = (ValueEncoder) this.f13145c.get(obj.getClass());
                    if (valueEncoder != null) {
                        n(valueEncoder, fieldDescriptor, obj, z);
                        return this;
                    }
                    if (obj instanceof zzcv) {
                        f(fieldDescriptor, ((zzcv) obj).zza(), true);
                        return this;
                    }
                    if (obj instanceof Enum) {
                        f(fieldDescriptor, ((Enum) obj).ordinal(), true);
                        return this;
                    }
                    m(this.f13146d, fieldDescriptor, obj, z);
                    return this;
                }
                byte[] bArr = (byte[]) obj;
                if (!z || bArr.length != 0) {
                    p((j(fieldDescriptor) << 3) | 2);
                    p(bArr.length);
                    this.f13143a.write(bArr);
                    return this;
                }
            }
        }
        return this;
    }

    final zzcz f(FieldDescriptor fieldDescriptor, int i2, boolean z) {
        if (!z || i2 != 0) {
            zzcx l2 = l(fieldDescriptor);
            int ordinal = l2.zzb().ordinal();
            if (ordinal == 0) {
                p(l2.zza() << 3);
                p(i2);
            } else if (ordinal == 1) {
                p(l2.zza() << 3);
                p((i2 + i2) ^ (i2 >> 31));
            } else if (ordinal == 2) {
                p((l2.zza() << 3) | 5);
                this.f13143a.write(o(4).putInt(i2).array());
            }
        }
        return this;
    }

    final zzcz g(FieldDescriptor fieldDescriptor, long j2, boolean z) {
        if (!z || j2 != 0) {
            zzcx l2 = l(fieldDescriptor);
            int ordinal = l2.zzb().ordinal();
            if (ordinal == 0) {
                p(l2.zza() << 3);
                q(j2);
            } else if (ordinal == 1) {
                p(l2.zza() << 3);
                q((j2 >> 63) ^ (j2 + j2));
            } else if (ordinal == 2) {
                p((l2.zza() << 3) | 1);
                this.f13143a.write(o(8).putLong(j2).array());
            }
        }
        return this;
    }

    final zzcz h(Object obj) {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.f13144b.get(obj.getClass());
        if (objectEncoder == null) {
            throw new EncodingException("No encoder for ".concat(String.valueOf(obj.getClass())));
        }
        objectEncoder.a(obj, this);
        return this;
    }
}

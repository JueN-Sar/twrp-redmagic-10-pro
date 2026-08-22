package com.google.android.gms.internal.mlkit_vision_common;

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
final class zzak implements ObjectEncoderContext {

    /* renamed from: f, reason: collision with root package name */
    private static final Charset f11866f = Charset.forName("UTF-8");

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f11867g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f11868h;

    /* renamed from: i, reason: collision with root package name */
    private static final ObjectEncoder f11869i;

    /* renamed from: a, reason: collision with root package name */
    private OutputStream f11870a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f11871b;

    /* renamed from: c, reason: collision with root package name */
    private final Map f11872c;

    /* renamed from: d, reason: collision with root package name */
    private final ObjectEncoder f11873d;

    /* renamed from: e, reason: collision with root package name */
    private final zzao f11874e = new zzao(this);

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("key");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f11867g = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("value");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f11868h = a3.b(zzaeVar2.b()).a();
        f11869i = new ObjectEncoder() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzaj
            @Override // com.google.firebase.encoders.ObjectEncoder
            public final void a(Object obj, Object obj2) {
                zzak.i((Map.Entry) obj, (ObjectEncoderContext) obj2);
            }
        };
    }

    zzak(OutputStream outputStream, Map map, Map map2, ObjectEncoder objectEncoder) {
        this.f11870a = outputStream;
        this.f11871b = map;
        this.f11872c = map2;
        this.f11873d = objectEncoder;
    }

    static /* synthetic */ void i(Map.Entry entry, ObjectEncoderContext objectEncoderContext) {
        objectEncoderContext.c(f11867g, entry.getKey());
        objectEncoderContext.c(f11868h, entry.getValue());
    }

    private static int j(FieldDescriptor fieldDescriptor) {
        zzai zzaiVar = (zzai) fieldDescriptor.c(zzai.class);
        if (zzaiVar != null) {
            return zzaiVar.zza();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final long k(ObjectEncoder objectEncoder, Object obj) {
        zzaf zzafVar = new zzaf();
        try {
            OutputStream outputStream = this.f11870a;
            this.f11870a = zzafVar;
            try {
                objectEncoder.a(obj, this);
                this.f11870a = outputStream;
                long a2 = zzafVar.a();
                zzafVar.close();
                return a2;
            } catch (Throwable th) {
                this.f11870a = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                zzafVar.close();
            } catch (Throwable th3) {
                try {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th2, th3);
                } catch (Exception unused) {
                }
            }
            throw th2;
        }
    }

    private static zzai l(FieldDescriptor fieldDescriptor) {
        zzai zzaiVar = (zzai) fieldDescriptor.c(zzai.class);
        if (zzaiVar != null) {
            return zzaiVar;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final zzak m(ObjectEncoder objectEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        long k2 = k(objectEncoder, obj);
        if (z && k2 == 0) {
            return this;
        }
        p((j(fieldDescriptor) << 3) | 2);
        q(k2);
        objectEncoder.a(obj, this);
        return this;
    }

    private final zzak n(ValueEncoder valueEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        this.f11874e.a(fieldDescriptor, z);
        valueEncoder.a(obj, this.f11874e);
        return this;
    }

    private static ByteBuffer o(int i2) {
        return ByteBuffer.allocate(i2).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void p(int i2) {
        while ((i2 & (-128)) != 0) {
            this.f11870a.write((i2 & 127) | 128);
            i2 >>>= 7;
        }
        this.f11870a.write(i2 & 127);
    }

    private final void q(long j2) {
        while (((-128) & j2) != 0) {
            this.f11870a.write((((int) j2) & 127) | 128);
            j2 >>>= 7;
        }
        this.f11870a.write(((int) j2) & 127);
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
        this.f11870a.write(o(8).putDouble(d2).array());
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
        this.f11870a.write(o(4).putFloat(f2).array());
        return this;
    }

    final ObjectEncoderContext e(FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z && charSequence.length() == 0) {
                return this;
            }
            p((j(fieldDescriptor) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(f11866f);
            p(bytes.length);
            this.f11870a.write(bytes);
            return this;
        }
        if (obj instanceof Collection) {
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                e(fieldDescriptor, it.next(), false);
            }
            return this;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                m(f11869i, fieldDescriptor, (Map.Entry) it2.next(), false);
            }
            return this;
        }
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
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (z && bArr.length == 0) {
                return this;
            }
            p((j(fieldDescriptor) << 3) | 2);
            p(bArr.length);
            this.f11870a.write(bArr);
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.f11871b.get(obj.getClass());
        if (objectEncoder != null) {
            m(objectEncoder, fieldDescriptor, obj, z);
            return this;
        }
        ValueEncoder valueEncoder = (ValueEncoder) this.f11872c.get(obj.getClass());
        if (valueEncoder != null) {
            n(valueEncoder, fieldDescriptor, obj, z);
            return this;
        }
        if (obj instanceof zzag) {
            f(fieldDescriptor, ((zzag) obj).zza(), true);
            return this;
        }
        if (obj instanceof Enum) {
            f(fieldDescriptor, ((Enum) obj).ordinal(), true);
            return this;
        }
        m(this.f11873d, fieldDescriptor, obj, z);
        return this;
    }

    final zzak f(FieldDescriptor fieldDescriptor, int i2, boolean z) {
        if (z && i2 == 0) {
            return this;
        }
        zzai l2 = l(fieldDescriptor);
        zzah zzahVar = zzah.DEFAULT;
        int ordinal = l2.zzb().ordinal();
        if (ordinal == 0) {
            p(l2.zza() << 3);
            p(i2);
        } else if (ordinal == 1) {
            p(l2.zza() << 3);
            p((i2 + i2) ^ (i2 >> 31));
        } else if (ordinal == 2) {
            p((l2.zza() << 3) | 5);
            this.f11870a.write(o(4).putInt(i2).array());
        }
        return this;
    }

    final zzak g(FieldDescriptor fieldDescriptor, long j2, boolean z) {
        if (z && j2 == 0) {
            return this;
        }
        zzai l2 = l(fieldDescriptor);
        zzah zzahVar = zzah.DEFAULT;
        int ordinal = l2.zzb().ordinal();
        if (ordinal == 0) {
            p(l2.zza() << 3);
            q(j2);
        } else if (ordinal == 1) {
            p(l2.zza() << 3);
            q((j2 >> 63) ^ (j2 + j2));
        } else if (ordinal == 2) {
            p((l2.zza() << 3) | 1);
            this.f11870a.write(o(8).putLong(j2).array());
        }
        return this;
    }

    final zzak h(Object obj) {
        if (obj == null) {
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.f11871b.get(obj.getClass());
        if (objectEncoder == null) {
            throw new EncodingException("No encoder for ".concat(String.valueOf(obj.getClass())));
        }
        objectEncoder.a(obj, this);
        return this;
    }
}

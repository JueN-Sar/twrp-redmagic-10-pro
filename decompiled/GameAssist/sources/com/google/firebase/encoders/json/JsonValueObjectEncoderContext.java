package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {

    /* renamed from: a, reason: collision with root package name */
    private JsonValueObjectEncoderContext f15879a = null;

    /* renamed from: b, reason: collision with root package name */
    private boolean f15880b = true;

    /* renamed from: c, reason: collision with root package name */
    private final JsonWriter f15881c;

    /* renamed from: d, reason: collision with root package name */
    private final Map f15882d;

    /* renamed from: e, reason: collision with root package name */
    private final Map f15883e;

    /* renamed from: f, reason: collision with root package name */
    private final ObjectEncoder f15884f;

    /* renamed from: g, reason: collision with root package name */
    private final boolean f15885g;

    JsonValueObjectEncoderContext(Writer writer, Map map, Map map2, ObjectEncoder objectEncoder, boolean z) {
        this.f15881c = new JsonWriter(writer);
        this.f15882d = map;
        this.f15883e = map2;
        this.f15884f = objectEncoder;
        this.f15885g = z;
    }

    private boolean l(Object obj) {
        return obj == null || obj.getClass().isArray() || (obj instanceof Collection) || (obj instanceof Date) || (obj instanceof Enum) || (obj instanceof Number);
    }

    private JsonValueObjectEncoderContext o(String str, Object obj) {
        q();
        this.f15881c.name(str);
        if (obj != null) {
            return f(obj, false);
        }
        this.f15881c.nullValue();
        return this;
    }

    private JsonValueObjectEncoderContext p(String str, Object obj) {
        if (obj == null) {
            return this;
        }
        q();
        this.f15881c.name(str);
        return f(obj, false);
    }

    private void q() {
        if (!this.f15880b) {
            throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
        }
        JsonValueObjectEncoderContext jsonValueObjectEncoderContext = this.f15879a;
        if (jsonValueObjectEncoderContext != null) {
            jsonValueObjectEncoderContext.q();
            this.f15879a.f15880b = false;
            this.f15879a = null;
            this.f15881c.endObject();
        }
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext a(FieldDescriptor fieldDescriptor, long j2) {
        return h(fieldDescriptor.b(), j2);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext c(FieldDescriptor fieldDescriptor, Object obj) {
        return i(fieldDescriptor.b(), obj);
    }

    public JsonValueObjectEncoderContext e(long j2) {
        q();
        this.f15881c.value(j2);
        return this;
    }

    JsonValueObjectEncoderContext f(Object obj, boolean z) {
        if (z && l(obj)) {
            throw new EncodingException(String.format("%s cannot be encoded inline", obj == null ? null : obj.getClass()));
        }
        if (obj == null) {
            this.f15881c.nullValue();
            return this;
        }
        if (obj instanceof Number) {
            this.f15881c.value((Number) obj);
            return this;
        }
        int i2 = 0;
        if (!obj.getClass().isArray()) {
            if (obj instanceof Collection) {
                this.f15881c.beginArray();
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    f(it.next(), false);
                }
                this.f15881c.endArray();
                return this;
            }
            if (obj instanceof Map) {
                this.f15881c.beginObject();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    Object key = entry.getKey();
                    try {
                        i((String) key, entry.getValue());
                    } catch (ClassCastException e2) {
                        throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e2);
                    }
                }
                this.f15881c.endObject();
                return this;
            }
            ObjectEncoder objectEncoder = (ObjectEncoder) this.f15882d.get(obj.getClass());
            if (objectEncoder != null) {
                return n(objectEncoder, obj, z);
            }
            ValueEncoder valueEncoder = (ValueEncoder) this.f15883e.get(obj.getClass());
            if (valueEncoder != null) {
                valueEncoder.a(obj, this);
                return this;
            }
            if (!(obj instanceof Enum)) {
                return n(this.f15884f, obj, z);
            }
            b(((Enum) obj).name());
            return this;
        }
        if (obj instanceof byte[]) {
            return k((byte[]) obj);
        }
        this.f15881c.beginArray();
        if (obj instanceof int[]) {
            int length = ((int[]) obj).length;
            while (i2 < length) {
                this.f15881c.value(r6[i2]);
                i2++;
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            int length2 = jArr.length;
            while (i2 < length2) {
                e(jArr[i2]);
                i2++;
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length3 = dArr.length;
            while (i2 < length3) {
                this.f15881c.value(dArr[i2]);
                i2++;
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            int length4 = zArr.length;
            while (i2 < length4) {
                this.f15881c.value(zArr[i2]);
                i2++;
            }
        } else if (obj instanceof Number[]) {
            for (Number number : (Number[]) obj) {
                f(number, false);
            }
        } else {
            for (Object obj2 : (Object[]) obj) {
                f(obj2, false);
            }
        }
        this.f15881c.endArray();
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public JsonValueObjectEncoderContext b(String str) {
        q();
        this.f15881c.value(str);
        return this;
    }

    public JsonValueObjectEncoderContext h(String str, long j2) {
        q();
        this.f15881c.name(str);
        return e(j2);
    }

    public JsonValueObjectEncoderContext i(String str, Object obj) {
        return this.f15885g ? p(str, obj) : o(str, obj);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public JsonValueObjectEncoderContext d(boolean z) {
        q();
        this.f15881c.value(z);
        return this;
    }

    public JsonValueObjectEncoderContext k(byte[] bArr) {
        q();
        if (bArr == null) {
            this.f15881c.nullValue();
        } else {
            this.f15881c.value(Base64.encodeToString(bArr, 2));
        }
        return this;
    }

    void m() {
        q();
        this.f15881c.flush();
    }

    JsonValueObjectEncoderContext n(ObjectEncoder objectEncoder, Object obj, boolean z) {
        if (!z) {
            this.f15881c.beginObject();
        }
        objectEncoder.a(obj, this);
        if (!z) {
            this.f15881c.endObject();
        }
        return this;
    }
}

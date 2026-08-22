package com.google.firebase.encoders.json;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes.dex */
public final class JsonDataEncoderBuilder implements EncoderConfig<JsonDataEncoderBuilder> {

    /* renamed from: e, reason: collision with root package name */
    private static final ObjectEncoder f15866e = JsonDataEncoderBuilder$$Lambda$1.b();

    /* renamed from: f, reason: collision with root package name */
    private static final ValueEncoder f15867f = JsonDataEncoderBuilder$$Lambda$4.b();

    /* renamed from: g, reason: collision with root package name */
    private static final ValueEncoder f15868g = JsonDataEncoderBuilder$$Lambda$5.b();

    /* renamed from: h, reason: collision with root package name */
    private static final TimestampEncoder f15869h = new TimestampEncoder();

    /* renamed from: a, reason: collision with root package name */
    private final Map f15870a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map f15871b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private ObjectEncoder f15872c = f15866e;

    /* renamed from: d, reason: collision with root package name */
    private boolean f15873d = false;

    private static final class TimestampEncoder implements ValueEncoder<Date> {

        /* renamed from: a, reason: collision with root package name */
        private static final DateFormat f15878a;

        static {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            f15878a = simpleDateFormat;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        private TimestampEncoder() {
        }

        @Override // com.google.firebase.encoders.ValueEncoder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Date date, ValueEncoderContext valueEncoderContext) {
            valueEncoderContext.b(f15878a.format(date));
        }
    }

    public JsonDataEncoderBuilder() {
        m(String.class, f15867f);
        m(Boolean.class, f15868g);
        m(Date.class, f15869h);
    }

    static /* synthetic */ void i(Object obj, ObjectEncoderContext objectEncoderContext) {
        throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
    }

    public DataEncoder f() {
        return new DataEncoder() { // from class: com.google.firebase.encoders.json.JsonDataEncoderBuilder.1
            @Override // com.google.firebase.encoders.DataEncoder
            public void a(Object obj, Writer writer) {
                JsonValueObjectEncoderContext jsonValueObjectEncoderContext = new JsonValueObjectEncoderContext(writer, JsonDataEncoderBuilder.this.f15870a, JsonDataEncoderBuilder.this.f15871b, JsonDataEncoderBuilder.this.f15872c, JsonDataEncoderBuilder.this.f15873d);
                jsonValueObjectEncoderContext.f(obj, false);
                jsonValueObjectEncoderContext.m();
            }

            @Override // com.google.firebase.encoders.DataEncoder
            public String b(Object obj) {
                StringWriter stringWriter = new StringWriter();
                try {
                    a(obj, stringWriter);
                } catch (IOException unused) {
                }
                return stringWriter.toString();
            }
        };
    }

    public JsonDataEncoderBuilder g(Configurator configurator) {
        configurator.a(this);
        return this;
    }

    public JsonDataEncoderBuilder h(boolean z) {
        this.f15873d = z;
        return this;
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public JsonDataEncoderBuilder a(Class cls, ObjectEncoder objectEncoder) {
        this.f15870a.put(cls, objectEncoder);
        this.f15871b.remove(cls);
        return this;
    }

    public JsonDataEncoderBuilder m(Class cls, ValueEncoder valueEncoder) {
        this.f15871b.put(cls, valueEncoder);
        this.f15870a.remove(cls);
        return this;
    }
}

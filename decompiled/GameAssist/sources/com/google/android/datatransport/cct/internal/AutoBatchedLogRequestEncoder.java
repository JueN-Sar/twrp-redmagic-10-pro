package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;

/* loaded from: classes.dex */
public final class AutoBatchedLogRequestEncoder implements Configurator {

    /* renamed from: a, reason: collision with root package name */
    public static final Configurator f10079a = new AutoBatchedLogRequestEncoder();

    private static final class AndroidClientInfoEncoder implements ObjectEncoder<AndroidClientInfo> {

        /* renamed from: a, reason: collision with root package name */
        static final AndroidClientInfoEncoder f10080a = new AndroidClientInfoEncoder();

        /* renamed from: b, reason: collision with root package name */
        private static final FieldDescriptor f10081b = FieldDescriptor.d("sdkVersion");

        /* renamed from: c, reason: collision with root package name */
        private static final FieldDescriptor f10082c = FieldDescriptor.d("model");

        /* renamed from: d, reason: collision with root package name */
        private static final FieldDescriptor f10083d = FieldDescriptor.d("hardware");

        /* renamed from: e, reason: collision with root package name */
        private static final FieldDescriptor f10084e = FieldDescriptor.d("device");

        /* renamed from: f, reason: collision with root package name */
        private static final FieldDescriptor f10085f = FieldDescriptor.d("product");

        /* renamed from: g, reason: collision with root package name */
        private static final FieldDescriptor f10086g = FieldDescriptor.d("osBuild");

        /* renamed from: h, reason: collision with root package name */
        private static final FieldDescriptor f10087h = FieldDescriptor.d("manufacturer");

        /* renamed from: i, reason: collision with root package name */
        private static final FieldDescriptor f10088i = FieldDescriptor.d("fingerprint");

        /* renamed from: j, reason: collision with root package name */
        private static final FieldDescriptor f10089j = FieldDescriptor.d("locale");

        /* renamed from: k, reason: collision with root package name */
        private static final FieldDescriptor f10090k = FieldDescriptor.d("country");

        /* renamed from: l, reason: collision with root package name */
        private static final FieldDescriptor f10091l = FieldDescriptor.d("mccMnc");

        /* renamed from: m, reason: collision with root package name */
        private static final FieldDescriptor f10092m = FieldDescriptor.d("applicationBuild");

        private AndroidClientInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(AndroidClientInfo androidClientInfo, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.c(f10081b, androidClientInfo.m());
            objectEncoderContext.c(f10082c, androidClientInfo.j());
            objectEncoderContext.c(f10083d, androidClientInfo.f());
            objectEncoderContext.c(f10084e, androidClientInfo.d());
            objectEncoderContext.c(f10085f, androidClientInfo.l());
            objectEncoderContext.c(f10086g, androidClientInfo.k());
            objectEncoderContext.c(f10087h, androidClientInfo.h());
            objectEncoderContext.c(f10088i, androidClientInfo.e());
            objectEncoderContext.c(f10089j, androidClientInfo.g());
            objectEncoderContext.c(f10090k, androidClientInfo.c());
            objectEncoderContext.c(f10091l, androidClientInfo.i());
            objectEncoderContext.c(f10092m, androidClientInfo.b());
        }
    }

    private static final class BatchedLogRequestEncoder implements ObjectEncoder<BatchedLogRequest> {

        /* renamed from: a, reason: collision with root package name */
        static final BatchedLogRequestEncoder f10093a = new BatchedLogRequestEncoder();

        /* renamed from: b, reason: collision with root package name */
        private static final FieldDescriptor f10094b = FieldDescriptor.d("logRequest");

        private BatchedLogRequestEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(BatchedLogRequest batchedLogRequest, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.c(f10094b, batchedLogRequest.c());
        }
    }

    private static final class ClientInfoEncoder implements ObjectEncoder<ClientInfo> {

        /* renamed from: a, reason: collision with root package name */
        static final ClientInfoEncoder f10095a = new ClientInfoEncoder();

        /* renamed from: b, reason: collision with root package name */
        private static final FieldDescriptor f10096b = FieldDescriptor.d("clientType");

        /* renamed from: c, reason: collision with root package name */
        private static final FieldDescriptor f10097c = FieldDescriptor.d("androidClientInfo");

        private ClientInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ClientInfo clientInfo, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.c(f10096b, clientInfo.c());
            objectEncoderContext.c(f10097c, clientInfo.b());
        }
    }

    private static final class LogEventEncoder implements ObjectEncoder<LogEvent> {

        /* renamed from: a, reason: collision with root package name */
        static final LogEventEncoder f10098a = new LogEventEncoder();

        /* renamed from: b, reason: collision with root package name */
        private static final FieldDescriptor f10099b = FieldDescriptor.d("eventTimeMs");

        /* renamed from: c, reason: collision with root package name */
        private static final FieldDescriptor f10100c = FieldDescriptor.d("eventCode");

        /* renamed from: d, reason: collision with root package name */
        private static final FieldDescriptor f10101d = FieldDescriptor.d("eventUptimeMs");

        /* renamed from: e, reason: collision with root package name */
        private static final FieldDescriptor f10102e = FieldDescriptor.d("sourceExtension");

        /* renamed from: f, reason: collision with root package name */
        private static final FieldDescriptor f10103f = FieldDescriptor.d("sourceExtensionJsonProto3");

        /* renamed from: g, reason: collision with root package name */
        private static final FieldDescriptor f10104g = FieldDescriptor.d("timezoneOffsetSeconds");

        /* renamed from: h, reason: collision with root package name */
        private static final FieldDescriptor f10105h = FieldDescriptor.d("networkConnectionInfo");

        private LogEventEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(LogEvent logEvent, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.a(f10099b, logEvent.c());
            objectEncoderContext.c(f10100c, logEvent.b());
            objectEncoderContext.a(f10101d, logEvent.d());
            objectEncoderContext.c(f10102e, logEvent.f());
            objectEncoderContext.c(f10103f, logEvent.g());
            objectEncoderContext.a(f10104g, logEvent.h());
            objectEncoderContext.c(f10105h, logEvent.e());
        }
    }

    private static final class LogRequestEncoder implements ObjectEncoder<LogRequest> {

        /* renamed from: a, reason: collision with root package name */
        static final LogRequestEncoder f10106a = new LogRequestEncoder();

        /* renamed from: b, reason: collision with root package name */
        private static final FieldDescriptor f10107b = FieldDescriptor.d("requestTimeMs");

        /* renamed from: c, reason: collision with root package name */
        private static final FieldDescriptor f10108c = FieldDescriptor.d("requestUptimeMs");

        /* renamed from: d, reason: collision with root package name */
        private static final FieldDescriptor f10109d = FieldDescriptor.d("clientInfo");

        /* renamed from: e, reason: collision with root package name */
        private static final FieldDescriptor f10110e = FieldDescriptor.d("logSource");

        /* renamed from: f, reason: collision with root package name */
        private static final FieldDescriptor f10111f = FieldDescriptor.d("logSourceName");

        /* renamed from: g, reason: collision with root package name */
        private static final FieldDescriptor f10112g = FieldDescriptor.d("logEvent");

        /* renamed from: h, reason: collision with root package name */
        private static final FieldDescriptor f10113h = FieldDescriptor.d("qosTier");

        private LogRequestEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(LogRequest logRequest, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.a(f10107b, logRequest.g());
            objectEncoderContext.a(f10108c, logRequest.h());
            objectEncoderContext.c(f10109d, logRequest.b());
            objectEncoderContext.c(f10110e, logRequest.d());
            objectEncoderContext.c(f10111f, logRequest.e());
            objectEncoderContext.c(f10112g, logRequest.c());
            objectEncoderContext.c(f10113h, logRequest.f());
        }
    }

    private static final class NetworkConnectionInfoEncoder implements ObjectEncoder<NetworkConnectionInfo> {

        /* renamed from: a, reason: collision with root package name */
        static final NetworkConnectionInfoEncoder f10114a = new NetworkConnectionInfoEncoder();

        /* renamed from: b, reason: collision with root package name */
        private static final FieldDescriptor f10115b = FieldDescriptor.d("networkType");

        /* renamed from: c, reason: collision with root package name */
        private static final FieldDescriptor f10116c = FieldDescriptor.d("mobileSubtype");

        private NetworkConnectionInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(NetworkConnectionInfo networkConnectionInfo, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.c(f10115b, networkConnectionInfo.c());
            objectEncoderContext.c(f10116c, networkConnectionInfo.b());
        }
    }

    private AutoBatchedLogRequestEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void a(EncoderConfig encoderConfig) {
        BatchedLogRequestEncoder batchedLogRequestEncoder = BatchedLogRequestEncoder.f10093a;
        encoderConfig.a(BatchedLogRequest.class, batchedLogRequestEncoder);
        encoderConfig.a(AutoValue_BatchedLogRequest.class, batchedLogRequestEncoder);
        LogRequestEncoder logRequestEncoder = LogRequestEncoder.f10106a;
        encoderConfig.a(LogRequest.class, logRequestEncoder);
        encoderConfig.a(AutoValue_LogRequest.class, logRequestEncoder);
        ClientInfoEncoder clientInfoEncoder = ClientInfoEncoder.f10095a;
        encoderConfig.a(ClientInfo.class, clientInfoEncoder);
        encoderConfig.a(AutoValue_ClientInfo.class, clientInfoEncoder);
        AndroidClientInfoEncoder androidClientInfoEncoder = AndroidClientInfoEncoder.f10080a;
        encoderConfig.a(AndroidClientInfo.class, androidClientInfoEncoder);
        encoderConfig.a(AutoValue_AndroidClientInfo.class, androidClientInfoEncoder);
        LogEventEncoder logEventEncoder = LogEventEncoder.f10098a;
        encoderConfig.a(LogEvent.class, logEventEncoder);
        encoderConfig.a(AutoValue_LogEvent.class, logEventEncoder);
        NetworkConnectionInfoEncoder networkConnectionInfoEncoder = NetworkConnectionInfoEncoder.f10114a;
        encoderConfig.a(NetworkConnectionInfo.class, networkConnectionInfoEncoder);
        encoderConfig.a(AutoValue_NetworkConnectionInfo.class, networkConnectionInfoEncoder);
    }
}

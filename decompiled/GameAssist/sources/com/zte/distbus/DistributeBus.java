package com.zte.distbus;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.zte.distbus.basetransfer.BaseTransfer;
import com.zte.distbus.basetransfer.BusCallback;
import com.zte.distbus.basetransfer.ConnectionInfo;
import com.zte.distbus.basetransfer.ConnectionLifecycleCallback;
import com.zte.distbus.basetransfer.ConnectionOptions;
import com.zte.distbus.basetransfer.ConnectionResolution;
import com.zte.distbus.basetransfer.Constants;
import com.zte.distbus.basetransfer.DistBusKeys;
import com.zte.distbus.basetransfer.InitCallback;
import com.zte.distbus.basetransfer.PayloadCallback;
import com.zte.distbus.basetransfer.Status;
import com.zte.distbus.basetransfer.model.ConnectionParam;
import com.zte.distbus.basetransfer.model.InitParam;
import com.zte.distbus.basetransfer.model.NotificationParam;
import com.zte.distbus.basetransfer.servicemanager.DistService;
import com.zte.distbus.basetransfer.servicemanager.model.PhysicalConnCallBack;
import com.zte.distbus.basetransfer.servicemanager.model.PublishServiceParam;
import com.zte.distbus.basetransfer.servicemanager.model.ServiceParam;
import com.zte.distbus.basetransfer.servicemanager.model.WifiParam;
import com.zte.distbus.filetransfer.FileTransfer;
import com.zte.distbus.msgtransfer.MessageTransfer;
import com.zte.distbus.streamtransfer.StreamTransfer;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class DistributeBus {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16298a;

    /* renamed from: b, reason: collision with root package name */
    private final String f16299b;

    /* renamed from: c, reason: collision with root package name */
    private final String f16300c;

    /* renamed from: d, reason: collision with root package name */
    private final InitParam f16301d;

    /* renamed from: e, reason: collision with root package name */
    private BaseTransfer f16302e;

    /* renamed from: g, reason: collision with root package name */
    private BaseTransfer f16304g;

    /* renamed from: i, reason: collision with root package name */
    private BaseTransfer f16306i;

    /* renamed from: k, reason: collision with root package name */
    private ConnectionLifecycleCallback f16308k;

    /* renamed from: l, reason: collision with root package name */
    private PayloadCallback f16309l;

    /* renamed from: m, reason: collision with root package name */
    private ConnectionOptions f16310m;

    /* renamed from: n, reason: collision with root package name */
    private ConnectionParam f16311n;

    /* renamed from: p, reason: collision with root package name */
    private boolean f16313p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f16314q;

    /* renamed from: s, reason: collision with root package name */
    private JsonObject f16316s;
    private String t;
    private PhysicalConnCallBack u;
    private WifiParam v;
    private WifiParam w;

    /* renamed from: f, reason: collision with root package name */
    private int f16303f = -1;

    /* renamed from: h, reason: collision with root package name */
    private int f16305h = -1;

    /* renamed from: j, reason: collision with root package name */
    private int f16307j = -1;

    /* renamed from: o, reason: collision with root package name */
    private int f16312o = -1;

    /* renamed from: r, reason: collision with root package name */
    private int f16315r = 0;
    private BusCallback x = new BusCallback() { // from class: com.zte.distbus.DistributeBus.1
        @Override // com.zte.distbus.basetransfer.BusCallback
        public void acceptConnection(String str) {
            Log.d("DistributeBus", "acceptConnection uuid:" + DistributeBus.this.f16299b + ", localAccept: " + DistributeBus.this.f16313p + ", jsonMsg: " + str);
            if (!TextUtils.isEmpty(str) && !"null".equals(str)) {
                try {
                    DistributeBus.this.f16316s = JsonParser.parseString(str).getAsJsonObject();
                    if (DistributeBus.this.f16316s.has("v")) {
                        DistributeBus distributeBus = DistributeBus.this;
                        distributeBus.f16315r = distributeBus.f16316s.get("v").getAsInt();
                    }
                } catch (JsonSyntaxException | IllegalStateException e2) {
                    e2.printStackTrace();
                    Log.d("DistributeBus", "acceptConnection uuid:" + DistributeBus.this.f16299b + ", jsonMsg error: " + str);
                }
            }
            DistributeBus.this.f16314q = true;
            if (DistributeBus.this.f16313p) {
                DistributeBus.this.Z();
            }
        }

        @Override // com.zte.distbus.basetransfer.BusCallback
        public void establishConnection(ConnectionParam connectionParam) {
            Log.d("DistributeBus", "establishConnection connectionParam: " + connectionParam + ", uuid: " + DistributeBus.this.f16299b);
            if (connectionParam != null) {
                Log.d("DistributeBus", "establishConnection type: " + connectionParam.getType());
                DistributeBus.this.f16310m = new ConnectionOptions(connectionParam.getType());
                if ((DistributeBus.this.f16310m.getConnectionType() & 1) != 0 || (DistributeBus.this.f16310m.getConnectionType() & 8) != 0) {
                    DistributeBus.this.I(connectionParam);
                }
                if ((DistributeBus.this.f16310m.getConnectionType() & 2) != 0) {
                    DistributeBus.this.J(connectionParam);
                }
                if ((DistributeBus.this.f16310m.getConnectionType() & 4) != 0) {
                    DistributeBus.this.H(connectionParam);
                }
            }
        }

        @Override // com.zte.distbus.basetransfer.BusCallback
        public void onConnectionChange(Intent intent) {
            if (DistributeBus.this.u == null) {
                Log.d("DistributeBus", "onConnectionChange physicalConnCallBack null, uuid: " + DistributeBus.this.f16299b);
                return;
            }
            String action = intent.getAction();
            if (Constants.ON_PHYSICAL_CONNECTION.equals(action)) {
                int intExtra = intent.getIntExtra(Constants.EXTRA_RESULT, 1);
                Log.d("DistributeBus", "requestPhysicalConnection result code: " + intExtra + ", uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.u.onConnectionResult(DistributeBus.this.t, new ConnectionResolution(new Status(intExtra)), new WifiParam(intent.getStringExtra(Constants.EXTRA_LOCAL)), new WifiParam(intent.getStringExtra(Constants.EXTRA_REMOTE)));
                return;
            }
            if (!Constants.ON_CLOSE_PHYSICAL_CONNECTION.equals(action)) {
                Log.d("DistributeBus", "onConnectionChange unknown action: " + action + ", uuid: " + DistributeBus.this.f16299b);
                return;
            }
            int intExtra2 = intent.getIntExtra(Constants.EXTRA_RESULT, 1);
            Log.d("DistributeBus", "ON_CLOSE_PHYSICAL_CONNECTION result code: " + intExtra2 + ", uuid: " + DistributeBus.this.f16299b);
            DistributeBus.this.u.onDisconnect(DistributeBus.this.t, intExtra2);
        }

        @Override // com.zte.distbus.basetransfer.BusCallback
        public void onConnectionInitiated(String str) {
            Log.d("DistributeBus", "onConnectionInitiated profile: " + str);
            try {
                DistributeBus.this.f16310m = new ConnectionOptions(((ConnectionParam) new Gson().fromJson(str, ConnectionParam.class)).getType());
                Log.d("DistributeBus", "connectionOptions type: " + DistributeBus.this.f16310m.getConnectionType());
            } catch (JsonSyntaxException | IllegalStateException e2) {
                e2.printStackTrace();
            }
            DistributeBus.this.f16308k.onConnectionInitiated(DistributeBus.this.f16300c, new ConnectionInfo(DistributeBus.this.f16300c, "", true, str));
        }

        @Override // com.zte.distbus.basetransfer.BusCallback
        public void processNotification(ConnectionParam connectionParam) {
            Log.d("DistributeBus", "processNotification connectionParam: " + connectionParam + ", uuid: " + DistributeBus.this.f16299b);
            if (connectionParam != null) {
                Log.d("DistributeBus", "processNotification type: " + connectionParam.getType());
                DistributeBus.this.f16310m = new ConnectionOptions(connectionParam.getType());
                if ((DistributeBus.this.f16310m.getConnectionType() & 1) != 0 && DistributeBus.this.f16302e != null) {
                    DistributeBus.this.f16302e.processNotification(connectionParam.getMsgTransferProfile());
                }
                if ((DistributeBus.this.f16310m.getConnectionType() & 2) != 0 && DistributeBus.this.f16304g != null) {
                    DistributeBus.this.f16304g.processNotification(connectionParam.getStreamTransferProfile());
                }
                if ((DistributeBus.this.f16310m.getConnectionType() & 4) == 0 || DistributeBus.this.f16306i == null) {
                    return;
                }
                DistributeBus.this.f16306i.processNotification(connectionParam.getFileTransferProfile());
            }
        }

        @Override // com.zte.distbus.basetransfer.BusCallback
        public void rejectConnection() {
            Log.d("DistributeBus", "rejectConnection: " + DistributeBus.this.f16299b);
            DistributeBus.this.P("", new ConnectionResolution(new Status(4)));
        }

        @Override // com.zte.distbus.basetransfer.BusCallback
        public void sendNotification(ConnectionParam connectionParam) {
            Log.d("DistributeBus", "sendNotification type: " + connectionParam.getType() + ", uuid: " + DistributeBus.this.f16299b);
            Gson gson = new Gson();
            DistributeBus.this.Y(new ServiceParam(DistributeBus.this.f16299b, gson.toJson(new NotificationParam(5, gson.toJson(connectionParam)))));
        }
    };

    public DistributeBus(Context context, String str, String str2) {
        Log.d("DistributeBus", "DistributeBus uuid: " + str + ", deviceId: " + str2 + ", package: " + context.getPackageName());
        this.f16298a = context;
        this.f16299b = str;
        this.f16300c = str2;
        this.f16301d = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(ConnectionParam connectionParam) {
        Log.d("DistributeBus", "establishFileTransfer uuid: " + this.f16299b);
        BaseTransfer baseTransfer = this.f16306i;
        if (baseTransfer != null) {
            baseTransfer.stopAllEndpoints();
        }
        Context context = this.f16298a;
        String str = this.f16299b;
        BusCallback busCallback = this.x;
        ConnectionLifecycleCallback connectionLifecycleCallback = new ConnectionLifecycleCallback() { // from class: com.zte.distbus.DistributeBus.6
            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onBandwidthChanged() {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionInitiated(String str2, ConnectionInfo connectionInfo) {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionResult(String str2, ConnectionResolution connectionResolution) {
                DistributeBus.this.f16307j = connectionResolution.getStatus().getCode();
                Log.d("DistributeBus", "requestTransfer fileTransferResult: " + DistributeBus.this.f16307j + ", uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.R();
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onDisconnected(String str2) {
                Log.d("DistributeBus", "fileTransfer onDisconnected, uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.Q(str2);
            }
        };
        PayloadCallback payloadCallback = this.f16309l;
        InitParam initParam = this.f16301d;
        FileTransfer fileTransfer = new FileTransfer(context, str, busCallback, connectionLifecycleCallback, "", "", payloadCallback, initParam == null ? null : initParam.fileTransferParam);
        this.f16306i = fileTransfer;
        fileTransfer.connect(connectionParam.getFileTransferProfile());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(ConnectionParam connectionParam) {
        boolean z = (this.f16310m.getConnectionType() & 8) != 0;
        String str = z ? "TYPE_MESSAGE_HIGH" : null;
        Log.d("DistributeBus", "establishMsgTransfer uuid: " + this.f16299b + ", isHigh: " + z + ", messageTransfer: " + this.f16302e);
        BaseTransfer baseTransfer = this.f16302e;
        if (baseTransfer != null) {
            baseTransfer.stopAllEndpoints();
        }
        MessageTransfer messageTransfer = new MessageTransfer(this.f16299b, new ConnectionLifecycleCallback() { // from class: com.zte.distbus.DistributeBus.8
            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onBandwidthChanged() {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionInitiated(String str2, ConnectionInfo connectionInfo) {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionResult(String str2, ConnectionResolution connectionResolution) {
                DistributeBus.this.f16303f = connectionResolution.getStatus().getCode();
                Log.d("DistributeBus", "establishMsgTransfer onConnectionResult: " + DistributeBus.this.f16303f + ", uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.R();
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onDisconnected(String str2) {
                Log.d("DistributeBus", "establishMsgTransfer onDisconnected, uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.Q(str2);
            }
        }, this.f16309l, str, 0);
        this.f16302e = messageTransfer;
        messageTransfer.connect(connectionParam.getMsgTransferProfile());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(ConnectionParam connectionParam) {
        Log.d("DistributeBus", "establishStreamTransfer uuid: " + this.f16299b);
        BaseTransfer baseTransfer = this.f16304g;
        if (baseTransfer != null) {
            baseTransfer.stopAllEndpoints();
        }
        BusCallback busCallback = this.x;
        ConnectionLifecycleCallback connectionLifecycleCallback = new ConnectionLifecycleCallback() { // from class: com.zte.distbus.DistributeBus.7
            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onBandwidthChanged() {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionInitiated(String str, ConnectionInfo connectionInfo) {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionResult(String str, ConnectionResolution connectionResolution) {
                DistributeBus.this.f16305h = connectionResolution.getStatus().getCode();
                Log.d("DistributeBus", "establishStreamTransfer onConnectionResult: " + DistributeBus.this.f16305h + ", uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.R();
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onDisconnected(String str) {
                Log.d("DistributeBus", "streamTransfer onDisconnected, uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.Q(str);
            }
        };
        String streamTransferProfile = connectionParam.getStreamTransferProfile();
        InitParam initParam = this.f16301d;
        StreamTransfer streamTransfer = new StreamTransfer(busCallback, connectionLifecycleCallback, "", streamTransferProfile, initParam == null ? null : initParam.streamTransferParam, 2);
        this.f16304g = streamTransfer;
        streamTransfer.connect(connectionParam.getStreamTransferProfile());
    }

    private Intent K(String str, ServiceParam serviceParam) {
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.f16298a, str));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_UUID, serviceParam.getUuid());
        intent.putExtra(Constants.EXTRA_DEVICE_ID, this.f16300c);
        Log.d("DistributeBus", "getSendIntent uuid: " + this.f16299b + ", Param uuid: " + serviceParam.getUuid() + ", deviceId: " + this.f16300c);
        if (!TextUtils.isEmpty(serviceParam.getProfile())) {
            intent.putExtra(Constants.EXTRA_PROFILE, serviceParam.getProfile());
        }
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(Status status, String str) {
        Log.d("DistributeBus", "messageTransfer init status: " + status.getCode() + ", profile: " + str + ", uuid: " + this.f16299b);
        if (status.getCode() == 0) {
            ConnectionParam connectionParam = this.f16311n;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            connectionParam.setMsgTransferProfile(str);
        } else {
            Log.d("DistributeBus", "messageTransfer init failed, uuid: " + this.f16299b);
            this.f16303f = status.getCode();
            R();
            this.f16311n.setMsgTransferProfile("");
        }
        X();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(Status status, String str) {
        Log.d("DistributeBus", "streamTransfer init status: " + status.getCode() + ", profile: " + str + ", uuid: " + this.f16299b);
        if (status.getCode() == 0) {
            ConnectionParam connectionParam = this.f16311n;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            connectionParam.setStreamTransferProfile(str);
        } else {
            Log.d("DistributeBus", "streamTransfer init failed, uuid: " + this.f16299b);
            this.f16305h = status.getCode();
            R();
            this.f16311n.setStreamTransferProfile("");
        }
        X();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(Status status, String str) {
        Log.d("DistributeBus", "fileTransfer init status: " + status.getCode() + ", profile: " + str + ", uuid: " + this.f16299b);
        if (status.getCode() == 0) {
            ConnectionParam connectionParam = this.f16311n;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            connectionParam.setFileTransferProfile(str);
        } else {
            Log.d("DistributeBus", "fileTransfer init failed, uuid: " + this.f16299b);
            this.f16307j = status.getCode();
            R();
            this.f16311n.setFileTransferProfile("");
        }
        X();
    }

    private boolean O() {
        ConnectionOptions connectionOptions = this.f16310m;
        return (connectionOptions == null || ((connectionOptions.getConnectionType() & 2) == 0 && (this.f16310m.getConnectionType() & 4) == 0 && (this.f16310m.getConnectionType() & 8) == 0)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P(String str, ConnectionResolution connectionResolution) {
        int code = connectionResolution.getStatus().getCode();
        Log.d("DistributeBus", "onConnectionResultCallback uuid: " + this.f16299b + ", code: " + code + ", distBus: " + this);
        if (this.f16312o != -1) {
            Log.d("DistributeBus", "onConnectionResultCallback uuid: " + this.f16299b + ", connectionResult: " + this.f16312o + " not -1 means already called, skip.");
            return;
        }
        this.f16312o = code;
        if (code != 0) {
            this.v = null;
            this.w = null;
        }
        ConnectionLifecycleCallback connectionLifecycleCallback = this.f16308k;
        if (connectionLifecycleCallback != null) {
            connectionLifecycleCallback.onConnectionResult(str, connectionResolution);
            return;
        }
        Log.d("DistributeBus", "onConnectionResultCallback serviceCallback is null, uuid: " + this.f16299b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q(String str) {
        Log.d("DistributeBus", "onDisconnectCallback uuid: " + this.f16299b);
        ConnectionLifecycleCallback connectionLifecycleCallback = this.f16308k;
        if (connectionLifecycleCallback != null) {
            connectionLifecycleCallback.onDisconnected(str);
            return;
        }
        Log.d("DistributeBus", "onDisconnectCallback serviceCallback is null, uuid: " + this.f16299b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005b A[Catch: all -> 0x002e, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000c, B:8:0x0057, B:10:0x005b, B:12:0x005f, B:17:0x007b, B:18:0x009e, B:20:0x00a2, B:22:0x00a6, B:26:0x00c2, B:27:0x00e5, B:30:0x0010, B:32:0x0014, B:36:0x0033), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a2 A[Catch: all -> 0x002e, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000c, B:8:0x0057, B:10:0x005b, B:12:0x005f, B:17:0x007b, B:18:0x009e, B:20:0x00a2, B:22:0x00a6, B:26:0x00c2, B:27:0x00e5, B:30:0x0010, B:32:0x0014, B:36:0x0033), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void R() {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.distbus.DistributeBus.R():void");
    }

    private void T(PayloadCallback payloadCallback, String str) {
        Log.d("DistributeBus", "requestMsgTransfer uuid: " + this.f16299b + ", localIp: " + str);
        BaseTransfer baseTransfer = this.f16302e;
        if (baseTransfer != null) {
            baseTransfer.stopAllEndpoints();
        }
        MessageTransfer messageTransfer = new MessageTransfer(this.f16299b, new ConnectionLifecycleCallback() { // from class: com.zte.distbus.DistributeBus.5
            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onBandwidthChanged() {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionInitiated(String str2, ConnectionInfo connectionInfo) {
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onConnectionResult(String str2, ConnectionResolution connectionResolution) {
                DistributeBus.this.f16303f = connectionResolution.getStatus().getCode();
                Log.d("DistributeBus", "requestMsgTransfer messageTransferResult: " + DistributeBus.this.f16303f + ", uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.R();
            }

            @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
            public void onDisconnected(String str2) {
                Log.d("DistributeBus", "messageTransfer onDisconnected, uuid: " + DistributeBus.this.f16299b);
                DistributeBus.this.Q(str2);
            }
        }, payloadCallback, str, this.f16315r);
        this.f16302e = messageTransfer;
        messageTransfer.init(new InitCallback() { // from class: com.zte.distbus.a
            @Override // com.zte.distbus.basetransfer.InitCallback
            public final void onInitResult(Status status, String str2) {
                DistributeBus.this.L(status, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V(WifiParam wifiParam, WifiParam wifiParam2) {
        Log.d("DistributeBus", "requestTransfer uuid: " + this.f16299b + ", local: " + wifiParam.ip + ", remote: " + wifiParam2.ip);
        this.v = wifiParam;
        this.w = wifiParam2;
        if ((this.f16310m.getConnectionType() & 2) != 0) {
            BaseTransfer baseTransfer = this.f16304g;
            if (baseTransfer != null) {
                baseTransfer.stopAllEndpoints();
            }
            BusCallback busCallback = this.x;
            ConnectionLifecycleCallback connectionLifecycleCallback = new ConnectionLifecycleCallback() { // from class: com.zte.distbus.DistributeBus.3
                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onBandwidthChanged() {
                }

                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onConnectionInitiated(String str, ConnectionInfo connectionInfo) {
                }

                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onConnectionResult(String str, ConnectionResolution connectionResolution) {
                    DistributeBus.this.f16305h = connectionResolution.getStatus().getCode();
                    Log.d("DistributeBus", "requestTransfer streamTransferResult: " + DistributeBus.this.f16305h + ", uuid: " + DistributeBus.this.f16299b);
                    DistributeBus.this.R();
                }

                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onDisconnected(String str) {
                    Log.d("DistributeBus", "requestMsgTransfer onDisconnected, uuid: " + DistributeBus.this.f16299b);
                    DistributeBus.this.Q(str);
                }
            };
            String str = wifiParam.ip;
            String str2 = wifiParam2.ip;
            InitParam initParam = this.f16301d;
            StreamTransfer streamTransfer = new StreamTransfer(busCallback, connectionLifecycleCallback, str, str2, initParam == null ? null : initParam.streamTransferParam, this.f16315r);
            this.f16304g = streamTransfer;
            streamTransfer.init(new InitCallback() { // from class: com.zte.distbus.b
                @Override // com.zte.distbus.basetransfer.InitCallback
                public final void onInitResult(Status status, String str3) {
                    DistributeBus.this.M(status, str3);
                }
            });
        }
        if ((this.f16310m.getConnectionType() & 4) != 0) {
            BaseTransfer baseTransfer2 = this.f16306i;
            if (baseTransfer2 != null) {
                baseTransfer2.stopAllEndpoints();
            }
            Context context = this.f16298a;
            String str3 = this.f16299b;
            BusCallback busCallback2 = this.x;
            ConnectionLifecycleCallback connectionLifecycleCallback2 = new ConnectionLifecycleCallback() { // from class: com.zte.distbus.DistributeBus.4
                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onBandwidthChanged() {
                }

                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onConnectionInitiated(String str4, ConnectionInfo connectionInfo) {
                }

                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onConnectionResult(String str4, ConnectionResolution connectionResolution) {
                    DistributeBus.this.f16307j = connectionResolution.getStatus().getCode();
                    Log.d("DistributeBus", "requestTransfer fileTransferResult: " + DistributeBus.this.f16307j + ", uuid: " + DistributeBus.this.f16299b);
                    DistributeBus.this.R();
                }

                @Override // com.zte.distbus.basetransfer.ConnectionLifecycleCallback
                public void onDisconnected(String str4) {
                    Log.d("DistributeBus", "fileTransfer onDisconnected, uuid: " + DistributeBus.this.f16299b);
                    DistributeBus.this.Q(str4);
                }
            };
            String str4 = wifiParam.ip;
            String str5 = wifiParam2.ip;
            PayloadCallback payloadCallback = this.f16309l;
            InitParam initParam2 = this.f16301d;
            FileTransfer fileTransfer = new FileTransfer(context, str3, busCallback2, connectionLifecycleCallback2, str4, str5, payloadCallback, initParam2 != null ? initParam2.fileTransferParam : null);
            this.f16306i = fileTransfer;
            fileTransfer.init(new InitCallback() { // from class: com.zte.distbus.c
                @Override // com.zte.distbus.basetransfer.InitCallback
                public final void onInitResult(Status status, String str6) {
                    DistributeBus.this.N(status, str6);
                }
            });
        }
        if ((this.f16310m.getConnectionType() & 8) != 0) {
            T(this.f16309l, wifiParam.ip);
        }
    }

    private void X() {
        if (!((this.f16310m.getConnectionType() & 1) == 0 && (this.f16310m.getConnectionType() & 8) == 0) && this.f16311n.getMsgTransferProfile() == null) {
            Log.d("DistributeBus", "sendConnectNotification, MsgTransfer not ok, uuid: " + this.f16299b);
            return;
        }
        if ((this.f16310m.getConnectionType() & 2) != 0 && this.f16311n.getStreamTransferProfile() == null) {
            Log.d("DistributeBus", "sendConnectNotification, StreamTransfer not ok, uuid: " + this.f16299b);
            return;
        }
        if ((this.f16310m.getConnectionType() & 4) != 0 && this.f16311n.getFileTransferProfile() == null) {
            Log.d("DistributeBus", "sendConnectNotification, FileTransfer not ok, uuid: " + this.f16299b);
            return;
        }
        Gson gson = new Gson();
        ServiceParam serviceParam = new ServiceParam(this.f16299b, gson.toJson(new NotificationParam(3, gson.toJson(this.f16311n))));
        Log.d("DistributeBus", "uuid: " + this.f16299b + ", sendConnectNotification: " + serviceParam.getProfile());
        Y(serviceParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(ServiceParam serviceParam) {
        Log.d("DistributeBus", "sendNotification uuid: " + this.f16299b + ", Param: " + serviceParam.getProfile());
        Intent K = K(Constants.SEND_NOTIFICATION, serviceParam);
        if (!DistService.getInstance().isSdk()) {
            K.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.f16298a.sendBroadcast(K);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z() {
        Log.d("DistributeBus", "startConnection uuid:" + this.f16299b + ", type: " + this.f16310m.getConnectionType());
        if ((this.f16310m.getConnectionType() & 1) != 0) {
            T(this.f16309l, null);
        }
        if (O()) {
            U(new PhysicalConnCallBack() { // from class: com.zte.distbus.DistributeBus.2
                @Override // com.zte.distbus.basetransfer.servicemanager.model.PhysicalConnCallBack
                public void onConnectionResult(String str, ConnectionResolution connectionResolution, WifiParam wifiParam, WifiParam wifiParam2) {
                    if (connectionResolution.getStatus().getCode() == 0) {
                        DistributeBus.this.V(wifiParam, wifiParam2);
                    } else {
                        DistributeBus.this.P(str, connectionResolution);
                    }
                }

                @Override // com.zte.distbus.basetransfer.servicemanager.model.PhysicalConnCallBack
                public void onDisconnect(String str, int i2) {
                    DistributeBus.this.Q(str);
                }
            });
        }
    }

    public void S(PublishServiceParam publishServiceParam) {
        publishServiceParam.setPackageName(this.f16298a.getPackageName());
        String json = new Gson().toJson(publishServiceParam);
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.f16298a, Constants.PUBLISH_SERVICE));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_PROFILE, json);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.f16298a.sendBroadcast(intent);
        Log.d("DistributeBus", "publishService profile: " + json);
    }

    protected void U(PhysicalConnCallBack physicalConnCallBack) {
        Log.d("DistributeBus", "requestPhysicalConnection uuid:" + this.f16299b);
        this.u = physicalConnCallBack;
        Intent K = K(Constants.PHYSICAL_CONNECTION, new ServiceParam(this.f16299b, ""));
        K.putExtra(Constants.EXTRA_CALLBACK_PKG, this.f16298a.getPackageName());
        JsonObject jsonObject = this.f16316s;
        if (jsonObject != null) {
            K.putExtra(DistBusKeys.KEY_DIST_BUS_DATA, jsonObject.toString());
        }
        if (!DistService.getInstance().isSdk()) {
            K.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        InitParam initParam = this.f16301d;
        if (initParam != null) {
            K.putExtra(DistBusKeys.KEY_PHYSICAL_TYPE, initParam.physicalConnectionType);
        }
        this.f16298a.sendBroadcast(K);
        Log.d("DistributeBus", "requestPhysicalConnection intent: " + K);
    }

    public void W(String str) {
        Log.d("DistributeBus", "sendCommMsg deviceId: " + this.f16300c + ", uuid: " + this.f16299b + ", msg: " + str);
        if (TextUtils.isEmpty(this.f16300c) || TextUtils.isEmpty(this.f16299b) || TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.f16298a, Constants.COMM_MSG));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_UUID, this.f16299b);
        intent.putExtra(Constants.EXTRA_DEVICE_ID, this.f16300c);
        intent.putExtra(Constants.EXTRA_PROFILE, str);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.f16298a.sendBroadcast(intent);
    }

    public void a0(ServiceParam serviceParam) {
        Log.d("DistributeBus", "startService uuid:" + this.f16299b + ", serviceParam.getUuid: " + serviceParam.getUuid());
        Intent K = K(Constants.START_SERVICE, serviceParam);
        if (!DistService.getInstance().isSdk()) {
            K.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.f16298a.sendBroadcast(K);
    }

    public void b0(ServiceParam serviceParam) {
        Log.d("DistributeBus", "stopService uuid:" + this.f16299b + ", serviceParam.getUuid: " + serviceParam.getUuid());
        Intent K = K(Constants.STOP_SERVICE, serviceParam);
        if (!DistService.getInstance().isSdk()) {
            K.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.f16298a.sendBroadcast(K);
        Log.d("DistributeBus", "stopService intent: " + K.toString());
    }

    public void c0(PublishServiceParam publishServiceParam) {
        publishServiceParam.setPackageName(this.f16298a.getPackageName());
        String json = new Gson().toJson(publishServiceParam);
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.f16298a, Constants.DISCOVER_SERVICE));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_PROFILE, json);
        intent.putExtra(Constants.EXTRA_DEVICE_ID, this.f16300c);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.f16298a.sendBroadcast(intent);
        Log.d("DistributeBus", "subscribeService profile: " + json);
    }
}

package androidx.localbroadcastmanager.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class LocalBroadcastManager {

    /* renamed from: f, reason: collision with root package name */
    private static final Object f4481f = new Object();

    /* renamed from: g, reason: collision with root package name */
    private static LocalBroadcastManager f4482g;

    /* renamed from: a, reason: collision with root package name */
    private final Context f4483a;

    /* renamed from: b, reason: collision with root package name */
    private final HashMap f4484b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final HashMap f4485c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    private final ArrayList f4486d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    private final Handler f4487e;

    private static final class BroadcastRecord {

        /* renamed from: a, reason: collision with root package name */
        final Intent f4489a;

        /* renamed from: b, reason: collision with root package name */
        final ArrayList f4490b;
    }

    private static final class ReceiverRecord {

        /* renamed from: a, reason: collision with root package name */
        final IntentFilter f4491a;

        /* renamed from: b, reason: collision with root package name */
        final BroadcastReceiver f4492b;

        /* renamed from: c, reason: collision with root package name */
        boolean f4493c;

        ReceiverRecord(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f4491a = intentFilter;
            this.f4492b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f4492b);
            sb.append(" filter=");
            sb.append(this.f4491a);
            if (this.f4493c) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private LocalBroadcastManager(Context context) {
        this.f4483a = context;
        this.f4487e = new Handler(context.getMainLooper()) { // from class: androidx.localbroadcastmanager.content.LocalBroadcastManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    super.handleMessage(message);
                } else {
                    LocalBroadcastManager.this.a();
                }
            }
        };
    }

    public static LocalBroadcastManager b(Context context) {
        LocalBroadcastManager localBroadcastManager;
        synchronized (f4481f) {
            try {
                if (f4482g == null) {
                    f4482g = new LocalBroadcastManager(context.getApplicationContext());
                }
                localBroadcastManager = f4482g;
            } catch (Throwable th) {
                throw th;
            }
        }
        return localBroadcastManager;
    }

    void a() {
        int size;
        BroadcastRecord[] broadcastRecordArr;
        while (true) {
            synchronized (this.f4484b) {
                try {
                    size = this.f4486d.size();
                    if (size <= 0) {
                        return;
                    }
                    broadcastRecordArr = new BroadcastRecord[size];
                    this.f4486d.toArray(broadcastRecordArr);
                    this.f4486d.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
            for (int i2 = 0; i2 < size; i2++) {
                BroadcastRecord broadcastRecord = broadcastRecordArr[i2];
                int size2 = broadcastRecord.f4490b.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    ReceiverRecord receiverRecord = (ReceiverRecord) broadcastRecord.f4490b.get(i3);
                    if (!receiverRecord.f4493c) {
                        receiverRecord.f4492b.onReceive(this.f4483a, broadcastRecord.f4489a);
                    }
                }
            }
        }
    }

    public void c(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.f4484b) {
            try {
                ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver);
                ArrayList arrayList = (ArrayList) this.f4484b.get(broadcastReceiver);
                if (arrayList == null) {
                    arrayList = new ArrayList(1);
                    this.f4484b.put(broadcastReceiver, arrayList);
                }
                arrayList.add(receiverRecord);
                for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                    String action = intentFilter.getAction(i2);
                    ArrayList arrayList2 = (ArrayList) this.f4485c.get(action);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                        this.f4485c.put(action, arrayList2);
                    }
                    arrayList2.add(receiverRecord);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void d(BroadcastReceiver broadcastReceiver) {
        synchronized (this.f4484b) {
            try {
                ArrayList arrayList = (ArrayList) this.f4484b.remove(broadcastReceiver);
                if (arrayList == null) {
                    return;
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ReceiverRecord receiverRecord = (ReceiverRecord) arrayList.get(size);
                    receiverRecord.f4493c = true;
                    for (int i2 = 0; i2 < receiverRecord.f4491a.countActions(); i2++) {
                        String action = receiverRecord.f4491a.getAction(i2);
                        ArrayList arrayList2 = (ArrayList) this.f4485c.get(action);
                        if (arrayList2 != null) {
                            for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                                ReceiverRecord receiverRecord2 = (ReceiverRecord) arrayList2.get(size2);
                                if (receiverRecord2.f4492b == broadcastReceiver) {
                                    receiverRecord2.f4493c = true;
                                    arrayList2.remove(size2);
                                }
                            }
                            if (arrayList2.size() <= 0) {
                                this.f4485c.remove(action);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

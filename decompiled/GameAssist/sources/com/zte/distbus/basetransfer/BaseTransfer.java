package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public abstract class BaseTransfer {
    public abstract void cancelPayload(long j2);

    public abstract void connect(String str);

    public abstract void disconnectFromEndpoint(String str);

    public abstract void init(InitCallback initCallback);

    public void processNotification(String str) {
    }

    public abstract void sendPayload(String str, Payload payload);

    public abstract void stopAllEndpoints();
}

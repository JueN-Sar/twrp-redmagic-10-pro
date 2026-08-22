package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public interface PayloadCallback {
    void onPayloadReceived(String str, Payload payload);

    void onPayloadTransferUpdate(String str, PayloadTransferUpdate payloadTransferUpdate);
}

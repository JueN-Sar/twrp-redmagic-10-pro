package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public class PayloadTransferUpdate {
    private long bytesTransferred;
    private long payloadId;
    private int status;
    private long totalBytes;

    public PayloadTransferUpdate(int i2, long j2, long j3, long j4) {
        this.status = i2;
        this.payloadId = j2;
        this.bytesTransferred = j3;
        this.totalBytes = j4;
    }

    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public long getPayloadId() {
        return this.payloadId;
    }

    public int getStatus() {
        return this.status;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }
}

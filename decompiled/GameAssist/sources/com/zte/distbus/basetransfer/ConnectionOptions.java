package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public final class ConnectionOptions {
    public static final int TYPE_FILE = 4;
    public static final int TYPE_MESSAGE = 1;
    public static final int TYPE_MESSAGE_HIGH = 8;
    public static final int TYPE_STREAM = 2;
    private int connectionType;

    public ConnectionOptions() {
    }

    public int getConnectionType() {
        return this.connectionType;
    }

    public void setConnectionType(int i2) {
        this.connectionType = i2;
    }

    public ConnectionOptions(int i2) {
        this.connectionType = i2;
    }
}

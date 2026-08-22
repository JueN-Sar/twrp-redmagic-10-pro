package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public final class ConnectOptionBuilder {
    private ConnectionOptions options = new ConnectionOptions();

    public ConnectionOptions build() {
        return this.options;
    }

    public ConnectOptionBuilder setConnectionType(int i2) {
        this.options.setConnectionType(i2);
        return this;
    }
}

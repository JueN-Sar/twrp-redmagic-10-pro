package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public class Endpoint {
    int id;
    String ip;
    int port;

    public Endpoint(int i2, int i3, String str) {
        this.id = i2;
        this.port = i3;
        this.ip = str;
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }
}

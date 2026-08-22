package com.zte.distbus.basetransfer.model;

/* loaded from: classes.dex */
public class NotificationParam {
    public static final int ACCEPT_CONNECTION = 2;
    public static final int CMD_NOTIFICATION = 5;
    public static final int ESTABLISH_CONNECTION = 3;
    public static final int INIT_CONNECTION = 1;
    public static final int REJECT_CONNECTION = 4;
    private int command;
    private String profile;

    public NotificationParam(int i2, String str) {
        this.command = i2;
        this.profile = str;
    }

    public int getCommand() {
        return this.command;
    }

    public String getProfile() {
        return this.profile;
    }
}

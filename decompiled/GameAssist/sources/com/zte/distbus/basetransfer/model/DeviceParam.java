package com.zte.distbus.basetransfer.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class DeviceParam {

    @SerializedName("btAddress")
    public String btAddress;

    @SerializedName("btPsm")
    public int btPsm;

    @SerializedName("ip")
    public String ip;

    @SerializedName("port")
    public int port;
}

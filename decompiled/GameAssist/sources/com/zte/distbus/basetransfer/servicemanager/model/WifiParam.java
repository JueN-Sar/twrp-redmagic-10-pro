package com.zte.distbus.basetransfer.servicemanager.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class WifiParam implements Serializable {
    private static final long serialVersionUID = 209631097600035286L;

    @SerializedName("ip")
    public String ip;

    public WifiParam(String str) {
        this.ip = str;
    }
}

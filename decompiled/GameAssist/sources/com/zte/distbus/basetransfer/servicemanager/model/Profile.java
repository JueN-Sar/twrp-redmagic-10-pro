package com.zte.distbus.basetransfer.servicemanager.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class Profile implements Serializable {
    private static final long serialVersionUID = 8679555324795657978L;

    @SerializedName("dModel")
    public String dModel;

    @SerializedName("dName")
    public String dName;

    public Profile(@NonNull String str, @NonNull String str2) {
        this.dName = str;
        this.dModel = str2;
    }
}

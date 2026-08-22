package com.zte.distbus.basetransfer.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PayloadCallbackParam {

    @SerializedName("currentFile")
    public int currentFile;

    @SerializedName("endpointId")
    public String endpointId;

    @SerializedName("message")
    public String message;

    @SerializedName("path")
    public String path;

    @SerializedName("status")
    public int status;

    @SerializedName("successFile")
    public int successFile;

    @SerializedName("totalFile")
    public int totalFile;

    @SerializedName("payloadId")
    public long payloadId = -1;

    @SerializedName("transferSize")
    public long transferSize = -1;

    @SerializedName("totalSize")
    public long totalSize = -1;

    @SerializedName("percent")
    public int percent = -1;

    @SerializedName("receiveFileUris")
    public ArrayList<String> receiveFileUris = new ArrayList<>();
}

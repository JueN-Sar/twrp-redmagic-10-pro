package com.zte.distbus.basetransfer;

import android.net.Uri;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Payload {
    private byte[] bytes;
    private File file;
    private InputStream inputStream;
    private Uri uriPath;
    private ArrayList<Uri> uriPathList;
    private long id = -1;
    private int type = 4;

    public Payload(byte[] bArr) {
        this.bytes = bArr;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public File getFile() {
        return this.file;
    }

    public long getId() {
        return this.id;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public int getType() {
        return this.type;
    }

    public Uri getUriPath() {
        return this.uriPath;
    }

    public ArrayList<Uri> getUriPathList() {
        return this.uriPathList;
    }

    public void setId(long j2) {
        this.id = j2;
    }

    public Payload(File file) {
        this.file = file;
    }

    public Payload(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Payload(Uri uri) {
        this.uriPath = uri;
    }

    public Payload(ArrayList<Uri> arrayList) {
        this.uriPathList = arrayList;
    }
}

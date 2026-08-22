package cn.nubia.gamecenter.settings.records.bean;

import android.net.Uri;

/* loaded from: classes.dex */
public class HighlightsFile {
    private int duration;
    private long modified;
    private String path;
    private String title;
    private int type;
    private Uri uri;

    public HighlightsFile() {
    }

    public HighlightsFile(String str, String str2, long j, Uri uri, int i, int i2) {
        this.path = str;
        this.title = str2;
        this.modified = j;
        this.uri = uri;
        this.duration = i;
        this.type = i2;
    }

    public int getDuration() {
        return this.duration;
    }

    public long getModified() {
        return this.modified;
    }

    public String getPath() {
        return this.path;
    }

    public String getTitle() {
        return this.title;
    }

    public int getType() {
        return this.type;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setModified(long j) {
        this.modified = j;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}

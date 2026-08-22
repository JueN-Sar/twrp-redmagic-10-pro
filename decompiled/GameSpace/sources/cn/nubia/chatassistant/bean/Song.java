package cn.nubia.chatassistant.bean;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Song implements Serializable {
    public long albumId;
    public int duration;
    public long id;
    public String name;
    public String path;
    public String singer;
    public long size;
    public String time;

    public long getAlbumId() {
        return this.albumId;
    }

    public int getDuration() {
        return this.duration;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public String getSinger() {
        return this.singer;
    }

    public long getSize() {
        return this.size;
    }

    public String getTime() {
        return this.time;
    }

    public void setAlbumId(long j) {
        this.albumId = j;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSinger(String str) {
        this.singer = str;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String toString() {
        return "Song{name='" + this.name + "', singer='" + this.singer + "', size=" + this.size + ", duration=" + this.duration + ", path='" + this.path + "', time='" + this.time + "', albumId=" + this.albumId + ", id=" + this.id + '}';
    }
}

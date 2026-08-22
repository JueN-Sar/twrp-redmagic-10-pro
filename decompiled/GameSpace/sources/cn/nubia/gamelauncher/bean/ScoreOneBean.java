package cn.nubia.gamelauncher.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class ScoreOneBean implements Comparable {
    private String assit;
    private String dead;
    private String duration;
    private String endTime;
    private String kill;
    private String packageName;
    private String result;
    private Long startTime;

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return this.startTime.longValue() >= ((ScoreOneBean) obj).startTime.longValue() ? -1 : 1;
    }

    public String getAssit() {
        return this.assit;
    }

    public String getDead() {
        return this.dead;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getEndTimeDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        date.setTime(Long.parseLong(this.endTime));
        return simpleDateFormat.format(date);
    }

    public String getKill() {
        return this.kill;
    }

    public Long getLongStartTime() {
        return this.startTime;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getResult() {
        return this.result;
    }

    public String getStartTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        date.setTime(this.startTime.longValue());
        return simpleDateFormat.format(date);
    }

    public boolean isWin() {
        String str = this.result;
        if (str != null) {
            return str.equals("1");
        }
        return true;
    }

    public void setAssit(String str) {
        String str2 = "--";
        if (str != null) {
            if (Integer.parseInt(str) < 0) {
                str = "--";
            }
            str2 = str;
        }
        this.assit = str2;
    }

    public void setDead(String str) {
        String str2 = "--";
        if (str != null) {
            if (Integer.parseInt(str) < 0) {
                str = "--";
            }
            str2 = str;
        }
        this.dead = str2;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setKill(String str) {
        String str2 = "--";
        if (str != null) {
            if (Integer.parseInt(str) < 0) {
                str = "--";
            }
            str2 = str;
        }
        this.kill = str2;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }
}

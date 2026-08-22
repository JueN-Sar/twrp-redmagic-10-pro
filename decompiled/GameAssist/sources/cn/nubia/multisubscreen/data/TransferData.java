package cn.nubia.multisubscreen.data;

import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes.dex */
public class TransferData {
    public static final int CODE_FAILED = 0;
    public static final int CODE_SUCCESS = 1;
    public static final String MSG_FAILD = "failed";
    public static final String MSG_SUCCESS = "success";
    private final String action;
    private final String cmd;
    private Integer code;
    private String data;
    private String message;
    private final Long timeStamp = Long.valueOf(System.currentTimeMillis());

    public TransferData(String str, String str2) {
        this.cmd = str;
        this.action = str2;
    }

    public String getAction() {
        return this.action;
    }

    public String getCmd() {
        return this.cmd;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "TransferData{cmd='" + this.cmd + NubiaTextClock.QUOTE + ", timeStamp=" + this.timeStamp + ", action='" + this.action + NubiaTextClock.QUOTE + ", code=" + this.code + ", message='" + this.message + NubiaTextClock.QUOTE + ", data='" + this.data + NubiaTextClock.QUOTE + '}';
    }
}

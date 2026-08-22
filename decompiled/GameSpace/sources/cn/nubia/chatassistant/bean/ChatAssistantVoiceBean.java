package cn.nubia.chatassistant.bean;

import java.io.Serializable;

/* loaded from: classes.dex */
public class ChatAssistantVoiceBean implements Serializable {
    private boolean isSelected;
    private boolean isSystemDefault;
    private int process = -1;
    private long time;
    private String voiceFileName;
    private String voiceFilePath;

    public ChatAssistantVoiceBean() {
    }

    public ChatAssistantVoiceBean(String str) {
        this.voiceFileName = str;
    }

    public int getProcess() {
        return this.process;
    }

    public long getTime() {
        return this.time;
    }

    public String getVoiceFileName() {
        return this.voiceFileName;
    }

    public String getVoiceFilePath() {
        return this.voiceFilePath;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public boolean isSystemDefault() {
        return this.isSystemDefault;
    }

    public void setProcess(int i) {
        this.process = i;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setSystemDefault(boolean z) {
        this.isSystemDefault = z;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public void setVoiceFileName(String str) {
        this.voiceFileName = str;
    }

    public void setVoiceFilePath(String str) {
        this.voiceFilePath = str;
    }
}

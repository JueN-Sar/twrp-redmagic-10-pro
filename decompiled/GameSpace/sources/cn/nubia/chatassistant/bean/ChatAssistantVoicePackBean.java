package cn.nubia.chatassistant.bean;

import java.io.Serializable;

/* loaded from: classes.dex */
public class ChatAssistantVoicePackBean implements Serializable {
    private boolean isShow;
    private boolean isSystemDefault;
    private int position;
    private String voicePackName;

    public ChatAssistantVoicePackBean() {
    }

    public ChatAssistantVoicePackBean(String str) {
        this.voicePackName = str;
    }

    public int getPosition() {
        return this.position;
    }

    public String getVoicePackName() {
        return this.voicePackName;
    }

    public boolean isShow() {
        return this.isShow;
    }

    public boolean isSystemDefault() {
        return this.isSystemDefault;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }

    public void setSystemDefault(boolean z) {
        this.isSystemDefault = z;
    }

    public void setVoicePackName(String str) {
        this.voicePackName = str;
    }
}

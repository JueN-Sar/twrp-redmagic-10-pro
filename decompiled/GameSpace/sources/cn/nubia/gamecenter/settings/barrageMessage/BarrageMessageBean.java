package cn.nubia.gamecenter.settings.barrageMessage;

import android.content.Context;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.settings.trackclient.Track;

/* loaded from: classes.dex */
public class BarrageMessageBean {
    private static final String GSC_BARRAGE_MESSAGE = "gsc_meditation_level";
    private static final String GSC_BARRAGE_MESSAGE_LOCATION = "gsc_barrage_message_location";
    private static final String GSC_BARRAGE_MESSAGE_MAX_LENGTH = "gsc_barrage_message_max_length";
    private static final String GSC_BARRAGE_MESSAGE_NOTIFICATION = "gsc_barrage_message_shield_notification";
    private static final String GSC_BARRAGE_MESSAGE_QUICKREPLY = "gsc_barrage_message_quickreply";
    private static final String GSC_BARRAGE_MESSAGE_SOURCE = "gsc_barrage_message_bubble_source";
    private static final String GSC_BARRAGE_MESSAGE_TRANSPARENCY = "gsc_barrage_message_transparency";
    private static final String GSC_BARRAGE_MESSAGE_TYPE = "gsc_barrage_message_bubble_type";
    private static final String GSC_BARRAGE_MESSAGE_TYPEFACE = "gsc_barrage_message_bubble_typeface";
    private static final String GSC_BARRAGE_MESSAGE_VELOCITY = "gsc_barrage_message_bubble_velocity";
    private int bubble;
    private Context context;
    private int fontSize;
    private int length;
    private int location;
    private boolean quickReply;
    private boolean shieldNotification;
    private String source;
    private boolean totalSwitch;
    private int transparency;
    private int velocity;

    public BarrageMessageBean(Context context) {
        this.context = context;
        this.totalSwitch = SettingUtil.getBoolean(context, "gsc_meditation_level", false);
        this.bubble = SettingUtil.getInt(context, GSC_BARRAGE_MESSAGE_TYPE, 0);
        this.velocity = SettingUtil.getInt(context, GSC_BARRAGE_MESSAGE_VELOCITY, 1);
        this.fontSize = SettingUtil.getInt(context, GSC_BARRAGE_MESSAGE_TYPEFACE, 1);
        this.transparency = SettingUtil.getInt(context, GSC_BARRAGE_MESSAGE_TRANSPARENCY, 5);
        this.quickReply = SettingUtil.getBoolean(context, GSC_BARRAGE_MESSAGE_QUICKREPLY, true);
        this.shieldNotification = SettingUtil.getBoolean(context, GSC_BARRAGE_MESSAGE_NOTIFICATION, true);
        this.location = SettingUtil.getInt(context, GSC_BARRAGE_MESSAGE_LOCATION, 0);
        this.length = SettingUtil.getInt(context, GSC_BARRAGE_MESSAGE_MAX_LENGTH, 54);
    }

    public int getBubble() {
        return this.bubble;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public int getLength() {
        return this.length;
    }

    public int getLocation() {
        return this.location;
    }

    public String getSource() {
        return this.source;
    }

    public int getTransparency() {
        return this.transparency;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public boolean isQuickReply() {
        return this.quickReply;
    }

    public boolean isShieldNotification() {
        return this.shieldNotification;
    }

    public boolean isTotalSwitch() {
        return this.totalSwitch;
    }

    public void setBubble(int i) {
        this.bubble = i;
        SettingUtil.putInt(this.context, GSC_BARRAGE_MESSAGE_TYPE, i);
        Track.bulletMessage(this);
    }

    public void setFontSize(int i) {
        this.fontSize = i;
        SettingUtil.putInt(this.context, GSC_BARRAGE_MESSAGE_TYPEFACE, i);
        Track.bulletMessage(this);
    }

    public void setLength(int i) {
        this.length = i;
        SettingUtil.putInt(this.context, GSC_BARRAGE_MESSAGE_MAX_LENGTH, i);
        Track.bulletMessage(this);
    }

    public void setLocation(int i) {
        this.location = i;
        SettingUtil.putInt(this.context, GSC_BARRAGE_MESSAGE_LOCATION, i);
        Track.bulletMessage(this);
    }

    public void setQuickReply(boolean z) {
        this.quickReply = z;
        SettingUtil.putBoolean(this.context, GSC_BARRAGE_MESSAGE_QUICKREPLY, z);
        Track.bulletMessage(this);
    }

    public void setShieldNotification(boolean z) {
        this.shieldNotification = z;
        SettingUtil.putBoolean(this.context, GSC_BARRAGE_MESSAGE_NOTIFICATION, z);
        Track.bulletMessage(this);
    }

    public void setSource(String str) {
        this.source = str;
        Track.bulletMessage(this);
    }

    public void setTotalSwitch(boolean z) {
        this.totalSwitch = z;
        SettingUtil.putBoolean(this.context, "gsc_meditation_level", z);
        Track.bulletMessage(this);
    }

    public void setTransparency(int i) {
        this.transparency = i;
        SettingUtil.putInt(this.context, GSC_BARRAGE_MESSAGE_TRANSPARENCY, i);
        Track.bulletMessage(this);
    }

    public void setVelocity(int i) {
        this.velocity = i;
        SettingUtil.putInt(this.context, GSC_BARRAGE_MESSAGE_VELOCITY, i);
        Track.bulletMessage(this);
    }
}

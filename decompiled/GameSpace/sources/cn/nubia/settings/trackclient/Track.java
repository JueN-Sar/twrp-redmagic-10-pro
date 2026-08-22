package cn.nubia.settings.trackclient;

import cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageBean;

/* loaded from: classes.dex */
public class Track {
    private static final String BUBBLE = "bubble";
    private static final String BULLET_MESSAGE_SWITCH = "bullet_message_switch";
    private static final String BYPASS_SHIELD = "bypass_shield";
    private static final String DOWN = "down";
    private static final String FONT_SIZE = "font_size";
    public static final String GAME_ACCOUNT_PAGE_VIEW = "game_account_page_view";
    public static final String GAME_CENTER_ATHLETIC_SWITCH_OFF_STATUS = "game_center_athletic_switch_off_status";
    private static final String HIGH = "high";
    private static final String LENGTH = "length";
    private static final String LONG = "long";
    private static final String LOW = "low";
    private static final String MIDDLE = "middle";
    public static final String MOMENT_LIGHT_EFFECT_STATUS = "moment_light_effect_status";
    private static final String NO = "no";
    private static final String OFF = "off";
    private static final String ON = "on";
    private static final String OPACITY = "opacity";
    public static final String OPTION = "option";
    public static final String PERS_CENTER_BASIC_CALLING_FLOAT_SWITCH = "pers_center_basic_calling_float_switch";
    private static final String POSITION = "position";
    public static final String REDMAGIC_WATERMARK_SWITCH_CLICK = "redmagic_watermark_switch_click";
    private static final String REPLY_BUTTON = "reply_button";
    private static final String SHORT = "short";
    private static final String SOURCE = "source";
    private static final String SPEED = "speed";
    private static final String SWITCH_STATUS = "switch_status";
    private static final String TOTAL_SWITCH = "total_switch";
    private static final String UNKNOWN = "unknown";
    private static final String UP = "up";
    private static final String YES = "yes";

    public static void bulletMessage(BarrageMessageBean barrageMessageBean) {
        StringBuilder sb = new StringBuilder("total_switch=");
        sb.append(barrageMessageBean.isTotalSwitch() ? "on" : "off").append(" bubble=");
        sb.append(barrageMessageBean.getBubble()).append(" speed=");
        sb.append(getType(barrageMessageBean.getVelocity())).append(" font_size=");
        sb.append(getType(barrageMessageBean.getFontSize())).append(" opacity=");
        sb.append(barrageMessageBean.getTransparency()).append(" source=");
        sb.append(barrageMessageBean.getSource()).append(" reply_button=");
        sb.append(barrageMessageBean.isQuickReply() ? "on" : "off").append(" bypass_shield=");
        sb.append(barrageMessageBean.isShieldNotification() ? "on" : "off").append(" position=");
        sb.append(barrageMessageBean.getLocation() == 0 ? UP : DOWN).append(" length=");
        sb.append(barrageMessageBean.getLength() == 15 ? SHORT : LONG);
        NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", BULLET_MESSAGE_SWITCH, BULLET_MESSAGE_SWITCH, sb.toString());
    }

    public static void event(String str) {
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", str);
    }

    public static void eventEveryDay(String str, String str2, String str3) {
        NubiaTrackManager.getInstance().sendEventEveryDay("cn.nubia.gamelauncher", str, str2, str3);
    }

    private static String getType(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "unknown" : HIGH : MIDDLE : LOW;
    }

    public static void login(boolean z) {
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", GAME_ACCOUNT_PAGE_VIEW, "login", z ? YES : NO);
    }

    public static void option(String str, boolean z) {
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", str, "option", z ? "on" : "off");
    }

    public static void switchStatus(String str, boolean z) {
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", str, "switch_status", z ? "on" : "off");
    }
}

package cn.nubia.screensaver.power;

/* loaded from: classes.dex */
public final class PowerReason {
    public static String a(int i2) {
        switch (i2) {
            case 0:
                return "application";
            case 1:
                return "device_admin";
            case 2:
                return "timeout";
            case 3:
                return "lid_switch";
            case 4:
                return "power_button";
            case 5:
                return "hdmi";
            case 6:
                return "sleep_button";
            case 7:
                return "accessibility";
            case 8:
                return "force_suspend";
            case 9:
                return "inattentive";
            case 10:
                return "quiescent";
            case 11:
                return "display_group_removed";
            case 12:
                return "display_groups_turned_off";
            case 13:
                return "device_folded";
            default:
                return Integer.toString(i2);
        }
    }

    public static String b(int i2) {
        switch (i2) {
            case 0:
                return "WAKE_REASON_UNKNOWN";
            case 1:
                return "WAKE_REASON_POWER_BUTTON";
            case 2:
                return "WAKE_REASON_APPLICATION";
            case 3:
                return "WAKE_REASON_PLUGGED_IN";
            case 4:
                return "WAKE_REASON_GESTURE";
            case 5:
                return "WAKE_REASON_CAMERA_LAUNCH";
            case 6:
                return "WAKE_REASON_WAKE_KEY";
            case 7:
                return "WAKE_REASON_WAKE_MOTION";
            case 8:
                return "WAKE_REASON_HDMI";
            case 9:
                return "WAKE_REASON_LID";
            case 10:
                return "WAKE_REASON_DISPLAY_GROUP_ADDED";
            case 11:
                return "WAKE_REASON_DISPLAY_GROUP_TURNED_ON";
            case 12:
                return "WAKE_REASON_UNFOLD_DEVICE";
            case 13:
                return "WAKE_REASON_DREAM_FINISHED";
            default:
                return Integer.toString(i2);
        }
    }

    public static String c(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? Integer.toString(i2) : "DOZING" : "DREAMING" : "AWAKE" : "ASLEEP";
    }
}

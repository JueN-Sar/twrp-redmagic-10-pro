package cn.nubia.multisubscreen.primary;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class NotificationMsgCtrl extends AbsFunCtrl {

    /* renamed from: n, reason: collision with root package name */
    private ContentObserver f7959n;

    /* renamed from: o, reason: collision with root package name */
    private int f7960o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f7961p;

    public NotificationMsgCtrl(Context context, String str) {
        super(context, str);
        this.f7960o = 1;
        this.f7961p = false;
        this.f7959n = new ContentObserver(new Handler(ThreadManager.c().f())) { // from class: cn.nubia.multisubscreen.primary.NotificationMsgCtrl.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                int y;
                GaLog.e("NotificationMsgCtrl", "NotificationMsgCtrl onChange uri = " + uri);
                if (!uri.getLastPathSegment().equals("multi_sub_screen_notification_msg") || NotificationMsgCtrl.this.f7960o == (y = NotificationMsgCtrl.this.y())) {
                    return;
                }
                NotificationMsgCtrl.this.f7960o = y;
                NotificationMsgCtrl notificationMsgCtrl = NotificationMsgCtrl.this;
                notificationMsgCtrl.q(Integer.toString(notificationMsgCtrl.f7960o));
                NotificationMsgCtrl notificationMsgCtrl2 = NotificationMsgCtrl.this;
                notificationMsgCtrl2.x(notificationMsgCtrl2.f7960o == 1);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int y() {
        return Settings.System.getInt(this.f7938h.getContentResolver(), "multi_sub_screen_notification_msg", 1);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        int y = y();
        this.f7960o = y;
        q(Integer.toString(y));
        x(this.f7960o == 1);
        this.f7938h.getContentResolver().registerContentObserver(Settings.System.getUriFor("multi_sub_screen_notification_msg"), false, this.f7959n);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        this.f7938h.getContentResolver().unregisterContentObserver(this.f7959n);
        x(false);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean o(String str) {
        GaLog.b("NotificationMsgCtrl", "onModify value = " + str);
        Settings.System.putInt(this.f7938h.getContentResolver(), "multi_sub_screen_notification_msg", Integer.parseInt(str));
        return true;
    }
}

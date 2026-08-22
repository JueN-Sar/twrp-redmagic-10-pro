package cn.nubia.gameassist.dessert.policy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PowerManager;
import com.zte.gameassist.utils.GaLog;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class WackLockController {

    /* renamed from: a, reason: collision with root package name */
    private Context f6321a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f6322b;

    /* renamed from: c, reason: collision with root package name */
    private PowerManager.WakeLock f6323c;

    /* renamed from: d, reason: collision with root package name */
    public SharedPreferences f6324d;

    public WackLockController(Context context) {
        this.f6321a = context;
        this.f6324d = context.getSharedPreferences("active_mode_wack_lock_list", 0);
    }

    public void a() {
        if (this.f6323c == null) {
            this.f6323c = ((PowerManager) this.f6321a.getSystemService("power")).newWakeLock(10, "ActiveMode.WackLockController");
        }
        if (!this.f6322b) {
            this.f6323c.acquire();
            GaLog.a("ActiveMode.WackLockController", " ActiveMode  acquireWakeLock  ! ! ! ! ");
        }
        this.f6322b = true;
    }

    public boolean b(String str) {
        return this.f6324d.getInt(str, -1) >= 0;
    }

    public void c() {
        PowerManager.WakeLock wakeLock = this.f6323c;
        if (wakeLock != null && this.f6322b) {
            wakeLock.release();
        }
        GaLog.a("ActiveMode.WackLockController", " ActiveMode  releaseWakeLock  ! ! ! ! ");
        this.f6322b = false;
    }

    public void d(String str) {
        SharedPreferences.Editor edit = this.f6324d.edit();
        edit.remove(str);
        edit.apply();
    }

    public void e() {
        SharedPreferences.Editor edit = this.f6324d.edit();
        Iterator<Map.Entry<String, ?>> it = this.f6324d.getAll().entrySet().iterator();
        while (it.hasNext()) {
            edit.remove(it.next().getKey());
        }
        edit.apply();
    }

    public void f(String str, int i2) {
        SharedPreferences.Editor edit = this.f6324d.edit();
        edit.putInt(str, i2);
        edit.apply();
    }
}

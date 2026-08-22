package com.zte.gameassist.common;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameAssistToken;
import com.zte.gameassist.utils.GaLog;
import java.util.Iterator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
class GameAssistToken extends AbsGameAssistToken {
    private AbsGameAssistToken.ActivityEntity mResumedEntity;
    private Runnable mUpdateSystemWindows;
    private final SystemMgr systemMgr;

    /* renamed from: com.zte.gameassist.common.GameAssistToken$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(AbsGameAssistToken.SystemWindow systemWindow, String str) {
            String str2;
            if (GameAssistToken.this.systemMgr.f16568k || (str2 = systemWindow.mTitle) == null || !str2.contains(str)) {
                return;
            }
            GaLog.e("SystemMgr", "onWindowTokenListChange find black win=" + str);
            GameAssistToken.this.systemMgr.f16568k = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(final AbsGameAssistToken.SystemWindow systemWindow) {
            SystemMgr.H.forEach(new Consumer() { // from class: com.zte.gameassist.common.i
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GameAssistToken.AnonymousClass1.this.c(systemWindow, (String) obj);
                }
            });
            StringBuilder sb = new StringBuilder();
            SystemMgr systemMgr = GameAssistToken.this.systemMgr;
            sb.append(systemMgr.f16567j);
            sb.append(systemWindow.mTitle);
            sb.append(":");
            systemMgr.f16567j = sb.toString();
        }

        @Override // java.lang.Runnable
        public void run() {
            GameAssistToken.this.systemMgr.f16568k = false;
            GameAssistToken.this.systemMgr.f16567j = "";
            GameAssistToken.this.systemMgr.f16566i.forEach(new Consumer() { // from class: com.zte.gameassist.common.h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GameAssistToken.AnonymousClass1.this.d((AbsGameAssistToken.SystemWindow) obj);
                }
            });
        }
    }

    public GameAssistToken(SystemMgr systemMgr, CommanderList commanderList, Context context, Handler handler) {
        super(context, handler, commanderList);
        this.mUpdateSystemWindows = new AnonymousClass1();
        this.systemMgr = systemMgr;
    }

    private int getTaskHashcode(AbsGameAssistToken.ActivityEntity activityEntity) {
        Bundle bundle = activityEntity.mData;
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt("task_hash_code", 0);
    }

    private void onActivityResumedInternal(AbsGameAssistToken.ActivityEntity activityEntity) {
        if (activityEntity == null) {
            GaLog.k("SystemMgr", "onActivityResumedInternal null");
            return;
        }
        ComponentName componentName = activityEntity.mActivity;
        if ("com.tencent.mm".equals(componentName.getPackageName())) {
            GaLog.g("SystemMgr", "onActivityResumedInternal " + activityEntity.mData);
        } else if (SystemMgr.f16556q != null && SystemMgr.f16556q.toString().equals(activityEntity.toString())) {
            GaLog.e("SystemMgr", "onActivityResumedInternal equals");
            return;
        }
        AbsGameAssistToken.ActivityEntity activityEntity2 = SystemMgr.f16556q;
        SystemMgr.f16556q = activityEntity;
        String flattenToShortString = activityEntity.mActivity.flattenToShortString();
        if (activityEntity.mDisplayId == 0) {
            this.systemMgr.f16563f = flattenToShortString.contains("com.zte.convert3d");
        }
        if (activityEntity.mDisplayId == SystemMgr.J && !this.systemMgr.f16563f) {
            GaLog.e("SystemMgr", "onActivityResumedInternal 3d return");
            return;
        }
        if (this.systemMgr.Q(flattenToShortString)) {
            GaLog.e("SystemMgr", "onResumedActivity need filter param:" + flattenToShortString);
            return;
        }
        SystemMgr.E = false;
        SystemMgr.u = flattenToShortString;
        SystemMgr.x = activityEntity.mWindowMode;
        SystemMgr.y = activityEntity.mActivityType;
        SystemMgr.z = activityEntity.mUserId;
        SystemMgr.B = activityEntity.mStackId;
        SystemMgr.t = componentName.getPackageName();
        this.systemMgr.f16562e = false;
        if (SystemMgr.x == 0 || SystemMgr.x == 1 || SystemMgr.u.contains("com.android.quickstep.SplitActivity") || SystemMgr.x == 86) {
            String str = SystemMgr.w;
            int i2 = SystemMgr.A;
            SystemMgr.v = SystemMgr.u;
            SystemMgr.w = SystemMgr.t;
            SystemMgr.E = true;
            SystemMgr.A = getTaskHashcode(activityEntity);
            if (!str.equals(SystemMgr.w)) {
                this.systemMgr.f16562e = true;
                this.systemMgr.V(SystemMgr.w, SystemMgr.A);
            } else if ("com.tencent.mm".equals(SystemMgr.w) && i2 != SystemMgr.A) {
                this.systemMgr.f16562e = true;
                this.systemMgr.V(SystemMgr.w, SystemMgr.A);
            } else if (activityEntity2 != null && activityEntity.mActivity.equals(activityEntity2.mActivity)) {
                GaLog.e("SystemMgr", "resumed activity pid changed");
                this.systemMgr.l();
            }
            this.systemMgr.U(SystemMgr.v);
            if ("com.zte.mifavor.launcher".equals(SystemMgr.t) && SystemMgr.G()) {
                SystemMgr.f16557r = activityEntity;
            }
        }
        this.systemMgr.R(SystemMgr.u);
    }

    @Override // com.zte.gameassist.AbsGameAssistToken
    protected void init(AbsGameAssistToken.GameAssistControllerWrapper gameAssistControllerWrapper) {
        GaLog.a("SystemMgr", "init " + gameAssistControllerWrapper);
        this.systemMgr.S(gameAssistControllerWrapper);
    }

    @Override // com.zte.gameassist.AbsGameAssistToken
    protected void onActivityResumed(AbsGameAssistToken.ActivityEntity activityEntity) {
        GaLog.g("SystemMgr", "onActivityResumed " + activityEntity);
        if (activityEntity == null || activityEntity.mActivity == null) {
            return;
        }
        int i2 = activityEntity.mDisplayId;
        if (i2 == 0 || i2 == SystemMgr.J) {
            onActivityResumedInternal(activityEntity);
        } else {
            this.systemMgr.k(activityEntity.mActivity, activityEntity.mDisplayId);
        }
    }

    @Override // com.zte.gameassist.AbsGameAssistToken
    protected void onFocuesWindowChanged(AbsGameAssistToken.FocuesWindow focuesWindow) {
        this.systemMgr.T(focuesWindow);
    }

    @Override // com.zte.gameassist.AbsGameAssistToken
    protected void onFullActivityFirstCreate(AbsGameAssistToken.ActivityEntity activityEntity) {
        ComponentName componentName;
        if (activityEntity == null || (componentName = activityEntity.mActivity) == null) {
            return;
        }
        String flattenToShortString = componentName.flattenToShortString();
        String packageName = componentName.getPackageName();
        int i2 = activityEntity.mDisplayId;
        if (i2 == 0 || i2 == SystemMgr.J) {
            Iterator it = SystemMgr.K.iterator();
            while (it.hasNext()) {
                if (flattenToShortString.contains((String) it.next())) {
                    GaLog.e("SystemMgr", "onLaunch ignore activity : " + flattenToShortString);
                    return;
                }
            }
            int i3 = activityEntity.mWindowMode;
            if (i3 == 0 || i3 == 1 || SystemMgr.x == 86) {
                SystemMgr.D = packageName;
            }
            this.systemMgr.j(packageName);
            GaLog.g("SystemMgr", "onLauncherFirstActivity " + SystemMgr.D);
        }
    }

    @Override // com.zte.gameassist.AbsGameAssistToken
    protected void onSystemWindowChanged(boolean z, AbsGameAssistToken.SystemWindow systemWindow) {
        GaLog.g("SystemMgr", "onSystemWindowChanged show=" + z + " " + systemWindow);
        if (z && !this.systemMgr.f16566i.contains(systemWindow)) {
            this.systemMgr.f16566i.add(systemWindow);
            this.mHandler.removeCallbacks(this.mUpdateSystemWindows);
            this.mHandler.postDelayed(this.mUpdateSystemWindows, 10L);
            SystemWindowMonitor.g().a(true, systemWindow);
            return;
        }
        if (z || !this.systemMgr.f16566i.contains(systemWindow)) {
            return;
        }
        this.systemMgr.f16566i.remove(systemWindow);
        this.mHandler.removeCallbacks(this.mUpdateSystemWindows);
        this.mHandler.postDelayed(this.mUpdateSystemWindows, 10L);
        SystemWindowMonitor.g().a(false, systemWindow);
    }
}

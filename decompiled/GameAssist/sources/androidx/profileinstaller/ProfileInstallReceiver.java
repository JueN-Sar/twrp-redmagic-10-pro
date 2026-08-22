package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.profileinstaller.ProfileInstaller;

/* loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {

    class ResultDiagnostics implements ProfileInstaller.DiagnosticsCallback {
        ResultDiagnostics() {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void a(int i2, Object obj) {
            ProfileInstaller.f4824b.a(i2, obj);
            ProfileInstallReceiver.this.setResultCode(i2);
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void b(int i2, Object obj) {
            ProfileInstaller.f4824b.b(i2, obj);
        }
    }

    static void a(ProfileInstaller.DiagnosticsCallback diagnosticsCallback) {
        Process.sendSignal(Process.myPid(), 10);
        diagnosticsCallback.a(12, null);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @Nullable Intent intent) {
        Bundle extras;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            ProfileInstaller.k(context, new b(), new ResultDiagnostics(), true);
            return;
        }
        if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if ("WRITE_SKIP_FILE".equals(string)) {
                    ProfileInstaller.l(context, new b(), new ResultDiagnostics());
                    return;
                } else {
                    if ("DELETE_SKIP_FILE".equals(string)) {
                        ProfileInstaller.c(context, new b(), new ResultDiagnostics());
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            a(new ResultDiagnostics());
            return;
        }
        if (!"androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) || (extras = intent.getExtras()) == null) {
            return;
        }
        String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
        ResultDiagnostics resultDiagnostics = new ResultDiagnostics();
        if ("DROP_SHADER_CACHE".equals(string2)) {
            BenchmarkOperation.b(context, resultDiagnostics);
        } else {
            resultDiagnostics.a(16, null);
        }
    }
}

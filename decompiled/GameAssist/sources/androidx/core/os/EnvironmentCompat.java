package androidx.core.os;

import android.os.Environment;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.io.File;

/* loaded from: classes.dex */
public final class EnvironmentCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static String a(File file) {
            return Environment.getExternalStorageState(file);
        }
    }
}

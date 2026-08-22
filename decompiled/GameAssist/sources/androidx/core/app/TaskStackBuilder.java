package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class TaskStackBuilder implements Iterable<Intent> {

    /* renamed from: c, reason: collision with root package name */
    private final ArrayList f2836c = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private final Context f2837h;

    public interface SupportParentable {
        Intent g();
    }

    private TaskStackBuilder(Context context) {
        this.f2837h = context;
    }

    public static TaskStackBuilder g(Context context) {
        return new TaskStackBuilder(context);
    }

    public TaskStackBuilder b(Intent intent) {
        this.f2836c.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TaskStackBuilder d(Activity activity) {
        Intent g2 = activity instanceof SupportParentable ? ((SupportParentable) activity).g() : null;
        if (g2 == null) {
            g2 = NavUtils.a(activity);
        }
        if (g2 != null) {
            ComponentName component = g2.getComponent();
            if (component == null) {
                component = g2.resolveActivity(this.f2837h.getPackageManager());
            }
            f(component);
            b(g2);
        }
        return this;
    }

    public TaskStackBuilder f(ComponentName componentName) {
        int size = this.f2836c.size();
        try {
            Intent b2 = NavUtils.b(this.f2837h, componentName);
            while (b2 != null) {
                this.f2836c.add(size, b2);
                b2 = NavUtils.b(this.f2837h, b2.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e2);
        }
    }

    public void h() {
        i(null);
    }

    public void i(Bundle bundle) {
        if (this.f2836c.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) this.f2836c.toArray(new Intent[0]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (ContextCompat.l(this.f2837h, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        this.f2837h.startActivity(intent);
    }

    @Override // java.lang.Iterable
    public Iterator<Intent> iterator() {
        return this.f2836c.iterator();
    }
}

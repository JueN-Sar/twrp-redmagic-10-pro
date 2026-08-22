package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata
@RestrictTo
/* loaded from: classes.dex */
public class ComponentActivity extends Activity implements LifecycleOwner, KeyEventDispatcher.Component {

    /* renamed from: c, reason: collision with root package name */
    private final SimpleArrayMap f2642c = new SimpleArrayMap();

    /* renamed from: h, reason: collision with root package name */
    private final LifecycleRegistry f2643h = new LifecycleRegistry(this);

    @Metadata
    @Deprecated
    @RestrictTo
    public static class ExtraData {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    private final boolean A(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            String str = strArr[0];
            switch (str.hashCode()) {
                case -645125871:
                    if (!str.equals("--translation")) {
                    }
                    break;
                case 100470631:
                    if (!str.equals("--dump-dumpable")) {
                    }
                    break;
                case 472614934:
                    if (!str.equals("--list-dumpables")) {
                    }
                    break;
                case 1159329357:
                    if (!str.equals("--contentcapture")) {
                    }
                    break;
                case 1455016274:
                    if (!str.equals("--autofill")) {
                    }
                    break;
            }
            return true;
        }
        return false;
    }

    public Lifecycle a() {
        return this.f2643h;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.e(event, "event");
        View decorView = getWindow().getDecorView();
        Intrinsics.d(decorView, "window.decorView");
        if (KeyEventDispatcher.a(decorView, event)) {
            return true;
        }
        return KeyEventDispatcher.b(this, decorView, this, event);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        Intrinsics.e(event, "event");
        View decorView = getWindow().getDecorView();
        Intrinsics.d(decorView, "window.decorView");
        if (KeyEventDispatcher.a(decorView, event)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override // androidx.core.view.KeyEventDispatcher.Component
    public boolean j(KeyEvent event) {
        Intrinsics.e(event, "event");
        return super.dispatchKeyEvent(event);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.f4354h.b(this);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.e(outState, "outState");
        this.f2643h.m(Lifecycle.State.CREATED);
        super.onSaveInstanceState(outState);
    }

    public ExtraData x(Class extraDataClass) {
        Intrinsics.e(extraDataClass, "extraDataClass");
        return (ExtraData) this.f2642c.get(extraDataClass);
    }

    public void y(ExtraData extraData) {
        Intrinsics.e(extraData, "extraData");
        this.f2642c.put(extraData.getClass(), extraData);
    }

    protected final boolean z(String[] strArr) {
        return !A(strArr);
    }
}

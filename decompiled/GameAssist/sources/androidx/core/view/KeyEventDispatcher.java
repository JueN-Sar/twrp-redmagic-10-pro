package androidx.core.view;

import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class KeyEventDispatcher {

    public interface Component {
        boolean j(KeyEvent keyEvent);
    }

    public static boolean a(View view, KeyEvent keyEvent) {
        return ViewCompat.g(view, keyEvent);
    }

    public static boolean b(Component component, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (component == null) {
            return false;
        }
        return component.j(keyEvent);
    }
}

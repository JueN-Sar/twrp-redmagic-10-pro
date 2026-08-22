package cn.nubia.componentcenter;

import android.content.Context;
import android.content.res.Configuration;

/* loaded from: classes.dex */
public interface IComApplication {
    default void a() {
    }

    default void addDependence() {
    }

    default void create(Context context) {
        addDependence();
        onCreate(context);
    }

    default void onConfigurationChanged(Configuration configuration) {
    }

    void onCreate(Context context);

    void onStop();

    default void stop() {
        onStop();
        a();
    }
}

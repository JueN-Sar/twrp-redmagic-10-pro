package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;

@RequiresApi
/* loaded from: classes.dex */
public class AppComponentFactory extends android.app.AppComponentFactory {
    public Activity a(ClassLoader classLoader, String str, Intent intent) {
        try {
            return (Activity) Class.forName(str, false, classLoader).asSubclass(Activity.class).getDeclaredConstructor(null).newInstance(null);
        } catch (NoSuchMethodException | InvocationTargetException e2) {
            throw new RuntimeException("Couldn't call constructor", e2);
        }
    }

    public Application b(ClassLoader classLoader, String str) {
        try {
            return (Application) Class.forName(str, false, classLoader).asSubclass(Application.class).getDeclaredConstructor(null).newInstance(null);
        } catch (NoSuchMethodException | InvocationTargetException e2) {
            throw new RuntimeException("Couldn't call constructor", e2);
        }
    }

    public ContentProvider c(ClassLoader classLoader, String str) {
        try {
            return (ContentProvider) Class.forName(str, false, classLoader).asSubclass(ContentProvider.class).getDeclaredConstructor(null).newInstance(null);
        } catch (NoSuchMethodException | InvocationTargetException e2) {
            throw new RuntimeException("Couldn't call constructor", e2);
        }
    }

    public BroadcastReceiver d(ClassLoader classLoader, String str, Intent intent) {
        try {
            return (BroadcastReceiver) Class.forName(str, false, classLoader).asSubclass(BroadcastReceiver.class).getDeclaredConstructor(null).newInstance(null);
        } catch (NoSuchMethodException | InvocationTargetException e2) {
            throw new RuntimeException("Couldn't call constructor", e2);
        }
    }

    public Service e(ClassLoader classLoader, String str, Intent intent) {
        try {
            return (Service) Class.forName(str, false, classLoader).asSubclass(Service.class).getDeclaredConstructor(null).newInstance(null);
        } catch (NoSuchMethodException | InvocationTargetException e2) {
            throw new RuntimeException("Couldn't call constructor", e2);
        }
    }

    @Override // android.app.AppComponentFactory
    public final Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) {
        return (Activity) CoreComponentFactory.a(a(classLoader, str, intent));
    }

    @Override // android.app.AppComponentFactory
    public final Application instantiateApplication(ClassLoader classLoader, String str) {
        return (Application) CoreComponentFactory.a(b(classLoader, str));
    }

    @Override // android.app.AppComponentFactory
    public final ContentProvider instantiateProvider(ClassLoader classLoader, String str) {
        return (ContentProvider) CoreComponentFactory.a(c(classLoader, str));
    }

    @Override // android.app.AppComponentFactory
    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) {
        return (BroadcastReceiver) CoreComponentFactory.a(d(classLoader, str, intent));
    }

    @Override // android.app.AppComponentFactory
    public final Service instantiateService(ClassLoader classLoader, String str, Intent intent) {
        return (Service) CoreComponentFactory.a(e(classLoader, str, intent));
    }
}

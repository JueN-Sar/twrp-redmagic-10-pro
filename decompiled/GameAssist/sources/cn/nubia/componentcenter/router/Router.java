package cn.nubia.componentcenter.router;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.api.IApi;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public class Router {
    private static final String TAG = "Router";
    private static final HashMap<String, IComApplication> mComponents = new HashMap<>();
    private final Map<Class<? extends IApi>, IApi> mDependenceMap;
    private final HashMap<String, Object> mServices;

    private static class Holder {

        /* renamed from: a, reason: collision with root package name */
        private static final Router f5871a = new Router();
    }

    public static synchronized <T extends IApi> void addDependence(Class<T> cls, T t) {
        synchronized (Router.class) {
            if (cls == null || t == null) {
                return;
            }
            getInstance().mDependenceMap.put(cls, t);
        }
    }

    public static synchronized <T extends IApi> T getDependence(Class<T> cls) {
        synchronized (Router.class) {
            if (cls == null) {
                return null;
            }
            return cls.cast(getInstance().mDependenceMap.get(cls));
        }
    }

    public static Router getInstance() {
        return Holder.f5871a;
    }

    public static void onConfigurationChanged(final Configuration configuration) {
        HashMap<String, IComApplication> hashMap = mComponents;
        if (hashMap.isEmpty()) {
            return;
        }
        hashMap.forEach(new BiConsumer() { // from class: cn.nubia.componentcenter.router.a
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((IComApplication) obj2).onConfigurationChanged(configuration);
            }
        });
    }

    public static void registerComponent(@Nullable String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HashMap<String, IComApplication> hashMap = mComponents;
        if (hashMap.keySet().contains(str)) {
            return;
        }
        try {
            GaLog.a(TAG, "register " + str);
            IComApplication iComApplication = (IComApplication) Class.forName(str).newInstance();
            iComApplication.create(context);
            hashMap.put(str, iComApplication);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static synchronized <T extends IApi> void removeDependence(Class<T> cls) {
        synchronized (Router.class) {
            if (cls == null) {
                return;
            }
            getInstance().mDependenceMap.remove(cls);
        }
    }

    public static void unregisterComponent(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HashMap<String, IComApplication> hashMap = mComponents;
        if (hashMap.keySet().contains(str)) {
            hashMap.get(str).stop();
            hashMap.remove(str);
            return;
        }
        try {
            ((IComApplication) Class.forName(str).newInstance()).stop();
            hashMap.remove(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void addService(String str, Object obj) {
        if (str == null || obj == null) {
            return;
        }
        GaLog.a(TAG, "add service " + str);
        this.mServices.put(str, obj);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("Router:");
        printWriter.println("  mComponents=" + mComponents);
    }

    public synchronized <T> T getService(String str) {
        if (str == null) {
            return null;
        }
        return (T) this.mServices.get(str);
    }

    public synchronized void removeService(String str) {
        if (str == null) {
            return;
        }
        this.mServices.remove(str);
    }

    private Router() {
        this.mServices = new HashMap<>();
        this.mDependenceMap = new HashMap();
    }
}

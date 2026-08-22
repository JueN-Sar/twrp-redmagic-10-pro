package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class NotificationManagerCompat {

    /* renamed from: d, reason: collision with root package name */
    private static String f2781d;

    /* renamed from: a, reason: collision with root package name */
    private final Context f2784a;

    /* renamed from: b, reason: collision with root package name */
    private final NotificationManager f2785b;

    /* renamed from: c, reason: collision with root package name */
    private static final Object f2780c = new Object();

    /* renamed from: e, reason: collision with root package name */
    private static Set f2782e = new HashSet();

    /* renamed from: f, reason: collision with root package name */
    private static final Object f2783f = new Object();

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static List<StatusBarNotification> a(NotificationManager notificationManager) {
            StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
            return activeNotifications == null ? new ArrayList() : Arrays.asList(activeNotifications);
        }

        @DoNotInline
        static int b(NotificationManager notificationManager) {
            return notificationManager.getCurrentInterruptionFilter();
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static boolean a(NotificationManager notificationManager) {
            return notificationManager.areNotificationsEnabled();
        }

        @DoNotInline
        static int b(NotificationManager notificationManager) {
            return notificationManager.getImportance();
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static void a(NotificationManager notificationManager, NotificationChannel notificationChannel) {
            notificationManager.createNotificationChannel(notificationChannel);
        }

        @DoNotInline
        static void b(NotificationManager notificationManager, NotificationChannelGroup notificationChannelGroup) {
            notificationManager.createNotificationChannelGroup(notificationChannelGroup);
        }

        @DoNotInline
        static void c(NotificationManager notificationManager, List<NotificationChannelGroup> list) {
            notificationManager.createNotificationChannelGroups(list);
        }

        @DoNotInline
        static void d(NotificationManager notificationManager, List<NotificationChannel> list) {
            notificationManager.createNotificationChannels(list);
        }

        @DoNotInline
        static void e(NotificationManager notificationManager, String str) {
            notificationManager.deleteNotificationChannel(str);
        }

        @DoNotInline
        static void f(NotificationManager notificationManager, String str) {
            notificationManager.deleteNotificationChannelGroup(str);
        }

        @DoNotInline
        static String g(NotificationChannel notificationChannel) {
            return notificationChannel.getId();
        }

        @DoNotInline
        static String h(NotificationChannelGroup notificationChannelGroup) {
            return notificationChannelGroup.getId();
        }

        @DoNotInline
        static NotificationChannel i(NotificationManager notificationManager, String str) {
            return notificationManager.getNotificationChannel(str);
        }

        @DoNotInline
        static List<NotificationChannelGroup> j(NotificationManager notificationManager) {
            return notificationManager.getNotificationChannelGroups();
        }

        @DoNotInline
        static List<NotificationChannel> k(NotificationManager notificationManager) {
            return notificationManager.getNotificationChannels();
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static NotificationChannelGroup a(NotificationManager notificationManager, String str) {
            return notificationManager.getNotificationChannelGroup(str);
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static NotificationChannel a(NotificationManager notificationManager, String str, String str2) {
            return notificationManager.getNotificationChannel(str, str2);
        }

        @DoNotInline
        static String b(NotificationChannel notificationChannel) {
            return notificationChannel.getParentChannelId();
        }
    }

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        static boolean a(NotificationManager notificationManager) {
            return notificationManager.canUseFullScreenIntent();
        }
    }

    private static class CancelTask implements Task {

        /* renamed from: a, reason: collision with root package name */
        final String f2786a;

        /* renamed from: b, reason: collision with root package name */
        final int f2787b;

        /* renamed from: c, reason: collision with root package name */
        final String f2788c;

        /* renamed from: d, reason: collision with root package name */
        final boolean f2789d;

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void a(INotificationSideChannel iNotificationSideChannel) {
            if (this.f2789d) {
                iNotificationSideChannel.cancelAll(this.f2786a);
            } else {
                iNotificationSideChannel.cancel(this.f2786a, this.f2787b, this.f2788c);
            }
        }

        public String toString() {
            return "CancelTask[packageName:" + this.f2786a + ", id:" + this.f2787b + ", tag:" + this.f2788c + ", all:" + this.f2789d + "]";
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface InterruptionFilter {
    }

    public static class NotificationWithIdAndTag {
    }

    private static class NotifyTask implements Task {

        /* renamed from: a, reason: collision with root package name */
        final String f2790a;

        /* renamed from: b, reason: collision with root package name */
        final int f2791b;

        /* renamed from: c, reason: collision with root package name */
        final String f2792c;

        /* renamed from: d, reason: collision with root package name */
        final Notification f2793d;

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void a(INotificationSideChannel iNotificationSideChannel) {
            iNotificationSideChannel.notify(this.f2790a, this.f2791b, this.f2792c, this.f2793d);
        }

        public String toString() {
            return "NotifyTask[packageName:" + this.f2790a + ", id:" + this.f2791b + ", tag:" + this.f2792c + "]";
        }
    }

    private static class ServiceConnectedEvent {

        /* renamed from: a, reason: collision with root package name */
        final ComponentName f2794a;

        /* renamed from: b, reason: collision with root package name */
        final IBinder f2795b;

        ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
            this.f2794a = componentName;
            this.f2795b = iBinder;
        }
    }

    private static class SideChannelManager implements Handler.Callback, ServiceConnection {

        /* renamed from: c, reason: collision with root package name */
        private final Context f2796c;

        /* renamed from: h, reason: collision with root package name */
        private final Handler f2797h;

        /* renamed from: i, reason: collision with root package name */
        private final Map f2798i;

        /* renamed from: j, reason: collision with root package name */
        private Set f2799j;

        private static class ListenerRecord {

            /* renamed from: a, reason: collision with root package name */
            final ComponentName f2800a;

            /* renamed from: c, reason: collision with root package name */
            INotificationSideChannel f2802c;

            /* renamed from: b, reason: collision with root package name */
            boolean f2801b = false;

            /* renamed from: d, reason: collision with root package name */
            ArrayDeque f2803d = new ArrayDeque();

            /* renamed from: e, reason: collision with root package name */
            int f2804e = 0;

            ListenerRecord(ComponentName componentName) {
                this.f2800a = componentName;
            }
        }

        private boolean a(ListenerRecord listenerRecord) {
            if (listenerRecord.f2801b) {
                return true;
            }
            boolean bindService = this.f2796c.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(listenerRecord.f2800a), this, 33);
            listenerRecord.f2801b = bindService;
            if (bindService) {
                listenerRecord.f2804e = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + listenerRecord.f2800a);
                this.f2796c.unbindService(this);
            }
            return listenerRecord.f2801b;
        }

        private void b(ListenerRecord listenerRecord) {
            if (listenerRecord.f2801b) {
                this.f2796c.unbindService(this);
                listenerRecord.f2801b = false;
            }
            listenerRecord.f2802c = null;
        }

        private void c(Task task) {
            i();
            for (ListenerRecord listenerRecord : this.f2798i.values()) {
                listenerRecord.f2803d.add(task);
                g(listenerRecord);
            }
        }

        private void d(ComponentName componentName) {
            ListenerRecord listenerRecord = (ListenerRecord) this.f2798i.get(componentName);
            if (listenerRecord != null) {
                g(listenerRecord);
            }
        }

        private void e(ComponentName componentName, IBinder iBinder) {
            ListenerRecord listenerRecord = (ListenerRecord) this.f2798i.get(componentName);
            if (listenerRecord != null) {
                listenerRecord.f2802c = INotificationSideChannel.Stub.asInterface(iBinder);
                listenerRecord.f2804e = 0;
                g(listenerRecord);
            }
        }

        private void f(ComponentName componentName) {
            ListenerRecord listenerRecord = (ListenerRecord) this.f2798i.get(componentName);
            if (listenerRecord != null) {
                b(listenerRecord);
            }
        }

        private void g(ListenerRecord listenerRecord) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + listenerRecord.f2800a + ", " + listenerRecord.f2803d.size() + " queued tasks");
            }
            if (listenerRecord.f2803d.isEmpty()) {
                return;
            }
            if (!a(listenerRecord) || listenerRecord.f2802c == null) {
                h(listenerRecord);
                return;
            }
            while (true) {
                Task task = (Task) listenerRecord.f2803d.peek();
                if (task == null) {
                    break;
                }
                try {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Sending task " + task);
                    }
                    task.a(listenerRecord.f2802c);
                    listenerRecord.f2803d.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Remote service has died: " + listenerRecord.f2800a);
                    }
                } catch (RemoteException e2) {
                    Log.w("NotifManCompat", "RemoteException communicating with " + listenerRecord.f2800a, e2);
                }
            }
            if (listenerRecord.f2803d.isEmpty()) {
                return;
            }
            h(listenerRecord);
        }

        private void h(ListenerRecord listenerRecord) {
            if (this.f2797h.hasMessages(3, listenerRecord.f2800a)) {
                return;
            }
            int i2 = listenerRecord.f2804e;
            int i3 = i2 + 1;
            listenerRecord.f2804e = i3;
            if (i3 <= 6) {
                int i4 = (1 << i2) * 1000;
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Scheduling retry for " + i4 + " ms");
                }
                this.f2797h.sendMessageDelayed(this.f2797h.obtainMessage(3, listenerRecord.f2800a), i4);
                return;
            }
            Log.w("NotifManCompat", "Giving up on delivering " + listenerRecord.f2803d.size() + " tasks to " + listenerRecord.f2800a + " after " + listenerRecord.f2804e + " retries");
            listenerRecord.f2803d.clear();
        }

        private void i() {
            Set a2 = NotificationManagerCompat.a(this.f2796c);
            if (a2.equals(this.f2799j)) {
                return;
            }
            this.f2799j = a2;
            List<ResolveInfo> queryIntentServices = this.f2796c.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (a2.contains(resolveInfo.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.f2798i.containsKey(componentName2)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                    }
                    this.f2798i.put(componentName2, new ListenerRecord(componentName2));
                }
            }
            Iterator it = this.f2798i.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!hashSet.contains(entry.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                    }
                    b((ListenerRecord) entry.getValue());
                    it.remove();
                }
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                c((Task) message.obj);
                return true;
            }
            if (i2 == 1) {
                ServiceConnectedEvent serviceConnectedEvent = (ServiceConnectedEvent) message.obj;
                e(serviceConnectedEvent.f2794a, serviceConnectedEvent.f2795b);
                return true;
            }
            if (i2 == 2) {
                f((ComponentName) message.obj);
                return true;
            }
            if (i2 != 3) {
                return false;
            }
            d((ComponentName) message.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName);
            }
            this.f2797h.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName);
            }
            this.f2797h.obtainMessage(2, componentName).sendToTarget();
        }
    }

    private interface Task {
        void a(INotificationSideChannel iNotificationSideChannel);
    }

    @VisibleForTesting
    NotificationManagerCompat(@NonNull NotificationManager notificationManager, @NonNull Context context) {
        this.f2784a = context;
        this.f2785b = notificationManager;
    }

    public static Set a(Context context) {
        Set set;
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (f2780c) {
            if (string != null) {
                try {
                    if (!string.equals(f2781d)) {
                        String[] split = string.split(":", -1);
                        HashSet hashSet = new HashSet(split.length);
                        for (String str : split) {
                            ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                            if (unflattenFromString != null) {
                                hashSet.add(unflattenFromString.getPackageName());
                            }
                        }
                        f2782e = hashSet;
                        f2781d = string;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            set = f2782e;
        }
        return set;
    }
}

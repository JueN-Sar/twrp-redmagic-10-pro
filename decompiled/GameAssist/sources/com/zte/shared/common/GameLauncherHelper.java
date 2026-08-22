package com.zte.shared.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.zte.shared.common.GameLauncherHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class GameLauncherHelper extends ContentObserver {

    /* renamed from: e, reason: collision with root package name */
    private static final Uri f18099e = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false");

    /* renamed from: f, reason: collision with root package name */
    private static GameLauncherHelper f18100f;

    /* renamed from: a, reason: collision with root package name */
    private final List f18101a;

    /* renamed from: b, reason: collision with root package name */
    private final List f18102b;

    /* renamed from: c, reason: collision with root package name */
    private final Context f18103c;

    /* renamed from: d, reason: collision with root package name */
    private final ContentResolver f18104d;

    /* renamed from: com.zte.shared.common.GameLauncherHelper$1, reason: invalid class name */
    class AnonymousClass1 extends ContentObserver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ GameLauncherHelper f18105a;

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            this.f18105a.h();
        }
    }

    public abstract class GameListListener {
        abstract void a(List list);

        public boolean equals(Object obj) {
            return obj instanceof Listener ? obj.equals(this) : super.equals(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class Listener {

        /* renamed from: a, reason: collision with root package name */
        private final GameListListener f18106a;

        /* renamed from: b, reason: collision with root package name */
        private final Handler f18107b;

        public boolean equals(Object obj) {
            return obj instanceof GameListListener ? this.f18106a == obj : super.equals(obj);
        }
    }

    private GameLauncherHelper(Context context) {
        super(null);
        this.f18101a = new ArrayList();
        this.f18102b = new ArrayList();
        this.f18103c = context;
        this.f18104d = context.getContentResolver();
    }

    public static List c(Context context) {
        ArrayList arrayList = new ArrayList();
        if (d(context).f18101a.size() > 0) {
            arrayList.addAll(d(context).f18102b);
        } else {
            try {
                Cursor query = context.getContentResolver().query(f18099e, new String[]{"component"}, null, null, null);
                if (query != null) {
                    int columnIndex = query.getColumnIndex("component");
                    while (query.moveToNext()) {
                        arrayList.add(query.getString(columnIndex));
                    }
                }
            } catch (Exception e2) {
                Log.e("GameLauncherHelper", e2.toString());
            }
        }
        return arrayList;
    }

    private static GameLauncherHelper d(Context context) {
        if (f18100f == null) {
            synchronized (GameLauncherHelper.class) {
                try {
                    if (f18100f == null) {
                        f18100f = new GameLauncherHelper(context);
                    }
                } finally {
                }
            }
        }
        return f18100f;
    }

    public static boolean e(Context context, String str) {
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                cursor = context.getContentResolver().query(f18099e, null, " component like ?", new String[]{str + ",%"}, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        z = true;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(Listener listener) {
        listener.f18106a.a(this.f18102b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(final Listener listener) {
        if (listener.f18107b == null) {
            listener.f18106a.a(this.f18102b);
        } else {
            listener.f18107b.post(new Runnable() { // from class: com.zte.shared.common.b
                @Override // java.lang.Runnable
                public final void run() {
                    GameLauncherHelper.this.f(listener);
                }
            });
        }
    }

    public void h() {
        try {
            Cursor query = this.f18103c.getContentResolver().query(f18099e, null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("component");
                ArrayList arrayList = new ArrayList();
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    arrayList.add(query.getString(columnIndex));
                }
                synchronized (this.f18102b) {
                    this.f18102b.clear();
                    this.f18102b.addAll(arrayList);
                }
                query.close();
            } finally {
            }
        } catch (Exception e2) {
            Log.e("GameLauncherHelper", "Failed load game app data.", e2);
        }
        if (this.f18101a.size() > 0) {
            this.f18101a.forEach(new Consumer() { // from class: com.zte.shared.common.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GameLauncherHelper.this.g((GameLauncherHelper.Listener) obj);
                }
            });
        }
    }
}

package com.zte.shared.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import com.redmagic.game.GameKeysHelper;
import com.zte.shared.common.GameLauncherHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class GameLauncherHelper extends ContentObserver {
    private static final String APPADD_URI_NO_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false";
    private static final Uri APPADD_URI_NO_NOTIFY_URI = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false");
    public static final String ATTR_APP_NAME = "component";
    private static GameLauncherHelper HELPER = null;
    private static final String TAG = "GameLauncherHelper";
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private final List<String> mGameApps;
    private ContentObserver mGamePackageAddObserver;
    private Handler mHandler;
    private final List<Listener> mListers;

    public abstract class GameListListener {
        public GameListListener() {
        }

        public boolean equals(Object obj) {
            return obj instanceof Listener ? obj.equals(this) : super.equals(obj);
        }

        abstract void onGameListUpdate(List<String> list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class Listener {
        private final Handler mHandler;
        private final GameListListener mListener;

        private Listener(GameListListener gameListListener, Handler handler) {
            this.mListener = gameListListener;
            this.mHandler = handler;
        }

        public boolean equals(Object obj) {
            return obj instanceof GameListListener ? this.mListener == obj : super.equals(obj);
        }
    }

    private GameLauncherHelper(Context context) {
        super(null);
        this.mListers = new ArrayList();
        this.mGameApps = new ArrayList();
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
    }

    private boolean getGameKeyState() {
        return GameKeysHelper.getDefault().isOpenGameKeys(Settings.Global.getInt(this.mContext.getContentResolver(), "nubia_db_game_keys", 0));
    }

    public static List<String> getGameLauncherAppNameList(Context context) {
        ArrayList arrayList = new ArrayList();
        if (getInstance(context).mListers.size() > 0) {
            arrayList.addAll(getInstance(context).mGameApps);
        } else {
            try {
                Cursor query = context.getContentResolver().query(APPADD_URI_NO_NOTIFY_URI, new String[]{"component"}, null, null, null);
                if (query != null) {
                    int columnIndex = query.getColumnIndex("component");
                    while (query.moveToNext()) {
                        arrayList.add(query.getString(columnIndex));
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
        return arrayList;
    }

    private static GameLauncherHelper getInstance(Context context) {
        if (HELPER == null) {
            synchronized (GameLauncherHelper.class) {
                if (HELPER == null) {
                    HELPER = new GameLauncherHelper(context);
                }
            }
        }
        return HELPER;
    }

    public static boolean isAppInDatabase(Context context, String str) {
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                cursor = context.getContentResolver().query(APPADD_URI_NO_NOTIFY_URI, null, " component like ?", new String[]{str + ",%"}, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        z = true;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
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

    public boolean isOpenGameKeys(int i) {
        return (i & 1) != 0;
    }

    /* renamed from: lambda$updateAddPackage$1$com-zte-shared-common-GameLauncherHelper, reason: not valid java name */
    /* synthetic */ void m460x9a4ed5d4(Listener listener) {
        listener.mListener.onGameListUpdate(this.mGameApps);
    }

    /* renamed from: lambda$updateAddPackage$2$com-zte-shared-common-GameLauncherHelper, reason: not valid java name */
    /* synthetic */ void m461xa1b40af3(final Listener listener) {
        if (listener.mHandler == null) {
            listener.mListener.onGameListUpdate(this.mGameApps);
        } else {
            listener.mHandler.post(new Runnable() { // from class: com.zte.shared.common.GameLauncherHelper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    GameLauncherHelper.this.m460x9a4ed5d4(listener);
                }
            });
        }
    }

    public synchronized void registerGameListListener(GameListListener gameListListener, Handler handler) {
        if (gameListListener != null) {
            if (!this.mListers.contains(gameListListener)) {
                this.mListers.add(new Listener(gameListListener, handler));
            }
            if (this.mListers.size() == 1) {
                if (handler == null && (handler = this.mHandler) == null) {
                    handler = new Handler(Looper.myLooper());
                }
                this.mGamePackageAddObserver = new ContentObserver(handler) { // from class: com.zte.shared.common.GameLauncherHelper.1
                    @Override // android.database.ContentObserver
                    public void onChange(boolean z) {
                        GameLauncherHelper.this.m459x234270dc();
                    }
                };
                handler.post(new Runnable() { // from class: com.zte.shared.common.GameLauncherHelper$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        GameLauncherHelper.this.m459x234270dc();
                    }
                });
                this.mContentResolver.registerContentObserver(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), false, this.mGamePackageAddObserver);
            }
        }
    }

    public void setLooper(Looper looper) {
        if (this.mHandler != null) {
            this.mHandler = new Handler(looper);
        }
    }

    public synchronized void unregisterGameListListener(GameListListener gameListListener) {
        if (gameListListener != null) {
            if (this.mListers.contains(gameListListener)) {
                this.mListers.remove(gameListListener);
            }
            if (this.mListers.size() == 0) {
                this.mContentResolver.unregisterContentObserver(this.mGamePackageAddObserver);
                this.mGamePackageAddObserver = null;
            }
        }
    }

    /* renamed from: updateAddPackage, reason: merged with bridge method [inline-methods] */
    public void m459x234270dc() {
        try {
            Cursor query = this.mContext.getContentResolver().query(APPADD_URI_NO_NOTIFY_URI, null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("component");
                ArrayList arrayList = new ArrayList();
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    arrayList.add(query.getString(columnIndex));
                }
                synchronized (this.mGameApps) {
                    this.mGameApps.clear();
                    this.mGameApps.addAll(arrayList);
                }
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed load game app data.", e);
        }
        if (this.mListers.size() > 0) {
            this.mListers.forEach(new Consumer() { // from class: com.zte.shared.common.GameLauncherHelper$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GameLauncherHelper.this.m461xa1b40af3((GameLauncherHelper.Listener) obj);
                }
            });
        }
    }
}

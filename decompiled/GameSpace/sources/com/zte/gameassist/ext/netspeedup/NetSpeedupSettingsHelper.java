package com.zte.gameassist.ext.netspeedup;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import com.zte.gameassist.ext.utils.SettingsHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public final class NetSpeedupSettingsHelper {
    public static final String SETTINGS_GLOBAL_GAME_NET_SPEEDUP_SUPPORT_PACKAGES = "game_net_speedup_support_packages";
    private static volatile NetSpeedupSettingsHelper sInstance;
    private final CopyOnWriteArrayList<NetSpeedupConfigChangeCallback> mCallbacks;
    private final AtomicReference<Map<String, NetSpeedupConfig>> mConfigByPackage;
    private ContentObserver mContentObserver;
    private final Context mContext;
    private final Object mLock;
    private final Handler mMainHandler;

    public interface NetSpeedupConfigChangeCallback {
        void onNetSpeedupConfigChanged(NetSpeedupConfig netSpeedupConfig);
    }

    private NetSpeedupSettingsHelper(Context context) {
        Object obj = new Object();
        this.mLock = obj;
        this.mCallbacks = new CopyOnWriteArrayList<>();
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mConfigByPackage = new AtomicReference<>(Collections.emptyMap());
        this.mContext = context;
        synchronized (obj) {
            registerSettingsObserverLocked();
            refreshCacheFromSettingsLocked();
        }
    }

    private static List<NetSpeedupConfig> buildConfigChangeNotifications(Map<String, NetSpeedupConfig> map, Map<String, NetSpeedupConfig> map2) {
        TreeSet<String> treeSet = new TreeSet();
        treeSet.addAll(map.keySet());
        treeSet.addAll(map2.keySet());
        ArrayList arrayList = new ArrayList();
        for (String str : treeSet) {
            NetSpeedupConfig netSpeedupConfig = map.get(str);
            NetSpeedupConfig netSpeedupConfig2 = map2.get(str);
            if (netSpeedupConfig == null && netSpeedupConfig2 != null) {
                arrayList.add(netSpeedupConfig2.normalizedCopy());
            } else if (netSpeedupConfig != null && netSpeedupConfig2 == null) {
                arrayList.add(NetSpeedupConfig.withDefaults(str));
            } else if (netSpeedupConfig != null && netSpeedupConfig2 != null && !sameNormalizedContent(netSpeedupConfig, netSpeedupConfig2)) {
                arrayList.add(netSpeedupConfig2.normalizedCopy());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deliverConfigCallbacks, reason: merged with bridge method [inline-methods] */
    public void m452xe1162b52(List<NetSpeedupConfig> list) {
        for (NetSpeedupConfig netSpeedupConfig : list) {
            Iterator<NetSpeedupConfigChangeCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onNetSpeedupConfigChanged(netSpeedupConfig);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private void dispatchConfigDeltas(Map<String, NetSpeedupConfig> map, Map<String, NetSpeedupConfig> map2) {
        List<NetSpeedupConfig> buildConfigChangeNotifications = buildConfigChangeNotifications(map, map2);
        if (buildConfigChangeNotifications.isEmpty()) {
            return;
        }
        final ArrayList arrayList = new ArrayList(buildConfigChangeNotifications);
        this.mMainHandler.post(new Runnable() { // from class: com.zte.gameassist.ext.netspeedup.NetSpeedupSettingsHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NetSpeedupSettingsHelper.this.m452xe1162b52(arrayList);
            }
        });
    }

    public static NetSpeedupSettingsHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (NetSpeedupSettingsHelper.class) {
                if (sInstance == null) {
                    sInstance = new NetSpeedupSettingsHelper(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static boolean isNormalizedDefaultConfig(NetSpeedupConfig netSpeedupConfig) {
        return !netSpeedupConfig.mUserManualEnabled && NetSpeedupConfig.VENDOR_LEIGOD.equals(netSpeedupConfig.mAcceleratorVendor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSettingsValueChanged() {
        Map<String, NetSpeedupConfig> snapshotMap;
        Map<String, NetSpeedupConfig> snapshotMap2;
        synchronized (this.mLock) {
            snapshotMap = snapshotMap(this.mConfigByPackage.get());
            refreshCacheFromSettingsLocked();
            snapshotMap2 = snapshotMap(this.mConfigByPackage.get());
        }
        dispatchConfigDeltas(snapshotMap, snapshotMap2);
    }

    private List<NetSpeedupConfig> parseRawToList(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        Iterator<String> it = splitSegments(str).iterator();
        while (it.hasNext()) {
            NetSpeedupConfig parseSegment = parseSegment(it.next());
            if (parseSegment != null) {
                arrayList.add(parseSegment);
            }
        }
        return arrayList;
    }

    private NetSpeedupConfig parseSegment(String str) {
        String trim = str.trim();
        if (TextUtils.isEmpty(trim)) {
            return null;
        }
        List<String> splitColonFields = splitColonFields(trim);
        if (splitColonFields.size() != 3) {
            return null;
        }
        String trim2 = splitColonFields.get(0).trim();
        String str2 = splitColonFields.get(1);
        String str3 = splitColonFields.get(2);
        if (TextUtils.isEmpty(trim2)) {
            return null;
        }
        boolean equalsIgnoreCase = NetSpeedupConfig.USER_MANUAL_ON.equalsIgnoreCase(str2);
        boolean z = (equalsIgnoreCase || "N".equalsIgnoreCase(str2)) ? equalsIgnoreCase : false;
        String normalizeVendor = NetSpeedupConfig.normalizeVendor(str3);
        NetSpeedupConfig netSpeedupConfig = new NetSpeedupConfig(trim2);
        netSpeedupConfig.mUserManualEnabled = z;
        netSpeedupConfig.mAcceleratorVendor = normalizeVendor;
        return netSpeedupConfig;
    }

    private void persistMapValues(Map<String, NetSpeedupConfig> map) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (NetSpeedupConfig netSpeedupConfig : map.values()) {
            if (!z) {
                sb.append(',');
            }
            sb.append(serialize(netSpeedupConfig));
            z = false;
        }
        SettingsHelper.putGlobalSettings(this.mContext, SETTINGS_GLOBAL_GAME_NET_SPEEDUP_SUPPORT_PACKAGES, sb.toString());
    }

    private String readGlobalString(String str) {
        try {
            return Settings.Global.getString(this.mContext.getContentResolver(), str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private void refreshCacheFromSettingsLocked() {
        replaceCacheFromListLocked(parseRawToList(readGlobalString(SETTINGS_GLOBAL_GAME_NET_SPEEDUP_SUPPORT_PACKAGES)));
    }

    private void registerSettingsObserverLocked() {
        if (this.mContentObserver != null) {
            return;
        }
        Uri uriFor = Settings.Global.getUriFor(SETTINGS_GLOBAL_GAME_NET_SPEEDUP_SUPPORT_PACKAGES);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        ContentObserver contentObserver = new ContentObserver(this.mMainHandler) { // from class: com.zte.gameassist.ext.netspeedup.NetSpeedupSettingsHelper.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                NetSpeedupSettingsHelper.this.onSettingsValueChanged();
            }
        };
        this.mContentObserver = contentObserver;
        contentResolver.registerContentObserver(uriFor, false, contentObserver);
    }

    private void replaceCacheAndPersistLocked(Map<String, NetSpeedupConfig> map) {
        if (map.isEmpty()) {
            SettingsHelper.putGlobalSettings(this.mContext, SETTINGS_GLOBAL_GAME_NET_SPEEDUP_SUPPORT_PACKAGES, "");
            this.mConfigByPackage.set(Collections.emptyMap());
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, NetSpeedupConfig> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue().normalizedCopy());
        }
        persistMapValues(linkedHashMap);
        this.mConfigByPackage.set(linkedHashMap);
    }

    private void replaceCacheFromListLocked(List<NetSpeedupConfig> list) {
        if (list.isEmpty()) {
            this.mConfigByPackage.set(Collections.emptyMap());
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (NetSpeedupConfig netSpeedupConfig : list) {
            linkedHashMap.put(netSpeedupConfig.mPackageName, netSpeedupConfig.normalizedCopy());
        }
        this.mConfigByPackage.set(linkedHashMap);
    }

    private static boolean sameNormalizedContent(NetSpeedupConfig netSpeedupConfig, NetSpeedupConfig netSpeedupConfig2) {
        NetSpeedupConfig normalizedCopy = netSpeedupConfig.normalizedCopy();
        NetSpeedupConfig normalizedCopy2 = netSpeedupConfig2.normalizedCopy();
        return normalizedCopy.mUserManualEnabled == normalizedCopy2.mUserManualEnabled && normalizedCopy.mAcceleratorVendor.equals(normalizedCopy2.mAcceleratorVendor);
    }

    private static String serialize(NetSpeedupConfig netSpeedupConfig) {
        NetSpeedupConfig normalizedCopy = netSpeedupConfig.normalizedCopy();
        return normalizedCopy.mPackageName + ':' + (normalizedCopy.mUserManualEnabled ? NetSpeedupConfig.USER_MANUAL_ON : "N") + ':' + normalizedCopy.mAcceleratorVendor;
    }

    private static Map<String, NetSpeedupConfig> snapshotMap(Map<String, NetSpeedupConfig> map) {
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<String, NetSpeedupConfig> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().normalizedCopy());
        }
        return hashMap;
    }

    private static List<String> splitColonFields(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == ':') {
                arrayList.add(str.substring(i, i2));
                i = i2 + 1;
            }
        }
        arrayList.add(str.substring(i));
        return arrayList;
    }

    private static List<String> splitSegments(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == ',') {
                arrayList.add(str.substring(i, i2));
                i = i2 + 1;
            }
        }
        arrayList.add(str.substring(i));
        return arrayList;
    }

    public Map<String, NetSpeedupConfig> getAllConfigsSnapshot() {
        Map<String, NetSpeedupConfig> map = this.mConfigByPackage.get();
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<String, NetSpeedupConfig> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().normalizedCopy());
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public NetSpeedupConfig getConfig(String str) {
        if (TextUtils.isEmpty(str)) {
            return NetSpeedupConfig.withDefaults("");
        }
        NetSpeedupConfig netSpeedupConfig = this.mConfigByPackage.get().get(str);
        return netSpeedupConfig != null ? netSpeedupConfig.normalizedCopy() : NetSpeedupConfig.withDefaults(str);
    }

    public void registerCallback(NetSpeedupConfigChangeCallback netSpeedupConfigChangeCallback) {
        this.mCallbacks.add(netSpeedupConfigChangeCallback);
    }

    public void setConfig(NetSpeedupConfig netSpeedupConfig) {
        Map<String, NetSpeedupConfig> snapshotMap;
        Map<String, NetSpeedupConfig> snapshotMap2;
        if (TextUtils.isEmpty(netSpeedupConfig.mPackageName)) {
            return;
        }
        NetSpeedupConfig normalizedCopy = netSpeedupConfig.normalizedCopy();
        synchronized (this.mLock) {
            snapshotMap = snapshotMap(this.mConfigByPackage.get());
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (NetSpeedupConfig netSpeedupConfig2 : this.mConfigByPackage.get().values()) {
                linkedHashMap.put(netSpeedupConfig2.mPackageName, netSpeedupConfig2.normalizedCopy());
            }
            if (isNormalizedDefaultConfig(normalizedCopy)) {
                linkedHashMap.remove(normalizedCopy.mPackageName);
            } else {
                linkedHashMap.put(normalizedCopy.mPackageName, normalizedCopy);
            }
            replaceCacheAndPersistLocked(linkedHashMap);
            snapshotMap2 = snapshotMap(this.mConfigByPackage.get());
        }
        dispatchConfigDeltas(snapshotMap, snapshotMap2);
    }

    public void unregisterCallback(NetSpeedupConfigChangeCallback netSpeedupConfigChangeCallback) {
        if (netSpeedupConfigChangeCallback == null) {
            return;
        }
        this.mCallbacks.remove(netSpeedupConfigChangeCallback);
    }
}

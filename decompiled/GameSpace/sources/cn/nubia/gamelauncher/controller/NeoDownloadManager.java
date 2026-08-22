package cn.nubia.gamelauncher.controller;

import android.content.Intent;
import android.util.Log;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.bean.NeoIconDownloadInfo;
import cn.nubia.gamelauncher.commoninterface.NeoGameDBColumns;
import cn.nubia.gamelauncher.model.NeoDownloadHelper;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class NeoDownloadManager {
    private static final String ACTION_GAME_PAUSE_DOWNLOAD = "cn.nubia.neogamecenter.PAUSE_DOWNLOAD";
    private static final String ACTION_GAME_RESUME_DOWNLOAD = "cn.nubia.neogamecenter.RESUME_DOWNLOAD";
    private static final String ACTION_PAUSE_DOWNLOAD = "cn.nubia.neostore.PAUSE_DOWNLOAD";
    private static final String ACTION_RESUME_DOWNLOAD = "cn.nubia.neostore.RESUME_DOWNLOAD";
    private static final String EXTRA_APP_ID = "app_id";
    private static final String EXTRA_PK_NAME_KEY = "android.intent.extra.update_progress_key";
    private static String GAME_PACKAGENAME = "cn.nubia.neogamecenter";
    private static String PACKAGENAME = "cn.nubia.neostore";
    private static final NeoDownloadManager ourInstance = new NeoDownloadManager();

    private NeoDownloadManager() {
        if (Util.isTencentAppStore()) {
            PACKAGENAME = "com.tencent.southpole.appstore";
            GAME_PACKAGENAME = "com.tencent.southpole.appstore";
        }
    }

    public static NeoDownloadManager getInstance() {
        return ourInstance;
    }

    private void updateDownloadByMyOs(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (neoIconDownloadInfo == null) {
            return;
        }
        Log.i(NeoDownloadHelper.TAG, "updateDownloadByMyOs()  info = " + neoIconDownloadInfo.status);
        Intent intent = new Intent();
        intent.setPackage(GAME_PACKAGENAME);
        intent.putExtra("app_id", neoIconDownloadInfo.appId);
        try {
            intent.putExtra(EXTRA_PK_NAME_KEY, neoIconDownloadInfo.packageName);
        } catch (Exception e) {
            Log.w(NeoDownloadHelper.TAG, "putExtra EXTRA_PK_NAME_KEY : " + neoIconDownloadInfo.packageName, e);
        }
        if (neoIconDownloadInfo.status.equals(NeoGameDBColumns.STATUS_DOWNLOADING)) {
            intent.setAction(ACTION_GAME_PAUSE_DOWNLOAD);
        } else if (!neoIconDownloadInfo.status.equals(NeoGameDBColumns.STATUS_PAUSE)) {
            return;
        } else {
            intent.setAction(ACTION_GAME_RESUME_DOWNLOAD);
        }
        try {
            GameLauncherApplication.CONTEXT.startService(intent);
        } catch (Exception e2) {
            Log.w(NeoDownloadHelper.TAG, "failed startService ", e2);
        }
    }

    private void updateDownloadByRedMagic(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (neoIconDownloadInfo == null) {
            return;
        }
        Log.i(NeoDownloadHelper.TAG, "Manager -- updateDownloadByRedMagic()  info = " + neoIconDownloadInfo);
        if (neoIconDownloadInfo != null) {
            Intent intent = new Intent();
            if (neoIconDownloadInfo.type == NeoIconDownloadInfo.TYPE_APP_CENTER) {
                intent.setPackage(PACKAGENAME);
            } else if (neoIconDownloadInfo.type == NeoIconDownloadInfo.TYPE_GAME_CENTER) {
                intent.setPackage(GAME_PACKAGENAME);
            }
            intent.putExtra("app_id", neoIconDownloadInfo.appId);
            try {
                intent.putExtra(EXTRA_PK_NAME_KEY, neoIconDownloadInfo.packageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (neoIconDownloadInfo.status.equals(NeoGameDBColumns.STATUS_DOWNLOADING)) {
                if (neoIconDownloadInfo.type == NeoIconDownloadInfo.TYPE_GAME_CENTER) {
                    intent.setAction(ACTION_GAME_PAUSE_DOWNLOAD);
                } else if (neoIconDownloadInfo.type == NeoIconDownloadInfo.TYPE_APP_CENTER) {
                    intent.setAction(ACTION_PAUSE_DOWNLOAD);
                }
            } else {
                if (!neoIconDownloadInfo.status.equals(NeoGameDBColumns.STATUS_PAUSE)) {
                    return;
                }
                if (neoIconDownloadInfo.type == NeoIconDownloadInfo.TYPE_GAME_CENTER) {
                    intent.setAction(ACTION_GAME_RESUME_DOWNLOAD);
                } else if (neoIconDownloadInfo.type == NeoIconDownloadInfo.TYPE_APP_CENTER) {
                    intent.setAction(ACTION_RESUME_DOWNLOAD);
                }
            }
            try {
                GameLauncherApplication.CONTEXT.startService(intent);
            } catch (Exception e2) {
                Log.i(NeoDownloadHelper.TAG, "Manager -- onclick, failed startService ", e2);
            }
        }
    }

    public void doClick(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (CommonUtil.isNubiaOs()) {
            updateDownloadByRedMagic(neoIconDownloadInfo);
        } else {
            updateDownloadByMyOs(neoIconDownloadInfo);
        }
    }
}

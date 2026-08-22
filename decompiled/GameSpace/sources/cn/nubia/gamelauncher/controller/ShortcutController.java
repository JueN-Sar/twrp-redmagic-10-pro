package cn.nubia.gamelauncher.controller;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.activity.GameSpaceActivity;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class ShortcutController implements View.OnClickListener {
    public static final String ACTION_ADD_SHORTCUT = "cn.nubia.launcher.gamespace.action.INSTALL_SHORTCUT";
    public static final String CLICKED_ADD_SHORTCUT = "has_game_launcher_shortcut_icon";
    public static final String GAME_SPACE_ENABLE = "game_space_shortcut";
    public static final String HAS_SHORTCUT = "has_game_launcher_shortcut_icon";
    public static final String TAG = "ShortcutController";
    boolean isHideShortcutTips = false;
    private Button mAddShortcut;
    private TextView mAddShortcutTips;
    private ZteShortcutObserver mZteShortcutObserver;

    private class ZteShortcutObserver extends ContentObserver {
        public ZteShortcutObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            int i = Settings.Global.getInt(ShortcutController.this.getContext().getContentResolver(), "has_game_launcher_shortcut_icon", 0);
            if (ShortcutController.this.supportAddShortcut()) {
                ShortcutController.this.mAddShortcut.setVisibility(i != 0 ? 4 : 0);
            }
        }

        public void register() {
            ShortcutController.this.getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor("has_game_launcher_shortcut_icon"), false, this);
        }

        public void unregister() {
            ShortcutController.this.getContext().getContentResolver().unregisterContentObserver(this);
        }
    }

    public ShortcutController(View view) {
        init(view);
    }

    private void addShortCutEnd() {
        Log.d(TAG, "----------> addShortCutEnd() hasShortcut : " + hasShortcut());
        Toast.makeText(getContext(), getContext().getString(R.string.add_shortcut_ok), 0).show();
        this.mAddShortcut.setVisibility(8);
        this.mAddShortcutTips.setVisibility(8);
    }

    private void clickAddShortcut() {
        Log.d(TAG, "----------> clickAddShortcut()");
        addShortcut(false);
        LobbySoundPoolHelper.getInstance().play();
    }

    private void clickAddShortcutTips() {
        Log.d(TAG, "clickAddShortcutTips() ");
        this.isHideShortcutTips = true;
        this.mAddShortcutTips.setVisibility(8);
        LobbySoundPoolHelper.getInstance().play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getContext() {
        return GameLauncherApplication.getAppContext();
    }

    private boolean hasClickedAddZte() {
        return Settings.Global.getInt(getContext().getContentResolver(), "has_game_launcher_shortcut_icon", 0) != 0;
    }

    private boolean hasShortcutNubia() {
        Log.d(TAG, "hasShortcutNubia = " + (Settings.Global.getInt(getContext().getContentResolver(), GAME_SPACE_ENABLE, -1) == 1));
        return Settings.Global.getInt(getContext().getContentResolver(), GAME_SPACE_ENABLE, -1) == 1;
    }

    private boolean hasShortcutZte() {
        int i = Settings.Global.getInt(getContext().getContentResolver(), "has_game_launcher_shortcut_icon", 0);
        Log.d(TAG, "hasShortcutZte = " + i);
        return i != 0;
    }

    private void init(View view) {
        Button button = (Button) view.findViewById(R.id.add_shortcut);
        this.mAddShortcut = button;
        button.setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(R.id.add_shortcut_tips);
        this.mAddShortcutTips = textView;
        textView.setOnClickListener(this);
        boolean z = supportAddShortcut() && !hasShortcut();
        Log.d(TAG, "init() visible = " + z);
        this.mAddShortcut.setVisibility(z ? 0 : 8);
        this.mAddShortcutTips.setVisibility((!z || this.isHideShortcutTips) ? 8 : 0);
    }

    public void addShortcut(boolean z) {
        LogUtil.i(TAG, "addShortcut cn.nubia.launcher.gamespace.action.INSTALL_SHORTCUT");
        Settings.Global.putInt(getContext().getContentResolver(), GAME_SPACE_ENABLE, 1);
        Intent intent = new Intent(ACTION_ADD_SHORTCUT);
        intent.putExtra("android.intent.extra.shortcut.NAME", getContext().getString(R.string.game_space_app_name));
        intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getContext(), R.mipmap.ic_launcher_adaptive));
        if (!GameSpaceConfig.supportGameKey() || Util.isRedMagicRunOnMyOs()) {
            Log.d(TAG, "----------> addShortcut() isExit : " + z);
            intent.setPackage("com.zte.mifavor.launcher");
            Settings.Global.putInt(getContext().getContentResolver(), "has_game_launcher_shortcut_icon", 1);
        }
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.setClass(getContext(), GameSpaceActivity.class);
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        intent.putExtra("fromPkgName", "cn.nubia.gamelauncher");
        getContext().sendBroadcast(intent);
        if (z) {
            return;
        }
        addShortCutEnd();
    }

    public boolean hasShortcut() {
        return (!GameSpaceConfig.supportGameKey() || Util.isRedMagicRunOnMyOs()) ? hasShortcutZte() : hasShortcutNubia();
    }

    public void hideShortcutTips() {
        this.mAddShortcutTips.setVisibility(8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_shortcut /* 2131361879 */:
                clickAddShortcut();
                break;
            case R.id.add_shortcut_tips /* 2131361880 */:
                clickAddShortcutTips();
                break;
        }
    }

    public void registerObserver() {
        if (Util.isZte()) {
            ZteShortcutObserver zteShortcutObserver = new ZteShortcutObserver(new Handler());
            this.mZteShortcutObserver = zteShortcutObserver;
            zteShortcutObserver.register();
        }
    }

    public void resetShortcutTips() {
        boolean z = supportAddShortcut() && !hasShortcut();
        Log.d(TAG, "init() visible = " + z);
        this.mAddShortcutTips.setVisibility((!z || this.isHideShortcutTips) ? 8 : 0);
    }

    public boolean supportAddShortcut() {
        if (Util.isSwitchGameKeyToOtherFunctions()) {
            Log.d(TAG, "supportAddShortcut() isGameKeyMultiFunctions true !");
            return false;
        }
        if ("NX799J".equals(SystemProperties.get("ro.product.name"))) {
            return false;
        }
        return Util.supportVirtualGameKey() || !GameSpaceConfig.supportGameKey();
    }

    public void unregisterObserver() {
        ZteShortcutObserver zteShortcutObserver = this.mZteShortcutObserver;
        if (zteShortcutObserver != null) {
            zteShortcutObserver.unregister();
        }
    }
}

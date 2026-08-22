package cn.nubia.gamelauncher.aimhelper;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AimConfigs {
    private static final String PREFS_NAME = "aim_config";
    private static volatile AimConfigs sInstance;
    private Map<String, AimConfig> configMap = new HashMap();
    private SharedPreferences prefs;

    private class AimConfig {
        private static final int DEFAULT_COLOR = -1;
        private static final int DEFAULT_HIDE_AIM_X = -1;
        private static final int DEFAULT_HIDE_AIM_Y = -1;
        private static final boolean DEFAULT_IS_AUTO = true;
        private static final boolean DEFAULT_IS_HIDE_AIM = false;
        private static final boolean DEFAULT_IS_ON = false;
        private static final boolean DEFAULT_IS_PLUG_ON = true;
        private static final boolean DEFAULT_IS_QUICK_HIDE = false;
        private static final int DEFAULT_SIZE = 70;
        private static final int DEFAULT_STYLE = 1;
        private static final int DEFAULT_TRANSPARENT = 100;
        private static final String KEY_COLOR = "color";
        private static final String KEY_HIDE_AIM = "hideaim";
        private static final String KEY_HIDE_AIM_X = "hideaimX";
        private static final String KEY_HIDE_AIM_Y = "hideaimY";
        private static final String KEY_IS_AUTO = "isAuto";
        private static final String KEY_IS_ON = "isOn";
        private static final String KEY_PLUG_ON = "plug_on";
        private static final String KEY_QUICK_HIDE = "quickhide";
        private static final String KEY_SIZE = "size";
        private static final String KEY_STYLE = "style";
        private static final String KEY_TRANSPARENT = "transparent";
        private JSONObject jsonObject;
        private final String packageName;

        AimConfig(String str, String str2) {
            this.packageName = str;
            try {
                this.jsonObject = new JSONObject(str2);
            } catch (JSONException unused) {
                this.jsonObject = new JSONObject();
            }
        }

        private void save() {
            AimConfigs.this.prefs.edit().putString(this.packageName, toJson()).apply();
        }

        public int getColor() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optInt("color", -1);
            }
            return -1;
        }

        public int getHideAimX() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optInt(KEY_HIDE_AIM_X, -1);
            }
            return -1;
        }

        public int getHideAimY() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optInt(KEY_HIDE_AIM_Y, -1);
            }
            return -1;
        }

        public int getSize() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optInt(KEY_SIZE, 70);
            }
            return 70;
        }

        public int getStyle() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optInt("style", 1);
            }
            return 1;
        }

        public int getTransParent() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optInt(KEY_TRANSPARENT, 100);
            }
            return 70;
        }

        public boolean isAuto() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optBoolean(KEY_IS_AUTO, true);
            }
            return true;
        }

        public boolean isHideAim() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optBoolean(KEY_HIDE_AIM, false);
            }
            return false;
        }

        public boolean isOn() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optBoolean(KEY_IS_ON, false);
            }
            return false;
        }

        public boolean isPlugOn() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optBoolean(KEY_PLUG_ON, true);
            }
            return true;
        }

        public boolean isQuickHide() {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject != null) {
                return jSONObject.optBoolean(KEY_QUICK_HIDE, false);
            }
            return false;
        }

        public void setAuto(boolean z) {
            try {
                if (isAuto() != z) {
                    this.jsonObject.put(KEY_IS_AUTO, z);
                    save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setColor(int i) {
            try {
                if (this.jsonObject.has("color") && getColor() == i) {
                    return;
                }
                this.jsonObject.put("color", i);
                save();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setHideAim(boolean z) {
            try {
                if (isHideAim() != z) {
                    this.jsonObject.put(KEY_HIDE_AIM, z);
                    save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setHideAimX(int i) {
            try {
                if (getHideAimX() != i) {
                    this.jsonObject.put(KEY_HIDE_AIM_X, i);
                    save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setHideAimY(int i) {
            try {
                if (getHideAimY() != i) {
                    this.jsonObject.put(KEY_HIDE_AIM_Y, i);
                    save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setOn(boolean z) {
            try {
                if (isOn() != z) {
                    this.jsonObject.put(KEY_IS_ON, z);
                    save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setPlugOn(boolean z) {
            try {
                if (isPlugOn() != z) {
                    this.jsonObject.put(KEY_PLUG_ON, z);
                    save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setQuickHide(boolean z) {
            try {
                if (isQuickHide() != z) {
                    this.jsonObject.put(KEY_QUICK_HIDE, z);
                    save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setSize(int i) {
            try {
                if (this.jsonObject.has(KEY_SIZE) && getSize() == i) {
                    return;
                }
                this.jsonObject.put(KEY_SIZE, i);
                save();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setStyle(int i) {
            try {
                if (this.jsonObject.has("style") && getStyle() == i) {
                    return;
                }
                this.jsonObject.put("style", i);
                save();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setTransParent(int i) {
            try {
                if (this.jsonObject.has(KEY_TRANSPARENT) && getTransParent() == i) {
                    return;
                }
                this.jsonObject.put(KEY_TRANSPARENT, i);
                save();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String toJson() {
            JSONObject jSONObject = this.jsonObject;
            return jSONObject != null ? jSONObject.toString() : "";
        }
    }

    private AimConfigs(Context context) {
        this.prefs = null;
        this.prefs = context.getSharedPreferences(PREFS_NAME, 0);
        load();
    }

    private AimConfig getConfig(String str) {
        AimConfig aimConfig = this.configMap.get(str);
        return aimConfig == null ? new AimConfig(str, this.prefs.getString(str, "")) : aimConfig;
    }

    public static AimConfigs getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AimConfigs.class) {
                if (sInstance == null) {
                    sInstance = new AimConfigs(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private void load() {
        this.configMap.clear();
        this.prefs.getAll();
        for (String str : this.prefs.getAll().keySet()) {
            this.configMap.put(str, new AimConfig(str, this.prefs.getString(str, "")));
        }
    }

    public int getColor(String str) {
        return getConfig(str).getColor();
    }

    public int getHideAimX(String str) {
        return getConfig(str).getHideAimX();
    }

    public int getHideAimY(String str) {
        return getConfig(str).getHideAimY();
    }

    public int getSize(String str) {
        return getConfig(str).getSize();
    }

    public int getStyle(String str) {
        return getConfig(str).getStyle();
    }

    public int getTransparent(String str) {
        return getConfig(str).getTransParent();
    }

    public boolean isAuto(String str) {
        return getConfig(str).isAuto();
    }

    public boolean isHideAim(String str) {
        return getConfig(str).isHideAim();
    }

    public boolean isQuickHide(String str) {
        return getConfig(str).isQuickHide();
    }

    public void setAuto(String str, boolean z) {
        getConfig(str).setAuto(z);
    }

    public void setColor(String str, int i) {
        getConfig(str).setColor(i);
        NubiaGameTrackManager.updateValue(str);
    }

    public void setHideAim(String str, boolean z) {
        getConfig(str).setHideAim(z);
    }

    public void setHideAimX(String str, int i) {
        getConfig(str).setHideAimX(i);
    }

    public void setHideAimY(String str, int i) {
        getConfig(str).setHideAimY(i);
    }

    public void setQuickHide(String str, boolean z) {
        getConfig(str).setQuickHide(z);
    }

    public void setSize(String str, int i) {
        getConfig(str).setSize(i);
        NubiaGameTrackManager.updateValue(str);
    }

    public void setStyle(String str, int i) {
        getConfig(str).setStyle(i);
        NubiaGameTrackManager.updateValue(str);
    }

    public void setTransparent(String str, int i) {
        getConfig(str).setTransParent(i);
        NubiaGameTrackManager.updateValue(str);
    }
}

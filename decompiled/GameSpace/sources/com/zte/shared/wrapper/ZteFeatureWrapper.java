package com.zte.shared.wrapper;

import android.util.Log;
import cn.nubia.common.util.FeatureUtil;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class ZteFeatureWrapper {
    private static final FeatureMethod FEATURE_METHOD;
    public static final boolean ZTE_FEATURE_KEY_MOUSE_MAP;
    public static final boolean ZTE_FEATURE_MAGIC_GAME_ASSIST;
    public static final boolean ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE;
    public static final boolean ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY;
    public static final boolean ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD;
    public static final boolean ZTE_FEATURE_SCREEN_KEY_MAP;

    private static class FeatureMethod {
        private final Method get;
        private final Method getBooleanDef;
        private final Method getDef;
        private final Method getIntDef;
        private final Method getLongDef;
        Class<?> mFeature;

        public FeatureMethod() {
            this.mFeature = null;
            try {
                this.mFeature = Class.forName("com.zte.feature.Feature");
            } catch (Error e) {
                Log.w("FeatureMethod", "forName e:" + e.getMessage());
            } catch (Exception e2) {
                Log.w("FeatureMethod", "forName e:" + e2.getMessage());
            }
            this.get = getMethod();
            this.getDef = getDefMethod();
            this.getBooleanDef = getBooleanDefMethod();
            this.getIntDef = getIntDefMethod();
            this.getLongDef = getLongDefMethod();
        }

        private Method getBooleanDefMethod() {
            return getMethod("getBoolean", String.class, Boolean.TYPE);
        }

        private Method getDefMethod() {
            return getMethod("get", String.class, String.class);
        }

        private Method getIntDefMethod() {
            return getMethod("getInt", String.class, Integer.TYPE);
        }

        private Method getLongDefMethod() {
            return getMethod("getLong", String.class, Long.TYPE);
        }

        private Method getMethod() {
            return getMethod("get", String.class);
        }

        private Method getMethod(String str, Class<?>... clsArr) {
            Class<?> cls = this.mFeature;
            if (cls == null) {
                return null;
            }
            try {
                return cls.getMethod(str, clsArr);
            } catch (Error e) {
                Log.w("FeatureMethod", "getMethod e:" + e.getMessage());
                return null;
            } catch (Exception e2) {
                Log.w("FeatureMethod", "getMethod e:" + e2.getMessage());
                return null;
            }
        }

        public String get(String str) {
            return get(str, null);
        }

        public String get(String str, String str2) {
            try {
                Method method = this.getDef;
                if (method == null) {
                    return null;
                }
                return (String) method.invoke(null, str, str2);
            } catch (Exception e) {
                Log.w("FeatureMethod", "get:" + e.getMessage());
                return str2;
            }
        }

        public boolean getBoolean(String str) {
            return getBoolean(str, false);
        }

        public boolean getBoolean(String str, boolean z) {
            try {
                Method method = this.getBooleanDef;
                Boolean bool = null;
                if (method != null) {
                    bool = Boolean.valueOf(((Boolean) method.invoke(null, str, Boolean.valueOf(z))).booleanValue());
                }
                return bool.booleanValue();
            } catch (Error unused) {
                return z;
            } catch (Exception e) {
                Log.w("FeatureMethod", "getBoolean:" + e.getMessage());
                return z;
            }
        }

        public int getInt(String str) {
            return getInt(str, 0);
        }

        public int getInt(String str, int i) {
            try {
                Method method = this.getIntDef;
                Integer num = null;
                if (method != null) {
                    num = Integer.valueOf(((Integer) method.invoke(null, str, Integer.valueOf(i))).intValue());
                }
                return num.intValue();
            } catch (Error unused) {
                return i;
            } catch (Exception e) {
                Log.w("FeatureMethod", "getInt:" + e.getMessage());
                return i;
            }
        }

        public long getLong(String str) {
            return getLong(str, 0L);
        }

        public long getLong(String str, long j) {
            try {
                Method method = this.getLongDef;
                Long l = null;
                if (method != null) {
                    l = Long.valueOf(((Long) method.invoke(null, str, Long.valueOf(j))).longValue());
                }
                return l.longValue();
            } catch (Error unused) {
                return j;
            } catch (Exception e) {
                Log.w("FeatureMethod", "getLong:" + e.getMessage());
                return j;
            }
        }
    }

    static {
        FeatureMethod featureMethod = new FeatureMethod();
        FEATURE_METHOD = featureMethod;
        ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY = featureMethod.getBoolean(FeatureUtil.ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY, false);
        ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE = featureMethod.getBoolean("ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE", false);
        ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD = featureMethod.getBoolean(FeatureUtil.X_GRAVITY_GAMEPAD, false);
        ZTE_FEATURE_MAGIC_GAME_ASSIST = featureMethod.getBoolean("ZTE_FEATURE_MAGIC_GAME_ASSIST", false);
        ZTE_FEATURE_SCREEN_KEY_MAP = featureMethod.getBoolean("ZTE_FEATURE_SCREEN_KEY_MAP", false);
        ZTE_FEATURE_KEY_MOUSE_MAP = featureMethod.getBoolean(FeatureUtil.ZTE_FEATURE_KEY_MOUSE_MAP, false);
    }

    public static String get(String str) {
        try {
            return FEATURE_METHOD.get(str);
        } catch (Error | Exception unused) {
            return "";
        }
    }

    public static String get(String str, String str2) {
        try {
            return FEATURE_METHOD.get(str, str2);
        } catch (Error | Exception unused) {
            return str2;
        }
    }

    public static boolean getBoolean(String str) {
        return FEATURE_METHOD.getBoolean(str);
    }

    public static boolean getBoolean(String str, boolean z) {
        return FEATURE_METHOD.getBoolean(str, z);
    }

    public static int getInt(String str) {
        return FEATURE_METHOD.getInt(str);
    }

    public static int getInt(String str, int i) {
        return FEATURE_METHOD.getInt(str, i);
    }

    public static long getLong(String str) {
        return FEATURE_METHOD.getLong(str);
    }

    public static long getLong(String str, long j) {
        return FEATURE_METHOD.getLong(str, j);
    }
}

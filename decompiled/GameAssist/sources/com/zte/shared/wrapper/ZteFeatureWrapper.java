package com.zte.shared.wrapper;

import android.util.Log;
import com.zte.gameassist.config.ZteFeature;
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
            } catch (Error e2) {
                Log.w("FeatureMethod", "forName e:" + e2.getMessage());
            } catch (Exception e3) {
                Log.w("FeatureMethod", "forName e:" + e3.getMessage());
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

        private Method getMethod(String str, Class<?>... clsArr) {
            Class<?> cls = this.mFeature;
            if (cls == null) {
                return null;
            }
            try {
                return cls.getMethod(str, clsArr);
            } catch (Error e2) {
                Log.w("FeatureMethod", "getMethod e:" + e2.getMessage());
                return null;
            } catch (Exception e3) {
                Log.w("FeatureMethod", "getMethod e:" + e3.getMessage());
                return null;
            }
        }

        public String get(String str) {
            return get(str, null);
        }

        public boolean getBoolean(String str) {
            return getBoolean(str, false);
        }

        public int getInt(String str) {
            return getInt(str, 0);
        }

        public long getLong(String str) {
            return getLong(str, 0L);
        }

        public String get(String str, String str2) {
            try {
                Method method = this.getDef;
                if (method == null) {
                    return null;
                }
                return (String) method.invoke(null, str, str2);
            } catch (Exception e2) {
                Log.w("FeatureMethod", "get:" + e2.getMessage());
                return str2;
            }
        }

        public boolean getBoolean(String str, boolean z) {
            try {
                Method method = this.getBooleanDef;
                Boolean bool = null;
                if (method != null) {
                    bool = (Boolean) method.invoke(null, str, Boolean.valueOf(z));
                    bool.booleanValue();
                }
                return bool.booleanValue();
            } catch (Error unused) {
                return z;
            } catch (Exception e2) {
                Log.w("FeatureMethod", "getBoolean:" + e2.getMessage());
                return z;
            }
        }

        public int getInt(String str, int i2) {
            try {
                Method method = this.getIntDef;
                Integer num = null;
                if (method != null) {
                    num = (Integer) method.invoke(null, str, Integer.valueOf(i2));
                    num.intValue();
                }
                return num.intValue();
            } catch (Error unused) {
                return i2;
            } catch (Exception e2) {
                Log.w("FeatureMethod", "getInt:" + e2.getMessage());
                return i2;
            }
        }

        public long getLong(String str, long j2) {
            try {
                Method method = this.getLongDef;
                Long l2 = null;
                if (method != null) {
                    l2 = (Long) method.invoke(null, str, Long.valueOf(j2));
                    l2.longValue();
                }
                return l2.longValue();
            } catch (Error unused) {
                return j2;
            } catch (Exception e2) {
                Log.w("FeatureMethod", "getLong:" + e2.getMessage());
                return j2;
            }
        }

        private Method getMethod() {
            return getMethod("get", String.class);
        }
    }

    static {
        FeatureMethod featureMethod = new FeatureMethod();
        FEATURE_METHOD = featureMethod;
        ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY = featureMethod.getBoolean(ZteFeature.ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY, false);
        ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE = featureMethod.getBoolean("ZTE_FEATURE_REDMAGIC_SPORTS_HANDLE", false);
        ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD = featureMethod.getBoolean(ZteFeature.ZTE_FEATURE_REDMAGIC_X_GRAVITY_GAMEPAD, false);
        ZTE_FEATURE_MAGIC_GAME_ASSIST = featureMethod.getBoolean(ZteFeature.ZTE_FEATURE_MAGIC_GAME_ASSIST, false);
        ZTE_FEATURE_SCREEN_KEY_MAP = featureMethod.getBoolean("ZTE_FEATURE_SCREEN_KEY_MAP", false);
        ZTE_FEATURE_KEY_MOUSE_MAP = featureMethod.getBoolean(ZteFeature.ZTE_FEATURE_KEY_MOUSE_MAP, false);
    }

    public static String get(String str) {
        try {
            return FEATURE_METHOD.get(str);
        } catch (Error | Exception unused) {
            return "";
        }
    }

    public static boolean getBoolean(String str) {
        return FEATURE_METHOD.getBoolean(str);
    }

    public static int getInt(String str) {
        return FEATURE_METHOD.getInt(str);
    }

    public static long getLong(String str) {
        return FEATURE_METHOD.getLong(str);
    }

    public static String get(String str, String str2) {
        try {
            return FEATURE_METHOD.get(str, str2);
        } catch (Error | Exception unused) {
            return str2;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        return FEATURE_METHOD.getBoolean(str, z);
    }

    public static int getInt(String str, int i2) {
        return FEATURE_METHOD.getInt(str, i2);
    }

    public static long getLong(String str, long j2) {
        return FEATURE_METHOD.getLong(str, j2);
    }
}

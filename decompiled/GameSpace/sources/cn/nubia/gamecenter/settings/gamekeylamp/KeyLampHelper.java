package cn.nubia.gamecenter.settings.gamekeylamp;

import android.content.Context;
import android.graphics.Color;
import android.provider.Settings;
import android.util.Log;
import androidx.core.view.ViewCompat;
import cn.nubia.common.CommonApplication;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.WorkThread;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class KeyLampHelper {
    private static final String COLORFULLIGHT_MANAGER = "com.zte.hardware.ColorfulLightManager";
    public static final HashMap<String, Integer> EFFECTS;
    public static final String TAG = "Lamp";
    private static Class<?> mColorfulLight;
    private static final Random sRandom;
    public ArrayList<String> mColorArray;
    public ArrayList<String> mColorCodeArray;
    public HashMap<String, ArrayList<String>> mColorCodeMap;
    public HashMap<String, ArrayList<String>> mColorMap;
    public HashMap<String, String> mColors;
    public ArrayList<Effect> mEffectArray;
    private final List<Runnable> mOnColorListChangedListeners;
    public String mSelectedColor;
    public String mSelectedColorCode;
    private int mSelectedColorPosition;
    public Effect mSelectedEffect;

    private static class KeyLampHelperHolder {
        public static final KeyLampHelper INSTANCE = new KeyLampHelper();

        private KeyLampHelperHolder() {
        }
    }

    /* renamed from: $r8$lambda$sUZQVfQ4XNh1oZg9vIcD2TcdV-o, reason: not valid java name */
    public static /* synthetic */ HashMap m211$r8$lambda$sUZQVfQ4XNh1oZg9vIcD2TcdVo() {
        return new HashMap();
    }

    static {
        HashMap<String, Integer> hashMap = new HashMap<>();
        EFFECTS = hashMap;
        hashMap.put(HighLightsUtils.AUTO_FIRST, Integer.valueOf(R.string.lamp_mode_light_with_music));
        hashMap.put(HighLightsUtils.AUTO_SECOND, Integer.valueOf(R.string.lamp_mode_all_bright));
        hashMap.put(HighLightsUtils.AUTO_THIRD, Integer.valueOf(R.string.lamp_mode_breath));
        hashMap.put(HighLightsUtils.AUTO_FOURTH, Integer.valueOf(R.string.lamp_mode_flashing));
        hashMap.put("005", Integer.valueOf(R.string.lamp_mode_scintillation));
        hashMap.put("006", Integer.valueOf(R.string.lamp_mode_flow));
        hashMap.put("007", Integer.valueOf(R.string.color_mode_Ripple));
        hashMap.put("008", Integer.valueOf(R.string.color_mode_Echo));
        hashMap.put("009", Integer.valueOf(R.string.color_mode_Hopping));
        hashMap.put("00a", Integer.valueOf(R.string.color_mode_Flashing));
        hashMap.put("00b", Integer.valueOf(R.string.color_mode_fl_to_fl));
        hashMap.put("00c", Integer.valueOf(R.string.color_mode_mech));
        hashMap.put("072", Integer.valueOf(R.string.type_mode_color_effect2));
        hashMap.put("201", Integer.valueOf(R.string.type_mode_config));
        sRandom = new Random();
    }

    private KeyLampHelper() {
        this.mColors = new HashMap<>();
        this.mColorMap = new HashMap<>();
        this.mColorCodeMap = new HashMap<>();
        this.mEffectArray = new ArrayList<>();
        this.mColorArray = new ArrayList<>();
        this.mColorCodeArray = new ArrayList<>();
        this.mSelectedColorPosition = 0;
        this.mOnColorListChangedListeners = new ArrayList();
        readSettings();
    }

    private static int[] getCurrentColor(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new int[]{ViewCompat.MEASURED_STATE_MASK, ViewCompat.MEASURED_STATE_MASK};
        }
        try {
            String[] split = str.split("\\s*,\\s*");
            if (split.length == 1) {
                int parseColor = Color.parseColor(split[0].trim());
                return new int[]{parseColor, parseColor};
            }
            int[] iArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                iArr[i] = Color.parseColor(split[i].trim());
            }
            return iArr;
        } catch (Exception e) {
            Log.e(TAG, "Error parsing color value: " + str, e);
            return new int[2];
        }
    }

    public static KeyLampHelper getInstance() {
        return KeyLampHelperHolder.INSTANCE;
    }

    static /* synthetic */ boolean lambda$parseColor$1(String[] strArr) {
        return strArr.length == 2;
    }

    private void notifyColorListChanged() {
        Iterator<Runnable> it = this.mOnColorListChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }

    private void parseColor() {
        String string = Settings.Global.getString(getContext().getContentResolver(), "lighting_color_game");
        if (string == null) {
            Log.i(TAG, "parseColor() null !");
        } else {
            this.mColors = (HashMap) Stream.of((Object[]) string.split(";")).map(new Function() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.KeyLampHelper$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String[] split;
                    split = ((String) obj).split(":");
                    return split;
                }
            }).filter(new Predicate() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.KeyLampHelper$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return KeyLampHelper.lambda$parseColor$1((String[]) obj);
                }
            }).collect(new Supplier() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.KeyLampHelper$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return KeyLampHelper.m211$r8$lambda$sUZQVfQ4XNh1oZg9vIcD2TcdVo();
                }
            }, new BiConsumer() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.KeyLampHelper$$ExternalSyntheticLambda3
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((HashMap) obj).put(r2[0], ((String[]) obj2)[1]);
                }
            }, new BiConsumer() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.KeyLampHelper$$ExternalSyntheticLambda4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((HashMap) obj).putAll((HashMap) obj2);
                }
            });
        }
    }

    private void parseLamp() {
        String string = Settings.Global.getString(getContext().getContentResolver(), "lighting_game_ui_json");
        if (string == null) {
            Log.i(TAG, "parseLamp() null !");
            return;
        }
        this.mEffectArray.clear();
        this.mColorMap.clear();
        this.mColorCodeMap.clear();
        try {
            JSONObject jSONObject = new JSONObject(string);
            JSONArray jSONArray = jSONObject.getJSONArray("color_palettes");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String string2 = jSONObject2.getString("id");
                JSONArray jSONArray2 = jSONObject2.getJSONArray("colors");
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<String> arrayList2 = new ArrayList<>();
                Log.i(TAG, "parseLamp() colorsArray : " + jSONArray2);
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    Log.i(TAG, "parseLamp() colorsArray : " + jSONArray2);
                    String string3 = jSONArray2.getString(i2);
                    arrayList2.add(string3);
                    if (isRandomCode(string3)) {
                        arrayList.add("");
                    } else {
                        arrayList.add(this.mColors.get(string3));
                    }
                }
                this.mColorMap.put(string2, arrayList);
                this.mColorCodeMap.put(string2, arrayList2);
            }
            JSONArray jSONArray3 = jSONObject.getJSONArray("type_color_combinations");
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                JSONObject jSONObject3 = jSONArray3.getJSONObject(i3);
                String string4 = jSONObject3.getString("type_id");
                String string5 = jSONObject3.getString("color_palette_id");
                HashMap<String, Integer> hashMap = EFFECTS;
                String string6 = hashMap.containsKey(string4) ? getContext().getResources().getString(hashMap.get(string4).intValue()) : null;
                if (string4.equals(HighLightsUtils.AUTO_FIRST) && CommonUtil.isP658F01()) {
                    string6 = getContext().getResources().getString(R.string.type_mode_color_effect1);
                }
                if (!string4.equals("201")) {
                    this.mEffectArray.add(new Effect(string4, string5, string6));
                } else if (SettingUtil.getString(getContext(), SettingUtil.SCENE_TYPE_CONFIG_GAME) != null) {
                    this.mEffectArray.add(new Effect(string4, string5, string6));
                }
            }
        } catch (JSONException e) {
            Log.i(TAG, "parseLamp() e : " + e.getMessage());
            e.printStackTrace();
        }
        Log.i(TAG, "parseLamp() mColorMap : " + this.mColorMap + ", mEffectArray : " + this.mEffectArray);
    }

    private void updateSelectedColor() {
        int indexOf;
        String str = this.mSelectedColorCode;
        if (str == null) {
            Log.i(TAG, "updateSelectedColor() but mSelectedColorCode is null !");
            return;
        }
        if (!isRandomCode(str)) {
            if (this.mColors.isEmpty()) {
                Log.i(TAG, "updateSelectedColor() but mColors is empty !");
                return;
            } else {
                if (!this.mColors.containsKey(this.mSelectedColorCode)) {
                    Log.i(TAG, "updateSelectedColor() but mColors not contains mSelectedColorCode !");
                    return;
                }
                String str2 = this.mColors.get(this.mSelectedColorCode);
                this.mSelectedColor = str2;
                this.mSelectedColorPosition = this.mColorArray.indexOf(str2);
                return;
            }
        }
        ArrayList<String> arrayList = this.mColorCodeArray;
        if (arrayList == null || arrayList.isEmpty() || (indexOf = this.mColorCodeArray.indexOf(this.mSelectedColorCode)) < 0) {
            return;
        }
        this.mSelectedColorPosition = indexOf;
        ArrayList<String> arrayList2 = this.mColorArray;
        if (arrayList2 == null || indexOf >= arrayList2.size()) {
            this.mSelectedColor = null;
        } else {
            this.mSelectedColor = this.mColorArray.get(indexOf);
        }
    }

    public void addOnColorListChangedListener(Runnable runnable) {
        this.mOnColorListChangedListeners.add(runnable);
    }

    public Effect findEffectByTypeId(String str) {
        if (str != null && !this.mEffectArray.isEmpty()) {
            Log.i(TAG, "findEffectByTypeId() typeId : " + str);
            Iterator<Effect> it = this.mEffectArray.iterator();
            while (it.hasNext()) {
                Effect next = it.next();
                if (str.equals(next.typeId)) {
                    return next;
                }
            }
        }
        return null;
    }

    public String getColorCodeByPosition(int i) {
        ArrayList<String> arrayList = this.mColorCodeArray;
        if (arrayList == null || arrayList.isEmpty() || i >= this.mColorCodeArray.size()) {
            return null;
        }
        return this.mColorCodeArray.get(i);
    }

    public int[] getColorsByPosition(int i) {
        if (this.mColorArray.isEmpty() || i >= this.mColorArray.size()) {
            return null;
        }
        String str = this.mColorArray.get(i);
        Log.i(TAG, "getColorsByPosition() value : " + str);
        return getCurrentColor(str);
    }

    public Context getContext() {
        return CommonApplication.getInstance().getAppContext();
    }

    public ArrayList<String> getCurrentColors() {
        return this.mColorArray;
    }

    public Effect getEffectByPosition(int i) {
        if (i >= this.mEffectArray.size()) {
            return null;
        }
        return this.mEffectArray.get(i);
    }

    public ArrayList<Effect> getEffects() {
        return this.mEffectArray;
    }

    public int[] getRandomColorsFromCurrentPalette() {
        ArrayList<String> arrayList;
        String str;
        try {
            arrayList = this.mColorArray;
        } catch (Throwable th) {
            LogUtil.w(TAG, th);
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = this.mColorArray.size();
            for (int i = 0; i < Math.min(10, size); i++) {
                int nextInt = sRandom.nextInt(size);
                if (!isRandomCode(getColorCodeByPosition(nextInt)) && (str = this.mColorArray.get(nextInt)) != null && !str.trim().isEmpty()) {
                    return getCurrentColor(str);
                }
            }
            return new int[2];
        }
        return new int[2];
    }

    public String getSelectedColor() {
        return this.mSelectedColor;
    }

    public String getSelectedColorCode() {
        return this.mSelectedColorCode;
    }

    public int getSelectedColorPosition() {
        return this.mSelectedColorPosition;
    }

    public int[] getSelectedColors() {
        try {
            String str = this.mColorArray.get(this.mSelectedColorPosition);
            Log.i(TAG, "getColorsByPosition() value : " + str);
            return getCurrentColor(str);
        } catch (Exception e) {
            Log.e(TAG, "Error parsing color value: " + this.mSelectedColor, e);
            return new int[2];
        }
    }

    public Effect getSelectedEffect() {
        return this.mSelectedEffect;
    }

    public int getSelectedEffectPosition() {
        return this.mEffectArray.indexOf(this.mSelectedEffect);
    }

    public boolean isCurrentEffectMusicWithLight() {
        Effect effect = this.mSelectedEffect;
        if (effect == null) {
            return false;
        }
        return effect.typeId.equals(HighLightsUtils.AUTO_FIRST);
    }

    public boolean isRandomCode(String str) {
        return "201".equalsIgnoreCase(str) || "301".equalsIgnoreCase(str) || "401".equalsIgnoreCase(str);
    }

    public boolean isSelectedEffect(int i) {
        Effect effectByPosition = getEffectByPosition(i);
        return effectByPosition != null && effectByPosition.equals(this.mSelectedEffect);
    }

    public void onSelectedColorChange(int i) {
        String colorCodeByPosition = getColorCodeByPosition(i);
        if (colorCodeByPosition == null || colorCodeByPosition.trim().isEmpty()) {
            Iterator<String> it = this.mColors.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (Objects.equals(this.mColors.get(next), this.mColorArray.get(i))) {
                    this.mSelectedColorCode = next;
                    updateSelectedColor();
                    break;
                }
            }
            Log.i(TAG, "onSelectedColorChange(" + i + ") mSelectedColor : " + this.mSelectedColor + ", code : " + this.mSelectedColorCode);
            WorkThread.runOnWorkThread(new KeyLampHelper$$ExternalSyntheticLambda5(this));
            return;
        }
        this.mSelectedColorPosition = i;
        this.mSelectedColorCode = colorCodeByPosition;
        ArrayList<String> arrayList = this.mColorArray;
        if (arrayList == null || i < 0 || i >= arrayList.size()) {
            this.mSelectedColor = null;
        } else {
            this.mSelectedColor = this.mColorArray.get(i);
        }
        Log.i(TAG, "onSelectedColorChange(" + i + ") via code, mSelectedColor : " + this.mSelectedColor + ", code : " + this.mSelectedColorCode);
        WorkThread.runOnWorkThread(new KeyLampHelper$$ExternalSyntheticLambda5(this));
    }

    public void onSelectedEffectChange(int i) {
        onSelectedEffectChange(this.mEffectArray.get(i));
    }

    public void onSelectedEffectChange(Effect effect) {
        if (effect == null) {
            return;
        }
        Log.i(TAG, "onSelectedEffectChange() update select from : " + this.mSelectedEffect + " to  " + effect);
        this.mSelectedEffect = effect;
        ArrayList<String> arrayList = this.mColorMap.containsKey(effect.paletteId) ? this.mColorMap.get(this.mSelectedEffect.paletteId) : new ArrayList<>();
        this.mColorArray = arrayList;
        if (this.mSelectedColorPosition >= arrayList.size()) {
            this.mSelectedColorPosition = this.mColorArray.isEmpty() ? -1 : 0;
            if (!this.mColorArray.isEmpty()) {
                this.mSelectedColor = this.mColorArray.get(this.mSelectedColorPosition);
                Iterator<String> it = this.mColors.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (Objects.equals(this.mColors.get(next), this.mSelectedColor)) {
                        this.mSelectedColorCode = next;
                        break;
                    }
                }
            }
        }
        notifyColorListChanged();
        WorkThread.runOnWorkThread(new KeyLampHelper$$ExternalSyntheticLambda5(this));
    }

    public void parseSelected() {
        try {
            Context context = getContext();
            if (context != null && context.getContentResolver() != null) {
                String string = Settings.Global.getString(context.getContentResolver(), "lighting_config_game");
                Log.i(TAG, "parseSelected() selected : " + string);
                if (string == null) {
                    return;
                }
                Matcher matcher = Pattern.compile("^.*(\\w{3})(\\w{3})$").matcher(string);
                if (matcher.find()) {
                    this.mSelectedEffect = findEffectByTypeId(matcher.group(1));
                    this.mSelectedColorCode = matcher.group(2);
                    Effect effect = this.mSelectedEffect;
                    if (effect != null && this.mColorMap.containsKey(effect.paletteId)) {
                        this.mColorArray = this.mColorMap.get(this.mSelectedEffect.paletteId);
                        this.mColorCodeArray = this.mColorCodeMap.containsKey(this.mSelectedEffect.paletteId) ? this.mColorCodeMap.get(this.mSelectedEffect.paletteId) : new ArrayList<>();
                    } else if (this.mSelectedEffect != null) {
                        this.mColorArray = new ArrayList<>();
                        this.mColorCodeArray = new ArrayList<>();
                    }
                    notifyColorListChanged();
                    updateSelectedColor();
                }
                Log.i(TAG, "parseSelected() selected : " + string + ", mEffect : " + this.mSelectedEffect + ", mSelectedColorCode : " + this.mSelectedColorCode);
            }
        } catch (Exception e) {
            Log.i(TAG, "parseSelected() e : " + e.getMessage());
        }
    }

    public void previewColorfulLight(boolean z, String str) {
        try {
            if (mColorfulLight == null) {
                mColorfulLight = Class.forName(COLORFULLIGHT_MANAGER);
            }
            Method declaredMethod = mColorfulLight.getDeclaredMethod("previewColorfulLight", Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, Boolean.valueOf(z), str);
            LogUtil.i(TAG, "previewColorfulLight isPreview: " + z + ", config: " + str);
        } catch (Exception e) {
            LogUtil.i(TAG, "previewColorfulLight error " + e.getMessage());
        }
    }

    public void readSettings() {
        try {
            parseColor();
            parseLamp();
            parseSelected();
        } catch (Exception e) {
            Log.i(TAG, "readSettings() e : " + e.getMessage());
        }
    }

    public void recordSelectedChange() {
        String string = Settings.Global.getString(getContext().getContentResolver(), "lighting_config_game");
        String replaceAll = string.replaceAll("(.{6})$", this.mSelectedEffect.typeId + this.mSelectedColorCode);
        Log.i(TAG, "recordSelectedChange() update lighting_config_game : " + string + " to  " + replaceAll);
        Settings.Global.putString(getContext().getContentResolver(), "lighting_config_game", replaceAll);
        previewColorfulLight(true, replaceAll);
    }

    public void removeOnColorListChangedListener(Runnable runnable) {
        this.mOnColorListChangedListeners.remove(runnable);
    }

    public boolean usesLampColorDrawableAt(int i) {
        if (isRandomCode(getColorCodeByPosition(i))) {
            return true;
        }
        ArrayList<String> arrayList = this.mColorArray;
        if (arrayList == null || i < 0 || i >= arrayList.size()) {
            return false;
        }
        String str = this.mColorArray.get(i);
        return str == null || str.trim().isEmpty();
    }
}

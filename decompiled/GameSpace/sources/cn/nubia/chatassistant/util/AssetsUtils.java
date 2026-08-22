package cn.nubia.chatassistant.util;

import android.content.Context;
import cn.nubia.chatassistant.db.ChatAssistantBean;
import cn.nubia.chatassistant.db.DBManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AssetsUtils {
    public static final String ASSETS_DATA_FILE_ROOTDIR = "chat_assistant";
    public static int ASSETS_FILE_COUNT = 3;
    private static final String TAG = "AssetsUtils";
    public static final String[][] systemVoiceList = {new String[]{"你怎么肥事", "大家好呀", "奇怪的知识又增加了", "小脑袋瓜热昏了头", "我感觉周围有杀气", "我的手机好冷", "牛哇牛哇", "瑞思拜", "针不戳", "骚奥瑞"}, new String[]{"三十六计，走为上计", "你在菜鸟客栈上班吗", "停止思考，跟我上头", "您是及时雨送江吗", "我准备好了〜〜〜吗", "来我这里，阴一波", "河蟹走位都比你好", "王者峡谷里没有爱情", "看我carry全场", "脸都秀歪了"}, new String[]{"人机不要怕", "在下第一伏地魔", "在饱了吗上班吗", "太无聊了", "快递都内卷了", "我是顺风快递", "盒子可以送人", "空投都砸脸上了", "跟着大爷绝对吃鸡", "这操作我饱了"}, new String[]{"鼓掌"}};

    public static List<List> getContentItemTitle(Context context) {
        int size = getFistTitle(context).size();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < size) {
            List<Map<String, Object>> itemTitle = getItemTitle(i, getFistTitle(context).get(i), i < ASSETS_FILE_COUNT, context);
            if (itemTitle != null) {
                arrayList.add(itemTitle);
            }
            i++;
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public static List<String> getFistTitle(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            String[] list = context.getAssets().list("chat_assistant");
            if (list != null) {
                arrayList.addAll(Arrays.asList((String[]) list.clone()));
            }
            synchronized (AssetsUtils.class) {
                ASSETS_FILE_COUNT = arrayList.size();
            }
        } catch (IOException unused) {
            LogUtils.e(TAG, "open data root dir failed!");
        }
        Comparator<String> comparator = new Comparator<String>() { // from class: cn.nubia.chatassistant.util.AssetsUtils.1
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                int i;
                int i2 = 0;
                try {
                    i = Integer.valueOf(str.split("_", 2)[0]).intValue();
                } catch (NumberFormatException unused2) {
                    LogUtils.e(AssetsUtils.TAG, "s format failed!");
                    i = 0;
                }
                try {
                    i2 = Integer.valueOf(str2.split("_", 2)[0]).intValue();
                } catch (NumberFormatException unused3) {
                    LogUtils.e(AssetsUtils.TAG, "t format failed!");
                }
                return i - i2;
            }
        };
        Collections.sort(arrayList, comparator);
        File[] listFiles = new File(context.getFilesDir().getPath() + "/chat_assistant").listFiles();
        if (listFiles != null) {
            ArrayList arrayList2 = new ArrayList();
            for (File file : listFiles) {
                LogUtils.d(TAG, "subFile = " + file.getName());
                if (file.isDirectory()) {
                    arrayList2.add(file.getName());
                }
            }
            Collections.sort(arrayList2, comparator);
            arrayList.addAll(arrayList2);
        }
        return arrayList;
    }

    public static List<Map<String, Object>> getItemTitle(int i, String str, boolean z, Context context) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            try {
                str = "chat_assistant/" + str;
                String[] list = context.getAssets().list(str);
                for (int i2 = 0; i2 < list.length; i2++) {
                    HashMap hashMap = new HashMap();
                    String str2 = list[i2];
                    str2.length();
                    hashMap.put("title", systemVoiceList[i][i2]);
                    hashMap.put("path", str + "/" + str2);
                    arrayList.add(hashMap);
                }
            } catch (IOException unused) {
                LogUtils.e(TAG, "open " + str + " failed!");
            }
        } else {
            for (File file : new File(context.getFilesDir().getPath() + "/chat_assistant/" + str).listFiles()) {
                String name = file.getName();
                String path = file.getPath();
                HashMap hashMap2 = new HashMap();
                hashMap2.put("title", name.substring(0, name.length() - 4));
                hashMap2.put("path", path);
                arrayList.add(hashMap2);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public static void updateChatAssistantVoicePack(Context context) {
        List<String> fistTitle = getFistTitle(context);
        List<List> contentItemTitle = getContentItemTitle(context);
        if (fistTitle == null || contentItemTitle == null) {
            LogUtils.e(TAG, "data is null!");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < fistTitle.size()) {
            ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
            String[] split = fistTitle.get(i).split("_", 2);
            if (split[1].equals("redmagic")) {
                split[1] = "红魔姬";
            } else if (split[1].equals("king")) {
                split[1] = "王者风";
            } else if (split[1].equals("peace")) {
                split[1] = "和平风";
            }
            arrayList.add(split[1]);
            List<Map<String, Object>> itemTitle = getItemTitle(i, fistTitle.get(i), i < ASSETS_FILE_COUNT, context);
            for (int i2 = 0; i2 < itemTitle.size(); i2++) {
                chatAssistantBean.voicePackName = (String) arrayList.get(i);
                chatAssistantBean.voiceFileName = (String) itemTitle.get(i2).get("title");
                chatAssistantBean.voiceFilePath = (String) itemTitle.get(i2).get("path");
                DBManager.getInstance(context).updateVoicePack(chatAssistantBean);
            }
            i++;
        }
    }
}

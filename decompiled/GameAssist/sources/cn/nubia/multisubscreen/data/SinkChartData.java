package cn.nubia.multisubscreen.data;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SinkChartData {
    public static final int MAX_CHART_NUM = 300;
    private List<Float> mFpsList = new ArrayList();
    private List<Float> mNetList = new ArrayList();
    private List<Float> mCpsList = new ArrayList();
    private List<Float> mMpmList = new ArrayList();

    private void trim(List list, int i2) {
        if (list.size() >= i2) {
            list.remove(list.get(0));
        }
    }

    public void addCps(float f2) {
        trim(this.mCpsList, 300);
        this.mCpsList.add(Float.valueOf(f2));
    }

    public void addFps(float f2) {
        trim(this.mFpsList, 300);
        this.mFpsList.add(Float.valueOf(f2));
    }

    public void addMpm(float f2) {
        trim(this.mMpmList, 60);
        this.mMpmList.add(Float.valueOf(f2));
    }

    public void addNet(float f2) {
        trim(this.mNetList, 300);
        this.mNetList.add(Float.valueOf(f2));
    }

    public List<Float> getCpsList() {
        return this.mCpsList;
    }

    public List<Float> getFpsList() {
        return this.mFpsList;
    }

    public List<Float> getMpmList() {
        return this.mMpmList;
    }

    public List<Float> getNetList() {
        return this.mNetList;
    }
}

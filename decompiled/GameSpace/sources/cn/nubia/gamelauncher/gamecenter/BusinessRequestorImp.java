package cn.nubia.gamelauncher.gamecenter;

import android.content.Context;
import cn.nubia.gamelauncher.bean.AtmosphereBean;
import cn.nubia.gamelauncher.bean.ResponseBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.commoninterface.ICoverUrlCallback;
import cn.nubia.gamelauncher.commoninterface.IRequestListener;
import cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.HTTPUtils;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.WorkThread;
import cn.nubia.volley.AuthFailureError;
import cn.nubia.volley.RequestQueue;
import cn.nubia.volley.Response;
import cn.nubia.volley.VolleyError;
import cn.nubia.volley.toolbox.StringRequest;
import cn.nubia.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BusinessRequestorImp {
    String TAG = "RequestImp";

    private void httpPost(final String str, final Context context, final int i, final Map<String, String> map, final IRequestListener iRequestListener, final ICoverUrlCallback iCoverUrlCallback) {
        LogUtil.i(this.TAG, "--->httpPost() request :\u3000" + map);
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp.1

            /* renamed from: cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp$1$1, reason: invalid class name and collision with other inner class name */
            class C00151 implements Response.Listener<String> {
                C00151() {
                }

                /* renamed from: lambda$onResponse$0$cn-nubia-gamelauncher-gamecenter-BusinessRequestorImp$1$1, reason: not valid java name */
                /* synthetic */ void m243x1849c120(String str, ICoverUrlCallback iCoverUrlCallback) {
                    BusinessRequestorImp.this.onRequestSuccess(str, iCoverUrlCallback);
                }

                @Override // cn.nubia.volley.Response.Listener
                public void onResponse(final String str) {
                    LogUtil.i(BusinessRequestorImp.this.TAG, "--->onResponse() response :\u3000" + str);
                    if (i != 300) {
                        BusinessRequestorImp.this.onRequestSuccess(str, i, iRequestListener);
                        return;
                    }
                    BusinessRequestorImp businessRequestorImp = BusinessRequestorImp.this;
                    final ICoverUrlCallback iCoverUrlCallback = iCoverUrlCallback;
                    businessRequestorImp.runOnCalledThread(new Runnable() { // from class: cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp$1$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            BusinessRequestorImp.AnonymousClass1.C00151.this.m243x1849c120(str, iCoverUrlCallback);
                        }
                    });
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                RequestQueue newRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest stringRequest = new StringRequest(1, str, new C00151(), new Response.ErrorListener() { // from class: cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp.1.2
                    @Override // cn.nubia.volley.Response.ErrorListener
                    public void onErrorResponse(VolleyError volleyError) {
                        LogUtil.i(BusinessRequestorImp.this.TAG, "--->onErrorResponse() error :\u3000" + volleyError);
                        BusinessRequestorImp.this.onRequestFailed(volleyError, iRequestListener);
                    }
                }) { // from class: cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp.1.3
                    @Override // cn.nubia.volley.Request
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return map;
                    }
                };
                stringRequest.setShouldCache(false);
                newRequestQueue.add(stringRequest);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestFailed(VolleyError volleyError, final IRequestListener iRequestListener) {
        final String volleyError2 = volleyError.toString();
        runOnCalledThread(new Runnable() { // from class: cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp.2
            @Override // java.lang.Runnable
            public void run() {
                IRequestListener iRequestListener2 = iRequestListener;
                if (iRequestListener2 != null) {
                    iRequestListener2.responseError(volleyError2);
                    LogUtil.e(BusinessRequestorImp.this.TAG, "Http post error!! ErrorListener(" + volleyError2 + ")");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestSuccess(String str, int i, final IRequestListener iRequestListener) {
        try {
            final ResponseBean jSONObjectToBean = jSONObjectToBean(new JSONObject(str), i);
            runOnCalledThread(new Runnable() { // from class: cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp.3
                @Override // java.lang.Runnable
                public void run() {
                    IRequestListener iRequestListener2 = iRequestListener;
                    if (iRequestListener2 == null && jSONObjectToBean == null) {
                        LogUtil.e(BusinessRequestorImp.this.TAG, "onRequestSuccess() Http post error!! listener(" + iRequestListener + "):result(" + jSONObjectToBean + ")");
                    } else {
                        iRequestListener2.responseInfo(jSONObjectToBean);
                    }
                }
            });
        } catch (JSONException e) {
            LogUtil.i(this.TAG, "onRequestSuccess() e : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestSuccess(String str, ICoverUrlCallback iCoverUrlCallback) {
        if (iCoverUrlCallback == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("code") != 0) {
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            ArrayList<AtmosphereBean> arrayList = new ArrayList<>();
            if (jSONArray == null) {
                return;
            }
            LogUtil.i("Atmosphere", "onRequestSuccess() jSONArray.length : " + jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    LogUtil.i("Atmosphere", "onRequestSuccess() item : " + jSONObject2);
                    AtmosphereBean atmosphereBean = new AtmosphereBean(jSONObject2);
                    if (atmosphereBean.isValid()) {
                        arrayList.add(atmosphereBean);
                    }
                } catch (JSONException e) {
                    LogUtil.i("Atmosphere", "onRequestSuccess() e : " + e.getMessage());
                    e.printStackTrace();
                }
            }
            iCoverUrlCallback.responseInfo(arrayList);
        } catch (JSONException e2) {
            LogUtil.i("Atmosphere", "onRequestSuccess() e : " + e2.getMessage());
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runOnCalledThread(Runnable runnable) {
        WorkThread.runOnWorkThread(runnable);
    }

    public void getApplicationByPackageName(Context context, String str, IRequestListener iRequestListener) {
        if (!ConstantVariable.HAS_PERMISSION || CommonUtil.isInternalVersion()) {
            LogUtil.i(this.TAG, "has not the permission");
            if (iRequestListener != null) {
                iRequestListener.responseError("has not the net permission");
                return;
            }
            return;
        }
        LogUtil.i(this.TAG, "getApplicationByPackageName connect to net##########");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        getApplicationsByPackageNames(context, arrayList, iRequestListener);
    }

    public void getApplicationsByPackageNames(Context context, ArrayList<String> arrayList, IRequestListener iRequestListener) {
        if (ConstantVariable.HAS_PERMISSION && !CommonUtil.isInternalVersion()) {
            LogUtil.i(this.TAG, "getApplicationsByPackageNames connect to net##########");
            httpPost(HTTPUtils.getSoftListByPackageNames(), context, 200, new PackageParams(arrayList).getParams(), iRequestListener, null);
        } else {
            LogUtil.i(this.TAG, "has not the permission");
            if (iRequestListener != null) {
                iRequestListener.responseError("has not the permission");
            }
        }
    }

    public void getBannersByPackageNames(Context context, ArrayList<String> arrayList, ICoverUrlCallback iCoverUrlCallback) {
        if (ConstantVariable.HAS_PERMISSION && !CommonUtil.isInternalVersion()) {
            LogUtil.i("Atmosphere", "getBannersByPackageNames connect to net##########");
            httpPost(BannerRequest.getSoftListByPackageNames(), context, 300, BannerRequest.createParams(arrayList), null, iCoverUrlCallback);
        } else {
            LogUtil.i("Atmosphere", "has not the permission");
            if (iCoverUrlCallback != null) {
                iCoverUrlCallback.responseError("has not the permission");
            }
        }
    }

    public void getTopicSoftList(Context context, int i, IRequestListener iRequestListener) {
        if (ConstantVariable.HAS_PERMISSION && !CommonUtil.isInternalVersion()) {
            String topicSoftList = HTTPUtils.getTopicSoftList();
            LogUtil.i(this.TAG, "" + topicSoftList);
            httpPost(topicSoftList, context, 200, new PackageParams().getTopicSoftListParams(i), iRequestListener, null);
        } else {
            LogUtil.i(this.TAG, "has not the permission");
            if (iRequestListener != null) {
                iRequestListener.responseError("has not the permission");
            }
        }
    }

    public ResponseBean jSONObjectToBean(JSONObject jSONObject, int i) {
        try {
            if (jSONObject.getInt("StateCode") == 1) {
                return new ResponseBean(jSONObject, i);
            }
            return null;
        } catch (Exception e) {
            LogUtil.e(this.TAG, "jSONObjectToBean Error!! " + e.fillInStackTrace());
            return null;
        }
    }
}

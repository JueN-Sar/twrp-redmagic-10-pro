package cn.nubia.gamelauncher.gamecenter;

import android.content.Context;
import cn.nubia.gamelauncher.bean.CheckStateResponse;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.commoninterface.ICheckStateRequestListener;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.HTTPUtils;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.WorkThread;
import cn.nubia.volley.RequestQueue;
import cn.nubia.volley.Response;
import cn.nubia.volley.VolleyError;
import cn.nubia.volley.toolbox.StringRequest;
import cn.nubia.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CheckStateRequestorImp {
    String TAG = "CheckState";

    private void httpPost(final String str, final Context context, final Map<String, String> map, final ICheckStateRequestListener iCheckStateRequestListener) {
        LogUtil.i(this.TAG, "--->httpPost() request :\u3000" + map);
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.gamecenter.CheckStateRequestorImp$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CheckStateRequestorImp.this.m246xdc37fbb9(context, str, iCheckStateRequestListener, map);
            }
        });
    }

    private void onRequestFailed(VolleyError volleyError, final ICheckStateRequestListener iCheckStateRequestListener) {
        final String volleyError2 = volleyError.toString();
        runOnCalledThread(new Runnable() { // from class: cn.nubia.gamelauncher.gamecenter.CheckStateRequestorImp$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CheckStateRequestorImp.this.m247xdbade8a5(iCheckStateRequestListener, volleyError2);
            }
        });
    }

    private void onRequestSuccess(String str, final ICheckStateRequestListener iCheckStateRequestListener) {
        try {
            final CheckStateResponse jSONObjectToBean = jSONObjectToBean(new JSONObject(str));
            runOnCalledThread(new Runnable() { // from class: cn.nubia.gamelauncher.gamecenter.CheckStateRequestorImp$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CheckStateRequestorImp.this.m248xd5ed81e6(iCheckStateRequestListener, jSONObjectToBean);
                }
            });
        } catch (JSONException e) {
            LogUtil.i(this.TAG, "onRequestSuccess() e : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void runOnCalledThread(Runnable runnable) {
        WorkThread.runOnWorkThread(runnable);
    }

    public void checkTopicSoft(Context context, ArrayList<String> arrayList, ICheckStateRequestListener iCheckStateRequestListener) {
        if (ConstantVariable.HAS_PERMISSION && !CommonUtil.isInternalVersion()) {
            String checkTopicSoft = HTTPUtils.checkTopicSoft();
            LogUtil.i(this.TAG, "" + checkTopicSoft);
            httpPost(checkTopicSoft, context, new PackageParams().checkTopicSoft(arrayList), iCheckStateRequestListener);
        } else {
            LogUtil.i(this.TAG, "has not the permission");
            if (iCheckStateRequestListener != null) {
                iCheckStateRequestListener.responseError("has not the permission");
            }
        }
    }

    public CheckStateResponse jSONObjectToBean(JSONObject jSONObject) {
        try {
            if (jSONObject.getInt("StateCode") == 1) {
                return new CheckStateResponse(jSONObject);
            }
            return null;
        } catch (Exception e) {
            LogUtil.e(this.TAG, "jSONObjectToBean Error!! " + e.fillInStackTrace());
            return null;
        }
    }

    /* renamed from: lambda$httpPost$0$cn-nubia-gamelauncher-gamecenter-CheckStateRequestorImp, reason: not valid java name */
    /* synthetic */ void m244x910fe9b7(ICheckStateRequestListener iCheckStateRequestListener, String str) {
        LogUtil.i(this.TAG, "--->onResponse() response :\u3000" + str);
        onRequestSuccess(str, iCheckStateRequestListener);
    }

    /* renamed from: lambda$httpPost$1$cn-nubia-gamelauncher-gamecenter-CheckStateRequestorImp, reason: not valid java name */
    /* synthetic */ void m245xb6a3f2b8(ICheckStateRequestListener iCheckStateRequestListener, VolleyError volleyError) {
        LogUtil.i(this.TAG, "--->onErrorResponse() error :\u3000" + volleyError);
        onRequestFailed(volleyError, iCheckStateRequestListener);
    }

    /* renamed from: lambda$httpPost$2$cn-nubia-gamelauncher-gamecenter-CheckStateRequestorImp, reason: not valid java name */
    /* synthetic */ void m246xdc37fbb9(Context context, String str, final ICheckStateRequestListener iCheckStateRequestListener, final Map map) {
        RequestQueue newRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        StringRequest stringRequest = new StringRequest(1, str, new Response.Listener() { // from class: cn.nubia.gamelauncher.gamecenter.CheckStateRequestorImp$$ExternalSyntheticLambda3
            @Override // cn.nubia.volley.Response.Listener
            public final void onResponse(Object obj) {
                CheckStateRequestorImp.this.m244x910fe9b7(iCheckStateRequestListener, (String) obj);
            }
        }, new Response.ErrorListener() { // from class: cn.nubia.gamelauncher.gamecenter.CheckStateRequestorImp$$ExternalSyntheticLambda4
            @Override // cn.nubia.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                CheckStateRequestorImp.this.m245xb6a3f2b8(iCheckStateRequestListener, volleyError);
            }
        }) { // from class: cn.nubia.gamelauncher.gamecenter.CheckStateRequestorImp.1
            @Override // cn.nubia.volley.Request
            protected Map<String, String> getParams() {
                return map;
            }
        };
        stringRequest.setShouldCache(false);
        newRequestQueue.add(stringRequest);
    }

    /* renamed from: lambda$onRequestFailed$3$cn-nubia-gamelauncher-gamecenter-CheckStateRequestorImp, reason: not valid java name */
    /* synthetic */ void m247xdbade8a5(ICheckStateRequestListener iCheckStateRequestListener, String str) {
        if (iCheckStateRequestListener != null) {
            iCheckStateRequestListener.responseError(str);
            LogUtil.e(this.TAG, "Http post error!! ErrorListener(" + str + ")");
        }
    }

    /* renamed from: lambda$onRequestSuccess$4$cn-nubia-gamelauncher-gamecenter-CheckStateRequestorImp, reason: not valid java name */
    /* synthetic */ void m248xd5ed81e6(ICheckStateRequestListener iCheckStateRequestListener, CheckStateResponse checkStateResponse) {
        if (iCheckStateRequestListener == null && checkStateResponse == null) {
            LogUtil.e(this.TAG, "onRequestSuccess() Http post error!! listener(" + iCheckStateRequestListener + "):result(" + checkStateResponse + ")");
        } else {
            iCheckStateRequestListener.responseInfo(checkStateResponse);
        }
    }
}

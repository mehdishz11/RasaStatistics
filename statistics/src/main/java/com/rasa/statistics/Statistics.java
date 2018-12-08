package com.rasa.statistics;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import com.rasa.statistics.model.ParamsStatistics;
import com.rasa.statistics.model.ResponseStatistics;
import com.rasa.statistics.utils.SharePrefrences;
import com.rasa.statistics.utils.WebUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class Statistics {
    Context context;
    int marketId = -1;

    private static final String STATUS_INSTALL = "Install";
    private static final String STATUS_ACTIVE = "Active";
    private static final String STATUS_DEACTIVE = "DeActive";

    public Statistics(Context context, int marketId) {
        this.context = context;
        this.marketId = marketId;
    }


    public void install() {
        if (!SharePrefrences.getValue(getContext(), SharePrefrences.STATIST_KEY_INSTALL)) {
            doSendStatistics(makeParams("",STATUS_INSTALL));
        }
    }

    public void active(String phoneNumber) {
        if (!SharePrefrences.getValue(getContext(), SharePrefrences.STATIST_KEY_ACTIVE)) {
            doSendStatistics(makeParams(phoneNumber,STATUS_ACTIVE));
        }
    }

    public void active(String phoneNumber,String referenceCode,String irancellToken){
        if (!SharePrefrences.getValue(getContext(), SharePrefrences.STATIST_KEY_ACTIVE)) {
            doSendStatistics(makeParams(phoneNumber,STATUS_ACTIVE,referenceCode,irancellToken));
        }
    }

    public void deactive(String phoneNumber) {
        if (SharePrefrences.getValue(getContext(), SharePrefrences.STATIST_KEY_ACTIVE) ||
                SharePrefrences.getValue(getContext(), SharePrefrences.STATIST_KEY_INSTALL)
                ) {
            doSendStatistics(makeParams(phoneNumber,STATUS_DEACTIVE));
        }
    }

    private void doSendStatistics(
          final ParamsStatistics params
    ) {
        ApiStatistics service = WebUtils.getRetrofit().create(ApiStatistics.class);
        Call<ResponseStatistics> call = service.report(params);
        call.enqueue(new Callback<ResponseStatistics>() {
            @Override
            public void onResponse(Call<ResponseStatistics> call, Response<ResponseStatistics> response) {
                if(response.code()==200){
                    if(response.body().getOk()){
                        saveStatus(params.getStatus());
                    }else if(params.getStatus().equals(STATUS_DEACTIVE)){
                        saveStatus(params.getStatus());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseStatistics> call, Throwable t) {
                if(params.getStatus().equals(STATUS_DEACTIVE)){
                    saveStatus(params.getStatus());
                }
                t.printStackTrace();
            }
        });
    }

    private void saveStatus(String status) {
        if(status.equals(STATUS_INSTALL)){
            SharePrefrences.setValue(getContext(), SharePrefrences.STATIST_KEY_INSTALL, true);
        }else if(status.equals(STATUS_ACTIVE)){
            SharePrefrences.setValue(getContext(), SharePrefrences.STATIST_KEY_ACTIVE, true);
        }else if(status.equals(STATUS_DEACTIVE)){
            SharePrefrences.setValue(getContext(), SharePrefrences.STATIST_KEY_INSTALL, false);
            SharePrefrences.setValue(getContext(), SharePrefrences.STATIST_KEY_ACTIVE, false);
        }
    }


    private ParamsStatistics makeParams(String phoneNumber, String status) {


        return makeParams(phoneNumber,status,null,null);
    }

    private ParamsStatistics makeParams(String phoneNumber, String status,String refernceCode,String irancellToken) {
        ApplicationInfo applicationInfo = getContext().getApplicationInfo();
        String appName = applicationInfo.name;

        String pkgName = getContext().getPackageName();
        String versionName = null;
        long versionCode = 0;
        try {
            ApplicationInfo ai;
            try {
                ai = getContext().getPackageManager().getApplicationInfo(pkgName, 0);
            } catch (final PackageManager.NameNotFoundException e) {
                ai = null;
            }
            appName = (String) (ai != null ? getContext().getPackageManager().getApplicationLabel(ai) : "(unknown)");
            versionName = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
            versionCode = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode;


        } catch (PackageManager.NameNotFoundException e) {
            versionName = "";
            e.printStackTrace();
        }

        ParamsStatistics params = new ParamsStatistics();

        params.setPackageName(pkgName);
        params.setVersionCode(versionCode);


        params.setDeviceModel(Build.MODEL);
        params.setOsVersion("" + android.os.Build.VERSION.SDK_INT);

        params.setDeviceId(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));

        params.setPhoneNumber((phoneNumber == null ? "" : phoneNumber));
        params.setMarketerId(this.marketId);

        params.setStatus(status);

        params.setIrancellToken(irancellToken);
        params.setRefrenceCode(refernceCode);


        return params;
    }


    public Context getContext() {
        return context;
    }


    interface ApiStatistics {
        @POST("UsageReport")
        Call<ResponseStatistics> report(@Body() ParamsStatistics params);
    }
}

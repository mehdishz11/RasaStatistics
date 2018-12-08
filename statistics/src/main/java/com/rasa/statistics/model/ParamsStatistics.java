
package com.rasa.statistics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParamsStatistics implements Serializable
{

    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("versionCode")
    @Expose
    private Long versionCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("deviceModel")
    @Expose
    private String deviceModel;
    @SerializedName("osVersion")
    @Expose
    private String osVersion;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("refrenceCode")
    @Expose
    private String refrenceCode;
    @SerializedName("irancellToken")
    @Expose
    private String irancellToken;
    @SerializedName("marketerId")
    @Expose
    private int marketerId;
    private final static long serialVersionUID = 4513584269895331374L;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRefrenceCode() {
        return refrenceCode;
    }

    public void setRefrenceCode(String refrenceCode) {
        this.refrenceCode = refrenceCode;
    }

    public String getIrancellToken() {
        return irancellToken;
    }

    public void setIrancellToken(String irancellToken) {
        this.irancellToken = irancellToken;
    }

    public int getMarketerId() {
        return marketerId;
    }

    public void setMarketerId(int marketerId) {
        this.marketerId = marketerId;
    }

}

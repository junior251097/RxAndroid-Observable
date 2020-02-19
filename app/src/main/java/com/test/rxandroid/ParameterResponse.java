package com.test.rxandroid;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParameterResponse {

    @SerializedName("tabDetCode001")
    @Expose
    private String tabDetCode001;
    @SerializedName("tabDetCode002")
    @Expose
    private String tabDetCode002;
    @SerializedName("tabDetCode003")
    @Expose
    private Integer tabDetCode003;
    @SerializedName("tabDetCode004")
    @Expose
    private Object tabDetCode004;
    @SerializedName("tabDetCode005")
    @Expose
    private Integer tabDetCode005;
    @SerializedName("tabDetCode006")
    @Expose
    private String tabDetCode006;
    @SerializedName("tabDetCode007")
    @Expose
    private String tabDetCode007;
    @SerializedName("tabDetCode008")
    @Expose
    private String tabDetCode008;

    public ParameterResponse(String tabDetCode001, String tabDetCode002, Integer tabDetCode003, Object tabDetCode004, Integer tabDetCode005, String tabDetCode006, String tabDetCode007, String tabDetCode008) {
        this.tabDetCode001 = tabDetCode001;
        this.tabDetCode002 = tabDetCode002;
        this.tabDetCode003 = tabDetCode003;
        this.tabDetCode004 = tabDetCode004;
        this.tabDetCode005 = tabDetCode005;
        this.tabDetCode006 = tabDetCode006;
        this.tabDetCode007 = tabDetCode007;
        this.tabDetCode008 = tabDetCode008;
    }

    public String getTabDetCode001() {
        return tabDetCode001;
    }

    public void setTabDetCode001(String tabDetCode001) {
        this.tabDetCode001 = tabDetCode001;
    }

    public String getTabDetCode002() {
        return tabDetCode002;
    }

    public void setTabDetCode002(String tabDetCode002) {
        this.tabDetCode002 = tabDetCode002;
    }

    public Integer getTabDetCode003() {
        return tabDetCode003;
    }

    public void setTabDetCode003(Integer tabDetCode003) {
        this.tabDetCode003 = tabDetCode003;
    }

    public Object getTabDetCode004() {
        return tabDetCode004;
    }

    public void setTabDetCode004(Object tabDetCode004) {
        this.tabDetCode004 = tabDetCode004;
    }

    public Integer getTabDetCode005() {
        return tabDetCode005;
    }

    public void setTabDetCode005(Integer tabDetCode005) {
        this.tabDetCode005 = tabDetCode005;
    }

    public String getTabDetCode006() {
        return tabDetCode006;
    }

    public void setTabDetCode006(String tabDetCode006) {
        this.tabDetCode006 = tabDetCode006;
    }

    public String getTabDetCode007() {
        return tabDetCode007;
    }

    public void setTabDetCode007(String tabDetCode007) {
        this.tabDetCode007 = tabDetCode007;
    }

    public String getTabDetCode008() {
        return tabDetCode008;
    }

    public void setTabDetCode008(String tabDetCode008) {
        this.tabDetCode008 = tabDetCode008;
    }

    @NonNull
    @Override
    public String toString() {
        return this.tabDetCode002;
    }

}

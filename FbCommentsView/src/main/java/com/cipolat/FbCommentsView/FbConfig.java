package com.cipolat.FbCommentsView;

/**
 * Created by sebastian on 08/03/18.
 */

public class FbConfig {
    public static final String MAX_COMMENTS = "15";
    private String appID, url;

    public FbConfig(String appID, String url) {
        this.appID = appID;
        this.url = url;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

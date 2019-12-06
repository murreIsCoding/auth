package com.murre.auth;

import com.murre.auth.util.UrlUtil;

public class ApiRequest {
    String baseUrl;
    String appId;
    String token;
    private long timestamp;

    public ApiRequest(String baseUrl, String appId, String token, long timestamp) {
        this.baseUrl = baseUrl;
        this.appId = appId;
        this.token = token;
        this.timestamp = timestamp;
    }

    /**
     * 从原始url中解析出真正的信息
     *
     * @param url
     * @return
     */
    public static ApiRequest buildFromUrl(String url) {
        UrlUtil.UrlEntity urlEntity = UrlUtil.parse(url);
        String baseUrl = urlEntity.baseUrl;
        String appId = urlEntity.getParam("appId");
        String token = urlEntity.getParam("token");
        long timestamp = Long.valueOf(urlEntity.getParam("ts"));
        ApiRequest apiRequest = new ApiRequest(baseUrl,appId,token,timestamp);
        return apiRequest;
    }

    public String getAppId() {
        return appId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

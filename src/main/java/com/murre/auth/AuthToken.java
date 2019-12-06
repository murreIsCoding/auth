package com.murre.auth;

import com.murre.auth.util.SHAUtil;

public class AuthToken {
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;
    public static final String SPLIT="&";

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
       this(token,createTime);
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public static AuthToken generate(String baseUrl, String appId, String password, long timestamp) {
        //将4个字段用特殊字符拼接起来
        String token = genToken(baseUrl,appId,password,timestamp);
        return new AuthToken(token,timestamp);
    }

    public static String genToken(String baseUrl, String appId, String password, long timestamp){
        StringBuffer sb = new StringBuffer();
        sb.append(baseUrl);
        sb.append(SPLIT);
        sb.append(appId);
        sb.append(SPLIT);
        sb.append(password);
        sb.append(SPLIT);
        sb.append(timestamp);
        String token  = SHAUtil.SHA(sb.toString());
        return token;
    }



    public boolean isExpired() {
        return System.currentTimeMillis()>(createTime+DEFAULT_EXPIRED_TIME_INTERVAL);
    }

    public boolean match(AuthToken clientAuthToken) {
        if (token.equals(clientAuthToken.token)){
            return true;
        }else {
            return false;
        }
    }
}

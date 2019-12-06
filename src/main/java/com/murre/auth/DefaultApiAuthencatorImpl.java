package com.murre.auth;


import com.murre.auth.inter.ApiAuthencator;
import com.murre.auth.inter.CredentialStorage;

public class DefaultApiAuthencatorImpl implements ApiAuthencator {
  private CredentialStorage credentialStorage;
  
  public DefaultApiAuthencatorImpl() {
    this.credentialStorage = new MemoryCredentialStorage();
  }
  
  public DefaultApiAuthencatorImpl(CredentialStorage credentialStorage) {
    this.credentialStorage = credentialStorage;
  }

  @Override
  public void auth(String url) {
    ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
    auth(apiRequest);
  }

  @Override
  public void auth(ApiRequest apiRequest) {
    String appId = apiRequest.getAppId();
    String token = apiRequest.getToken();
    long timestamp = apiRequest.getTimestamp();
    String baseUrl = apiRequest.getBaseUrl();

    AuthToken clientAuthToken = new AuthToken(token, timestamp);
    if (clientAuthToken.isExpired()) {
      throw new RuntimeException("Token is expired.");
    }

    String password = credentialStorage.getPasswordByAppId(appId);
    AuthToken serverAuthToken = AuthToken.generate(baseUrl, appId, password, timestamp);
    if (!serverAuthToken.match(clientAuthToken)) {
      throw new RuntimeException("Token verfication failed.");
    }
  }
}
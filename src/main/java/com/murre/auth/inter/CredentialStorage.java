package com.murre.auth.inter;

public interface CredentialStorage {
    String getPasswordByAppId(String appId);
}

package org.stofli.adapter.webapi.auth.core;

public interface CredentialProvider<T> {
    T loadCredentials() throws InterruptedException;
}
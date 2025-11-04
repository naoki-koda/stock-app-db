package org.stofli.adopter.webapi.auth.core;

public interface CredentialProvider<T> {
    T loadCredentials() throws InterruptedException;
}
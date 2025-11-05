package org.stofli.adapter.webapi.auth.core;

import java.io.IOException;

public interface TokenProvider {
    IdToken obtainToken() throws IOException, InterruptedException;
}

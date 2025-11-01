package org.stofli.jquants.infrastructure.api.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Value object representing the payload required by the J-Quants authentication API.
 */
public record JQuantsCredentials(
        @JsonProperty("mailaddress") String mailAddress,
        @JsonProperty("password") String password) {
}

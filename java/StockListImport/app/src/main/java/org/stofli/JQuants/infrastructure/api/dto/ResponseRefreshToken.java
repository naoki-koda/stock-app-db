package org.stofli.jquants.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseRefreshToken(@JsonProperty("refreshToken") String value) {
}

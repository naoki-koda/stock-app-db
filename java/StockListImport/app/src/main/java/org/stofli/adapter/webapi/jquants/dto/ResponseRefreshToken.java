package org.stofli.adapter.webapi.jquants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseRefreshToken(@JsonProperty("refreshToken") String value) {
}

package org.stofli.adopter.webapi.jquants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseRefreshToken(@JsonProperty("refreshToken") String value) {
}

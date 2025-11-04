package org.stofli.jquants.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IdToken(@JsonProperty("idToken") String value) {
}

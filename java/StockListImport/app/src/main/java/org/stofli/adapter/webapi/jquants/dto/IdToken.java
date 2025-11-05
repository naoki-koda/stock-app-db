package org.stofli.adapter.webapi.jquants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IdToken(@JsonProperty("idToken") String value) {
}

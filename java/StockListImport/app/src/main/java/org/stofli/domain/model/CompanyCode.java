package org.stofli.domain.model;

import java.util.Objects;

public final class CompanyCode {
    private final String value;
    public CompanyCode(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Company code cannot be null or blank.");
        }
        if(value.length() == 5) {
            value = value.substring(0, 4);
        }
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyCode)) return false;
        CompanyCode that = (CompanyCode) o;
        return Objects.equals(this.value, that.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    public String value() {
        return value;
    }
    @Override
    public String toString() {
        return "CompanyCode = " + value;
    }
}

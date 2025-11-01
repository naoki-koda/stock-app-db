package org.stofli.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RegistrationDate {
    private final LocalDate value;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public RegistrationDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            throw new IllegalArgumentException("登録日が空です。");
        }
        this.value = LocalDate.parse(dateStr, FORMATTER);
    }

    public LocalDate value() {
        return value;
    }
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof RegistrationDate)) return false;
        RegistrationDate that = (RegistrationDate) o;
        return value.equals(that.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value)  ;
    }
    @Override
    public String toString() {
        return value.format(FORMATTER);
    }
}

package org.stofli.domain.model;

import java.util.Objects;

public class ExcelFilePath {
    private String filePath;

    public ExcelFilePath(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("Excelファイルパスが空");
        }
        if (!filePath.toLowerCase().endsWith(".xlsx") && !filePath.toLowerCase().endsWith(".xls")) {
            throw new IllegalArgumentException("Excelファイルの拡張子が不正です。");
        }
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExcelFilePath)) return false;
        ExcelFilePath excel = (ExcelFilePath) o;
        return Objects.equals(filePath, excel.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath);
    }

    @Override
    public String toString() {
        return "ExcelFilePath = " + filePath;
    }
}

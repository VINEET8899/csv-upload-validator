package com.vineet.csvupload.model;

public class CsvError {
    private int row;
    private String message;

    public CsvError(int row, String message) {
        this.row = row;
        this.message = message;
    }

    public int getRow() {
        return row;
    }

    public String getMessage() {
        return message;
    }
}


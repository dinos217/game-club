package com.project.gameclub.enums;

public enum GameStatus {

    LOANED("Loaned", (short) 1),
    NOT_LOANED("Not Loaned", (short) 0);

    private final String label;
    private final Short code;

    GameStatus(String label, Short code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public Short getCode() {
        return code;
    }
}

package com.joi.crudspring.enums;

public enum Category {
    BACK_END("back-end"),
    FRONT_END("front-end");

    private final String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

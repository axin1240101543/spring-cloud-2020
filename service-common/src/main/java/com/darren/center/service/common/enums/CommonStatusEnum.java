package com.darren.center.service.common.enums;

import lombok.Getter;

public enum CommonStatusEnum {

    SUCCESS(1, "success"),
    FAIL(0, "fail"),
    EXCEPTION(500, "exception");

    @Getter
    private final int code;

    @Getter
    private final String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}

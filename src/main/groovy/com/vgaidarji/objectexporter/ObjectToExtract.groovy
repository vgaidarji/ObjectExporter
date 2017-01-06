package com.vgaidarji.objectexporter

class ObjectToExtract {
    private final String type
    private final String name
    private final String value

    ObjectToExtract(String type, String name, String value) {
        this.type = type
        this.name = name
        this.value = value
    }

    String getType() {
        return type
    }

    String getName() {
        return name
    }

    String getValue() {
        return value
    }
}
package com.vgaidarji.objectexporter

import com.sun.jdi.Type
import com.sun.jdi.VirtualMachine

class PrimitiveType implements Type {
    String name

    PrimitiveType(String name) {
        this.name = name
    }

    @Override
    String signature() {
        return null
    }

    @Override
    String name() {
        return name
    }

    @Override
    VirtualMachine virtualMachine() {
        return null
    }
}

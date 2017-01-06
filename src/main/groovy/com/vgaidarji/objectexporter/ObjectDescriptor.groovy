package com.vgaidarji.objectexporter

import com.sun.jdi.Type
import com.sun.jdi.Value

class ObjectDescriptor {
    final String variableName
    final Type variableType
    final Value variableValue

    ObjectDescriptor(String variableName, Type variableType, Value variableValue) {
        this.variableName = variableName
        this.variableType = variableType
        this.variableValue = variableValue
    }

    String getVariableName() {
        return variableName
    }

    Type getVariableType() {
        return variableType
    }

    Value getVariableValue() {
        return variableValue
    }
}

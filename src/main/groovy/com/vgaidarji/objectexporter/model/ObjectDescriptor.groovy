/*
 * Copyright (C) 2016-2017 Veaceslav Gaidarji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vgaidarji.objectexporter.model

import com.intellij.debugger.engine.JavaValue
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import com.sun.jdi.Type
import com.sun.jdi.Value

class ObjectDescriptor {
    final String variableName
    final Type variableType
    final Value variableValue
    final ValueDescriptorImpl valueDescriptor

    ObjectDescriptor(JavaValue value) {
        this.variableName = value.name
        this.variableType = value.descriptor.type
        this.variableValue = value.descriptor.value
        this.valueDescriptor = value.descriptor
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

    ValueDescriptorImpl getValueDescriptor() {
        return valueDescriptor
    }
}

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

package com.vgaidarji.objectexporter.parser

import com.vgaidarji.objectexporter.model.ObjectDescriptor
import com.vgaidarji.objectexporter.model.ObjectToExtract

class PrimitiveParser extends ObjectParser {

    PrimitiveParser(ObjectDescriptor objectDescriptor) {
        super(objectDescriptor)
    }

    String getPrimitiveAsString() {
        Map<String, Object> input = new HashMap<String, Object>()
        def objectToExtract = new ObjectToExtract(objectDescriptor.variableName,
                objectDescriptor.variableType.name(),
                objectDescriptor.variableValue.toString())
        input.put(OBJECT_PRIMITIVE_TEMPLATE_MAPPING, objectToExtract)
        applyTemplate(TEMPLATE_PRIMITIVE_OBJECT, input)
    }
}

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

package com.vgaidarji.objectexporter

import com.vgaidarji.objectexporter.model.ObjectDescriptor
import com.vgaidarji.objectexporter.parser.NonPrimitiveParser
import com.vgaidarji.objectexporter.parser.PrimitiveParser

class ObjectExporter {
    private final ObjectDescriptor objectDescriptor

    ObjectExporter(ObjectDescriptor objectDescriptor) {
        this.objectDescriptor = objectDescriptor
    }

    /**
     * Extracts object as String.
     */
    String asString() {
        if (objectDescriptor.valueDescriptor.isPrimitive()) {
            new PrimitiveParser(objectDescriptor).primitiveAsString
        } else {
            new NonPrimitiveParser(objectDescriptor).nonPrimitiveAsString
        }
    }
}

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

import com.sun.tools.jdi.ClassTypeImpl
import com.sun.tools.jdi.ObjectReferenceImpl
import com.vgaidarji.objectexporter.model.ObjectDescriptor
import com.vgaidarji.objectexporter.model.ObjectToExtract
import javassist.bytecode.SignatureAttribute

class NonPrimitiveParser extends ObjectParser {

    NonPrimitiveParser(ObjectDescriptor objectDescriptor) {
        super(objectDescriptor)
    }

    String getNonPrimitiveAsString() {
        Map<String, Object> input = new HashMap<String, Object>()

        //        Person person = new Person(); \
        //        person.setFirstName(\"Veaceslav\"); \
        //        person.setLastName(\"Gaidarji\"); \
        //        person.setAge(17); \
        //        Address address = new Address(); \
        //        address.setCity(\"Chisinau\"); \
        //        address.setStreet(\"bd. Stefan cel Mare\"); \
        //        address.setBuilding(1); \
        //        person.setAddress(address);

        ClassTypeImpl classType = (ClassTypeImpl)objectDescriptor.valueDescriptor.type
        ObjectReferenceImpl value = (ObjectReferenceImpl)objectDescriptor.valueDescriptor.value

        StringBuilder generated = new StringBuilder()
                .append(extractSimpleName(classType.signature()))
                .append()

        // TODO use FreeBuilder API and create object metadata http://programtalk.com/vs/FreeBuilder/src/test/java/org/inferred/freebuilder/processor/SetSourceTest.java/
        // TODO generate object from metadata

        def objectToExtract = new ObjectToExtract(objectDescriptor.variableName,
                objectDescriptor.variableType.name(),
                objectDescriptor.variableValue.toString())
        input.put(OBJECT_PRIMITIVE_TEMPLATE_MAPPING, objectToExtract)
        applyTemplate(TEMPLATE_NON_PRIMITIVE_OBJECT, input)
    }


    /**
     * @param className e.g.: Lsample/SampleJava$Person;
     * @return SampleJava.Person
     */
    String extractSimpleName(String className) {
        int i = className.lastIndexOf('/')
        if (i > 0) {
            className = className.substring(i + 1)
        }
        className = className.replace(';', '')
        className = className.replace('$', '.')
        return className
    }
}

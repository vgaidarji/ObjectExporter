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

import com.sun.jdi.Field
import com.sun.jdi.Value
import com.sun.tools.jdi.ArrayTypeImpl
import com.sun.tools.jdi.ClassTypeImpl
import com.sun.tools.jdi.ObjectReferenceImpl
import com.vgaidarji.objectexporter.model.ObjectDescriptor

class NonPrimitiveParser extends ObjectParser {

    NonPrimitiveParser(ObjectDescriptor objectDescriptor) {
        super(objectDescriptor)
    }

    def getNonPrimitiveAsString() {
        Map<String, Object> input = new HashMap<String, Object>()
        StringBuilder generated = new StringBuilder()
        //        Person person = new Person(); \
        //        person.setFirstName(\"Veaceslav\"); \
        //        person.setLastName(\"Gaidarji\"); \
        //        person.setAge(17); \
        //        Address address = new Address(); \
        //        address.setCity(\"Chisinau\"); \
        //        address.setStreet(\"bd. Stefan cel Mare\"); \
        //        address.setBuilding(1); \
        //        person.setAddress(address);

        def type = objectDescriptor.valueDescriptor.type
        if (type instanceof ClassTypeImpl) {
            ClassTypeImpl classType = (ClassTypeImpl) type
            ObjectReferenceImpl value = (ObjectReferenceImpl) objectDescriptor.valueDescriptor.value
            generated.append(extractSimpleName(classType.signature()));
            appendFields(classType, generated)
        } else if (type instanceof ArrayTypeImpl) {
            // parse array signature
        }

        // TODO use FreeBuilder API and create object metadata http://programtalk.com/vs/FreeBuilder/src/test/java/org/inferred/freebuilder/processor/SetSourceTest.java/
        // TODO generate object from metadata
        generated
    }

    /**
     * @param className e.g.: Lsample/SampleJava$Person;
     * @return SampleJava.Person
     */
    static def extractSimpleName(String className) {
        int i = className.lastIndexOf('/')
        if (i > 0) {
            className = className.substring(i + 1)
        }
        className = className.replace(';', '')
        className = className.replace('$', '.')
        return className
    }

    static def appendFields(ClassTypeImpl classType, StringBuilder builder) {
        for (Field f : classType.fields()) {
            String name = f.name()
            Value v = classType.getValue(f)
            builder.append("set")
                    .append(name.capitalize())
                    .append("(")
                    .append(v.toString())
                    .append(")")
                    .append(";")
        }
    }
}

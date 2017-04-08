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

import com.sun.tools.jdi.ClassTypeImpl
import com.sun.tools.jdi.MyIntegerValueImpl
import com.vgaidarji.objectexporter.mock.MockJavaValue
import com.vgaidarji.objectexporter.model.ObjectDescriptor
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4)
class ObjectExporterTest extends BaseGroovyTest {


    @Test
    void generatesCodeForExtractedPrimitive() {
        def type = new PrimitiveType("int")
        def name = "myVariable"
        def value = new MyIntegerValueImpl(virtualMachine, 3)
        ObjectExporter exporter = new ObjectExporter(new ObjectDescriptor(
                new MockJavaValue(createPrimitiveValueDescriptor(type, name, value), evaluationContext)
        ))

        String exportedPrimitive = exporter.asString()

        assertEquals("int myVariable = 3;", exportedPrimitive)
    }

    @Test
    void generatesCodeForExtractedObject() {
        String expected = "Person person = new Person(); \
        person.setFirstName(\"Veaceslav\"); \
        person.setLastName(\"Gaidarji\"); \
        person.setAge(17); \
        Address address = new Address(); \
        address.setCity(\"Chisinau\"); \
        address.setStreet(\"bd. Stefan cel Mare\"); \
        address.setBuilding(1); \
        person.setAddress(address);"
        def type = mock(ClassTypeImpl.class)
        def name = "person"
        def value = null
        ObjectExporter exporter = new ObjectExporter(new ObjectDescriptor(
                new MockJavaValue(createClassTypeDescriptor(type, name, value), evaluationContext)
        ))

        String exported = exporter.asString()

        assertEquals(expected, exported)
    }
}

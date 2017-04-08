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

import com.vgaidarji.objectexporter.BaseGroovyTest
import com.vgaidarji.objectexporter.model.ObjectDescriptor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import static org.junit.Assert.*
import static org.mockito.Mockito.mock

/**
 * See <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/jni/spec/types.html#wp276">Java type signatures</a>
 *
 */
@RunWith(Parameterized.class)
class NonPrimitiveParserTest extends BaseGroovyTest {

    private NonPrimitiveParser parser
    private String input
    private String expected

    @Parameterized.Parameters
    static ArrayList<Object> fullyQualifiedClassNames() {
        [
                ["Ljava/lang/Boolean;", "Boolean"],
                ["Ljava/lang/Byte;", "Byte"],
                ["Ljava/lang/String;", "String"],
                ["Ljava/lang/Short;", "Short"],
                ["Ljava/lang/Character;", "Character"],
                ["Ljava/lang/Integer;", "Integer"],
                ["Ljava/lang/Long;", "Long"],
                ["Ljava/lang/Float;", "Float"],
                ["Ljava/lang/Double;", "Double"],
                ["Lsample/SampleJava;", "SampleJava"],
                ["Lsample/SampleJava\$Person;", "SampleJava.Person"]
        ]*.toArray()
    }

    NonPrimitiveParserTest(String input, String expected) {
        this.input = input
        this.expected = expected
    }

    @Before
    @Override
    void setUp() {
        super.setUp()
        parser = new NonPrimitiveParser(mock(ObjectDescriptor.class))
    }

    @Test
    void extractsSimpleNameFromClass() throws Exception {
        assertEquals(expected, parser.extractSimpleName(input))
    }
}
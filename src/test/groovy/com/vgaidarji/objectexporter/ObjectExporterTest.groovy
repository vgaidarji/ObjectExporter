package com.vgaidarji.objectexporter

import com.sun.tools.jdi.MyIntegerValueImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4)
class ObjectExporterTest extends BaseGroovyTest {

    @Test
    void asString_shouldGenerateCodeForExtractedPrimitive() {
        def type = new PrimitiveType("int")
        def name = "myVariable"
        def value = new MyIntegerValueImpl(virtualMachine, 3)
        ObjectExporter exporter = new ObjectExporter(new ObjectDescriptor(name, type, value))

        String exportedPrimitive = exporter.asString()

        assertEquals("int myVariable = 3;", exportedPrimitive)
    }

    @Test
    void asString_shouldGenerateNothingForEmptyConfiguration() {

    }
}

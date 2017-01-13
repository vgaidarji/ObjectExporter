package com.vgaidarji.objectexporter

import com.sun.tools.jdi.MyVirtualMachineImpl

import static org.mockito.Mockito.mock

abstract class BaseGroovyTest extends GroovyTestCase {
    protected MyVirtualMachineImpl virtualMachine

    @Override
    void setUp() {
        super.setUp()
        virtualMachine = mock(MyVirtualMachineImpl)
    }
}

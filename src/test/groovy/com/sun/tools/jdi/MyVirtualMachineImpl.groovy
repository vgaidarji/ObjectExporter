package com.sun.tools.jdi

import com.sun.jdi.VirtualMachineManager
import com.sun.jdi.connect.spi.Connection

class MyVirtualMachineImpl extends VirtualMachineImpl{
    MyVirtualMachineImpl(VirtualMachineManager virtualMachineManager,
            Connection connection, Process process, int i) {
        super(virtualMachineManager, connection, process, i)
    }
}

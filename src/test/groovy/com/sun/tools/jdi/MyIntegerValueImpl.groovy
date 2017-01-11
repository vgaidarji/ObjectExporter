package com.sun.tools.jdi

import com.sun.jdi.VirtualMachine

class MyIntegerValueImpl extends IntegerValueImpl {
    MyIntegerValueImpl(VirtualMachine virtualMachine, int i) {
        super(virtualMachine, i)
    }
}

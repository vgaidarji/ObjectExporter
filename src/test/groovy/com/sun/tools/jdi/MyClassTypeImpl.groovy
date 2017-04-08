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

package com.sun.tools.jdi

import com.sun.jdi.ClassNotLoadedException
import com.sun.jdi.ClassType
import com.sun.jdi.Field
import com.sun.jdi.IncompatibleThreadStateException
import com.sun.jdi.InterfaceType
import com.sun.jdi.InvalidTypeException
import com.sun.jdi.InvocationException
import com.sun.jdi.Method
import com.sun.jdi.ObjectReference
import com.sun.jdi.ThreadReference
import com.sun.jdi.Value
import com.sun.jdi.VirtualMachine

class MyClassTypeImpl extends InvokableTypeImpl implements ClassType  {
    def MyClassTypeImpl(VirtualMachine virtualMachine, long l) {
        super(virtualMachine, l)
    }

    @Override
    def CommandSender getInvokeMethodSender(ThreadReferenceImpl threadReference, MethodImpl method,
            ValueImpl[] values, int i) {
        return null
    }

    @Override
    def InvocationResult waitForReply(PacketStream packetStream) throws JDWPException {
        return null
    }

    @Override
    ClassType superclass() {
        return null
    }

    @Override
    List<InterfaceType> interfaces() {
        return null
    }

    @Override
    def boolean canInvoke(Method method) {
        return false
    }

    @Override
    List<InterfaceType> allInterfaces() {
        return null
    }

    @Override
    List<ClassType> subclasses() {
        return null
    }

    @Override
    boolean isEnum() {
        return false
    }

    @Override
    void setValue(Field field, Value value) throws InvalidTypeException, ClassNotLoadedException {
    }

    @Override
    ObjectReference newInstance(ThreadReference threadReference, Method method,
            List<? extends Value> list, int i)
            throws InvalidTypeException, ClassNotLoadedException, IncompatibleThreadStateException,
                    InvocationException {
        return null
    }

    @Override
    Method concreteMethodByName(String s, String s1) {
        return null
    }
}

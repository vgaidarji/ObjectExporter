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

import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl
import com.sun.jdi.PrimitiveValue
import com.sun.tools.jdi.MyIntegerValueImpl
import com.vgaidarji.objectexporter.model.ObjectDescriptor
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@RunWith(JUnit4)
class DebuggerToObjectTest extends BaseGroovyTest {

    @Test
    void extractsPrimitive() {
        DebuggerTreeToObject extractor = new DebuggerTreeToObject()
        def type = new PrimitiveType("int")
        def name = "myVariable"
        def value = new MyIntegerValueImpl(virtualMachine, 3)
        XDebuggerTree xDebuggerTree = createDebuggerTreeWithPrimitive(type, name, value)

        ObjectDescriptor descriptor = extractor.toObject(xDebuggerTree)

        assertEquals(type, descriptor.getVariableType())
        assertEquals(name, descriptor.getVariableName())
        assertEquals(value, descriptor.getVariableValue())
    }

    private XDebuggerTree createDebuggerTreeWithPrimitive(PrimitiveType type, String name,
            PrimitiveValue value) {
        XDebuggerTree tree = mock(XDebuggerTree)
        TreeSelectionModel treeSelectionModel = mock(TreeSelectionModel)
        TreePath treePath = mock(TreePath)
        XValueNodeImpl xValueNodeImpl = mock(XValueNodeImpl)
        when(tree.getSelectionModel()).thenReturn(treeSelectionModel)
        when(treeSelectionModel.getLeadSelectionPath()).thenReturn(treePath)
        when(treePath.getLastPathComponent()).thenReturn(xValueNodeImpl)
        when(xValueNodeImpl.getValueContainer()).thenReturn(preparePrimitive(type, name, value))
        tree
    }
}

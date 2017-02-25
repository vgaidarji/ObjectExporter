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

import com.intellij.debugger.engine.DebugProcessImpl
import com.intellij.debugger.engine.JavaValue
import com.intellij.debugger.engine.MockSuspendContext
import com.intellij.debugger.engine.SuspendContextImpl
import com.intellij.debugger.engine.evaluation.EvaluationContextImpl
import com.intellij.debugger.settings.NodeRendererSettings
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.Application
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.extensions.Extensions
import com.intellij.openapi.project.Project
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl
import com.sun.jdi.PrimitiveValue
import com.sun.tools.jdi.MyIntegerValueImpl
import com.vgaidarji.objectexporter.mock.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.picocontainer.PicoContainer

import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@RunWith(JUnit4)
class DebuggerToObjectTest extends BaseGroovyTest {
    private Disposable disposable
    private PicoContainer picoContainer
    private Application application
    private Project project

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

    private JavaValue preparePrimitive(PrimitiveType type, String name, PrimitiveValue value) {
        mockDependencies()
        DebugProcessImpl debugProcess = new MockDebugProcess(project)
        SuspendContextImpl suspendContext = new MockSuspendContext(debugProcess, 0, 0, null)
        ValueDescriptorImpl descriptor = createValueDescriptor(type, name, value)
        new MockJavaValue(descriptor, new EvaluationContextImpl(suspendContext, null, null))
    }

    private void mockDependencies() {
        Extensions.registerAreaClass("IDEA_PROJECT", null);
        disposable = new MockDisposable()
        picoContainer = new MockPicoContainer()
        application = new MockApplication(picoContainer, disposable)
        application.addComponent(NodeRendererSettings, mock(NodeRendererSettings))
        ApplicationManager.setApplication(application, disposable)
    }

    private ValueDescriptorImpl createValueDescriptor(PrimitiveType type, String name,
            PrimitiveValue value) {
        ValueDescriptorImpl valueDescriptor = new MockDescriptor(project)
        valueDescriptor.value = value
        valueDescriptor.type = type
        valueDescriptor.name = name
        valueDescriptor
    }
}

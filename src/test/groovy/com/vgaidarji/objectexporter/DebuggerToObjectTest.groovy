package com.vgaidarji.objectexporter

import com.intellij.debugger.engine.JavaValue
import com.intellij.debugger.engine.evaluation.EvaluationContextImpl
import com.intellij.debugger.ui.impl.watch.ArgumentValueDescriptorImpl
import com.intellij.debugger.ui.impl.watch.NodeManagerImpl
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import com.intellij.mock.MockProjectEx
import com.intellij.testFramework.LightCodeInsightTestCase
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl
import com.sun.jdi.PrimitiveValue
import com.sun.tools.jdi.MyIntegerValueImpl
import com.sun.tools.jdi.MyVirtualMachineImpl
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@RunWith(JUnit4)
class DebuggerToObjectTest extends GroovyTestCase {
    private MyVirtualMachineImpl virtualMachine

    @Override
    void setUp() {
        super.setUp()
        virtualMachine = mock(MyVirtualMachineImpl)
    }

    @Ignore("find a way to prepare `JavaValue` object for test")
    @Test
    void toObject_shouldExtractPrimitive() {
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

    private JavaValue preparePrimitive(PrimitiveType type, String name,
            PrimitiveValue value) {
        // TODO: find a way to prepare `JavaValue` object for test
        ValueDescriptorImpl valueDescriptor = mock(ValueDescriptorImpl.class)
        JavaValue javaValue = mock(JavaValue)
        when(valueDescriptor.getType()).thenReturn(type)
        when(valueDescriptor.getValue()).thenReturn(value)
        when(javaValue.getDescriptor()).thenReturn(valueDescriptor)
        when(javaValue.getName()).thenReturn(name)
        javaValue
    }
}

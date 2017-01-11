package com.vgaidarji.objectexporter

import com.intellij.debugger.engine.JavaValue
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl

import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

/**
 * Can extract object representation from debugger tree.
 */
class DebuggerTreeToObject {

    ObjectDescriptor toObject(XDebuggerTree xDebuggerTree) {
        final TreeSelectionModel selectionModel = xDebuggerTree.getSelectionModel()
        final TreePath leadSelectionPath = selectionModel.getLeadSelectionPath()
        final XValueNodeImpl lastPathComponent = (XValueNodeImpl) leadSelectionPath.
                getLastPathComponent()
        final JavaValue javaValue = (JavaValue) lastPathComponent.getValueContainer()
        final ValueDescriptorImpl valueDescriptor = javaValue.getDescriptor()
        new ObjectDescriptor(javaValue.getName(), valueDescriptor.getType(),
                valueDescriptor.getValue())
    }
}

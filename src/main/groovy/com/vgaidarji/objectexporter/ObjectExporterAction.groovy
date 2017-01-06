/*
 *  Copyright (C) 2016 Veaceslav Gaidarji
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.vgaidarji.objectexporter

import com.intellij.debugger.engine.JavaValue
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl

import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

class ObjectExporterAction extends AnAction {

    @Override
    void actionPerformed(AnActionEvent e) {
        XDebuggerTree xDebuggerTree = XDebuggerTree.getTree(e)
        if (xDebuggerTree != null) {
            String code =
                    new ObjectExporter(e.getProject(), toObjectDescriptor(xDebuggerTree)).asString()
            Messages.showMessageDialog(e.getProject(), code, "Extracted object",
                    Messages.getInformationIcon())
        }
    }

    private static ObjectDescriptor toObjectDescriptor(XDebuggerTree xDebuggerTree) {
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

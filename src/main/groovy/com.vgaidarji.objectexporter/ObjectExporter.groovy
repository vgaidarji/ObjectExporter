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

import com.intellij.debugger.actions.DebuggerAction
import com.intellij.debugger.engine.DebugProcessImpl
import com.intellij.debugger.impl.DebuggerContextImpl
import com.intellij.debugger.ui.impl.watch.DebuggerTree
import com.intellij.debugger.ui.impl.watch.DebuggerTreeNodeImpl
import com.intellij.debugger.ui.impl.watch.NodeDescriptorImpl
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.xdebugger.impl.ui.tree.ValueMarkup

import java.awt.Component

class ObjectExporter extends AnAction {
    @Override
    void actionPerformed(AnActionEvent e) {
        System.out.println("ObjectsExporter - action performed");

        final DebuggerTreeNodeImpl node = DebuggerAction.getSelectedNode(e.getDataContext())
        if (node != null) {
            NodeDescriptorImpl descriptor = node.getDescriptor()
            if (descriptor instanceof ValueDescriptorImpl) {
                final DebuggerTree tree = node.getTree()
                tree.saveState(node)
                final Component parent = (Component)e.getData(PlatformDataKeys.CONTEXT_COMPONENT)
                final ValueDescriptorImpl valueDescriptor = (ValueDescriptorImpl)descriptor
                final DebuggerContextImpl debuggerContext = tree.getDebuggerContext()
                final DebugProcessImpl debugProcess = debuggerContext.getDebugProcess()
                final ValueMarkup markup = valueDescriptor.getMarkup(debugProcess)
            }
        }
    }
}

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
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl
import com.sun.jdi.Type
import com.sun.jdi.Value
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import freemarker.template.Version

import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

class ObjectExporter extends AnAction {
    private Configuration freemakerConfiguration

    @Override
    void actionPerformed(AnActionEvent e) {
        XDebuggerTree xDebuggerTree = XDebuggerTree.getTree(e)
        if (xDebuggerTree != null) {
            final TreeSelectionModel selectionModel = xDebuggerTree.getSelectionModel()
            final TreePath leadSelectionPath = selectionModel.getLeadSelectionPath()
            final XValueNodeImpl lastPathComponent = (XValueNodeImpl) leadSelectionPath.
                    getLastPathComponent()
            final JavaValue javaValue = (JavaValue) lastPathComponent.getValueContainer()
            final ValueDescriptorImpl valueDescriptor = javaValue.getDescriptor()
            final String variableName = javaValue.getName()
            final Type variableType = valueDescriptor.getType()
            final Value variableValue = valueDescriptor.getValue()

            generateCode(variableType, variableName, variableValue)

            println variableType.name() + " " + variableName + " = " + variableValue.toString()
        }
    }

    void generateCode(Type variableType, String variableName, Value variableValue) {
        Map<String, Object> input = new HashMap<String, Object>()
        input.put("objectToExtract",
                new ObjectToExtract(variableType.name(), variableName, variableValue.toString()))
        Template template = getConfiguration().getTemplate("primitive.ftl")
        Writer consoleWriter = new OutputStreamWriter(System.out)
        template.process(input, consoleWriter)

        Writer stringWriter = new StringWriter()
        try {
            template.process(input, stringWriter)
        } finally {
            stringWriter.close()
        }
    }

    private Configuration getConfiguration() {
        if (freemakerConfiguration == null) {
            freemakerConfiguration = new Configuration()
            this.freemakerConfiguration.setClassForTemplateLoading(ObjectExporter.class, "templates")
            this.freemakerConfiguration.setIncompatibleImprovements(new Version(2, 3, 20))
            this.freemakerConfiguration.setDefaultEncoding("UTF-8")
            this.freemakerConfiguration.setLocale(Locale.US)
            this.freemakerConfiguration.setTemplateExceptionHandler(
                    TemplateExceptionHandler.RETHROW_HANDLER)
        }
        freemakerConfiguration
    }

    class ObjectToExtract {
        private final String type
        private final String name
        private final String value

        ObjectToExtract(String type, String name, String value) {
            this.type = type
            this.name = name
            this.value = value
        }

        String getType() {
            return type
        }

        String getName() {
            return name
        }

        String getValue() {
            return value
        }
    }
}

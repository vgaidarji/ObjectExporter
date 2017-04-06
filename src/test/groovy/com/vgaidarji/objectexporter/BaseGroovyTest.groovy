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
import com.sun.jdi.PrimitiveValue
import com.sun.tools.jdi.MyVirtualMachineImpl
import com.vgaidarji.objectexporter.mock.MockApplication
import com.vgaidarji.objectexporter.mock.MockDebugProcess
import com.vgaidarji.objectexporter.mock.MockDescriptor
import com.vgaidarji.objectexporter.mock.MockDisposable
import com.vgaidarji.objectexporter.mock.MockJavaValue
import com.vgaidarji.objectexporter.mock.MockPicoContainer
import org.junit.Before
import org.picocontainer.PicoContainer

import static org.mockito.Mockito.mock

abstract class BaseGroovyTest extends GroovyTestCase {
    private Disposable disposable
    private PicoContainer picoContainer
    private Application application
    private Project project
    protected MyVirtualMachineImpl virtualMachine
    protected EvaluationContextImpl evaluationContext

    @Before
    @Override
    void setUp() {
        super.setUp()
        mockDependencies()
        DebugProcessImpl debugProcess = new MockDebugProcess(project)
        SuspendContextImpl suspendContext = new MockSuspendContext(debugProcess, 0, 0, null)
        virtualMachine = mock(MyVirtualMachineImpl)
        evaluationContext = new EvaluationContextImpl(suspendContext, null, null)
    }

    protected JavaValue preparePrimitive(PrimitiveType type, String name, PrimitiveValue value) {
        new MockJavaValue(createPrimitiveValueDescriptor(type, name, value), evaluationContext)
    }

    protected ValueDescriptorImpl createPrimitiveValueDescriptor(PrimitiveType type, String name,
            PrimitiveValue value) {
        ValueDescriptorImpl valueDescriptor = new MockDescriptor(project)
        valueDescriptor.value = value
        valueDescriptor.type = type
        valueDescriptor.name = name
        valueDescriptor.isPrimitive = true
        valueDescriptor
    }

    private void mockDependencies() {
        Extensions.registerAreaClass("IDEA_PROJECT", null);
        disposable = new MockDisposable()
        picoContainer = new MockPicoContainer()
        application = new MockApplication(picoContainer, disposable)
        application.addComponent(NodeRendererSettings, mock(NodeRendererSettings))
        ApplicationManager.setApplication(application, disposable)
    }
}

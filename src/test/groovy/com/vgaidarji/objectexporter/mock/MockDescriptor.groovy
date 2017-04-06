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

package com.vgaidarji.objectexporter.mock

import com.intellij.debugger.DebuggerContext
import com.intellij.debugger.engine.evaluation.EvaluateException
import com.intellij.debugger.engine.evaluation.EvaluationContextImpl
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiExpression
import com.sun.jdi.Type
import com.sun.jdi.Value
import com.sun.jdi.VirtualMachine

class MockDescriptor extends ValueDescriptorImpl {

    Value value
    Type type
    String name
    Boolean isPrimitive

    MockDescriptor(Project project) {
        super(project)
    }

    @Override
    Value calcValue(EvaluationContextImpl evaluationContext) throws EvaluateException {
        return null
    }

    @Override
    String calcValueName() {
        return name
    }

    @Override
    PsiExpression getDescriptorEvaluation(DebuggerContext context) throws EvaluateException {
        return null
    }

    @Override
    Type getType() {
        return type
    }

    @Override
    Value getValue() {
        return value
    }

    @Override
    boolean isPrimitive() {
        return isPrimitive
    }
}

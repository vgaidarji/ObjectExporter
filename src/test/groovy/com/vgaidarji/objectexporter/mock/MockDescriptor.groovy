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

}

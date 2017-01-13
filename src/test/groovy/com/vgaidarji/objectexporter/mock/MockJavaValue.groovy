package com.vgaidarji.objectexporter.mock

import com.intellij.debugger.engine.JavaValue
import com.intellij.debugger.engine.evaluation.EvaluationContextImpl
import com.intellij.debugger.ui.impl.watch.ValueDescriptorImpl
import org.jetbrains.annotations.NotNull

class MockJavaValue extends JavaValue {

    MockJavaValue(@NotNull ValueDescriptorImpl valueDescriptor,
            @NotNull EvaluationContextImpl evaluationContext) {
        super(null, valueDescriptor, evaluationContext, null, false)
    }
}

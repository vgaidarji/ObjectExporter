package com.vgaidarji.objectexporter.mock

import com.intellij.debugger.engine.DebugProcessImpl
import com.intellij.openapi.project.Project

class MockDebugProcess extends DebugProcessImpl {

    MockDebugProcess(Project project) {
        super(project)
    }
}

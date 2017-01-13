package com.intellij.debugger.engine

import com.sun.jdi.event.EventSet
import org.jetbrains.annotations.NotNull

class MockSuspendContext extends SuspendContextImpl {

    MockSuspendContext(@NotNull DebugProcessImpl debugProcess, int suspendPolicy, int eventVotes,
            EventSet set) {
        super(debugProcess, suspendPolicy, eventVotes, set)
    }

    @Override
    protected void resumeImpl() {
    }
}

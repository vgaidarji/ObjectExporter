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

import com.intellij.debugger.settings.NodeRendererSettings
import com.intellij.openapi.fileTypes.MockFileTypeManager
import org.picocontainer.*

class MockPicoContainer implements PicoContainer {

    @Override
    Object getComponentInstance(Object o) {
        if (o == NodeRendererSettings.class.name) {
            new NodeRendererSettings()
        } else {
            new MockFileTypeManager()
        }
    }

    @Override
    ComponentAdapter getComponentAdapter(Object o) {
        return new ComponentAdapter() {
            @Override
            Object getComponentKey() {
                return o
            }

            @Override
            Class getComponentImplementation() {
                return null
            }

            @Override
            Object getComponentInstance(PicoContainer picoContainer)
                    throws PicoInitializationException, PicoIntrospectionException {
                return null
            }

            @Override
            void verify(PicoContainer picoContainer) throws PicoIntrospectionException {
            }

            @Override
            void accept(PicoVisitor picoVisitor) {
            }
        }
    }

    @Override
    Object getComponentInstanceOfType(Class aClass) {
        return null
    }

    @Override
    List getComponentInstances() {
        return null
    }

    @Override
    PicoContainer getParent() {
        return null
    }

    @Override
    ComponentAdapter getComponentAdapterOfType(Class aClass) {
        return null
    }

    @Override
    Collection getComponentAdapters() {
        return null
    }

    @Override
    List getComponentAdaptersOfType(Class aClass) {
        return null
    }

    @Override
    void verify() throws PicoVerificationException {
    }

    @Override
    List getComponentInstancesOfType(Class aClass) {
        return null
    }

    @Override
    void accept(PicoVisitor picoVisitor) {
    }

    @Override
    void dispose() {
    }

    @Override
    void start() {
    }

    @Override
    void stop() {
    }
}

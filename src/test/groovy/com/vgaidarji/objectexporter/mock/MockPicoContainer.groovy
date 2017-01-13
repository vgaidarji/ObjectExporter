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
            Object getComponentInstance(PicoContainer picoContainer) throws PicoInitializationException, PicoIntrospectionException {
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

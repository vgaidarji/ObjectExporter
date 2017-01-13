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

import freemarker.template.Configuration
import freemarker.template.Version

import java.nio.charset.StandardCharsets

import static freemarker.template.TemplateExceptionHandler.RETHROW_HANDLER

class ObjectExporter {
    public static final String TEMPLATES_FOLDER = "/templates"
    public static final String TEMPLATE_PRIMITIVE_OBJECT = "primitive.ftl"
    public static final String OBJECT_PRIMITIVE_TEMPLATE_MAPPING = "objectToExtract"

    private Configuration freemakerConfiguration
    private final ObjectDescriptor descriptor

    ObjectExporter(ObjectDescriptor descriptor) {
        this.descriptor = descriptor
    }

    /**
     * Extracts object as String.
     */
    String asString() {
        Map<String, Object> input = new HashMap<String, Object>()
        input.put(OBJECT_PRIMITIVE_TEMPLATE_MAPPING,
                new ObjectToExtract(descriptor.getVariableType().name(),
                        descriptor.getVariableName(),
                        descriptor.getVariableValue().toString()))
        Writer stringWriter = new StringWriter()
        try {
            if (getConfiguration()) {
                getConfiguration().getTemplate(TEMPLATE_PRIMITIVE_OBJECT)
                        .process(input, stringWriter)
            }
        } finally {
            stringWriter.close()
        }
        stringWriter.toString()
    }

    /**
     * Initialize Freemaker configuration.
     */
    private Configuration getConfiguration() {
        if (freemakerConfiguration == null) {
            freemakerConfiguration = new Configuration()
            this.freemakerConfiguration.setDirectoryForTemplateLoading(
                    new File(getClass().getResource(TEMPLATES_FOLDER).getPath()))
            this.freemakerConfiguration.setIncompatibleImprovements(new Version(2, 3, 20))
            this.freemakerConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.toString())
            this.freemakerConfiguration.setLocale(Locale.US)
            this.freemakerConfiguration.setTemplateExceptionHandler(RETHROW_HANDLER)
        }
        freemakerConfiguration
    }
}

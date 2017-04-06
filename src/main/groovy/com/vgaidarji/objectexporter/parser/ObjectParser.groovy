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

package com.vgaidarji.objectexporter.parser

import com.vgaidarji.objectexporter.model.ObjectDescriptor
import freemarker.template.Configuration
import freemarker.template.Version

import java.nio.charset.StandardCharsets

import static freemarker.template.TemplateExceptionHandler.RETHROW_HANDLER

abstract class ObjectParser {
    public static final String TEMPLATES_FOLDER = "/templates"
    public static final String TEMPLATE_PRIMITIVE_OBJECT = "primitive.ftl"
    public static final String TEMPLATE_NON_PRIMITIVE_OBJECT = "non-primitive.ftl"
    public static final String OBJECT_PRIMITIVE_TEMPLATE_MAPPING = "objectToExtract"

    private Configuration freemakerConfiguration

    protected final ObjectDescriptor objectDescriptor

    ObjectParser(ObjectDescriptor objectDescriptor) {
        this.objectDescriptor = objectDescriptor
    }

    protected String applyTemplate(String templateName, HashMap<String, Object> input) {
        Writer stringWriter = new StringWriter()
        try {
            if (getConfiguration()) {
                getConfiguration().getTemplate(templateName).process(input, stringWriter)
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
            this.freemakerConfiguration.directoryForTemplateLoading =
                    new File(getClass().getResource(TEMPLATES_FOLDER).path)
            this.freemakerConfiguration.incompatibleImprovements = new Version(2, 3, 20)
            this.freemakerConfiguration.defaultEncoding = StandardCharsets.UTF_8.toString()
            this.freemakerConfiguration.locale = Locale.US
            this.freemakerConfiguration.templateExceptionHandler = RETHROW_HANDLER
        }
        freemakerConfiguration
    }
}

package com.vgaidarji.objectexporter

import freemarker.template.Configuration
import freemarker.template.Template
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
        Template template = getConfiguration().getTemplate(TEMPLATE_PRIMITIVE_OBJECT)
        Writer consoleWriter = new OutputStreamWriter(System.out)
        template.process(input, consoleWriter)

        Writer stringWriter = new StringWriter()
        try {
            template.process(input, stringWriter)
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

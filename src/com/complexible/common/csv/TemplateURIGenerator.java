package com.complexible.common.csv;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class TemplateURIGenerator extends TemplateValueGenerator<URI> {
    private static final ValueFactory FACTORY = ValueFactoryImpl.getInstance();


    public TemplateURIGenerator(String template, ValueProvider[] providers) {
        super(template, providers);
    }

    public URI generate(int rowIndex, String[] row) {
        return FACTORY.createURI(applyTemplate(rowIndex, row));
    }
}

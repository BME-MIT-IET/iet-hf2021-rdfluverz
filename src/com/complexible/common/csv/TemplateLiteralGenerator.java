package com.complexible.common.csv;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class TemplateLiteralGenerator extends TemplateValueGenerator<Literal> {
    private static final ValueFactory FACTORY = ValueFactoryImpl.getInstance();


    private final URI datatype;
    private final String lang;

    public TemplateLiteralGenerator(Literal literal, ValueProvider[] providers) {
        super(literal.getLabel(), providers);

        this.datatype = literal.getDatatype();
        this.lang = literal.getLanguage();
    }

    public Literal generate(int rowIndex, String[] row) {
        String value = applyTemplate(rowIndex, row);
        return datatype == null ? lang == null ? FACTORY.createLiteral(value) : FACTORY.createLiteral(value, lang)
                : FACTORY.createLiteral(value, datatype);
    }
}

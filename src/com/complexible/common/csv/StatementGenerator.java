package com.complexible.common.csv;

import org.openrdf.model.*;
import org.openrdf.model.impl.ValueFactoryImpl;

public class StatementGenerator {
    private static final ValueFactory FACTORY = ValueFactoryImpl.getInstance();

    private final ValueGenerator<Resource> subject;
    private final ValueGenerator<URI> predicate;
    private final ValueGenerator<Value> object;

    public StatementGenerator(ValueGenerator<Resource> s, ValueGenerator<URI> p, ValueGenerator<Value> o) {
        this.subject = s;
        this.predicate = p;
        this.object = o;
    }

    public Statement generate(int rowIndex, String[] row) {
        Resource s = subject.generate(rowIndex, row);
        URI p = predicate.generate(rowIndex, row);
        Value o = object.generate(rowIndex, row);
        return FACTORY.createStatement(s, p, o);
    }
}

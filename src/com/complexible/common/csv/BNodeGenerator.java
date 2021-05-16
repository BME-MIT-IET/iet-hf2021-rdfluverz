package com.complexible.common.csv;

import org.openrdf.model.BNode;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class BNodeGenerator implements ValueGenerator<BNode> {
    private static final ValueFactory FACTORY = ValueFactoryImpl.getInstance();

    private BNode value = null;
    private int generatedRow = -1;

    public BNode generate(int rowIndex, String[] row) {
        if (value == null || generatedRow != rowIndex) {
            value = FACTORY.createBNode();
            generatedRow = rowIndex;
        }
        return value;
    }
}

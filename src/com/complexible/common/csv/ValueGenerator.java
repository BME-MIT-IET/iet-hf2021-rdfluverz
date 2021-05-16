package com.complexible.common.csv;

import org.openrdf.model.Value;

public interface ValueGenerator<V extends Value> {
    V generate(int rowIndex, String[] row);
}

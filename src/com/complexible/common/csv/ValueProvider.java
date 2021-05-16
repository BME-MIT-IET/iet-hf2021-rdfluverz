package com.complexible.common.csv;

import java.nio.charset.StandardCharsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.nio.charset.Charset;
import java.util.UUID;

public abstract class ValueProvider {
    private static final Charset OUTPUT_CHARSET = StandardCharsets.UTF_8;

    public final String placeholder = UUID.randomUUID().toString();
    private boolean isHash;

    public String provide(int rowIndex, String[] row) {
        String value = provideValue(rowIndex, row);
        if (value != null && isHash) {
            HashCode hash = Hashing.sha1().hashString(value, OUTPUT_CHARSET);
            value = BaseEncoding.base32Hex().omitPadding().lowerCase().encode(hash.asBytes());
        }
        return value;
    }

    public void setIsHash(boolean isHash){ this.isHash = isHash; }

    protected abstract String provideValue(int rowIndex, String[] row);
}

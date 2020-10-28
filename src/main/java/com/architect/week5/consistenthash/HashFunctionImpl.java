package com.architect.week5.consistenthash;

import java.util.Objects;

public class HashFunctionImpl implements HashFunction {
    @Override
    public int hash(String object) {
        return Objects.hash(object);
    }
}

package com.architect.week5.consistenthash;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.Objects;

public class HashFunctionImpl implements HashFunction {
    com.google.common.hash.HashFunction googleHashFunc = Hashing.crc32();
    @Override
    public int hash(String object) {

        Hasher hasher = googleHashFunc.newHasher();
        hasher.putString(object, Charset.defaultCharset());
        return hasher.hash().asInt();
    }
}

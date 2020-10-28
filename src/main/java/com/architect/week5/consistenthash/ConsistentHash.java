package com.architect.week5.consistenthash;

public interface ConsistentHash {
    String getServingNode(String key);
    void addNode(String nodeName);
    void removeNode(String nodeName);
}

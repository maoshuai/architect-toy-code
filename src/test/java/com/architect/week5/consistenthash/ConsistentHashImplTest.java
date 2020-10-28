package com.architect.week5.consistenthash;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class ConsistentHashImplTest {
    private ConsistentHashImpl consistentHash = new ConsistentHashImpl(150, new HashFunctionImpl());

    @Test
    void addNode_shouldContains150Nodes_whenAddOneNode(){
        consistentHash.addNode("node1");
        TreeMap<Integer, String> nodes = consistentHash.getNodes();
        assertThat(nodes.size()).isEqualTo(150);
    }


}
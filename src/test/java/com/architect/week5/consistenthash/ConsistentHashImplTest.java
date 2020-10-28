package com.architect.week5.consistenthash;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class ConsistentHashImplTest {
    @Test
    void addNode_shouldContain150Nodes_whenAddTheFirstNode(){
        ConsistentHashImpl consistentHash = new ConsistentHashImpl(150, new HashFunctionImpl());
        consistentHash.addNode("node1");
        TreeMap<Integer, String> nodes = consistentHash.getNodes();
        assertThat(nodes.size()).isEqualTo(150);
    }

    @Test
    void getServingNode_shouldAlwaysReturnNode1_givenOneNode(){
        ConsistentHashImpl consistentHash = new ConsistentHashImpl(150, new HashFunctionImpl());
        consistentHash.addNode("node1");
        String servingNode = consistentHash.getServingNode("key");
        assertThat(servingNode).isEqualTo("node1");
    }

    @Test
    void removeNode_shouldContain0Nodes_when_removeAllNodes(){
        ConsistentHashImpl consistentHash = new ConsistentHashImpl(150, new HashFunctionImpl());
        consistentHash.addNode("node1");
        consistentHash.addNode("node2");
        consistentHash.removeNode("node1");
        consistentHash.removeNode("node2");
        assertThat(consistentHash.getNodes().size()).isEqualTo(0);
    }

}
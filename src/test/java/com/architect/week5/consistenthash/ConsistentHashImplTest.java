package com.architect.week5.consistenthash;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

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
    void getServingNode_shouldReturnRightNode(){
        HashFunctionImpl hashFunction = mock(HashFunctionImpl.class);
//        when(hashFunction.hash("key1")).thenReturn(100);
//        when(hashFunction.hash("key2")).thenReturn(200);
//        when(hashFunction.hash("key3")).thenReturn(300);
//        when(hashFunction.hash("key4")).thenReturn(400);
//
//
//        when(hashFunction.hash("node1")).thenReturn(1);
        when(hashFunction.hash(anyString())).thenAnswer((Answer<Integer>) invocationOnMock -> {
            String hashKey = invocationOnMock.getArgument(0);

            // mock key
            if(hashKey.startsWith("key")){
                return Integer.parseInt(hashKey.substring(3));
            }

            // mock for node1
            if(hashKey.startsWith("node1")){
                return 10001;
            }
            // 1, 101, 201,...,14901
            if(hashKey.startsWith("10001")){
                int seq = Integer.parseInt(hashKey.substring(5));
                return seq * 100 + 1;
            }


            // mock for node2
            if(hashKey.startsWith("node2")){
                return 10002;
            }
            // 50, 150, 250,...,14950
            if(hashKey.startsWith("10002")){
                int seq = Integer.parseInt(hashKey.substring(5));
                return seq * 100 + 50;
            }
            return 0;
        });




        ConsistentHashImpl consistentHash = new ConsistentHashImpl(150, hashFunction);
        consistentHash.addNode("node1");
        consistentHash.addNode("node2");

        System.out.println(consistentHash.getNodes());

        assertThat(consistentHash.getServingNode("key0")).isEqualTo("node1");
        assertThat(consistentHash.getServingNode("key1")).isEqualTo("node2");
        assertThat(consistentHash.getServingNode("key25")).isEqualTo("node2");
        assertThat(consistentHash.getServingNode("key149")).isEqualTo("node2");
        assertThat(consistentHash.getServingNode("key150")).isEqualTo("node1");
        assertThat(consistentHash.getServingNode("key14950")).isEqualTo("node1");
    }


    @Test
    void getServingNode_shouldThrowException_whenNoNodesAdded(){
        ConsistentHashImpl consistentHash = new ConsistentHashImpl(150, new HashFunctionImpl());
        assertThatThrownBy(()->consistentHash.getServingNode("key")).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void constructor_shouldThrowException_whenSettingTooSmallVirtualNodeSize(){
        assertThatThrownBy(()->new ConsistentHashImpl(1, new HashFunctionImpl())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void removeNode_shouldContain0Nodes_whenRemoveAllNodes(){
        ConsistentHashImpl consistentHash = new ConsistentHashImpl(150, new HashFunctionImpl());
        consistentHash.addNode("node1");
        consistentHash.addNode("node2");
        consistentHash.removeNode("node1");
        consistentHash.removeNode("node2");
        assertThat(consistentHash.getNodes().size()).isEqualTo(0);
    }

}
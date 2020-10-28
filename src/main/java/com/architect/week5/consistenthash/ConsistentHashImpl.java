package com.architect.week5.consistenthash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConsistentHashImpl implements ConsistentHash {

    public static final int MIN_VIRTUAL_NODE_SIZE = 1;

    private int virtualNodeSize;
    private HashFunction hashFunction;

    public ConsistentHashImpl(int virtualNodeSize, HashFunction hashFunction){
        this.hashFunction = hashFunction;
        if(virtualNodeSize <= MIN_VIRTUAL_NODE_SIZE){
            throw new IllegalArgumentException("virtual node size should be at least: " + MIN_VIRTUAL_NODE_SIZE);
        }
        this.virtualNodeSize = virtualNodeSize;
    }


    /**
     * store all nodes available
     */
    private TreeMap<Integer, String> nodes = new TreeMap<>();

    public TreeMap<Integer, String> getNodes() {
        return nodes;
    }

    @Override
    public String getServingNode(String key) {
        int keyHash = this.hashFunction.hash(key);

        for(Map.Entry<Integer, String> entry: nodes.entrySet()){
            if(keyHash<=entry.getKey()){
                return entry.getValue();
            }
        }

        // otherwise choose the first node
        for(Map.Entry<Integer, String> entry: nodes.entrySet()){
           return entry.getValue();
        }

        throw new IllegalStateException("No serving node");

    }

    @Override
    public void addNode(String nodeName) {
        List<String> virtualNodes = createVirtualNodes(nodeName);
        // each virtual node equals to nodeName
        for(String virtualNodeName : virtualNodes){
            int hash = this.hashFunction.hash(virtualNodeName);
            nodes.put(hash, nodeName);
        }
    }

    private List<String> createVirtualNodes(String nodeName){
        List<String> virtualNodes = new ArrayList<>(virtualNodeSize);
        for (int i = 0; i <virtualNodeSize; i++) {
            String virtualNodeName = Integer.toString(this.hashFunction.hash(nodeName) )+ i;
            virtualNodes.add(virtualNodeName);
        }
        return virtualNodes;
    }

    @Override
    public void removeNode(String nodeName) {
        List<String> virtualNodes = createVirtualNodes(nodeName);
        // each virtual node equals to nodeName
        for(String virtualNodeName : virtualNodes){
            int hash = this.hashFunction.hash(virtualNodeName);
            nodes.remove(hash, nodeName);
        }
    }
}

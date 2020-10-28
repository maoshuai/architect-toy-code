package com.architect.week5.consistenthash;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class ConsistentHashImplStdTest {

    /**
     * compute stand deviation of consistent hash for different virtual node size
     */
    @Test
    void compareStandDeviationForConsistentHash(){
        int sampleSize = 1000_000;
        int [] virtualNodeSize = {10, 100, 150, 200, 300, 500};
        for (int i = 0; i < virtualNodeSize.length; i++) {
            int size = virtualNodeSize[i];
            System.out.println(size + ": " + getConsistentHashStd(size, sampleSize));
        }




    }

    private double getConsistentHashStd(int virtualNodeSize, int sampleSize){
        // generate key-values to test
        KeyValueGenerator generator = new KeyValueGenerator();
        Map<String, String> keyValues = generator.generate(sampleSize);

        // create a consistent hash with 10 nodes
        ConsistentHash consistentHash = new ConsistentHashImpl(virtualNodeSize, new HashFunctionImpl());

        Map<String, Integer> loadByNode = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            consistentHash.addNode("node" + i);
        }

        for(Map.Entry<String,String> entry: keyValues.entrySet()){
            String servingNode = consistentHash.getServingNode(entry.getKey());
            loadByNode.computeIfPresent(servingNode, (k, oldValue) -> ++oldValue);
            loadByNode.computeIfAbsent(servingNode, k -> 1);
        }


        DescriptiveStatistics stats = new DescriptiveStatistics( loadByNode.values().stream().mapToDouble(Double::valueOf).toArray());

        return stats.getStandardDeviation();
    }

    public class KeyValueGenerator{
        public Map<String, String> generate(int size){
            Map<String, String> keyValues = new HashMap<>(size);

            for(int i = 0;i<size;i++){
//                int suffix = ThreadLocalRandom.current().nextInt();
                keyValues.put("key" + i, "value" + i);
            }
            return keyValues;
        }

    }
}
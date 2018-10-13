/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class FrequencyTable {
    private String message;

    /**
     * constructor
     * @param message message to be used
     */
    public FrequencyTable(String message) {
        this.message = message;
    }
    
    /**
     * counts appearances of chars
     * @return them in set of symfreq
     */
    public Set<SymbolFrequency> createTable(){
        NavigableSet <SymbolFrequency> ret = new TreeSet<>();
        Map <Character, Integer> occurences = new HashMap<>();
        for (Character ch : message.toCharArray()) {
            if(occurences.containsKey(ch)){
                occurences.put(ch, occurences.get(ch)+1);
            }else{
                occurences.put(ch, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : occurences.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            ret.add(new SymbolFrequency(key, value));
        }         
        return ret;
    }
    
    
}

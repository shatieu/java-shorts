/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class CollectionConverter {
    /**
     * makes nodes out of symbolfreq
     * @param charSet symfreq
     * @return Set of nodes
     */
    public static NavigableSet<TreeNode> charSetToLeafNodeSet(Set<SymbolFrequency> charSet){
        NavigableSet<TreeNode> ret = new TreeSet<>();
        for (SymbolFrequency symFreq : charSet) {
            LeafTreeNode ad = new LeafTreeNode(symFreq);
            ret.add(ad);
        }
        return ret;
    }
    
    /**
     * 
     * @param nodeStringMap map of chars
     * @return encoded strings of chars 
     */
    public static Map<Character, String> nodeMapToEncodingMap(Map<TreeNode, String> nodeStringMap){
        Map<Character, String> ret = new TreeMap<>();
        for (Map.Entry<TreeNode, String> entry : nodeStringMap.entrySet()) {
            TreeNode key = entry.getKey();
            String value = entry.getValue();
            ret.put(key.getCharacter(), value);
        }
        return ret;
    }
}

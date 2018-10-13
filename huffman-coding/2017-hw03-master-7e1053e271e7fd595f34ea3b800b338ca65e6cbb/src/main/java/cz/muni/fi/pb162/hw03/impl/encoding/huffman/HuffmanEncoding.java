/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl.encoding.huffman;
import cz.muni.fi.pb162.hw03.Encoding;
import cz.muni.fi.pb162.hw03.HuffmanAlgorithm;
import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
/**
 *
 * @author Ondrej Urbanovsky
 */
public class HuffmanEncoding implements Encoding, HuffmanAlgorithm{
    private FrequencyTable freqTable;
    private TreeNode root;
    private Map<TreeNode, String> codeTree = new TreeMap<>();

    /**
     *constructor 
     * creates map of the enc tree
     * @param freqTable table of frequencies
     */
    public HuffmanEncoding(FrequencyTable freqTable) {
        this.freqTable = freqTable;
        root = frequencyTableToTree(freqTable.createTable());
        createCodeTree(codeTree, root, "");
    }

    @Override
    public String getEncodingString(char encodingChar) {
        return CollectionConverter.nodeMapToEncodingMap(codeTree).get(encodingChar);
    }

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public TreeNode frequencyTableToTree(Set<SymbolFrequency> characterFrequencies) {
        NavigableSet <TreeNode> forest = CollectionConverter.charSetToLeafNodeSet(characterFrequencies);
        if (forest.size() > 1){
            //for (int i = 0; i <= forest.size()-1; i++)
            while(forest.size()>1){
                forest.add(new InnerTreeNode(forest.pollFirst(), forest.pollFirst()));
            }
        }
        return forest.first();
    }

    @Override
    public void createCodeTree(Map<TreeNode, String> map, TreeNode node, String encodingString) {
        if(node == null){
            return;
        }
        if(node.isLeaf()){
            map.put(node, encodingString);
        } else{
        createCodeTree(map, node.getLeftChild(), encodingString + "0");
        createCodeTree(map, node.getRightChild(), encodingString + "1");
        }
    }
    
}

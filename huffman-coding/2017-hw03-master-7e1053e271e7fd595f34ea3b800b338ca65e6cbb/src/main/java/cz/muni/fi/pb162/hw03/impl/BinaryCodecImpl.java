/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl;
import cz.muni.fi.pb162.hw03.BinaryCodec;
import cz.muni.fi.pb162.hw03.Encoding;
import cz.muni.fi.pb162.hw03.TreeNode;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class BinaryCodecImpl implements BinaryCodec{
    private Encoding enc;

    /**
     * constructor
     * @param enc encoding
     */
    public BinaryCodecImpl(Encoding enc) {
        this.enc = enc;
    }
    
    

    @Override
    public String encode(String originalMessage) {
        StringBuilder builder = new StringBuilder();
        String ret = "";
        for (char character : originalMessage.toCharArray()) {
            builder.append(enc.getEncodingString(character));
            //ret += enc.getEncodingString(character);
        }
        return builder.toString();
    }

    @Override
    public String decode(String binaryMessage) {
        TreeNode root = enc.getRoot();
        TreeNode actual = enc.getRoot();
        //String copyMessage = binaryMessage; 
        StringBuilder builder = new StringBuilder();
        //String ret = "";
        int i = 0;
        for (char ch : binaryMessage.toCharArray()) {
            if(actual.isLeaf()){
                builder.append(actual.getCharacter());
                actual = enc.getRoot();
                continue;
            }
            if (ch == '0'){
                actual = actual.getLeftChild();
            } else{
                actual = actual.getRightChild();
            }
            if(actual.isLeaf()){
                builder.append(actual.getCharacter());
                actual = enc.getRoot();
            }
            
        }
        return builder.toString();
    }
    
}

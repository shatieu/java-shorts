/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class LeafTreeNode extends AbstractTreeNode
        implements cz.muni.fi.pb162.hw03.TreeNode{

    /**
     * constructor
     * @param symFreq symbol frequency
     */
    public LeafTreeNode(SymbolFrequency symFreq) {
        super(symFreq);
    }

    @Override
    public String toString() {
        return "Leaf " + super.getSymFreq().toString();
    }
    
    

    
}

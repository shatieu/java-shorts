/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.TreeNode;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class InnerTreeNode extends AbstractTreeNode 
        implements cz.muni.fi.pb162.hw03.TreeNode{

    /**
     * constructor
     * @param left tree
     * @param right tree 
     */
    public InnerTreeNode(TreeNode left, TreeNode right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return super.getLeftChild().getCharacter().toString() + "-(" +
                super.getFrequency() + ")-" + 
                super.getRightChild().getCharacter().toString();
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.TreeNode;
import java.util.Objects;

/**
 *
 * @author Ondrej Urbanovsky
 */
public abstract class AbstractTreeNode implements TreeNode, Comparable<TreeNode>{
    private SymbolFrequency symFreq;
    private TreeNode left;
    private TreeNode right;

    /**
     * constructor for leafnode
     * @param symFreq symbol frequency to be stored
     */
    public AbstractTreeNode(SymbolFrequency symFreq) {
        this.symFreq = symFreq;
        left = null;
        right = null;
    }

    /**
     * constructor for innernode
     * @param left tree
     * @param right tree
     */
    public AbstractTreeNode(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        symFreq = new SymbolFrequency(EMPTY_CHAR, this.left.getFrequency()+
                this.right.getFrequency());
    }
    
    /**
     * 
     * @return symfreq of leafnode
     */
    public SymbolFrequency getSymFreq () {
        return symFreq;
    }

    @Override
    public int getFrequency() {
        return this.symFreq.getFrequency();
    }

    @Override
    public Character getCharacter() {
        return this.symFreq.getCharacter();
    }

    @Override
    public cz.muni.fi.pb162.hw03.TreeNode getLeftChild() {
        return left;
    }

    @Override
    public cz.muni.fi.pb162.hw03.TreeNode getRightChild() {
        return right;
    }

    @Override
    public int compareTo(cz.muni.fi.pb162.hw03.TreeNode o) {
        if(this.getFrequency() < o.getFrequency()){
            return -1;
        }
        if(this.getFrequency() > o.getFrequency()){
            return 1;
        }
        if (!o.isLeaf() && this.isLeaf()){
            return 1;
        }
        if (o.isLeaf() && !this.isLeaf()){
            return -1;
        }
        if (o.isLeaf() && this.isLeaf()){
            return this.getCharacter().compareTo(o.getCharacter());
            
        }
        if (o.getLeftChild().equals(this.getLeftChild())){
            return this.getRightChild().compareTo(o.getRightChild());
        }
        return this.getLeftChild().compareTo(o.getLeftChild());
    
    }
     @Override
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hashCode(this.symFreq);
        if(!this.isLeaf()){
        hash = 37 * hash + Objects.hashCode(this.left);
        hash = 37 * hash + Objects.hashCode(this.right);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractTreeNode other = (AbstractTreeNode) obj;
        if (!Objects.equals(this.symFreq, other.symFreq)) {
            return false;
        }
        if (!Objects.equals(this.left, other.left)) {
            return false;
        }
        if (!Objects.equals(this.right, other.right)) {
            return false;
        }
        return true;
    }

    
}

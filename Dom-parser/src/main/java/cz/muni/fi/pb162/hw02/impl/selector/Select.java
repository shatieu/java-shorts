/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw02.impl.selector;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ondrej Urbanovsky
 */
public abstract class Select implements Selector{
    private String name;
    /**
     * constrs
     * @param name name
     */
    public Select(String name) {
        this.name = name;
    }
    /**
     * gets name
     * @return name
     */
    public String getName() {
        return name;
    }
    //TODO 
    /**
     * filters by name
     * @param elements el
     * @return changed el
     */
    public Set<Element> apply(Set<Element> elements) {
        Set <Element> elems = new HashSet<>();
        if (elements == null){
            throw new NullPointerException();
        }
        for (Element x : elements) {
            if (x.getName().equals(this.getName())){
                elems.add(x);
            }
            
        }
        return elems;
    }
}

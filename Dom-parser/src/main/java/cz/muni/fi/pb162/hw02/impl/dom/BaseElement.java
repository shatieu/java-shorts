/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw02.impl.dom;

import cz.muni.fi.pb162.hw02.Attribute;
import cz.muni.fi.pb162.hw02.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class BaseElement implements Element{
    private List <Element> childElems = new ArrayList<>();
    private Set <Attribute> attributes = new HashSet<>();
    private String name;
    
    /**
     * constr
     * @param nameIn name 
     */
    public BaseElement(String nameIn) {
        if (nameIn == null){
            throw new IllegalArgumentException();
        }
        name = nameIn; 
    }

    /**
     * constr
     * @param name name
     * @param attributes attributes
     * @param childElements elems
     */
    public BaseElement(String name, Set<Attribute> attributes,
            List<Element> childElements) {
        if (attributes == null || childElements == null || name == null){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.attributes = attributes;
        childElems = childElements;
    }
    

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean addAttribute(Attribute attribute) {
        if (attribute == null){
            throw new IllegalArgumentException();
        }
        if(attributes.contains(attribute)){
            return false;
        }else{
            attributes.add(attribute);
            return true;
        }
    }

    @Override
    public Attribute findAttribute(String name) {
        if (name == null){
            return null;
        }
        for (Attribute attr : attributes) {
            if (attr.getName().equals(name)){
                return attr;
            }
        }
        return null;
    }

    @Override
    public boolean deleteAttribute(String name) {
        if (name == null){
            return false;
        }
        return attributes.remove(findAttribute(name));
    }

    @Override
    public Set<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void appendChildElement(Element element) {
        if (element == null){
            throw new IllegalArgumentException();
        }
        childElems.add(element);
    }

    @Override
    public boolean deleteChildElement(Element element) {
        if (element == null){
            return false;
        }
        for (Element elem : childElems) {
            if(elem.equals(element)){
                childElems.remove(element);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Element> getChildElements() {
        return childElems;
    }

    @Override
    public Set<String> getAllClasses() {
        if (findAttribute("class") == null){
            Set<String> ret = new HashSet<>();
            return ret;
        }
        return parseClass(findAttribute("class").getValue());
    }
    /**
     * parses class atr
     * @param value val
     * @return set string of classes
     */
    public Set<String> parseClass(String value){
        Set<String> ret = new HashSet<>();
        String [] splitted= value.split("\\s+");
        ret.addAll(Arrays.asList(splitted));
        ret.remove("");
        return ret;
    }

    @Override
    public boolean containsClass(String clazz) {
        if (clazz == null){
            return false;
        }
        if(getAllClasses() == null){
            return false;
        }
        return getAllClasses().contains(clazz);
    }

    @Override
    public Set<Element> getAllDescendants() {
        Set<Element> ret = new HashSet<>();
        ret.addAll(childElems);
        for (Element x : childElems) {
            ret.addAll(x.getAllDescendants());            
        }
        return ret;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw02.impl.dom;
import cz.muni.fi.pb162.hw02.Attribute;
import java.util.Objects;


/**
 *
 * @author Ondrej Urbanovsky
 */
public class BaseAttribute implements Attribute{
    private String name = null;
    private String value = null;
    /**
     * constr
     * @param nameIn name
     */
    public BaseAttribute(String nameIn) {
        if (nameIn == null){
            throw new IllegalArgumentException();
        }
        name = nameIn;
    }
    /**
     * consttr
     * @param nameIn name
     * @param valueIn value
     */
    public BaseAttribute(String nameIn, String valueIn) {
        this(nameIn);
        value = valueIn;        
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.name);
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
        final BaseAttribute other = (BaseAttribute) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (value == null){
            return name;
        }
        return name + "=\"" + value + "\"";
    }
    
    
    
    
}

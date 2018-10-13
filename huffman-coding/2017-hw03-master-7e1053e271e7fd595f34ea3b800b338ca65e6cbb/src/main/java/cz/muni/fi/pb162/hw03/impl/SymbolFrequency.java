/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl;

import java.util.Objects;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class SymbolFrequency implements Comparable<SymbolFrequency>{
    private Character character;
    private int frequency;
    
    /**
     * constructor for symbol frequency
     * @param characterIn char
     * @param countIn n of appearances
     */
    public SymbolFrequency(Character characterIn, int countIn) {
        character = characterIn;
        frequency = countIn;
    }

    /**
     * 
     * @return character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * 
     * @return freq of char
     */
    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return frequency + "x'"+character+"'";
    }

    @Override
    public int compareTo(SymbolFrequency o) {
        if (this.frequency < o.frequency){
            return -1;
        }
        if (this.frequency == o.frequency){
            return this.character.compareTo(o.character);
        }
        return 1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.character);
        hash = 29 * hash + this.frequency;
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
        final SymbolFrequency other = (SymbolFrequency) obj;
        if (this.frequency != other.frequency) {
            return false;
        }
        if (!Objects.equals(this.character, other.character)) {
            return false;
        }
        return true;
    }
    
    
    
    
}

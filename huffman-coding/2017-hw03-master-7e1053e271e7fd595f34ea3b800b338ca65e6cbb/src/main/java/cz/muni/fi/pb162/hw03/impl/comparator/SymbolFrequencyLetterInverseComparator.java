/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw03.impl.comparator;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import java.util.Comparator;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class SymbolFrequencyLetterInverseComparator implements Comparator<SymbolFrequency>{

    @Override
    public int compare(SymbolFrequency o1, SymbolFrequency o2) {
        if (o1.getFrequency() > o2.getFrequency()){
            return 1;
        }
        if (o1.getFrequency() == o2.getFrequency()){
            return o2.getCharacter().compareTo(o1.getCharacter());
        }
        if (o1.equals(o2)){
            return 0;
        }
        return -1;
    }
    
}

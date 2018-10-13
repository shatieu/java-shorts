/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw02.impl.parser;

import cz.muni.fi.pb162.hw02.QueryParser;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.Utils;
import cz.muni.fi.pb162.hw02.impl.selector.ClassSelector;
import cz.muni.fi.pb162.hw02.impl.selector.DescendantSelector;
import cz.muni.fi.pb162.hw02.impl.selector.ElementSelector;
import cz.muni.fi.pb162.hw02.impl.selector.IdSelector;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class QueryParserImpl implements QueryParser{
    private String query;
    /**
     * constre
     * @param query string of queries
     */
    public QueryParserImpl(String query) {
        if (query == null){
            throw new IllegalArgumentException();
        }
        this.query = query.trim();
    }
    /**
     * takes care of invalid chars
     * @throws InvalidQueryException 
     */
    private void invalidChar() throws InvalidQueryException{
        String check = query.replaceAll("\\s+","");
        for (char x : check.toCharArray()) {
            if(!Utils.isNameChar(x) && !(x == '#') && !(x == '.')){
                throw new InvalidQueryException();
            }
        }
    }
    /*private void invalidChar (String next) throws InvalidQueryException{
        char [] arr = next.toCharArray();
        for (int i = 0; i < arr.length; i++){
            if(!Utils.isNameChar(arr[i])){
                throw new InvalidQueryException();
            }
        }
        

    }*/
    /**
     * next name changes query
     * @return next name with ident
     */
    private String nextName (){
        char [] arr = query.toCharArray();
        if (Character.isWhitespace(arr[0])){ // descendants
                query = query.replaceFirst("\\s+","");
                return " ";
            }
        String ret = "";
        ret += arr[0];
        int i = 1;
        
        while (i < arr.length -1 && Utils.isNameChar(arr[i])) {            
            ret += arr[i];
            i++;            
        }
        if(arr.length > i && Utils.isNameChar(arr[i])){
            ret+= arr[i];
        }
        query = query.replaceFirst(ret,"");
        return ret;
    }
    
    @Override
    public Selector getNextSelector() throws InvalidQueryException {
        invalidChar();
        if (hasNextSelector()){
            String nextName = nextName();
            if(nextName.startsWith(".")){
                return new ClassSelector(nextName.substring(1));
            }
            if(nextName.startsWith("#")){
                return new IdSelector(nextName.substring(1));
            }
            if(nextName == " "){
                return new DescendantSelector();
            }
            return new ElementSelector(nextName);
        }else {
            return null;
        }
    }

    @Override
    public boolean hasNextSelector() {
        return query.length() > 0;
    }
    
}

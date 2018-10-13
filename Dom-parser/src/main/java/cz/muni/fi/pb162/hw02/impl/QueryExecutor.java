/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Element;
import cz.muni.fi.pb162.hw02.Selector;
import cz.muni.fi.pb162.hw02.impl.parser.InvalidQueryException;
import cz.muni.fi.pb162.hw02.impl.parser.QueryParserImpl;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ondrej Urbanovsky
 */
public final class QueryExecutor {
    
    /**
     * private constr
     */
    private QueryExecutor(){
    }
    
    /**
     * goes through query and finds fitting elements
     * executes
     * @param query q
     * @param rootElement r
     * @return elements set of fitting elements
     * @throws InvalidQueryException 
     */
    public static Set<Element> execute(String query, Element rootElement)
            throws InvalidQueryException{
        Set <Element> rt;
        Set <Element> elements = new HashSet<>();
        elements.add(rootElement);
        elements.addAll(rootElement.getAllDescendants());
        if(query.equals("")){
            return elements;
        }else{
        QueryParserImpl parser = new QueryParserImpl(query);
        Selector selector = parser.getNextSelector();
        rt = selector.apply(elements);
        while (parser.hasNextSelector()){
            selector = parser.getNextSelector();
            rt = (selector.apply(rt));
        }
        
        return rt;
        }
    }
    /*
    private static Set<Element> addAllelems (Element el){
        Set <Element> elements = new HashSet<>();
        for (Element x : el.) {
            
        }
    }*/
}

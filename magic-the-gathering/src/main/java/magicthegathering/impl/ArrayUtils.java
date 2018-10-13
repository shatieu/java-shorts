/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicthegathering.impl;

import magicthegathering.game.Card;
import magicthegathering.game.CreatureCard;
import magicthegathering.game.LandCard;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class ArrayUtils {
    
       
    /**
     * filters Lands
     * @param cards arr of cards
     * @return array of Lands
     */
    public static LandCard [] filterLands(Card [] cards){
        int count = 0; 
        for (Card card : cards) {
            if (card instanceof LandCard) {
                count ++;   
            }
        }
        LandCard [] lands = new LandCard[count];
        int actualIndex = 0;
        for (Card card : cards) {
            if (card instanceof LandCard) {
                lands[actualIndex] = (LandCard)card;
                actualIndex ++;   
            }
        }
        return lands;
    }
    
    /**
     * filters Creatures
     * @param cards arr of cards
     * @return array of Creatures
     */
    public static CreatureCard[] filterCreatures(Card[] cards){
        int count = 0; 
        for (Card card : cards) {
            if (card instanceof CreatureCard) {
                count ++;   
            }
        }
        CreatureCard [] creatures = new CreatureCard[count];
        int actualIndex = 0;
        for (Card card : cards) {
            if (card instanceof CreatureCard) {
                creatures[actualIndex] = (CreatureCard)card;
                actualIndex ++;   
            }
        }
        return creatures;
    }
    
    /**
     * filters hand
     * @param cards arr of cards
     * @return array of crds in hand
     */
    public static Card[] filterInHand(Card[] cards){
        int count = 0; 
        for (Card card : cards) {
            if (!card.isOnTable()) {
                count ++;   
            }
        }
        Card [] hand = new Card[count];
        int actualIndex = 0;
        for (Card card : cards) {
            if (!card.isOnTable()) {
                hand[actualIndex] = card;
                actualIndex ++;   
            }
        }
        return hand;
    }
    
    
    /**
     * filters tabble
     * @param cards arr of cards
     * @return array of crds on table
     */
    public static Card[] filterOnTable(Card[] cards){
        int count = 0; 
        for (Card card : cards) {
            if (card.isOnTable()) {
                count ++;   
            }
        }
        Card [] table = new Card[count];
        int actualIndex = 0;
        for (Card card : cards) {
            if (card.isOnTable()) {
                table[actualIndex] = card;
                actualIndex ++;   
            }
        }
        return table;
    }
    /**
     * tells if there are duplicates except null
     * @param cards to filter
     * @return true when there are duplicates
     */    
    public static boolean hasDuplicatesExceptNull(Card[] cards){
        for (int i = 0; (i + 1) < cards.length; i++) {
            for (int j = 1 + i ; j < cards.length; j++) {
                if (cards[i] != null && cards[i].equals(cards[j])){
                    return true;
                }  
            } 
        }
        return false;
    }
    /**
     * searches for a card
     * @param searchedCard card
     * @param cards card []
     * @return true upon card found
     */
    public static boolean containsCard(Card searchedCard, Card[] cards){
        int i = findCardIndex(searchedCard, cards);
        return  i >= 0;
    }
    /**
     * searches for index of a card
     * @param searchedCard card
     * @param cards card []
     * @return index of card -1 when not found
     */
    public static int findCardIndex(Card searchedCard, Card[] cards){
        if (searchedCard == null){
            for (int i = 0; i < cards.length; i++) {
            if (searchedCard == (cards[i])){
                return i;
                } 
            }
        } 
        
        for (int i = 0; i < cards.length; i++) {
            if (searchedCard.equals(cards[i])){
            return i;
            }         
        }
        
        return -1;
    }
    
    /**
     * removes desired card
     * @param unwantedCard card to be deleted
     * @param cards cards []
     * @return Card[] without unwanted
     */
    public static Card[] removeCard(Card unwantedCard, Card[] cards){
        int unwantedIndex = findCardIndex(unwantedCard, cards);
        if (unwantedIndex < 0){
            //possible mistake
            return cards.clone();
        } else{
            Card [] deleted = new Card [cards.length -1];
            int actIndex = 0;
            for (int i = 0; i < cards.length; i++) {
                if (i == unwantedIndex) {
                      continue;                  
                } else{
                    deleted[actIndex] = cards[i];
                    actIndex ++;
                }
                
            }
            return deleted;
        }
        
    }
   
    
    
}

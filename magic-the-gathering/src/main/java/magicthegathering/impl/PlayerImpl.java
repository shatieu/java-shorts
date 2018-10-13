/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicthegathering.impl;
import magicthegathering.game.Card;
import magicthegathering.game.CreatureCard;
import magicthegathering.game.LandCard;
import magicthegathering.game.ManaType;
import magicthegathering.game.Player;
import static magicthegathering.impl.ArrayUtils.containsCard;
import static magicthegathering.impl.ArrayUtils.filterCreatures;
import static magicthegathering.impl.ArrayUtils.filterInHand;
import static magicthegathering.impl.ArrayUtils.filterLands;
import static magicthegathering.impl.ArrayUtils.filterOnTable;
import static magicthegathering.impl.ArrayUtils.removeCard;





/**
 *
 * @author Ondrej Urbanovsky
 */
public class PlayerImpl implements Player {
    
    private String name;
    private int lives;
    private Card [] playerCards;
    
    /**
     * player constructor
     * @param nameIn name
     */
    public PlayerImpl(String nameIn){
        name = nameIn;
        lives = INIT_LIVES;
    }

    @Override
    public String toString() {
        return  name + "(" + lives + ")";
    }
    

    /**
     * Get player's name.
     *
     * @return players name
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Get player's number of lives.
     *
     * @return life
     */
    @Override
    public int getLife(){
        return lives;
    }

    /**
     * Subtract player's lives.
     *
     * @param lives lives
     */
    
    @Override
    public void subtractLives(int lives){
        this.lives -= lives; 
    }

    /**
     * Returns true, if player's lives are below or equal to 0.
     *
     * @return if a player is dead
     */
    @Override
    public boolean isDead(){
        return this.lives <= 0;
    }

    /**
     * Initial cards put into player's hand. Cards from the table are removed.
     * Input parameter is copied.
     *
     * @param cards to be put into player's hand
     */
    @Override
    public void initCards(Card[] cards){
        playerCards = cards.clone();
    }

    /**
     * Get player's cards in hand.
     *
     * @return player's cards in hand
     */
    @Override
    public Card[] getCardsInHand(){
        return filterInHand(playerCards);
    }

    /**
     * Get player's cards on the table.
     *
     * @return player's cards on the table
     */
    
    @Override
    public Card[] getCardsOnTable(){
        return filterOnTable(playerCards);
    }

    /**
     * Get player's lands on the table.
     *
     * @return player's lands on the table
     */
    @Override
    public LandCard[] getLandsOnTable(){
        return filterLands(filterOnTable(playerCards));
    }
    /**
     * Get player's creatures on the table.
     *
     * @return player's creatures on the table
     */
    @Override
    public CreatureCard[] getCreaturesOnTable(){
        return filterCreatures(filterOnTable(playerCards));
    }

    /**
     * Get player's lands in hand.
     *
     * @return player's lands in hand
     */
    @Override
    public LandCard[] getLandsInHand(){
        return filterLands(filterInHand(playerCards));
    }

    /**
     * Get player's creatures in hand.
     *
     * @return player's creatures in hand
     */
    @Override
    public CreatureCard[] getCreaturesInHand(){
        return filterCreatures(filterInHand(playerCards));
    }

    /**
     * Untap all cards on the table.
     */
    @Override
    public void untapAllCards(){
        for (Card card : filterOnTable(playerCards)) {
            card.untap();
        }
    }

    /**
     * Unset summoning sickness of all creatures.
     */
    @Override
    public void prepareAllCreatures(){
        for (CreatureCard card : filterCreatures(playerCards)) {
            card.unsetSummoningSickness();
        }
    }

    /**
     * Put a land from hand on table.
     *
     * @param landCard land to be put on the table
     * @return true if succeeded, false if land is not in player's hand or already on the table
     */
    @Override
    public boolean putLandOnTable(LandCard landCard){
        if (!containsCard(landCard, filterInHand(playerCards)) || 
                containsCard(landCard, filterOnTable(playerCards))){
            return false;
        }
        landCard.putOnTable();
        return true;
    }

    /**
     * Put a creature from hand to table. Taps lands for mana needed to summon a creature.
     *
     * @param creatureCard creature card to be put on the table
     * @return true if succeeded, false if creature is not in player's hand,
     * already on the table, or player does not have enough mana
     */
    @Override
    public boolean putCreatureOnTable(CreatureCard creatureCard){
        if (!hasManaForCreature(creatureCard) 
                || containsCard(creatureCard, filterOnTable(playerCards))
                || !containsCard(creatureCard, filterInHand(playerCards))){
            return false;
        } else{
            tapManaForCreature(creatureCard);
            creatureCard.putOnTable();
            creatureCard.setSummoningSickness();
            return true;
        }
        
    }

    /**
     * Checks whether user has enough mana to summon the input creature.
     *
     * @param creature creature to be checked
     * @return true if has enough mana, false otherwise
     */

     @Override
    public boolean hasManaForCreature(CreatureCard creature){
        int [] manaAvailable = calculateUntappedLands();
        int j= 0;
        for (int i : manaAvailable) {
            if (i < creature.getSpecialCost(ManaType.values()[j])){
                return false;
            }
            j++;
        }
        return true;
    }

    /**
     * Calculates how many untapped lands has player on the table.
     *
     * @return array of integers, where every number represents how many untapped lands the player has in the following
     * (ordinal) order: WHITE, RED, GREEN, BLUE, BLACK. You can use {@link ManaType#ordinal()} method.
     */
    @Override
    public int[] calculateUntappedLands(){
        int [] manaArr = new int []{0,0,0,0,0};
        for (LandCard card : filterLands(filterOnTable(playerCards))) {
            if (!card.isTapped()){
                manaArr[card.getManaType().ordinal()] ++;
            }
        }
        return manaArr; 
    }

    /**
     * Taps the lands needed for summoning a creature.
     *
     * @param creature creature which price needs to be paid
     */
    @Override
    public void tapManaForCreature(CreatureCard creature){
        for (int i = 0; i < 5; i++) {
            for(int j=0; j < creature.getSpecialCost(ManaType.values()[i]);j++){      
                for (LandCard card : filterLands(filterOnTable(playerCards))) {
                    if (!card.isTapped() && 
                            card.getManaType() == ManaType.values()[i]){
                        card.tap();
                    }
                }
            }
            
        }
    }

    /**
     * Removes creature from the game.
     *
     * @param creature creature to be removed.
     */
    @Override
    public void destroyCreature(CreatureCard creature){
        playerCards =removeCard(creature, playerCards);
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicthegathering.impl;

import magicthegathering.game.Card;
import magicthegathering.game.CreatureCard;
import magicthegathering.game.Game;
import static magicthegathering.game.Generator.generateCards;
import magicthegathering.game.Player;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class GameImpl implements Game {
    
    private Player st;
    private Player nd;
    private Player actual;
    
    /**
     * constructor  
     * @param st first player
     * @param nd second player
     */
    public GameImpl(Player st, Player nd){
        this.st = st;
        this.nd = nd;
        actual = this.st;
    }

    /**
     * Generates player's cards.
     */
    @Override
    public void initGame(){
        st.initCards(generateCards());
        nd.initCards(generateCards());
    }

    /**
     * Set (pick) next player.
     */
    @Override
    public void changePlayer(){
            if (actual == st){
                actual = nd;
                return;
            }
            actual = st;
    }

    /**
     * Prepare player for turn: untap all the cards and unset summoning sickness on creatures.
     */
    @Override
    public void prepareCurrentPlayerForTurn(){
        for (Card card : actual.getCardsOnTable()) {
            card.untap();
            if (card instanceof CreatureCard){
                ((CreatureCard) card).unsetSummoningSickness();
            }
        }
    }

    /**
     * Get current player.
     *
     * @return current player
     */
    @Override
    public Player getCurrentPlayer(){
        return actual;
    }

    /**
     * Get the other player.
     *
     * @return other player
     */
    @Override
    public Player getSecondPlayer(){
        if (actual == st){
            return nd;
        }
        return st;
    }

    /**
     * Perform attack from current player to the second player.
     * All attacking creatures are tapped.
     *
     * @param creatures creatures which are going to attack
     */
    @Override
    public void performAttack(CreatureCard[] creatures){
        if (isCreaturesAttackValid(creatures)){        
            for (CreatureCard creature : creatures) {
                creature.tap();
            }
        }
    }

    /**
     * Checks whether creatures which are going to attack do not break any rules.
     *
     * @param attackingCreatures array of attacking creatures
     * @return true if all creatures are able to attack, false if any creature has summoning sickness,
     * is tapped, does not belong to the current player or the array contains duplicate creatures
     */
    @Override
    public boolean isCreaturesAttackValid(CreatureCard[] attackingCreatures){
        for (CreatureCard attCreature : attackingCreatures) {
            if(ArrayUtils.containsCard(attCreature,actual.getCardsOnTable())
                    && !attCreature.hasSummoningSickness()
                    && !attCreature.isTapped()
                    && !ArrayUtils.hasDuplicatesExceptNull(attackingCreatures)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether blocking does not break any rules.
     * Null elements represent that creature is not going to be blocked.
     *
     * @param attackingCreatures array of attacking creatures
     * @param blockingCreatures array of blocking creatures
     * @return false if arrays have different length, contains duplicates elements excluding null,
     * or attacking/blocking creatures do not belong to the current/second player
     * or blocking creature is tapped
     */
    public boolean isCreaturesBlockValid(CreatureCard[] attackingCreatures, CreatureCard[] blockingCreatures){
        if (attackingCreatures.length != blockingCreatures.length
                || ArrayUtils.hasDuplicatesExceptNull(blockingCreatures)
                || ArrayUtils.hasDuplicatesExceptNull(attackingCreatures)){
            return false;
        }
        
        for (CreatureCard block : blockingCreatures) {
            if (block == null){
            continue;
            }
            if(block.isTapped() || 
                    !ArrayUtils.containsCard(block, getSecondPlayer().getCardsOnTable())){
                return false;
            }
        }
        for (CreatureCard attack : attackingCreatures) {
            if(!ArrayUtils.containsCard(attack, getCurrentPlayer().getCardsOnTable())){
            return false;
            }            
        }
        return true;
    }

    /**
     * Evaluates the block, damage to the creatures, their removal, damage to the player.
     *
     * @param attackingCreatures array of attacking creatures
     * @param blockingCreatures array of blocking creatures
     */
    public void performBlockAndDamage(CreatureCard[] attackingCreatures, CreatureCard[] blockingCreatures){
        if(isCreaturesAttackValid(attackingCreatures) &&
                isCreaturesBlockValid(attackingCreatures, blockingCreatures)){
            performAttack(attackingCreatures);
            for (int i = 0; i < attackingCreatures.length; i++) {
                whoDies(attackingCreatures[i], actual, blockingCreatures[i], getSecondPlayer());
            }
        }
    }
    /**
     * determines which creature should die and kills it or gives damage to player
     * @param attack attacking creatures
     * @param atPlayer attacking player
     * @param block blocking creatures
     * @param blPlayer blocking player
     */
    private void whoDies (CreatureCard attack, Player atPlayer, CreatureCard block, Player blPlayer){
        if (block == null){
            blPlayer.subtractLives(attack.getPower());
            return;
        }
        int atP = attack.getPower();
        int atT = attack.getToughness();
        int blP = block.getPower();
        int blT = block.getToughness();
        if (atP >= blT && blP >= atT){
            blPlayer.destroyCreature(block);
            atPlayer.destroyCreature(attack);
            return;
        } 
        if (atP >= blT){
            blPlayer.destroyCreature(block);
            return;
        }
        if (blP >= atT){
            atPlayer.destroyCreature(attack);
        }
    }
}

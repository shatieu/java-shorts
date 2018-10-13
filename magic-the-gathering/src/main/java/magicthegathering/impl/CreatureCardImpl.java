/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicthegathering.impl;

import magicthegathering.game.AbstractCard;
import magicthegathering.game.CreatureCard;
import magicthegathering.game.ManaType;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class CreatureCardImpl extends AbstractCard implements CreatureCard {
    private final String name;
    private final ManaType [] mana;
    private final int toughness;
    private final int power;
    private boolean tapped = super.isTapped();
    private boolean summonigSickness;
    private boolean onTable = super.isOnTable();
    
    /**
     * 
     * @param nameIn String name
     * @param manaIn mana enum []
     * @param toughnessIn int toughness
     * @param powerIn  int powerIn
     */
    public CreatureCardImpl (String nameIn, ManaType [] manaIn,int powerIn, int toughnessIn
    ){
        name = nameIn;
        mana = manaIn;
        toughness = toughnessIn;
        power = powerIn;
        tapped = super.isTapped();
        onTable = super.isOnTable();
    }
    /**
     * 
     * @return name cost power / toughness (TAPPED/can attack)
     */
    @Override
    public String toString(){
        String tapString = "";
        if (tapped || super.isTapped()){
         tapString = " TAPPED";
        }
        String canAttack = " can attack";
        if (summonigSickness){
            canAttack = "";
        }
        String manaString = manaToString();
        return String.format("%s %s %d / %d%s%s", name, manaString, power,
                toughness, canAttack, tapString);
    }
    
    private String manaToString(){
        String rt = "[";
        for (ManaType mana : mana) {
            rt = rt + mana.toString() + ", ";
        }
        rt = rt.substring(0, rt.length() - 2) + "]";
        return rt;
    }
   
    
    /**
     * Get the sum of costs for all the mana types.
     *
     * @return total cost
     */
    @Override
    public int getTotalCost(){
        return mana.length;
    }

    /**
     * Get cost for specific mana type.
     * If the creature does not use a certain mana type, 0 is returned.
     *
     * @param mana mana
     * @return cost for given mana type
     */
    @Override
    public int getSpecialCost(ManaType mana){
        int count = 0;
        for (ManaType mana1 : this.mana) {
            if (mana == mana1) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get name of the creature.
     *
     * @return name
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Get power of the creature. This is the value user for attack.
     *
     * @return power
     */
    @Override
    public int getPower(){
        return power;
    }

    /**
     * Get toughness of the creature. This value is used for defense.
     *
     * @return toughness
     */
    @Override
    public int getToughness(){
        return toughness;                
    }

    /**
     * Check, whether the creature has summoning sickness.
     *
     * @return summoning sickness
     */
    @Override
    public boolean hasSummoningSickness(){
        return summonigSickness;
    }

    /**
     * Set summoning sickness. This value is set after the creature is first put on table.
     */
    @Override
    public void setSummoningSickness(){
        summonigSickness = true;
    }

    /**
     * Unset summoning sickness so that the creature can attack now.
     */
    @Override
    public void unsetSummoningSickness(){
        summonigSickness = false;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicthegathering.impl;

import magicthegathering.game.AbstractCard;
import magicthegathering.game.LandCard;
import magicthegathering.game.LandCardType;
import magicthegathering.game.ManaType;

/**
 *
 * @author Ondrej Urbanovsky
 */
public class LandCardImpl extends AbstractCard implements LandCard{
    private ManaType mana;
    private LandCardType type;
    private boolean tapped = super.isTapped();
    private boolean onTable = super.isOnTable();
    
    
    /**
     * constructor for landcard
     * @param typeIn landcard type
     */
    public LandCardImpl (LandCardType typeIn){
        type = typeIn;
        tapped = super.isTapped();
        onTable = super.isOnTable();
        switch(type){
            case PLAINS:
                mana = ManaType.WHITE;
                break;
            case MOUNTAIN:
                mana = ManaType.RED;
                break;
            case SWAMP:
                mana = ManaType.BLACK;
                break;
            case FOREST:
                mana = ManaType.GREEN;
                break;
            case ISLAND:
                mana = ManaType.BLUE;
                break;
            default:
                mana = null;               
        }
                
    }

    public ManaType getManaType() {
        return mana;
    }

    public LandCardType getLandType() {
        return type;
    }
      
    @Override
    public String toString(){
        return String.format("Land %s, %s",type.toString().toLowerCase(),
                mana.toString());
    }
}

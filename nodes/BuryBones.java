package scripts.SPXCowKiller.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import scripts.SPXCowKiller.api.Node;


/**
 * Created by Sphiinx on 12/21/2015.
 */
public class BuryBones extends Node {

    private final int BONES_ID = 526;

    @Override
    public void execute() {
        RSItem[] bones = Inventory.find(BONES_ID);
        if (bones.length > 0) {
            Clicking.click("Bury", bones[0]);
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Player.getAnimation() == -1;
                }
            }, General.random(750, 1000));
        }
    }

    @Override
    public boolean validate() {
        return !Player.getRSPlayer().isInCombat() && Inventory.getCount(BONES_ID) > 0;
    }

}
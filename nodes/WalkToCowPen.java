package scripts.SPXCowKiller.nodes;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import scripts.SPXCowKiller.Variables;
import scripts.SPXCowKiller.API.Framework.Node;

/**
 * Created by Sphiinx on 12/21/2015.
 */
public class WalkToCowPen extends Node {

    public WalkToCowPen(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        WebWalking.walkTo(vars.currentLocation.getRandomTile());
    }

    @Override
    public String toString() {
        return "Walking to cows...";
    }

    @Override
    public boolean validate() {
        if (vars.foodName.length() > 0) {
            return Inventory.getCount(vars.foodName) > 0 && !vars.currentLocation.contains(Player.getPosition());
        }
       return !vars.currentLocation.contains(Player.getPosition());
    }

}


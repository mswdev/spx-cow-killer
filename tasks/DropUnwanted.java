package scripts.SPXCowKiller.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import scripts.SPXCowKiller.data.Vars;
import scripts.SPXCowKiller.framework.Task;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 12/22/2015.
 */
public class DropUnwanted implements Task {

    private String[] unwantedItems;

    public DropUnwanted() {
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("Raw beef");
        if (Vars.get().buryBones) {
            itemList.add("Cowhide");
        }
        if (Vars.get().bankHides) {
            itemList.add("Bones");
        }

        unwantedItems = new String[itemList.size()];
        unwantedItems = itemList.toArray(unwantedItems);
    }

    @Override
    public void execute() {
        if (Inventory.drop(unwantedItems) > 0) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return Inventory.getCount(unwantedItems) < 1;
                }
            }, General.random(750, 1000));
        }
    }

    @Override
    public String toString(){
        return "Dropping items...";
    }


    public boolean validate() {
        return Inventory.getCount(unwantedItems) > 0 &&
                !Player.getRSPlayer().isInCombat();
    }

}


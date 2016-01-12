package scripts.SPXCowKiller.nodes;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.SPXCowKiller.API.Framework.Node;
import scripts.SPXCowKiller.Variables;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class WithdrawItems extends Node{

    public WithdrawItems(Variables v) {
        super(v);
    }

    @Override
    public void execute() {
        if (Banking.isInBank()) {
            if (Banking.isBankScreenOpen()) {
                withdrawItems();
            } else {
                openBank();
            }
        } else {
            walkToBank();
        }
    }

    public void withdrawItems() {
        if (Banking.find(vars.foodName).length > 0) {
            if (Banking.withdraw(0, vars.foodName)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return Inventory.getCount(vars.foodName) > 0;
                    }
                }, General.random(750, 1000));
            }
        } else {
            General.println("We could not find the food requested...");
            General.println("Stopping Script...");
            vars.stopScript = true;
        }
    }

    public void openBank() {
        if (Banking.openBank()) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return Banking.isBankScreenOpen();
                }
            }, General.random(750, 1000));
        }
    }

    private void walkToBank() {
        if (WebWalking.walkToBank()) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return Banking.isInBank();
                }
            }, General.random(750, 1000));
        }
    }

    @Override
    public String toString() {
        return "Withdrawing food...";
    }

    @Override
    public boolean validate() {
        return vars.foodName.length() > 0 && Inventory.getCount(vars.foodName) <= 0;
    }

}

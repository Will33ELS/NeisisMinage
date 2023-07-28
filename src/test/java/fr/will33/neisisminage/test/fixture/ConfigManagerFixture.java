package fr.will33.neisisminage.test.fixture;

import fr.will33.neisisminage.api.IConfigManager;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ConfigManagerFixture {

    public static IConfigManager getDefault(){
        return new IConfigManager() {
            @Override
            public Integer getXpRequiredPerLevel() {
                return 100;
            }

            @Override
            public String getMessagesLevelMiner() {
                return null;
            }

            @Override
            public String getMessagesMinepointAddHelp() {
                return null;
            }

            @Override
            public String getMessagesMinepointAdd() {
                return null;
            }

            @Override
            public String getMessagesMinepointHelp() {
                return null;
            }

            @Override
            public String getMessagesMinepoint() {
                return null;
            }

            @Override
            public String getMessagesMinepointTakeHelp() {
                return null;
            }

            @Override
            public String getMessagesMinepointTake() {
                return null;
            }

            @Override
            public String getMessagesMinepointResetHelp() {
                return null;
            }

            @Override
            public String getMessagesMinepointReset() {
                return null;
            }

            @Override
            public String getMessagesMinexpHelp() {
                return null;
            }

            @Override
            public String getMessagesMinexp() {
                return null;
            }

            @Override
            public String getMessagesMinexpResetHelp() {
                return null;
            }

            @Override
            public String getMessagesMinexpReset() {
                return null;
            }

            @Override
            public String getMessagesMinexpAddHelp() {
                return null;
            }

            @Override
            public String getMessagesMinexpAdd() {
                return null;
            }

            @Override
            public String getMessagesMinexpRemoveHelp() {
                return null;
            }

            @Override
            public String getMessagesMinexpRemove() {
                return null;
            }

            @Override
            public String getPlayerEnoughPoint() {
                return null;
            }

            @Override
            public String getPlayerEnoughXP() {
                return null;
            }

            @Override
            public String getNoPermission() {
                return null;
            }

            @Override
            public String getNotInt() {
                return null;
            }

            @Override
            public String getLower0() {
                return null;
            }

            @Override
            public String getMessagesPoints() {
                return null;
            }

            @Override
            public Boolean isSystemEnabled() {
                return null;
            }

            @Override
            public ItemStack getPickaxe() {
                return null;
            }

            @Override
            public Map<Integer, Map<Enchantment, Integer>> getUpgradePickaxe() {
                return null;
            }
        };
    }

}

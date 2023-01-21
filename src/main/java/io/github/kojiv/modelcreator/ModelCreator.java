package io.github.kojiv.modelcreator;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ModelCreator extends JavaPlugin {

    @Getter private static ModelCreator plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        Bukkit.getOnlinePlayers().forEach(ModelCreator::setupPlayer);
    }

    public static void setupPlayer(Player p) {
        PClass.registerPlayer(new PClass(p));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}

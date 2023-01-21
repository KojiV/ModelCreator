package io.github.kojiv.modelcreator;

import koji.developerkit.utils.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ModelCreator.setupPlayer(e.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PClass pS = PClass.getPlayer(p);
        if(pS.getCurrentSession() != null) {
            ItemStack item = e.getItem();
            NBTItem nbtItem = new NBTItem(item);
            if(nbtItem.hasKey("ModelCreator")) {
                switch (nbtItem.getString("ModelCreator")) {

                }
            }
        }
    }
}

package io.github.kojiv.modelcreator.session;

import io.github.kojiv.modelcreator.PClass;
import koji.developerkit.KBase;
import koji.developerkit.utils.ItemBuilder;
import koji.developerkit.utils.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ModelSession extends KBase {
    Player p;
    PClass creator;
    ItemStack[] originalInventory;
    ItemStack[] armor;

    Location center;

    public ModelSession(PClass creator) {
        this.p = creator.getPlayer();
        this.creator = creator;
        this.originalInventory = p.getInventory().getContents();
        this.armor = p.getInventory().getArmorContents();
        center = KBase.getBlockLocationCentered(p.getLocation());

        p.getInventory().clear();

        p.getInventory().setContents(getHotbar());

        creator.setCurrentSession(this);
    }

    public ItemStack[] getHotbar() {
        ItemStack[] newInventory = new ItemStack[originalInventory.length];

        newInventory[0] = new ItemBuilder(XMaterial.ARMOR_STAND)
                .setName(ChatColor.GREEN + "Create New Stand")
                .setLore(
                        ChatColor.GRAY + "Click to create a new armor",
                        ChatColor.GRAY + "stand to edit.",
                        "",
                        ChatColor.YELLOW + "Click to spawn!"
                ).setString("ModelCreator", "createNew")
                .build();

        newInventory[1] = new ItemBuilder(XMaterial.ANVIL)
                .setName(ChatColor.GREEN + "Edit Equipment")
                .setLore(
                        ChatColor.GRAY + "Click to edit the selected",
                        ChatColor.GRAY + "armor stand's equipment.",
                        "",
                        ChatColor.YELLOW + "Click to edit!"
                ).setString("ModelCreator", "editEquip")
                .build();

        newInventory[2] = new ItemBuilder(XMaterial.DIAMOND_HORSE_ARMOR)
                .setName(ChatColor.GREEN + "Change Selected Stand")
                .setLore(
                        ChatColor.GRAY + "Click to change the selected",
                        ChatColor.GRAY + "armor stand.",
                        "",
                        ChatColor.YELLOW + "Click to change!"
                ).setString("ModelCreator", "changeSelected")
                .build();

        newInventory[4] = getChangePositionItems(creator.getMoveAmount(), creator.getMovementType(), true);

        newInventory[5] = new ItemBuilder(XMaterial.OAK_SIGN)
                .setName(ChatColor.GREEN + "Change Movement Type")
                .setLore(
                        ChatColor.GRAY + "Click to change movement type",
                        ChatColor.GRAY + "when editing armor stands.",
                        "",
                        ChatColor.GRAY + "Current type: ",
                        ChatColor.GREEN + creator.getMovementType().getName() + " Movement",
                        "",
                        ChatColor.YELLOW + "Click to change!"
                ).setString("ModelCreator", "movementType")
                .build();

        newInventory[6] = new ItemBuilder(XMaterial.QUARTZ)
                .setName(ChatColor.GREEN + "Change Tuning Amount")
                .setLore(
                        ChatColor.GRAY + "Click to change the amount an",
                        ChatColor.GRAY + "armor stand moves each time.",
                        "",
                        ChatColor.GRAY + "Current tuning: ",
                        ChatColor.GREEN + creator.getTuneAmount().getName(),
                        "",
                        ChatColor.AQUA + "Right-Click to input amount!",
                        ChatColor.YELLOW + "Click to change tuning type!"
                ).setString("ModelCreator", "changeTuning")
                .build();

        newInventory[7] = getChangePositionItems(creator.getMoveAmount(), creator.getMovementType(), false);

        newInventory[8] = new ItemBuilder(XMaterial.RED_TERRACOTTA)
                .setName(ChatColor.RED + "Stop Editing")
                .setLore(
                        ChatColor.GRAY + "Click to stop editing the",
                        ChatColor.GRAY + "current model.",
                        "",
                        ChatColor.GRAY + "You will be given the option",
                        ChatColor.GRAY + "to save your changes or to",
                        ChatColor.GRAY + "discard them.",
                        "",
                        ChatColor.YELLOW + "Click to stop!"
                ).setString("ModelCreator", "stopEdit")
                .build();
        return newInventory;
    }

    public void onDisable() {
        p.getInventory().setContents(originalInventory);
        p.getInventory().setArmorContents(armor);
        creator.setCurrentSession(null);
    }

    public ItemStack getChangePositionItems(double moveAmount, MovementType type, boolean leftUp) {
        switch (type) {
            default:
                return new ItemBuilder(XMaterial.ARROW)
                        .setName(ChatColor.GREEN + "Move " + (leftUp ? "Left" : "Right) ") + format(moveAmount))
                        .setLore(
                                ChatColor.GRAY + "This will move the selected",
                                ChatColor.GRAY + "armor stand to the " + (leftUp ? "left" : "right) ") + " of the",
                                ChatColor.GRAY + "cardinal direction you're",
                                ChatColor.GRAY + "facing " + format(moveAmount) + ".",
                                "",
                                ChatColor.GRAY + "This won't move two cord",
                                ChatColor.GRAY + "planes at a time. The armor",
                                ChatColor.GRAY + "stand's position will never on",
                                ChatColor.GRAY + "both the X and Z",
                                "",
                                ChatColor.YELLOW + "Click to move!"
                        ).setString("ModelCreator", leftUp ? "move1" : "move2")
                        .build();
            case Y:
                return new ItemBuilder(XMaterial.ARROW)
                        .setName(ChatColor.GREEN + "Move " + (leftUp ? "Up" : "Down) ") + format(moveAmount))
                        .setLore(
                                ChatColor.GRAY + "This will move the selected",
                                ChatColor.GRAY + "armor stand " + (leftUp ? "up" : "down) ") + " from where it",
                                ChatColor.GRAY + "is " + format(moveAmount) + ".",
                                "",
                                ChatColor.YELLOW + "Click to move!"
                        ).setString("ModelCreator", leftUp ? "move1" : "move2")
                        .build();
            case YAW:
                return new ItemBuilder(XMaterial.ARROW)
                        .setName(ChatColor.GREEN + "Move Yaw " + format(moveAmount))
                        .setLore(
                                ChatColor.GRAY + "This will move the selected",
                                ChatColor.GRAY + "armor stand's head yaw",
                                ChatColor.GRAY + "position " + format(moveAmount) + ".",
                                "",
                                ChatColor.YELLOW + "Click to rotate!"
                        ).setString("ModelCreator", leftUp ? "move1" : "move2")
                        .build();
            case PITCH:
                return new ItemBuilder(XMaterial.ARROW)
                        .setName(ChatColor.GREEN + "Move Pitch " + format(moveAmount))
                        .setLore(
                                ChatColor.GRAY + "This will move the selected",
                                ChatColor.GRAY + "armor stand's head pitch",
                                ChatColor.GRAY + "position " + format(moveAmount) + ".",
                                "",
                                ChatColor.YELLOW + "Click to rotate!"
                        ).setString("ModelCreator", leftUp ? "move1" : "move2")
                        .build();
        }
    }

    public String format(double num) {
        return ("+" + num).replace(".0", "").replace("+-", "");
    }
}

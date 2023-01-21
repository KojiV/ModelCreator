package io.github.kojiv.modelcreator;

import io.github.kojiv.modelcreator.session.ModelSession;
import io.github.kojiv.modelcreator.session.MovementType;
import io.github.kojiv.modelcreator.session.TuneAmount;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PClass {
    private static final HashMap<UUID, PClass> players = new HashMap<>();

    public static boolean hasPlayer(Player p) {
        return players.containsKey(p.getUniqueId());
    }

    public static PClass getPlayer(Player p) {
        return players.get(p.getUniqueId());
    }

    public static void registerPlayer(PClass pS) {
        players.put(pS.getPlayer().getUniqueId(), pS);
    }

    @Getter private final Player player;

    public PClass(Player player) {
        this.player = player;
    }

    @Getter @Setter private MovementType movementType = MovementType.XZ;
    @Getter @Setter private TuneAmount tuneAmount = TuneAmount.FINE;
    @Getter @Setter private double moveAmount = tuneAmount.getMovementAmount();
    @Getter @Setter private ModelSession currentSession = null;

    public void setNextMovementType() {
        switch (movementType) {
            case XZ:
                movementType = MovementType.Y;
                break;
            case Y:
                movementType = MovementType.YAW;
                break;
            case YAW:
                movementType = MovementType.PITCH;
                break;
            case PITCH:
                movementType = MovementType.XZ;
                break;
        }
        if(currentSession != null) {
            ItemStack[] hotbar = currentSession.getHotbar();
            for(int i = 4; i <= 7; i++) {
                player.getInventory().setItem(i, hotbar[i]);
            }
        }
    }

    public void setNextTuneAmount() {
        switch (tuneAmount) {
            case FINE:
                tuneAmount = TuneAmount.SMALLER;
                break;
            case SMALLER:
                tuneAmount = TuneAmount.SMALL;
                break;
            case SMALL:
                tuneAmount = TuneAmount.MEDIUM;
                break;
            case MEDIUM:
                tuneAmount = TuneAmount.LARGE;
                break;
            case LARGE:
                tuneAmount = TuneAmount.EXTREME;
                break;
            case EXTREME:
                tuneAmount = TuneAmount.CUSTOM;
                break;
            case CUSTOM:
                tuneAmount = TuneAmount.FINE;
                break;
        }
        moveAmount = tuneAmount.getMovementAmount();
    }
}

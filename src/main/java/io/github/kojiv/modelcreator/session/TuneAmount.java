package io.github.kojiv.modelcreator.session;

import koji.developerkit.utils.KStatic;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TuneAmount {
    FINE(0.01),
    SMALLER(0.1),
    SMALL(0.25),
    MEDIUM(0.5),
    LARGE(0.75),
    EXTREME(1),
    CUSTOM(0.0);

    @Getter private final double movementAmount;

    public String getName() {
        return KStatic.capitalize(name().toLowerCase());
    }
}

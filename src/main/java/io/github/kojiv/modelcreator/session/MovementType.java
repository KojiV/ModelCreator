package io.github.kojiv.modelcreator.session;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MovementType {
    XZ("X & Z Axis"),
    Y("Y Axis"),
    YAW("Yaw Rotation"),
    PITCH("Pitch Rotation");

    @Getter
    private final String name;
}

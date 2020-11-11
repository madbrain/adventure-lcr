package com.github.madbrain.adventure.objects;

import com.github.madbrain.adventure.ActionKey;
import com.github.madbrain.adventure.Behaviour;
import com.github.madbrain.adventure.GameObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameObjectImpl implements GameObject {
    protected Map<ActionKey, List<Behaviour>> behaviours = new HashMap<>();

    @Override
    public List<Behaviour> getBehaviour(ActionKey key) {
        return behaviours.getOrDefault(key, Collections.emptyList());
    }
}

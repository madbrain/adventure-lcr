package com.github.madbrain.adventure;

import java.util.List;

public interface GameObject {
    String getName();

    String getExamination();

    List<Behaviour> getBehaviour(ActionKey key);

}

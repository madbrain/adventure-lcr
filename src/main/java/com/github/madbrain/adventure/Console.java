package com.github.madbrain.adventure;

import java.io.BufferedReader;

public interface Console {
    void print(String text);

    BufferedReader getInput();
}

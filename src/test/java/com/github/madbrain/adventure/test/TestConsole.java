package com.github.madbrain.adventure.test;

import com.github.madbrain.adventure.Console;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestConsole implements Console {

    public List<String> lines = new ArrayList<>();
    private BufferedReader input;

    public TestConsole(String input) {
        this.input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
    }

    @Override
    public void print(String text) {
        Collections.addAll(lines, text.split("\n"));
    }

    @Override
    public BufferedReader getInput() {
        return input;
    }
}

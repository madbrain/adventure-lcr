package com.github.madbrain.adventure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AdventureLauncher {

    public static void main(String[] args) {
        new Adventure(new RealConsole()).main();
    }

    private static class RealConsole implements Console {

        @Override
        public void print(String text) {
            System.out.print(text);
        }

        @Override
        public BufferedReader getInput() {
            return new BufferedReader( new InputStreamReader( System.in ) );
        }
    }
}

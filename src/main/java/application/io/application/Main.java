package io.application;

import org.gradle.sample.list.LinkedList;

import static io.application.MessageUtils.getMessage;
import static org.gradle.sample.utilities.StringUtils.join;
import static org.gradle.sample.utilities.StringUtils.split;

public class Main {
    public static void main(String[] args) {
        LinkedList tokens;
        tokens = split(getMessage());
        System.out.println(join(tokens));
    }
}

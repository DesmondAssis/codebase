
package com.desmond.codebase.hello;

import java.util.Collections;
import java.util.LinkedList;
import java.util.HashSet;

public class Zy1 {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList();
        System.out.println("֮之前");
        list.add("Jonh Alex");
        list.add("Miller Scott");
        list.add("Jonh Anna");
        list.add("Jonhson Jack");
        list.add("Hunter Jeff");
        list.add("Williams Serena");
        list.add("Williams Uenus");

        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            System.out.println(name);
        }

        System.out.println("\n包含Jonh:");
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            if(name.startsWith("Jonh")) {
                System.out.println(name);
            }
        }

    }
}


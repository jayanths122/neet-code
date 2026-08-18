package com.neetcode.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solutions {
    public static boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int sReminder = s.charAt(i) - 'a';
            int tReminder = t.charAt(i) - 'a';
            System.out.println("sReminder: " + sReminder + "; tReminder: " + tReminder);
            counter[sReminder] = counter[sReminder] + 1;
            counter[tReminder] = counter[tReminder] - 1;
        }
        for (int i : counter) {
            if (counter[i] != 0) return false;
        }
        return true;
    }   

    public static boolean isAnagram2(String s, String t) {
        HashMap<String, Integer> sCounter = new HashMap<>();
        HashMap<String, Integer> tCounter = new HashMap<>();
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            String character = String.valueOf(s.charAt(i));
            if (sCounter.containsKey(character)) {
                sCounter.put(character, sCounter.get(character) + 1);
            } else {
                sCounter.put(character, 1);
            }
            String tCharacter = String.valueOf(t.charAt(i));
            if (tCounter.containsKey(tCharacter)) {
                tCounter.put(tCharacter, tCounter.get(tCharacter) + 1);
            } else {
                tCounter.put(tCharacter, 1);
            }
        }
        for (String character: sCounter.keySet()) {
            if (!sCounter.get(character).equals(tCounter.get(character))) {
                return false;
            }
        }
        return true;   
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagrams = new HashMap<>(); 
        for (String str: strs) {
            char[] frequencyList = new char[26];
            for (int i = 0; i < str.length(); i++) {
                frequencyList[str.charAt(i) - 'a'] += 1;
            }
            String frequencyString = String.valueOf(frequencyList);
            if (!anagrams.containsKey(frequencyString)) anagrams.put(frequencyString, new ArrayList<>());
            anagrams.get(frequencyString).add(str);
        }
        return anagrams.values().stream().toList();
    } 
}

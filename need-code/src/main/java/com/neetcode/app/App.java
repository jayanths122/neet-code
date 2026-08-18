package com.neetcode.app;

// import com.neetcode.strings.Solutions;
import com.neetcode.arrays.Solutions;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // System.out.println("IsValidAnagram? : " + Solutions.isAnagram1("rat", "tar"));
        // String[] anagrams = new String[]{
        //     "cat",
        //     "tac",
        //     "act",
        //     "sweet",
        //     "twees",
        //     "secure",
        //     "rescue"
        // };
        // List<List<String>> ags = Solutions.groupAnagrams(anagrams);
        // for (List<String> list: ags) {
        //     System.out.println(list.toString());
        // } 
        int[] nums = new int[]{1, 2, 3, 4};
        int[] product = Solutions.productExceptSelfO_n_Memory(nums);
        for(int num: product) {
            System.out.print(num + ", ");
        }
    }
}

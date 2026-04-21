package dsa.dp;


// Online Java Compiler
// Use this editor to write, compile and run your Java code online


/***
 * Given an encoded string, return its decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 * Example 1:

 Input: s = "3[a]2[bc]"
 Output: "aaabcbc"
 Example 2:

 Input: s = "3[a2[c]]"
 Output: "accaccacc"
 Example 3:

 Input: s = "2[abc]3[cd]ef"
 Output: "abcabccdcdcdef"
 */
 import java.util.*;
class StringEncoding {
    public static void main(String[] args) {
        String  input =  "3[a2[c]]";
        Stack<Character> stack  = new Stack<>();
        for (char strinChar : input.toCharArray()) {
            if(strinChar != ']'){
                stack.push(strinChar);
            } else{


                StringBuilder stringContent = new StringBuilder();
                while(stack.peek() != '[') {
                    stringContent.insert(0, stack.pop());
                }
                stack.pop();
                StringBuilder freqCount = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    freqCount.insert(0, stack.pop());
                }
                System.out.println(freqCount);
                int times = Integer.parseInt(freqCount.toString());
                String occurrences = stringContent.toString().repeat(times);
                for(char c : occurrences.toCharArray()){
                    stack.push(c);
                }
                System.out.println(occurrences);
            }
        }
        StringBuilder output = new StringBuilder();
        while(!stack.isEmpty()){
            output.insert(0,stack.pop());
        }
        System.out.println(output.toString());




    }
}
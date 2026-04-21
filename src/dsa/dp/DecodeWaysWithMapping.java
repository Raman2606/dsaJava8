package dsa.dp;

import java.util.*;

public class DecodeWaysWithMapping {

    public static void main(String[] args) {

        // ✅ Driver Code
        String s = "123";

        // ✅ Explicit Mapping: 1 → A, ..., 26 → Z
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            map.put(i, (char) ('A' + i - 1));
        }
        System.out.println(map);
        int result = numDecodings(s, map);

        System.out.println("Total Decoding Ways: " + result);
    }

    /**
     * Main function to count decoding ways
     */
    public static int numDecodings(String s, Map<Integer, Character> map) {

        if (s == null || s.length() == 0) return 0;

        int n = s.length();

        // Space optimized DP variables
        int next1 = 1; // dp[i+1]
        int next2 = 0; // dp[i+2]

        for (int i = n - 1; i >= 0; i--) {

            int curr = 0;

            // Convert single digit
            int oneDigit = s.charAt(i) - '0';

            // ❌ Invalid if 0 (not in mapping)
            if (!map.containsKey(oneDigit)) {
                curr = 0;
            } else {

                // ✅ Take single digit
                curr = next1;

                // ✅ Check two-digit number
                if (i + 1 < n) {
                    int twoDigit = oneDigit * 10 + (s.charAt(i + 1) - '0');

                    if (map.containsKey(twoDigit)) {
                        curr += next2;
                    }
                }
            }

            // Shift DP window
            next2 = next1;
            next1 = curr;
        }

        return next1;
    }
}
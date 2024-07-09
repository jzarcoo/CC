/**
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
Each letter in magazine can only be used once in ransomNote.

Example 1:
Input: ransomNote = "a", magazine = "b"
Output: false

Example 2:
Input: ransomNote = "aa", magazine = "ab"
Output: false

Example 3:
Input: ransomNote = "aa", magazine = "aab"
Output: true

Constraints:
    1 <= ransomNote.length, magazine.length <= 105
    ransomNote and magazine consist of lowercase English letters.
*/

import java.util.HashMap;
import java.util.Map;

class RansomNote {
    // First Attempt
    // 4ms
    // 44.72MB
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length())
            return false;
        int[] words = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            if (i < ransomNote.length()) {
                words[ransomNote.charAt(i) - 'a']++;
            }
            words[magazine.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (words[i] > 0) {
                return false;
            }
        }
        return true;
    }
}

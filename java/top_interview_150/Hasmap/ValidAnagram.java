/**
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true

Example 2:

Input: s = "rat", t = "car"
Output: false

 

Constraints:

    1 <= s.length, t.length <= 5 * 104
    s and t consist of lowercase English letters.

 

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

*/

// 4 ms
// 43.97 MB
class ValidAnagram {
  /**
  Implementation note: The sorting algorithm is a Dual-Pivot Quicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. 
  This algorithm offers O(n log(n)) performance on many data sets that cause other quicksorts to degrade to quadratic performance, 
  and is typically faster than traditional (one-pivot) Quicksort implementations.
  */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }
}

/**
// 6 ms
// 42.77 MB
class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr1[s.charAt(i) - 'a']++;
            arr2[t.charAt(i) - 'a']++;
        }
        return Arrays.equals(arr1, arr2);
    }
}
*/

/**
// 23 ms
// 42.8 MB
class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c1 = s.charAt(i);
            Integer n1 = map.putIfAbsent(c1, 1);
            if (n1 != null)
                map.put(c1, ++n1);
            Character c2 = t.charAt(i);
            Integer n2 = map.putIfAbsent(c2, -1);
            if (n2 != null)
                map.put(c2, --n2);
        }
        for (Integer n : map.values())
            if (n != 0)
                return false;
        return true;
    }
}
*/

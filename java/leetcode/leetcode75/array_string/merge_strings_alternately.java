class Solution {
    // You are given two strings word1 and word2.
    // Merge the strings by adding letters in alternating order, starting with word1.
    // If a string is longer than the other, append the additional letters onto the end of the merged string.
    // Return the merged string.
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int lengthOne = word1.length();
        int lengthTwo = word2.length();
        int longer = (lengthOne > lengthTwo) ? lengthOne : lengthTwo;
        for (int i = 0; i < longer; i++) {
            if (lengthOne > i)
                sb.append(word1.charAt(i));
            if (lengthTwo > i)
                sb.append(word2.charAt(i));
        }
        return sb.toString();
    }
}

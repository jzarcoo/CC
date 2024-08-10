/**
Given two binary strings a and b, return their sum as a binary string.

 

Example 1:

Input: a = "11", b = "1"
Output: "100"

Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

 

Constraints:

    1 <= a.length, b.length <= 104
    a and b consist only of '0' or '1' characters.
    Each string does not contain leading zeros except for the zero itself.

*/
class AddBinary {
    public String addBinary(String a, String b) {
        int carry = 0;
        int p1 = a.length() - 1;
        int p2 = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (p1 >= 0 || p2 >= 0 || carry > 0) {
            if (p1 >= 0) {
                carry += a.charAt(p1) - '0';
                p1--;
            }
            if (p2 >= 0) {
                carry += b.charAt(p2) - '0';
                p2--;
            }
            sb.insert(0, carry % 2);
            carry /= 2;
        }
        return sb.toString();
    }
}

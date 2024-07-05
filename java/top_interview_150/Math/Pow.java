/**
Topics
Companies

Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

 

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25

 

Constraints:

    -100.0 < x < 100.0
    -231 <= n <= 231-1
    n is an integer.
    Either x is not zero or n > 0.
    -104 <= xn <= 104

*/
class Pow {
    public double myPow(double x, int n) {
        if (Double.isNaN(x) || Double.isInfinite(x)) return Double.NaN;
        if (x == 0) return (n <= 0) ? Double.POSITIVE_INFINITY : 0;
        if (n == 0 || x == 1) return 1;
        if (x == -1) return (n % 2 == 0) ? 1 : -1;
        long N = n; // Integer.MIN_VALUE
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double pow = 1;
        // Exponenciación binaria. O(logn) multiplicaciones
        while (N != 0) {
            if ((N & 1) == 1)
                pow *= x;
            x *= x;
            N >>= 1;
        }
        return pow;
    }
}

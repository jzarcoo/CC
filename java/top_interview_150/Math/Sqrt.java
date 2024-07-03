/**
   Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

    For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

 

Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.

Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.

 

Constraints:

    0 <= x <= 231 - 1


*/
class Sqrt {
    public int mySqrt(int x) {    
        if (x < 2)
            return x;
	int l = 1;
	int r = x;
	int m = 0;
	while (l <= r) {
            m = l + ((r - l) >> 1);
            if (x / m == m)
		return m;
            else if (x / m < m)
		r = m - 1;
            else
		l = m + 1;
	}
        return r;
    }
}

/**
   Recursive
   
   public int mySqrt(int x) {
       return mySqrt(x, 1, x);
    }

    public int mySqrt(int x, int ini, int fin) {
        if (ini > fin)
            return fin;
        int m = ini + ((fin - ini) >> 1);
        if (x / m == m)
            return m;
        else if (x / m < m)
            return mySqrt(x, ini, fin - 1);
        else 
            return mySqrt(x, ini + 1, fin);
    }
*/

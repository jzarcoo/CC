public class NumberOfOneBits {
	public int hammingWeight(int n) {
        	int c = 0;
	        while (n != 0) {
	            if((n%2) == 1)
        	        c++;
            	    n >>= 1;
         	}
	        return c;
    	}
}
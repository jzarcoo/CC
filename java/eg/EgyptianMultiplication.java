class EgyptianMultiplication {
    public static void main(String[] args) {
	int a = Integer.parseInt(args[0]);
	int b = Integer.parseInt(args[1]);
	System.out.println(multiplication(a,b));
    }

    public static int multiplication(int a, int b) {
	int bigger = a < b ? b : a;
	int smaller = a < b ? a : b;
	int result = 0;
	while (bigger > 0) {
	    smaller <<= 1;
	    bigger >>= 1;
	    if (bigger > 0 && bigger % 2 == 1) {
		result += smaller;
	    }
	}
	return result;
    }
}

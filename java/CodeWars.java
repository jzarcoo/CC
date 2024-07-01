import java.util.Stack;

public class CodeWars {

  /**
  Write a function that takes a string of braces, and determines if the order of the braces is valid. It should return true if the string is valid, and false if it's invalid.

This Kata is similar to the Valid Parentheses Kata, but introduces new characters: brackets [], and curly braces {}. Thanks to @arnedag for the idea!

All input strings will be nonempty, and will only consist of parentheses, brackets and curly braces: ()[]{}.
What is considered Valid?

A string of braces is considered valid if all braces are matched with the correct brace.
  */
  public static boolean isValid(String braces) {
    Stack<Character> s = new Stack<>();
    for (Character c : braces.toCharArray()) {
      switch (c) {
        case '(': case '{': case '[':
          s.push(c);
          break;
      case ')':
        if(s.isEmpty() || s.pop() != '(')
          return false;
         break;
      case ']':
        if(s.isEmpty() || s.pop() != '[')
          return false;
          break;
      case '}':
        if(s.isEmpty() || s.pop() != '{')
          return false;
          break;
      default:
        break;
       }
    }
    return s.isEmpty();
  }

  /**
  Complete the solution so that the function will break up camel casing, using a space between words.
  */
  public static String camelCase(String input) {
    StringBuffer sb = new StringBuffer();
    for(Character c: input.toCharArray()) {
      if(Character.isUpperCase(c))
        sb.append(" ");
      sb.append(c);
    }
    return sb.toString();
  }

  /**
  Given an array of ones and zeroes, convert the equivalent binary value to an integer.

Eg: [0, 0, 0, 1] is treated as 0001 which is the binary representation of 1.
  */
  public static int ConvertBinaryArrayToInt(List<Integer> binary) {
      int num = 0;
      for(Integer i: binary) {
        num = (num << 1) | i;
      }
      return num;
    }

  /**
  Define a function that takes an integer argument and returns a logical value true or false depending on if the integer is a prime.

Per Wikipedia, a prime number ( or a prime ) is a natural number greater than 1 that has no positive divisors other than 1 and itself.
Requirements

    You can assume you will be given an integer input.
    You can not assume that the integer will be only positive. You may be given negative numbers as well ( or 0 ).
    NOTE on performance: There are no fancy optimizations required, but still the most trivial solutions might time out. Numbers go up to 2^31 ( or similar, depending on language ). Looping all the way up to n, or n/2, will be too slow.

*/
  public static boolean isPrime(int num) {
    if(num <= 1) return false;
    for(int i= 2 ; i<= Math.sqrt(num); i++) {
      if(num % i == 0)
        return false;
    }
    return true;
  }

  /**
  Create a function which answers the question "Are you playing banjo?".
If your name starts with the letter "R" or lower case "r", you are playing banjo!

The function takes a name as its only argument, and returns one of the following strings:
  */
   public static String areYouPlayingBanjo(String name) {
    String play = (name.startsWith("R") || name.startsWith("r")) ? "plays" : "does not play";
    return name + " " + play + " " + "banjo";
  }
}

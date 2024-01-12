## Contenidos

- [Haskell](#hs)
 	- [Funciones nativas](#nativas)
   	- [Temas](#temas)
   		- [Funciónes de Orden Superior](#f_ord_sup)
   	  		- [Folding](#folding)
   	  	- [Módulos](#modulos)
   	- [Codewars](#codeWars)
- [JavaScript](#js)
  
<a name="hs"></a>
# [Haskell](#http://aprendehaskell.es/main.html)

<a name="nativas"></a>
## Funciones nativas

```hs
-- Toma una función binaria y dos listas, y devuelve una lista que resulta de aplicar la función binaria a los elementos correspondientes de las dos listas.
-- Si una de las listas es más corta que la otra, los elementos sobrantes de la lista más larga se ignoran
zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]

-- Devuelve una lista [b] que resulta de aplicar la función a cada elemento de la lista y luego concatenar los resultados.
concatMap :: (a -> [b]) -> [a] -> [b]

 -- Se utiliza para crear una lista que contiene múltiples repeticiones de un mismo valor.
replicate :: Int -> a -> [a]

-- Tomar los primeros n elementos de una lista
take :: Int -> [a] -> [a]

-- Se utiliza para eliminar los primeros n elementos de una lista.
drop :: Int -> [a] -> [a]

-- Toman un predicado y una lista y comprueban si el predicado se satisface para algún o para todos los elementos respectivamente.
all :: Foldable t => (a -> Bool) -> t a -> Bool
any :: Foldable t => (a -> Bool) -> t a -> Bool

-- Toma una lista de booleanos y devuelve True solo si todos los elementos de la lista son True.
and :: Foldable t => t Bool -> Bool
-- Devuelve True solo si existe algún elemento True en la lista.
or :: Foldable t => t Bool -> Bool

-- ¿Es impar?
odd :: Integral a => a -> Bool
-- ¿Es par?
even :: Integral a => a -> Bool

-- Este operador se utiliza para agregar un elemento al principio de una lista
-- '' char
:

-- Este operador se utiliza para concatenar dos listas
-- "" string
++
```

<a name="temas"></a>
## Temas

<a name="f_ord_sup"></a>
### Funciónes de Orden Superior

<a name="folding"></a>
#### Folding

Existe una familia de funciones en Haskell para modelar un algoritmo que permite 

> procesar una estructura de datos para construir un valor
 
, a esta idea le decimos foldear (derivado del inglés, “to fold”) o reducir.

```hs
sum [] = 0 
sum (x:xs) = (+) x (sum xs) 

product [] = 1 
product (x:xs) = (*) x (prod xs) 

concatenar [] = []
concatenar (x:xs) = (++) x (concatenar xs)
```

Podemos generalizar el algoritmo anterior con
1. Una función binaria
2. Un valor acumulador inicial
3. Una lista

```hs
-- Asocia por la derecha
foldr :: Foldable t => (a -> b -> b) -> b -> t a -> b

-- Asocia por la izquierda
foldl :: Foldable t => (b -> a -> b) -> b -> t a -> b
```

Así,

```hs
foldr (\x y -> x + y ) 0 [1..100] --5050
foldr (\x y -> 1 + y) 0 "hola" --4
foldr (\x y -> x^2 + y) 0 [1..5] --55
foldr (:) [6..10] [1..5] --[1,2,3,4,5,6,7,8,9,10]
foldr (\x ys -> 1 : ys) [] [1..5] --[1,1,1,1,1] map (\x->1) [1..5]

foldl (\x ys -> ys : x) [] [1..5] --[5,4,3,2,1]
```

<a name="modulos"></a>
## Módulos

### Data.Char
```hs
digitToInt :: Char -> Int
```

### Data.List
```hs
-- Elimina duplicados de una lista
nub :: Eq a => [a] -> [a]
```

<a name="codeWars"></a>
## Codewars

1. **Who likes it?**

You probably know the "like" system from Facebook and other pages. People can "like" blog posts, pictures or other items. We want to create the text that should be displayed next to such an item.
Implement the function which takes an array containing the names of people that like an item. It must return the display text as shown in the examples:
```hs
[]                                -->  "no one likes this"
["Peter"]                         -->  "Peter likes this"
["Jacob", "Alex"]                 -->  "Jacob and Alex like this"
["Max", "John", "Mark"]           -->  "Max, John and Mark like this"
["Alex", "Jacob", "Mark", "Max"]  -->  "Alex, Jacob and 2 others like this"
```
Note: For 4 or more names, the number in "and 2 others" simply increases.
```hs
module Likes where

likes :: [String] -> String
-- TODO
likes [] = "no one likes this"
likes [x] = x ++ " likes this"
likes [x, y] = x ++ " and " ++ y ++ " like this"
likes [x,y,z] = x ++ ", " ++ y ++ " and " ++ z ++ " like this"
likes (x:y:rest) = x ++ ", " ++ y ++ " and " ++ show (length rest) ++ " others like this"
```

2. **Credit Card Mask**

Usually when you buy something, you're asked whether your credit card number, phone number or answer to your most secret question is still correct. However, since someone could look over your shoulder, you don't want that shown on your screen. Instead, we mask it.

Your task is to write a function maskify, which changes all but the last four characters into '#'.
```hs
module Maskify where

maskify :: String -> String
maskify str 
 | l >= 4 = replicate (l - 4) '#' ++ drop (l - 4) str
 | otherwise = str
 where l = length str

-- Recursivo
module Maskify where

maskify :: String -> String
maskify [] = []
maskify l@(x:xs) = (if (length l > 4) then '#' else x) : maskify xs
```

3. **Split Strings**

Complete the solution so that it splits the string into pairs of two characters. If the string contains an odd number of characters then it should replace the missing second character of the final pair with an underscore ('_').
```hs
solution :: String -> [String]
solution [] = []
solution [x] = [x : "_"]
solution (a:b:xs) = [a, b] : solution xs
```

4. **Create Phone Number**

Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.
Example
```hs
createPhoneNumber [1,2,3,4,5,6,7,8,9,0] -- => returns "(123) 456-7890"
```
The returned format must be correct in order to complete this challenge.
Don't forget the space after the closing parentheses!
```hs
createPhoneNumber :: [Int] -> String
createPhoneNumber (x:y:z:a:b:c:f) = "(" ++ concatMap show [x,y,z] ++ ")" ++ " " ++ concatMap show [a,b,c] ++ "-" ++ concatMap show f
```

5. **Isograms**

An isogram is a word that has no repeating letters, consecutive or non-consecutive. Implement a function that determines whether a string that contains only letters is an isogram. Assume the empty string is an isogram. Ignore letter case.
Example: (Input --> Output)
```hs
"Dermatoglyphics" --> true "aba" --> false "moOse" --> false (ignore letter case)

isIsogram "Dermatoglyphics" = true
isIsogram "moose" = false
isIsogram "aba" = false
```
```hs
module Isogram where
import Data.Char (toLower)

isIsogram :: String -> Bool
isIsogram [] = True
isIsogram (x:xs) = if (contiene x xs) then False else isIsogram xs

contiene :: Char -> [Char] -> Bool
contiene a [] = False
contiene a (x:xs) = (toLower a == toLower x) || contiene a xs
```
Random code
```hs
module Isogram where

import Data.Char
import Data.List

isIsogram :: String -> Bool
isIsogram x = y == nub y
  where y = map toLower x
```

6. **Delete occurrences of an element if it occurs more than n times**

Enough is enough!

Alice and Bob were on a holiday. Both of them took many pictures of the places they've been, and now they want to show Charlie their entire collection. However, Charlie doesn't like these sessions, since the motif usually repeats. He isn't fond of seeing the Eiffel tower 40 times.
He tells them that he will only sit for the session if they show the same motif at most N times. Luckily, Alice and Bob are able to encode the motif as a number. Can you help them to remove numbers such that their list contains each number only up to N times, without changing the order?
Task

Given a list and a number, create a new list that contains each number of list at most N times, without reordering.
For example if the input number is 2, and the input list is [1,2,3,1,2,1,2,3], you take [1,2,3,1,2], drop the next [1,2] since this would lead to 1 and 2 being in the result 3 times, and then take 3, which leads to [1,2,3,1,2,3].
With list [20,37,20,21] and number 1, the result would be [20,37,21].
```hs
module Codewars.Kata.Deletion where
import Data.List (nub)

deleteNth :: [Int] -> Int -> [Int]
deleteNth [] _ = []
deleteNth lst 1 = nub lst
deleteNth (x:xs) n = si ++ deleteNth xs n
 where
  si = if (numApariciones x xs < n) then [x] else []

numApariciones :: Int -> [Int] -> Int
numApariciones elem [] = 0
numApariciones elem (x:xs) = cabezaIgual + numApariciones elem xs
 where
  cabezaIgual = if (elem == x) then 1 else 0
```
Random code 
```hs
deleteNth :: [Int] -> Int -> [Int]
deleteNth lst n = foldl f [] lst
  where f acc x = if length (filter (==x) acc) >= n then acc else acc ++ [x]
```

7. **Take a Number And Sum Its Digits Raised To The Consecutive Powers And ....¡Eureka!!**

The number 898989 is the first integer with more than one digit that fulfills the property partially introduced in the title of this kata. What's the use of saying "Eureka"? Because this sum gives the same number: 89=81+9289 = 8^1 + 9^289=81+92

The next number in having this property is 135135135:

See this property again: 135=11+32+53135 = 1^1 + 3^2 + 5^3135=11+32+53
Task

We need a function to collect these numbers, that may receive two integers aaa, bbb that defines the range [a,b][a, b][a,b] (inclusive) and outputs a list of the sorted numbers in the range that fulfills the property described above.
Examples

Let's see some cases (input -> output):

1, 10  --> [1, 2, 3, 4, 5, 6, 7, 8, 9]
1, 100 --> [1, 2, 3, 4, 5, 6, 7, 8, 9, 89]

If there are no numbers of this kind in the range [a,b][a, b][a,b] the function should output an empty list.

90, 100 --> []

Enjoy it!!
```hs
module Codewars.G964.Sumdigpow where

sumDigPow :: Int -> Int -> [Int]
sumDigPow a b = filter esEureka [a..b]

esEureka :: Int -> Bool
esEureka n = n == sum potencias
 where 
  potencias = zipWith (^) digitos [1..]
  digitos = digitos' n

digitos' :: Int -> [Int]
digitos' 0 = []
digitos' n = digitos' (div n 10) ++ [mod n 10]
```

8. **Convert string to camel case**

Complete the method/function so that it converts dash/underscore delimited words into camel casing. The first word within the output should be capitalized only if the original word was capitalized (known as Upper Camel Case, also often referred to as Pascal case). The next words should be always capitalized.
Examples
- "the-stealth-warrior" gets converted to "theStealthWarrior"
- "The_Stealth_Warrior" gets converted to "TheStealthWarrior"
- "The_Stealth-Warrior" gets converted to "TheStealthWarrior"
```hs
module CamelCase (toCamelCase) where
import Data.Char (toUpper)

toCamelCase :: String -> String
toCamelCase [] = []
toCamelCase (x:xs)
 | (x == '_' || x == '-') = [toUpper $ head lst] ++ tail lst
 | otherwise = x : lst
 where 
  lst = toCamelCase xs
```

9. **Does my number look big in this?**

A Narcissistic Number (or Armstrong Number) is a positive number which is the sum of its own digits, each raised to the power of the number of digits in a given base. In this Kata, we will restrict ourselves to decimal (base 10).

For example, take 153 (3 digits), which is narcissistic:

    1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153

and 1652 (4 digits), which isn't:

    1^4 + 6^4 + 5^4 + 2^4 = 1 + 1296 + 625 + 16 = 1938

The Challenge:

Your code must return true or false (not 'true' and 'false') depending upon whether the given number is a Narcissistic number in base 10.

This may be True and False in your language, e.g. PHP.

Error checking for text strings or other invalid inputs is not required, only valid positive non-zero integers will be passed into the function.
```hs
module Narcissistic where

narcissistic :: Integral n => n -> Bool
narcissistic n = n == narcissistic'
 where
  lst = digitos n
  l = length lst
  narcissistic' = sum $ zipWith (^) lst [l,l..]

digitos :: Integral n => n -> [n]
digitos 0 = []
digitos n = digitos (div n 10) ++ [mod n 10]
```

10. **Convert number to reversed array of digits**

Given a random non-negative number, you have to return the digits of this number within an array in reverse order.
Example(Input => Output):
```hs
digitize :: Int -> [Int]
digitize 0 = [0]
digitize n = f n
 where
  f 0 = []
  f x = [mod x 10] ++ f (div x 10)

-- Recorre más de una vez la lista
import Data.Char (digitToInt)

digitize :: Int -> [Int]
digitize s 
 | s < 10 = [s]
 | otherwise = reverse $ map digitToInt $ show s
```

11. **Find the odd int**

Given an array of integers, find the one that appears an odd number of times.

There will always be only one integer that appears an odd number of times.
Examples

[7] should return 7, because it occurs 1 time (which is odd).
[0] should return 0, because it occurs 1 time (which is odd).
[1,1,2] should return 2, because it occurs 1 time (which is odd).
[0,1,0,1,0] should return 0, because it occurs 3 times (which is odd).
[1,2,2,3,3,3,4,3,3,3,2,2,1] should return 4, because it appears 1 time (which is odd).
```hs
findOdd :: [Int] -> Int
findOdd lst@(x:xs) = if mod l 2 == 0 then findOdd l2 else x
 where 
  l = length (filter (== x) lst)
  l2 = filter (/=x) xs
```

12. **Persistent Bugger.**

Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence, which is the number of times you must multiply the digits in num until you reach a single digit.

For example (Input --> Output):

39 --> 3 (because 3*9 = 27, 2*7 = 14, 1*4 = 4 and 4 has only one digit)
999 --> 4 (because 9*9*9 = 729, 7*2*9 = 126, 1*2*6 = 12, and finally 1*2 = 2)
4 --> 0 (because 4 is already a one-digit number)
```hs
import Data.Char (digitToInt)

persistence :: Int -> Int
persistence n
 | n < 10 = 0
 | otherwise = 1 + persistence (product digits)
 where
  digits = map digitToInt (show n)
```

13. **Magic Sum of 3s**

The magic sum of 3s is calculated on an array by summing up odd numbers which include the digit 3. Write a function magic_sum which accepts an array of integers and returns the sum.

Example: [3, 12, 5, 8, 30, 13] results in 16 (3 + 13)

If the sum cannot be calculated, 0 should be returned.
```hs
import Data.Char (digitToInt)

magicSum :: [Int] -> Int 
magicSum lst = sum $ foldr f [] lst
  where 
   f x acc = if (odd x && tieneTres x) then x : acc else acc

tieneTres :: Int -> Bool
tieneTres n = elem 3 lstDigits
 where
  lstDigits= map digitToInt (show n)

-- Solución 2: Listas por comprensión
magicSum :: [Int] -> Int 
magicSum lst = sum [x | x <- lst, odd x, elem '3' $ show x]
```

14. **Double Char**

Given a string, you have to return a string in which each character (case-sensitive) is repeated once.
Examples (Input -> Output):

* "String"      -> "SSttrriinngg"
* "Hello World" -> "HHeelllloo  WWoorrlldd"
* "1234!_ "     -> "11223344!!__  "

```hs
doubleChar :: [Char] -> [Char]
doubleChar = foldr (\x acc -> x : x : acc) []
```

15. **Is this a triangle?**

Implement a function that accepts 3 integer values a, b, c. The function should return true if a triangle can be built with the sides of given length and false in any other case.

(In this case, all triangles must have surface greater than 0 to be accepted).

```hs
isTriangle :: Int -> Int -> Int -> Bool
isTriangle a b c = noNegativos && formula
 where
  noNegativos = a >= 0 || b >= 0 || c >= 0
  formula = a + b > c && a + c > b && b + c > a

-- con mi versión de all
isTriangle :: Int -> Int -> Int -> Bool
isTriangle a b c = noNegativos && formula
 where
  noNegativos = all' (>= 0) [a,b,c]
  formula = all' (> 0) [a + b - c, a + c - b, b + c - a]
  
all' :: (a -> Bool) -> [a] -> Bool
all' func lst = and $ map func lst
```

16. ****
```hs
```

17. ****
```hs
```

18. ****
```hs
```

19. ****
```hs
```

20. ****
```hs
```

21. ****
```hs
```

22. ****
```hs
```

23. ****
```hs
```

24. ****
```hs
```

25. ****
```hs
```

26. ****
```hs
```

---

<a name="js"></a>
# JavaScript

1. **Multiples of 3 or 5**

If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in.
Additionally, if the number is negative, return 0.
Note: If the number is a multiple of both 3 and 5, only count it once.
```js
function solution(number){
    if (number < 0)
	return 0;
    var sum = 0;
    number--;
    while(number > 0) {
	if((number % 3) == 0 || (number % 5) == 0) {
	    sum += number;
	}
	number -= 1;
    }
    // for (let i = 1; i < number; i++) {
    // 	if ((i % 3) == 0 || (i % 5) == 0) {
    // 	    sum += i;
    // 	}
    // }
    return sum;
}
```
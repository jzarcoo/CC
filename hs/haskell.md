<a name="hs"></a>
# Haskell

## Contenidos

- [Native Functions](#nativas)
  - [Lists](#listas)
    - [Infinite lists](#listas_infinitas)
  - [Tuples](#tuplas)
  - [Higher Order Functions](#nf_higher_order_functions)
  - [Others](#otras)
- [Topics](#temas)
  - [Types and Typeclasses](#tipos_clases)
  - [Recursion](#recursion)
  - [Higher Order Functions](#f_ord_sup)
    - [Currying](#currying)
       - [Partial Application](#partial_application)
    - [High-order](#high_order)
    - [Folding](#folding)
  - [Módulos](#modulos)
- [Codewars](#codeWars)
  
---

<a name="nativas"></a>
## Native Functions

<a name="listas"></a>
### Lists

```hs
-- Divide la cadena en palabras utilizando espacios en blanco (espacios, tabulaciones, y saltos de línea) como delimitadores
words :: String -> [String]
-- Toma una lista de palabras y las concatena en una única cadena, separando cada palabra por un espacio en blanco.
unwords :: [String] -> String

-- Toman un predicado y una lista y comprueban si el predicado se satisface para algún o para todos los elementos respectivamente.
all :: Foldable t => (a -> Bool) -> t a -> Bool
any :: Foldable t => (a -> Bool) -> t a -> Bool

-- Máximo
maximum :: (Foldable t, Ord a) => t a -> a

-- Mínimo
minimum :: (Foldable t, Ord a) => t a -> a

-- Longitud. Regresa Int!!
length :: Foldable t => t a -> Int

-- Suma
sum :: (Foldable t, Num a) => t a -> a

-- Producto
product :: (Foldable t, Num a) => t a -> a

-- Contiene
elem :: (Foldable t, Eq a) => a -> t a -> Bool

-- Tomar los primeros n elementos de una lista
take :: Int -> [a] -> [a]

-- Se utiliza para eliminar los primeros n elementos de una lista.
drop :: Int -> [a] -> [a]

 -- Se utiliza para crear una lista que contiene múltiples repeticiones de un mismo valor.
replicate :: Int -> a -> [a]

-- Toma una lista de booleanos y devuelve True solo si todos los elementos de la lista son True.
and :: Foldable t => t Bool -> Bool
-- Devuelve True solo si existe algún elemento True en la lista.
or :: Foldable t => t Bool -> Bool

-- 1er elemento
head :: [a] -> a
-- Sin el 1er elemento
tail :: [a] -> [a]

-- Último elemento
last :: [a] -> a
-- Sin el último elemento
init :: [a] -> [a]

-- Takes a function and a list and applies that function to every element in the list, producing a new list
map :: (a -> b) -> [a] -> [b]

-- Condición y Lista
filter :: (a -> Bool) -> [a] -> [b]

-- Lista
reverse :: [a] -> [a]

-- Obtener el elemento de una lista sabiendo su índice
(!!) :: [a] -> Int -> a

-- Este operador se utiliza para agregar un elemento al principio de una lista
-- '' char
(:)

-- Este operador se utiliza para concatenar dos listas
-- "" string
(++)
```

<a name="listas_infinitas"></a>
#### Infinite lists

```hs
-- Repite el único elemento
repeat :: a -> [a]

-- Ciclo de listas iguales
cycle :: [a] -> [a]
```

---

<a name="tuplas"></a>
### Tuples

```hs
-- Regresa el 1er elemento
fst :: (a, b) -> a

-- Regresa el 2do elemento
snd :: (a, b) -> b

-- Toma 2 listas y las une en 1, juntando sus elementos en una tupla
zip :: [a] -> [b] -> [(a, b)]
```

---

<a name="nf_higher_order_functions"></a>
## Higher Order Functions

```hs
-- Toma una función binaria y dos listas, y devuelve una lista que resulta de aplicar la función binaria a los elementos correspondientes de las dos listas.
-- Si una de las listas es más corta que la otra, los elementos sobrantes de la lista más larga se ignoran
zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]

-- It evaluates the function flipping the order of arguments
flip :: (a -> b -> c) -> b -> a -> c

-- Devuelve una lista [b] que resulta de aplicar la función a cada elemento de la lista y luego concatenar los resultados.
concatMap :: (a -> [b]) -> [a] -> [b]
```

---

<a name="otras"></a>
### Others

```hs
-- Convierte un Int a otro más general (útil en length)
fromIntegral :: (Integral a, Num b) => a -> b

-- ¿Es impar?
odd :: Integral a => a -> Bool
-- ¿Es par?
even :: Integral a => a -> Bool

-- Máximo entre 2 elementos
max :: Ord a => a -> a -> a
-- Mínimo entre 2 elementos
min :: Ord a => a -> a -> a

-- Elemento siguiente
succ :: Enum a => a -> a

-- Resta
subtract :: Num a => a -> a -> a

-- División redondea a entero
div :: Integral a => a -> a -> a --div 5 2 = 2

-- División
(/) :: Fractional a => a -> a -> a --5/2=2.5
```

---

<a name="temas"></a>
## Topics

<a name="tipos_clases"></a>
### Types and Typeclasses

```hs
Eq

-- For types that have an ordering.
Ord
-- Takes two Ord members of the same type and returns an ordering: GT, LT or EQ.
compare :: Ord a => a -> a -> Ordering

-- Toma una cadena y devuelve un valor del tipo
read :: Read a => String -> a
-- Anotaciones de tipo. Ejemplo:
read "(3, 'a')" :: (Int, Char)

show :: Show a => a -> String

Enum

Bounded

Num

Integral

Floating
```

---

<a name="recursion"></a>
### Recursion

Implementación de funciones nativas

```hs
-- Regresa el elemento más grande en una lista
maximum' :: (Ord a) => [a] -> a
maximum' [] = error "Lista vacía"
maximum' [x] = x
maximum' (x:xs)
 | x > maxTail = x
 | otherwise = maxTail
   where
     maxTail = maximum' xs

maximum'' :: (Ord a) => [a] -> a
maximum'' [] = error "Lista vacía"
maximum'' [x] = x
maximum'' (x:xs) = x `max` maximum'' xs

-- Regresa el elemento más pequeño en una lista
minimum' :: (Ord a) => [a] -> a
minimum' [] = error "Lista vacía"
minimum' [x] = x
minimum' (x:xs)
 | x < minTail = x
 | otherwise = minTail
 where
   minTail = minimum' xs

minimum'' :: (Ord a) => [a] -> a
minimum'' [] = error "Lista vacía"
minimum'' [x] = x
minimum'' (x:xs) = x `min` minimum'' xs

-- Toma un Int y algún elemento y devuelve una lista con repeticiones de ese elemento
replicate' :: (Num i, Ord i) => i -> a -> [a]
replicate' 0 _ = [] -- n <= 0
replicate' n elem = elem : replicate' (n - 1) elem

-- Toma un Int y una lista y regresa los primeros elementos de una lista
take' :: (Num i, Ord i) => i -> [a] -> [a]
take' n _
 | n <= 0 = []
take' _ [] = []
take' n (x:xs) = x : take' (n - 1) xs

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

-- Infinito
repeat' :: a -> [a]
repeat' x = x : repeat' x

zip' :: [a] -> [b] -> [(a, b)]
zip' _ [] = []
zip' [] _ = []
zip' (a:as) (b:bs) = (a,b) : zip' as bs
```

QuickSort
```hs
-- It isn't in-place
quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) =
  let
    smallerSorted = quicksort [a | a<-xs, a<=x]
    biggerSorted = quicksort [a | a<-xs, a>x]
  in
    smallerSorted ++ [x] ++ biggerSorted
```

---

<a name="f_ord_sup"></a>
### Higher Order Functions

<a name="currying"></a>
#### Currying

Currying is the process of transforming a function that takes multiple arguments in a tuple as its argument, into a function that takes just a **single argument** and **returns another function** which accepts further arguments, one by one, that the original function would receive in the rest of that tuple. 

> All functions in Haskell take just one argument

- It can be said that arrows in the types notation *associate to the right*, so that <code>f :: a -> b -> c</code> is really <code>f :: a -> (b -> c)</code>
- Functional application, correspondingly, *associates to the left*: <code>f x y</code> is really <code>(f x) y</code>

As an illustration, `div` takes an `Int` and returns something of the type `Int -> Int`. So, <code>(div 11) 2</code> yields <code>5</code>

<a name="partial_application"></a>
##### Partial Application

```hs
-- (+) :: Num a => a -> a -> a
let addOne = (+) 1
addOne 10 --11
```
`addOne` is the result of partially applying `(+)`. It is a new function that takes a Num, adds 1 to it and returns that as the result. This means that `(+)` actually takes one argument and returns a function that takes another argument and returns a Num. 

Using partial application (calling functions with too few parameters, if you will) is a neat way to create **functions on the fly** so we can pass them to another function or to seed them with some data.

Functions can also be partially applied by using sections (simply surround it with parentheses and only supply a parameter on one side). That creates a function that takes one parameter and then applies it to the side that's missing an operand.

```hs
compareWithHundred :: (Num a, Ord a) => a -> Ordering
compareWithHundred = compare 100

isUpperAlphanum :: Char -> Bool
isUpperAlphanum = (`elem` ['A'..'Z'])
```

In JavaScript
```js
const divisionNormal_ArrowFunction = (a,b) => a/b;
const divisionNormal = function (a, b) {
  return a / b;
}
divisionNormal(10,2); //5

const divisionCurry_ArrowFunction = a => b => a/b;
const divisionCurry = function (a) {
  return function(b) {
  	return a / b;
	}
}
divisionCurry(10)(2); //5

const divWithTen = divisionCurry(10);
divWithTen(2); //5
```

---

<a name="high_order"></a>
#### High-order

> A higher-order function is a function that takes other functions as arguments or returns a function as result. 

```hs
applyTwice :: (a -> a) -> a -> a
applyTwice f x = f (f x)
```

If our function requires us to pass it a function that takes only one parameter, we can just partially apply a function to the point where it takes only one parameter and then pass it. <code>applyTwice (3:) [1] --[3,3,1]</code>
```hs
zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (a:as) (b:bs) = f a b : zipWith' f as bs

flip' :: (a -> b -> c) -> b -> a -> c
flip' f x y = f y x

-- We take advantage of the fact that functions are curried
let f = (subtract)
let f_flip = flip f
let f_flip_3 = f_flip 3
f_flip_3 4 -- -1

-- Using map is much more readable than list comprenhension
map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (x:xs) = f x : map' f xs

map (++ "!") ["BIFF", "BANG", "POW"] --["BIFF!","BANG!","POW!"]
```

Ejercicios: 

```hs
filter null [[], [4]] --[]
```

- Convierte cadena a mayúsculas.
  - `map`
```hs
import Data.Char

upperCase :: String -> String
upperCase s = map toUpper s
```

- Obtiene la sucesión de fibonacci.
  - `map`
```hs
-- Fibonacci
fib :: Integer -> Integer
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib (n-2)

-- Sucesión de Fibonacci
sFib :: Int -> [Int]
sFib n = map fib [0..n]
```

- Regresa palíndromos.
  - `filter`
```hs
-- Palíndromas
palindromo :: String -> Bool
palindromo x = x == reverse x

-- Lista palíndromas
palindromas :: [String] -> [String]
palindromas l = filter palindromas l
```

- Elimina ocurrencias del elemento.
  - `filter`
```hs
quitaElemento :: (Eq a) => [a] -> a -> [a]
quitaElemento l e = filter (/= e) l
```

- Quita las listas vacías de una lista de listas
  - `filter`
```hs
wel :: [[a]] -> [[a]]
wel l = filter (not . null) l
```

---

<a name="lambdas"></a>
#### Lambdas

Ejercicios:

```hs
(\x -> x+1) 10 -- 11
```

- Determina cual es positivo, negativo.
  - **lambdas** y `map`
```hs
signoNums :: [Int] -> [String]
signoNums l = map (\x -> if x < 0
  then "negativo"
  else if x > 0
    then "positivo"
    else "cero"
  ) l
```

- Regresa la suma de los primeros n+1 cuadrados.
  - **lambdas**, `sum` y `map`
```hs
sumSqrt :: Int -> Int
sumSqrt n = sum (map (\y -> y^2) [0..n])
```

- Filtra las cadenas más largas.
  - **lambdas**, `filter` y `length`
```hs
tooLong :: [String] -> [String]
tooLong l = filter (\x ->
  length z<= 7) l
```

- Dentro de una cadena, reemplaza las apariciones de un caracter por el segundo.
  - **lambdas** y `map`
```hs
replace :: String -> Char -> Char -> String
replace s c1 c2 = map
  (\cs -> if cs == c1
          then c2
          else cs) s
```

---

<a name="folding"></a>
#### Folding

Existe una familia de funciones en Haskell para modelar un algoritmo que permite 

> Procesar una estructura de datos para construir un valor
 
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

---

<a name="modulos"></a>
## Modules

### Data.Char

```hs
digitToInt :: Char -> Int
```

### Data.List

```hs
-- Elimina duplicados de una lista
nub :: Eq a => [a] -> [a]
```

### Data.Set

Remueve duplicados & Composición de funciones & Conjuntos
```hs
import Data.Set

quitar_duplicados :: (Ord a) => [a] -> [a]
quitar_duplicados = toList . fromList
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

16. **Stop gninnipS My sdroW!**

Write a function that takes in a string of one or more words, and returns the same string, but with all words that have five or more letters reversed (Just like the name of this Kata). Strings passed in will consist of only letters and spaces. Spaces will be included only when more than one word is present.

Examples:

"Hey fellow warriors"  --> "Hey wollef sroirraw" 
"This is a test        --> "This is a test" 
"This is another test" --> "This is rehtona test"

```hs
spinWords :: String -> String
spinWords str = drop 1 $ foldl f [] strToList
 where
  strToList = words str
  f acc elem = acc ++ " " ++ (if (length elem >= 5 ) then reverse elem else elem)
```

17. **Most frequently used words in a text**

Write a function that, given a string of text (possibly with punctuation and line-breaks), returns an array of the top-3 most occurring words, in descending order of the number of occurrences.
Assumptions:

    A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
    Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
    Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
    Matches should be case-insensitive, and the words in the result should be lowercased.
    Ties may be broken arbitrarily.
    If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned, or an empty array if a text contains no words.

Examples:

"In a village of La Mancha, the name of which I have no desire to call to
mind, there lived not long since one of those gentlemen that keep a lance
in the lance-rack, an old buckler, a lean hack, and a greyhound for
coursing. An olla of rather more beef than mutton, a salad on most
nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
on Sundays, made away with three-quarters of his income."

--> ["a", "of", "on"]


"e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"

--> ["e", "ddd", "aa"]


"  //wont won't won't"

--> ["won't", "wont"]

Bonus points (not really, but just for fun):

    Avoid creating an array whose memory footprint is roughly as big as the input text.
    Avoid sorting the entire array of unique words.

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



# HASKELL
### Contenidos
- [Notas](#notas)
- [Funciones nativas](#nativas)
- [Lambdas](#lambdas)

[Haskell](http://aprendehaskell.es/)
---

<a name="notas"></a>
## Notas
Remueve duplicados & Composición de funciones & Conjuntos
```hs
import Data.Set

quitar_duplicados :: (Ord a) => [a] -> [a]
quitar_duplicados = toList . fromList
```

---
<a name="nativas"></a>
## Funciones nativas
```hs
-- Función y Lista
map :: (a -> b) -> [a] -> [b]

-- Condición y Lista
filter :: (a -> Bool) -> [a] -> [b]

-- Lista
reverse :: [a] -> [a]
```
---
```hs
filter null [[], [4]]
[]
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
<a name="Lambdas"></a>
## Lambdas
```hs
(\x -> x+1) 10
11
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
```hs
```
```hs
```
```hs
```
```hs
```
```hs
```
```hs
```

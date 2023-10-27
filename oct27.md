# HASKELL
Funciones como 'toUpper'
```hs
import Data.Char
{-
@param una cadena
@return la cadena en mayúsculs
-}
upperCase :: String -> String
upperCase s = map toUpper s
```

Fibonacci
```hs
fib :: Integer -> Integer
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib (n-2)
```

```hs
sucesionFib :: Int -> [Int]
sucesionFib n = map fibo [0..n]
```

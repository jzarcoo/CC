module IO where

import System.IO

saluda :: IO()
saluda = do
  putStr "Hello "
  putStrLn "World!"

askForInput :: IO()
askForInput = do
  putStr "Type a word: "
  -- Forzar la escritura de los datos almacenados en el búfer de stdout
  hFlush stdout
  line <- getLine
  putStrLn $ "Word: " ++ line

leeArch :: IO()
leeArch = do
  handler <- openFile "f1.txt.txt" ReadMode -- Manejador del archivo
  size <- hFileSize handler
  putStrLn $ "Size: " ++ show size
  hClose handler

escribeArch :: IO()
escribeArch = do
  -- 1
  handler <- openFile "f2.txt" WriteMode
  hPutStr handler "Hello "
  hPutStrLn handler "World"
  hClose handler
  -- 2
  writeFile "f3.txt" "writeFile"
  -- 3
  appendFile "f3.txt" "appendFile"

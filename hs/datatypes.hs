data Person = P {
  getName :: String,
  getAge :: Int
  } deriving (Show)

-- Datatype with parameters
data Var a = V {
  name :: String,
  value :: a
  } deriving (Show)

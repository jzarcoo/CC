-- Las clases de tipos son una especie de interfaz que define algún tipo de comportamiento.
-- Eq, Ord, ...

-- Constructor de tipo "=" Contructor de dato

data Maybe a = Nothing | Just a

-- Sintaxis de registro
-- El orden puede variar
-- Car {company = "Ford", model = "Mustang", year = 1967} 
data Car = Car {
  company :: String, 
  model :: String, 
  year :: Int
  } deriving (Eq, Show, Read)
  -- Hs comprueba campo a campo, los cuales deben ser miembros derivados también.

-- Datatype with parameters
data Vector a = Vector a a a deriving (Show)

-- Suma 2 vectores
vplus :: (Num t) => Vector t -> Vector t -> Vector t
(Vector i j k) `vplus` (Vector l m n) = Vector (i+l) (j+m) (k+n)

-- Producto vector y escalar
vectMult :: (Num t) => Vector t -> t -> Vector t
(Vector i j k) `vectMult` m = Vector (i*m) (j*m) (k*m)

-- Producto escalar
scalarMult :: (Num t) => Vector t -> Vector t -> t
(Vector i j k) `scalarMult` (Vector l m n) = i*l + j*m + k*n

-- Especie de enumeraciones
data Day = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday
           deriving (Eq, Ord, Show, Read, Bounded, Enum) -- Orden por como los escribimos

-- Sinónimos de tipo
type String = [Char]

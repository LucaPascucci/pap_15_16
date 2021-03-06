--1) Implementare una funzione isSorted che data una lista di interi l restituisce vero o falso a seconda che l sia ordinata in ordine crescente oppure non lo sia.
--isSorted
isSorted :: [Int] -> Bool
isSorted [] = True
isSorted (x:xs)
  | null xs = True
  | x <= head xs = (isSorted xs)
  | otherwise = False

--2) Implementare la funzione che data una lista di stringhe l e una stringa s, determina tutte le posizioni (occorrenze) in cui s compare in l. La prima posizione ha indice zero.
--occSimple
occSimple :: [String] -> String -> [Int]
occSimple (xs) v = func (reverse xs) v --faccio reverse della lista cosi da poter utilizzare la lunghezza della coda come indice
  where
    func [] _ = []
    func [_] [] = []
    func (x:xs) s
      | x == s = func (xs) s ++ [length xs] -- aggiungo in coda l'indice dato che la lista viene analizzata a partire dalla fine
      | otherwise = func (xs) s

--3) Implementare la funzione che data una lista di stringhe l determina le occorrenze di tutte le stringhe contenute in l, determinando, per ogni stringa, tutte le posizioni in cui essa compare in l.
--quicksort
quicksort :: [String] -> [String]
quicksort [] = []
quicksort (x:xs) = quicksort([ y | y<-xs, y < x]) ++ [x] ++ quicksort([ y | y<-xs, y >= x])

--clearDuplicate = funzione che rimuove i duplicati in un array
clearDuplicate :: [String] -> [String]
clearDuplicate [] = []
clearDuplicate [a] = [a]
clearDuplicate (x:y:xs)
  | x == y = clearDuplicate(y:xs)
  | otherwise = [x]++clearDuplicate(y:xs)

--Funzione richiama occSimple sulla lista originale passando uno per volta i valori presenti nella lista senza duplicati
occLoop :: [String] -> [String] -> [(String, [Int])]
occLoop [] _ = []
occLoop _ [] = []
occLoop (xs) (y:ys) = [(y,occSimple(xs) y)] ++ occLoop (xs) (ys)

--occFull
occFull :: [String] -> [(String,[Int])]
occFull [] = []
occFull (xs) = occLoop xs (clearDuplicate(quicksort(xs)))

--4) Siano dati i seguenti tipi:

data Sym = Dot | Dash deriving (Eq)
data Code = Single Sym | Comp Sym Code deriving (Eq)

--Implementare la funzione che dato un valore di tipo Code, conta il numero di elementi Dash in esso presenti.
--countDash
countDash :: Code -> Int
countDash (Comp s c)
  | s == Dash = 1 + countDash c
  | otherwise = countDash c
countDash (Single s)
  | s == Dash = 1
  | otherwise  = 0

--5) Dato il tipo Code definito in precedenza, implementare la funzione che restituisce una rappresentazione testuale di Code, ove Dot ?? rappresentato dal carattere ???.??? e Dash dal carattere ???-???.
--showCode
showCode :: Code -> String
showCode (Comp s c)
  | s == Dash = "-" ++ showCode c
  | s == Dot = "." ++ showCode c
  | otherwise = showCode c
showCode (Single s)
  | s == Dash = "-"
  | s == Dot = "."
  | otherwise = ""

--6) Siano dati  i tipi:

data Digit = Zero | One deriving (Eq,Show)
type BNum = [Digit]

--ove BNum rappresenta un numero binario ad N cifre (interpretando l???elemento Zero come il valore 0 e l???elemento One come il valore 1), dove la prima posizione nella lista rappresenta la cifra pi?? significativa (non necessariamente zero).
--Implementare la funzione che dati due BNum determina se rappresentano lo stesso numero binario.

--Funzione che rimuove i valori zero a sinistra in un numero binario
--removeFirstZero
removeFirstZero :: BNum -> BNum
removeFirstZero [] = []
removeFirstZero (x:xs)
  | x == Zero = removeFirstZero xs
  | otherwise = (x:xs)

--equalsBNum
equalsBNum :: BNum -> BNum -> Bool
equalsBNum xs ys = func (removeFirstZero xs) (removeFirstZero ys) --rimuovo valori non utili (Zero a sinistra)
  where
    func [] [] = True
    func xs ys
      | length(xs) /= length (ys) = False --lunghezze diverse quindi valore diverso
    func (x:xs) (y:ys) --appena incontro due valori differenti nello stesso indice allora sono due numeri diversi
      | x == y = func xs ys
      | otherwise = False

-- 7) Dato il tipo BNum precedentemente definito, implementare la funzione che ne determina il valore come numero intero.
--convBNum
convBNum :: BNum -> Int
convBNum [] = 0
convBNum (x:xs)
  | x == Zero = convBNum xs
  | otherwise = 2^(length xs) + convBNum xs

--8) Dato il tipo BNum definito in precedenza, implementare la funzione che dati due numeri binari determina il numero binario che rappresenta la somma.

--sumBNum
sumBNum :: BNum -> BNum -> BNum
sumBNum [] [] = [Zero]
sumBNum xs ys = func (reverse xs) (reverse ys) Zero
  where
    func [] [] Zero = []
    func [] [] One = [One]
    func [] ys r = func [Zero] ys r
    func xs [] r = func xs [Zero] r
    func (x:xs) (y:ys) r
      | x == Zero && y == Zero && r == Zero = func xs ys Zero ++ [Zero]
      | x == Zero && y == Zero && r == One = func xs ys Zero ++ [One]
      | x == Zero && y == One && r == Zero = func xs ys Zero ++ [One]
      | x == Zero && y == One && r == One = func xs ys One ++ [Zero]
      | x == One && y == Zero && r == Zero = func xs ys Zero ++ [One]
      | x == One && y == Zero && r == One = func xs ys One ++ [Zero]
      | x == One && y == One && r == Zero = func xs ys One ++ [Zero]
      | x == One && y == One && r == One = func xs ys One ++ [One]

--9) Dato il tipo Digit definito in precedenza e il tipo he rappresenta un albero binario

data BTree a = Nil | Node a (BTree a) (BTree a) deriving (Eq)

--implementare la funzione che conta il numero di elementi Zero presenti nell???albero passato come parametro.
--countZeroInTree
countZeroInTree :: BTree Digit -> Int
countZeroInTree Nil = 0
countZeroInTree (Node v l r)
  | v == Zero = 1 + (countZeroInTree l) + (countZeroInTree r)
  | otherwise = (countZeroInTree l) + (countZeroInTree r)

--10) Dato il tipo BTree definito in precedenza, supponendo che rappresenti un albero binario di ricerca, implementare la funzione che, dato un albero t e un valore v, determina la lista degli elementi presenti in t che hanno un valore inferiore a v.
--getValuesLessThan
getValuesLessThan :: BTree Int -> Int -> [Int]
getValuesLessThan Nil _ = []
getValuesLessThan (Node n l r) v
  | n < v = getValuesLessThan l v ++ [n] ++ getValuesLessThan r v
  | otherwise = getValuesLessThan l v

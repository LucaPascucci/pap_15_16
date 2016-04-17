--1) Implementare una funzione isSorted che data una lista di interi l restituisce vero o falso a seconda che l sia ordinata in ordine crescente oppure non lo sia.
--isSorted
isSorted :: [Int] -> Bool
isSorted [] = True
isSorted (x:xs)
  | null xs = True --Coda vuota quindi è rimasto un solo elemento
  | x <= head xs = (isSorted xs)
  | otherwise = False

test1 = isSorted [1,2,4,5] --True
test2 = isSorted [1,3,5,2] --False

--2) Implementare la funzione che data una lista di stringhe l e una stringa s, determina tutte le posizioni (occorrenze) in cui s compare in l. La prima posizione ha indice zero.
--occSimple
occSimple :: [String] -> String -> [Int]
occSimple (xs) v = func (reverse xs) v --faccio reverse della lista cosi da poter utilizzare la lunghezza della coda come indice
  where
    func [] _ = []
    func [_] [] = [] --errore!!!
    func (x:xs) s
      | x == s = func (xs) s ++ [length xs] -- aggiungo in coda l'indice che equivale alla lunghezza della coda dato che la lista viene analizzata a partire dal fondo
      | otherwise = func (xs) s

test3 = occSimple [] "aa" --[]
test4 = occSimple ["a","bb"] "aa" --[]
test5 = occSimple ["a","aa","b","aa"] "aa" --[1,3]

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
  | otherwise = [x] ++ clearDuplicate(y:xs)

--Funzione richiama occSimple sulla lista originale passando uno per volta i valori presenti nella lista senza duplicati
occLoop :: [String] -> [String] -> [(String, [Int])]
occLoop [] _ = []
occLoop _ [] = []
occLoop (xs) (y:ys) = [(y,occSimple(xs) y)] ++ occLoop (xs) (ys)

--occFull
occFull :: [String] -> [(String,[Int])]
occFull [] = []
occFull (xs) = occLoop xs (clearDuplicate(quicksort(xs)))

test6 = occFull [] -- []
test7 = occFull ["a","aa","b","aa","b","c"] -- [("a",[0]), ("aa",[1,3]),("b",[2,4]),("c",[5])]

--4) Siano dati i seguenti tipi:

data Sym = Dot | Dash
data Code = Single Sym | Comp Sym Code

--Implementare la funzione che dato un valore di tipo Code, conta il numero di elementi Dash in esso presenti.
--countDash
countDash :: Code -> Int
countDash (Comp Dash c) = 1 + countDash c
countDash (Comp Dot c) = countDash c
countDash (Single Dash) = 1
countDash (Single Dot) = 0

test8 = countDash (Single Dot) -- 0
test9 = countDash (Single Dash) -- 1
test10 = countDash (Comp Dash (Single Dash)) -- 2

--5) Dato il tipo Code definito in precedenza, implementare la funzione che restituisce una rappresentazione testuale di Code, ove Dot è rappresentato dal carattere ‘.’ e Dash dal carattere ‘-’.
--showCode
showCode :: Code -> String
showCode (Comp Dash c) = "-" ++ showCode c
showCode (Comp Dot c) = "." ++ showCode c
showCode (Single Dash) = "-"
showCode (Single Dot) = "."

test11 = showCode (Single Dot) -- "."
test12 = showCode (Single Dash) -- "-"
test13 = showCode (Comp Dash (Single Dash)) -- "--"

--6) Siano dati  i tipi voe BNum rappresenta un numero binario ad N cifre (interpretando l’elemento Zero come il valore 0 e l’elemento One come il valore 1), dove la prima posizione nella lista rappresenta la cifra più significativa (non necessariamente zero).

data Digit = Zero | One deriving (Show)
type BNum = [Digit]

--Implementare la funzione che dati due BNum determina se rappresentano lo stesso numero binario.

--Funzione che rimuove i valori zero a sinistra in un numero binario
--removeFirstZero
removeFirstZero :: BNum -> BNum
removeFirstZero [] = []
removeFirstZero (Zero:xs) = removeFirstZero xs
removeFirstZero (One:xs) = (One:xs)

--equalsBNum
equalsBNum :: BNum -> BNum -> Bool
equalsBNum xs ys = func (removeFirstZero xs) (removeFirstZero ys)
  where
    func [] [] = True
    func xs ys
      | length(xs) /= length (ys) = False
    func (Zero:xs) (Zero:ys) = func xs ys
    func (One:xs) (One:ys) = func xs ys
    func (Zero:xs) (One:ys) = False
    func (One:xs) (Zero:ys) = False

test14 = equalsBNum [] [One,Zero] -- False
test15 = equalsBNum [One,Zero] [One,Zero] -- True
test16 = equalsBNum [Zero, One, Zero] [One,Zero] -- True
test17 = equalsBNum [One, One, Zero] [One,One,Zero,Zero] -- False

-- 7) Dato il tipo BNum precedentemente definito, implementare la funzione che ne determina il valore come numero intero.
--convBNum
convBNum :: BNum -> Int
convBNum [] = 0
convBNum (Zero:xs) = convBNum xs
convBNum (One:xs) = 2^(length xs) + convBNum xs

test18 = convBNum [One,Zero] --2
test19 = convBNum [One, Zero, One] --5
test20 = convBNum [Zero, One, One, One, One] --15

--8) Dato il tipo BNum definito in precedenza, implementare la funzione che dati due numeri binari determina il numero binario che rappresenta la somma.

--sumBNum
sumBNum :: BNum -> BNum -> BNum
sumBNum [] [] = [Zero]
sumBNum xs ys = func (reverse (removeFirstZero xs)) (reverse (removeFirstZero ys)) Zero
  where
    func [] [] Zero = []
    func [] [] One = [One]
    func [] ys r = func [Zero] ys r
    func xs [] r = func xs [Zero] r
    func (Zero:xs) (Zero:ys) Zero = func xs ys Zero ++ [Zero]
    func (Zero:xs) (Zero:ys) One = func xs ys Zero ++ [One]
    func (Zero:xs) (One:ys) Zero = func xs ys Zero ++ [One]
    func (Zero:xs) (One:ys) One = func xs ys One ++ [Zero]
    func (One:xs) (Zero:ys) Zero = func xs ys Zero ++ [One]
    func (One:xs) (Zero:ys) One = func xs ys One ++ [Zero]
    func (One:xs) (One:ys) Zero = func xs ys One ++ [Zero]
    func (One:xs) (One:ys) One = func xs ys One ++ [One]

test21 = sumBNum [One] [One,Zero] -- [One,One]
test22 = sumBNum [Zero,One,One] [Zero,Zero] -- [One,One]

--9) Dato il tipo Digit definito in precedenza e il tipo he rappresenta un albero binario

data BTree a = Nil | Node a (BTree a) (BTree a)

--implementare la funzione che conta il numero di elementi Zero presenti nell’albero passato come parametro.
--countZeroInTree
countZeroInTree :: BTree Digit -> Int
countZeroInTree Nil = 0
countZeroInTree (Node Zero l r) = 1 + (countZeroInTree l) + (countZeroInTree r)
countZeroInTree (Node One l r) = (countZeroInTree l) + (countZeroInTree r)

test23 = countZeroInTree Nil -- 0
test24 = countZeroInTree (Node One Nil Nil ) -- 0
test25 = countZeroInTree (Node Zero (Node One Nil Nil) (Node Zero Nil Nil)) -- 2

--10) Dato il tipo BTree definito in precedenza, supponendo che rappresenti un albero binario di ricerca, implementare la funzione che, dato un albero t e un valore v, determina la lista degli elementi presenti in t che hanno un valore inferiore a v.
--getValuesLessThan
getValuesLessThan :: BTree Int -> Int -> [Int]
getValuesLessThan Nil _ = []
getValuesLessThan (Node n l r) v
  | n < v = getValuesLessThan l v ++ [n] ++ getValuesLessThan r v
  | otherwise = getValuesLessThan l v

test26 = getValuesLessThan Nil 5 -- []
test27 = getValuesLessThan (Node 5 (Node 3 Nil Nil) (Node 8 Nil Nil)) 5 -- [3]
test28 = getValuesLessThan (Node 5 (Node 3 Nil (Node 4 Nil Nil)) (Node 8 Nil Nil)) 8 -- [3,4,5]
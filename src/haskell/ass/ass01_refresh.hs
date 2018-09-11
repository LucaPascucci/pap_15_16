-- 1) Implementare una funzione isSorted che data una lista di interi l
-- restituisce vero o falso a seconda che l sia ordinata in ordine crescente
-- oppure non lo sia.

isSorted :: [Int] -> Bool
isSorted [] = True -- Array vuoto
isSorted [_] = True -- Array con un solo elemento
isSorted (x:y:xs) -- Array con minimo due elementi "x" e "y" ed una coda "xs" che può essere anche vuota
  | x <= y = isSorted (y:xs)
  | otherwise = False

test1 = isSorted [1,2,4,5] --True
test2 = isSorted [1,3,5,2] --False


-- 2) Implementare la  funzione
-- occSimple :: [String] -> String -> [Int]
-- che data una lista di stringhe l e una stringa s, determina tutte le posizioni
-- (occorrenze) in cui s compare in l. La prima posizione ha indice zero.

occSimple :: [String] -> String -> [Int]
occSimple [] _ = []
occSimple l s = occSimpleWithIndex l s 0 -- passato indice
  where
    occSimpleWithIndex [] _ _ = []
    occSimpleWithIndex (x:xs) s i
      | x == s = i : updateIndex
      | otherwise = updateIndex
      where
        updateIndex = occSimpleWithIndex xs s (i+1) -- funzione per incrementare l'indice e lavorare sulla coda

test3 = occSimple [] "aa" --[]
test4 = occSimple ["a","bb"] "aa" --[]
test5 = occSimple ["a","aa","b","aa"] "aa" --[1,3]


-- 3) Implementare la  funzione
-- occFull :: [String] -> [(String, [Int])]
-- che data una lista di stringhe l determina le occorrenze di tutte le stringhe
-- contenute in l, determinando, per ogni stringa, tutte le posizioni in cui essa
-- compare in l.

occFull :: [String] -> [(String, [Int])]
occFull list = occFull2 list list [] -- creo una funzione di appoggio dove duplico la lista iniziale e tengo un array (checked) per memorizzare i valori che controllo man mano, per evitare i duplicati
    where
        occFull2 [] _ _ = []
        occFull2 (x:xs) startList checked
            -- prendo il primo valore nella lista iniziale e controllo se è gia stato conteggiato (per evitare duplicati) tramite la funzione member
            | not (member x checked) = (x, occSimple startList x) : occFull2 xs startList (x:checked) -- valore nuovo utilizzo 'occSimple' per sapere la posizione all'interno dell'array, continuo sul resto della lista memorizzando il valore appena conteggiato nella lista checked
            | otherwise = occFull2 xs startList checked -- valore precedentemente conteggiato e quindi vado avanti con occFull2
            where
                member _ [] = False
                member v (x:xs)
                    | v == x = True
                    | otherwise = member v xs

test6 = occFull [] --[]
test7 = occFull ["a","aa","b","aa","b","c"] --[(“a”,[0]), (“aa”,[1,3]),(“b”,[2,4]),(“c”,5)]

-- 4) Siano dati i seguenti tipi:
data Sym = Dot | Dash
data Code = Single Sym | Comp Sym Code

-- Implementare la funzione:
-- countDash :: Code -> Int
-- che dato un valore di tipo Code, conta il numero di elementi Dash in esso presenti.

countDash :: Code -> Int
countDash (Single Dash) = 1
countDash (Single Dot) = 0
countDash (Comp Dash c) = 1 + countDash c
countDash (Comp Dot c) = countDash c

test8 = countDash (Single Dot) -- 0
test9 = countDash (Single Dash) -- 1
test10 = countDash (Comp Dash (Single Dash)) -- 2


-- 5) Dato il tipo Code definito in precedenza, implementare la funzione
-- showCode :: Code -> String
-- che restituisce una rappresentazione testuale di Code, ove Dot è rappresentato dal carattere ‘.’ e Dash dal carattere ‘-’.

showCode :: Code -> String
showCode (Single Dash) = "-"
showCode (Single Dot) = "."
showCode (Comp Dash c) = "-" ++ showCode c
showCode (Comp Dot c) = "." ++ showCode c

test11 = showCode (Single Dot) -- “.”
test12 = showCode (Single Dash) -- “-”
test13 = showCode (Comp Dash (Single Dash)) -- “--”


-- 6) Siano dati  i tipi:
data Digit = Zero | One deriving (Show)
type BNum = [Digit]
-- dove BNum rappresenta un numero binario ad N cifre (interpretando l’elemento Zero come il valore 0 e l’elemento One come il valore 1), dove la prima posizione nella lista rappresenta la cifra più significativa (non necessariamente zero).
-- Implementare la funzione
-- equalsBNum :: BNum -> BNum -> Bool
-- che dati due BNum determina se rappresentano lo stesso numero binario.

-- rimuove i primi zeri (valori inutili) del numero binario
removeFirstZeros :: BNum -> BNum
removeFirstZeros [] = []
removeFirstZeros (Zero:xs) = removeFirstZeros xs
removeFirstZeros (One:xs) =  One:xs

equalsBNum :: BNum -> BNum -> Bool
equalsBNum num1 num2 = func (removeFirstZeros num1) (removeFirstZeros num2)
    where
        func [] [] = True
        func num1 num2 -- se hanno lunghezza diversa allora sono sicuramente due numeri diversi
           | length num1 /= length num2 = False
        func (Zero:xs) (Zero:ys) = func xs ys -- stessi valori
        func (One:xs) (One:ys) = func xs ys -- stessi valori
        func (Zero:xs) (One:ys) = False -- valori diversi
        func (One:xs) (Zero:ys) = False -- valori diversi

test14 = equalsBNum [] [One,Zero] -- False
test15 = equalsBNum [One, Zero] [One,Zero] -- True
test16 = equalsBNum [Zero, One, Zero] [One,Zero] -- True
test17 = equalsBNum [One, One, Zero] [One,One,Zero,Zero] -- False


-- 7) Dato il tipo BNum precedentemente definito, implementare la funzione
-- convBNum :: BNum -> Int
-- che ne determina il valore come numero intero.

convBNum :: BNum -> Int
convBNum [] = 0
convBNum (Zero:xs) = convBNum xs
convBNum (One:xs) = 2^(length xs) + convBNum xs -- 2^ elevato alla lunghezza della coda (così da avere già la posizione corretta) aggiungo al resto dell'array

test18 = convBNum [One,Zero] -- 0*2^0 + 1*2^1 = 2
test19 = convBNum [One, Zero, One] -- 1*2^0 + 0*2^1 + 1*2^2 = 5
test20 = convBNum [Zero, One, One, One, One] -- 1*2^0 + 1*2^1 + 1*2^2 + 1*2^3 + 0*2^4 = 15

-- 8) Dato il tipo BNum definito in precedenza, implementare la funzione
-- sumBNum :: BNum -> BNum -> BNum
-- che dati due numeri binari determina il numero binario che rappresenta la somma.

sumBNum :: BNum -> BNum -> BNum
-- rimuovo gli zero inutili tramite 'removeFirstZeros' poi inverto i numeri tramite 'reverse' e inverto il risultato perchè nella funzione sumBNum' costruisco la somma al contrario
sumBNum num1 num2 = reverse (sumBNum' (reverse (removeFirstZeros num1)) (reverse (removeFirstZeros num2)) 0)
    where
        sumBNum' [] b 0 = b
        sumBNum' [] [] 1 = [One]
        sumBNum' [] (Zero:ys) 1 = One : ys
        sumBNum' [] (One:ys) 1 = Zero : sumBNum' [] ys 1
        sumBNum' a [] 0 = a
        sumBNum' (Zero:xs) [] 1 = One : xs
        sumBNum' (One:xs) [] 1 = Zero : sumBNum' xs [] 1
        sumBNum' (Zero:xs) (Zero:ys) 0 = Zero : sumBNum' xs ys 0
        sumBNum' (Zero:xs) (Zero:ys) 1 = One : sumBNum' xs ys 0
        sumBNum' (One:xs) (Zero:ys) 0 = One : sumBNum' xs ys 0
        sumBNum' (One:xs) (Zero:ys) 1 = Zero : sumBNum' xs ys 1
        sumBNum' (Zero:xs) (One:ys) 0 = One : sumBNum' xs ys 0
        sumBNum' (Zero:xs) (One:ys) 1 = Zero : sumBNum' xs ys 1
        sumBNum' (One:xs) (One:ys) 0 = Zero : sumBNum' xs ys 1
        sumBNum' (One:xs) (One:ys) 1 = One : sumBNum' xs ys 1

test21 = sumBNum [One] [One,Zero] -- [One,One]
test22 = sumBNum [Zero,One,One] [Zero,Zero] -- [One,One]
test23 = sumBNum [Zero,One,One] [One,Zero] -- [One,Zero,One]

-- 9) Dato il tipo Digit definito in precedenza e il tipo
data BTree a = Nil | Node a (BTree a) (BTree a)
-- che rappresenta un albero binario implementare la funzione:
-- countZeroInTree :: BTree Digit -> Int
-- che conta il numero di elementi Zero presenti nell’albero passato come parametro.

countZeroInTree :: BTree Digit -> Int
countZeroInTree Nil = 0
countZeroInTree (Node Zero l r) = 1 + countZeroInTree l + countZeroInTree r -- riceca ricorsiva in entrambi i sottoalberi
countZeroInTree (Node One l r) = countZeroInTree l + countZeroInTree r -- riceca ricorsiva in entrambi i sottoalberi

test24 = countZeroInTree Nil -- 0
test25 = countZeroInTree (Node One Nil Nil ) -- 0
test26 = countZeroInTree (Node Zero (Node One Nil Nil) (Node Zero Nil Nil)) -- 2

-- 10) Dato il tipo BTree definito in precedenza, supponendo che rappresenti un albero binario di ricerca, implementare la funzione
-- getValuesLessThan :: BTree Int -> Int -> [Int]
-- che, dato un albero t e un valore v, determina la lista degli elementi presenti in t che hanno un valore inferiore a v.
getValuesLessThan :: BTree Int -> Int -> [Int]
getValuesLessThan Nil _ = []
getValuesLessThan (Node n l r) v
    | n < v = getValuesLessThan l v ++ [n] ++ getValuesLessThan r v -- riceca ricorsiva in entrambi i sottoalberi
    | otherwise = getValuesLessThan l v -- n è maggiore di v quindi ricerco solo nel sottoalbero di sinistra che contiene sempre valori minori di n


test27 = getValuesLessThan Nil 5 -- []
test28 = getValuesLessThan (Node 5 (Node 3 Nil Nil) (Node 8 Nil Nil)) 5 -- [3]
test29 = getValuesLessThan (Node 5 (Node 3 Nil (Node 4 Nil Nil)) (Node 8 Nil Nil)) 8 -- [3,4,5]
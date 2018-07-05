--1) Implementare la funzione countElems che data una lista di interi e un valore intero v, restituisce il numero di volte in cui l’elemento compare nella lista.

{-
  [Int] = primo parametro
  Int = secondo parametro
  Int = tipo di ritorno della espressione/funzione
-}

countElems :: [Int] -> Int -> Int
countElems [] _ = 0
countElems (x:xs) v
  | x == v = 1 + (countElems xs v) -- Si potrebbero togliere le parentesi tra countElems e la fine
  | otherwise = countElems xs v

-- countElems alternativo
countElemsAlternative :: [Int] -> Int -> Int
countElemsAlternative [] _ = 0
countElemsAlternative (x:xs) v
    | x == v = 1 + rest
    | otherwise = rest
  where
    rest = (countElemsAlternative xs v)


--2) Implementare la funzione countElems che data una lista di interi e un predicato p, restituisce il numero elemento della lista che soddisfano il predicato p.

countElemsWithPred :: [Int] -> (Int -> Bool) -> Int
countElemsWithPred [] _ = 0
countElemsWithPred (x:xs) p
  | p x = 1 + (countElemsWithPred xs p) -- "p x" è come scrivere (p x)==True
  | otherwise = countElemsWithPred xs p

--countElemsWithWhere alternativo
countElemsWithWhere :: [Int] -> Int -> Int
countElemsWithWhere [] _ = 0
countElemsWithWhere (x:xs) v
  | x == v = 1 + rest
  | otherwise = rest
  where
    rest = countElemsWithWhere xs v


--3) Implementare la funzione countDots che data una lista di Elem restituisce il numero di elemento Dot presenti nella lista.

data Elem = Dot | Star

--countDots (utilizza il pattern matching)
countDots :: [Elem] -> Int
countDots [] = 0
countDots (Dot:xs) = 1 + countDots xs
countDots (_:xs) = countDots xs -- Non specifichiamo il primo tipo, anche se in questo caso, se non è Dot è Star


--4) Implementare la funzione rev che data una lista di elementi generica, restituisce la lista con gli elementi in ordine inverso

rev :: [a] -> [a]
rev [] = []
rev (x:xs) = (rev xs) ++ [x] -- Non è possibile scrivere ""rev (x:xs) = xs:x"", perché è sbagliato il tipo: l'operatore ":" si aspetta un elemento e una lista.


--5) Implementare la funzione removeAll che data una lista di elementi generica e un elemento, restituisca una lista pari a quella passata senza tutti gli elementi pari all’elemento passato

-- Il problema con questa definizione è che la funzione dovrebbe essere valida
-- per ogni tipo di "a" per la quale l'operatore == è definito
-- In realtà c'è un problema con la versione polimorfica: la type variable potrebbe avere ulteriori vincoli nel corpo della funzione.
--removeAll :: [a] -> a -> [a]
removeAll :: (Eq a) => [a] -> a -> [a]
removeAll [] _ = []
removeAll (x:xs) v
  | x == v = removeAll xs v
  | otherwise = x : removeAll xs v -- "[x] ++ removeAll xs v" funzione ugualmente


--6) Implementare la funzione merge che date due liste ordinate di elementi interi, computa una sola lista ordinata

merge :: [Int] -> [Int] -> [Int]
merge [] [] = []
merge [] ys = ys
merge xs [] = xs
merge (x:xs) (y:ys)
  | x < y = [x] ++ merge xs (y:ys)
  | x > y = [y] ++ merge (x:xs) ys
  | otherwise = [x,y] ++ merge xs ys


--7) Data la struttura dati che rappresenta un albero binario di ricerca (BST = Binary Search Tree), implementare la funzione isPresentInBST generica che dato un valore e un BSTree restituisce vero o falso a seconda che il valore sia o meno presente

data BSTree a = Nil | Node a (BSTree a) (BSTree a) deriving (Show)

isPresentInBST :: (Eq a, Ord a) => a -> BSTree a -> Bool
isPresentInBST _ Nil = False
isPresentInBST v (Node x l r) -- Valore del nodo (x), sottoalbero sinistro (l) e destro (r)
  | x == v = True
  | x < v = isPresentInBST v r -- Ricerca nel sottoalbero destro (sappiamo che i valori minori sono a sinistra e quelli maggiori a destra, per definizione di BST)
  | otherwise = isPresentInBST v l --- Ricerca nel sottoalbero sinistro


--8) Implementare la funzione buildBST che, data la una lista di stringhe, costruisce l’albero binario ordinato che contiene le stringhe e la loro posizione all’interno della lista.

insertInBST :: String -> BSTree String -> BSTree String
insertInBST s Nil = (Node s Nil Nil)
insertInBST s (Node v l r)
  | s <= v = (Node v (insertInBST s l) r)
  | otherwise = (Node v l (insertInBST s r))

--buildBST
buildBST :: [String] -> BSTree String
buildBST [] = Nil
buildBST (x:xs) = insertInBST x (buildBST xs)


-- Dati per le esercitazioni

array :: [Int]
array = [1,2,5,2,4,5,5]

predicato :: Int -> Bool
predicato = (\x -> x < 3)

arrayDot_Stars :: [Elem]
arrayDot_Stars = [Star,Dot,Star,Dot]

array1 :: [Int]
array1 = [1,6,8]
array2 :: [Int]
array2 = [5,9,20,21]

testTree :: BSTree Int
testTree = (Node 10 (Node 5 (Node 4 (Node 3 Nil Nil) Nil) (Node 7 (Node 6 Nil Nil) Nil))(Node 13 (Node 11 (Node 10 Nil Nil) Nil) (Node 14 Nil Nil)))

testStringTree :: BSTree String
testStringTree = (Node "faro" (Node "caco" (Node "albero" Nil Nil) (Node "dado" Nil  Nil)) (Node "luce" (Node "iodio" Nil Nil) Nil ))

builtTree = buildBST ["Luca","Andrea","Luciana","Leonardo","Lucky","Alessio","Vittoria","Lorenzo","Paola","Ilario"]


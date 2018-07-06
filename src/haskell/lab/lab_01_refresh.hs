
-- 1) ​Implementare la funzione countElems​ che data una lista di interi e un valore intero v,
-- restituisce il numero di volte in cui l’elemento compare nella lista.

countElems :: [Int] -> Int -> Int
countElems [] _ = 0
countElems (x:xs) v
  | x == v = 1 + countElems xs v
  | otherwise = countElems xs v


-- 2)​ Implementare la funzione countElemsWithPred che data una lista di interi e un predicato p, restituisce
-- il numero elemento della lista che soddisfano il predicato p.

countElemsWithPred :: [Int] -> (Int -> Bool) -> Int
countElemsWithPred [] _ = 0
countElemsWithPred (x:xs) predicato
  | predicato x = 1 + countElemsWithPred xs predicato -- "predicato x" è come scrivere (predicato x)==True
  | otherwise = countElemsWithPred xs predicato


-- 3) Implementare la funzione countDots ​che data una lista di Elem restituisce il numero di
-- elemento Dot presenti nella lista

countDots :: [Elem] -> Int
countDots [] = 0
countDots (Dot:xs) = 1 + countDots xs
countDots (_:xs) = countDots xs


-- 4) ​Implementare la funzione rev​ che data una lista di elementi generica, restituisce la lista con
-- gli elementi in ordine inverso

rev :: [a] -> [a]
rev [] = []
rev (x:xs) = rev xs ++ [x]


-- 5)​ Implementare la funzione removeAll che data una lista di elementi generica e un elemento,
-- restituisca una lista pari a quella passata senza tutti gli elementi pari all’elemento passato

removeAll :: (Eq a) => [a] -> a -> [a]
removeAll [] _ = []
removeAll (x:xs) v
  | x == v = removeAll xs v
  | otherwise = [x] ++ removeAll xs v


-- 6) Implementare la funzione merge​ che date due liste ordinate di elementi interi, computa una
-- sola lista ordinata

merge :: [Int] -> [Int] -> [Int]
merge [] [] = []
merge [] xs = xs
merge xs [] = xs
merge (x:xs) (y:ys)
  | x < y = x : merge xs (y:ys)
  | x > y = y : merge (x:xs) ys
  | otherwise = x : y : merge xs ys


-- 07) Implementare la funzione isPresentInBST generica che dato un valore e un BSTree restituisce
-- vero o falso a seconda che il valore sia o meno presente

isPresentInBST :: (Eq a, Ord a) => a -> BSTree a -> Bool
isPresentInBST _ Nil = False
isPresentInBST v (Node n l r)
  | n == v = True
  | n < v = isPresentInBST v r
  | otherwise = isPresentInBST v l


-- 8) Implementare la funzione buildBST che, data la una lista di stringhe, costruisce l’albero binario ordinato
-- che contiene le stringhe e la loro posizione all’interno della lista.

insertIntoBST :: (Ord a) => a -> BSTree a -> BSTree a
insertIntoBST s Nil = Node s Nil Nil
insertIntoBST s (Node v l r)
  | s <= v = Node v (insertIntoBST s l) r
  | otherwise = Node v l (insertIntoBST s r)

buildBST :: (Ord a) => [a] -> BSTree a
buildBST [] = Nil
buildBST (x:xs) = insertIntoBST x (buildBST xs)


-- PRODUCT TYPES
data Elem = Dot | Star
data BSTree a = Nil | Node a (BSTree a) (BSTree a) deriving (Show)

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

array3 :: [String]
array3 = ["Luca","Andrea","Luciana","Leonardo","Lucky","Alessio","Vittoria","Lorenzo","Paola","Ilario"]

array4 :: [Int]
array4 = [10,10,5,4,3,6,7,13,14,11]
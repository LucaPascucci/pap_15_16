{-
IMPORTANTE: La funzione deve essere progettata esclusivamente in termini di
funzioni high-order pre-definite quali map, foldr/foldl, filter, le relative espressioni lambda e
funzioni predefinte come length e fromIntegral.
-}

--1 Scrivere la funzione che, data una lista di elementi di tipo Elem, dove Elem è definito come:
data Elem = Dot | Square

-- restituisce l’indice della sotto-sequenza di Dot più lunga. Assumere che il primo valore della lista abbia indice pari a 0.

maxSubSeq :: [Elem] -> Maybe Int
maxSubSeq [] = Nothing


{-
Esempi:
maxSubSeq [Square, Square] -> Nothing
maxSubSeq [Dot, Square, Square, Dot, Dot, Dot, Square, Dot, Dot] -> 3 (indice di partenza della sotto-sequenza più lunga)
-}


--2 Scrivere la funzione che, dato un albero binario di elementi di tipo Elem, determina il livello dell’albero con il numero maggiore di elementi Square (radice = livello 0).
-- Segue la definizione di Btree:
data BTree a = Empty | Node a (BTree a) (BTree a)

--levelWithMaxDots :: BTree Elem -> Maybe Int

{-
Esempi:
levelWithMaxDots tree1 -> Nothing
levelWithMaxDots tree2 -> 2
-}


--3 Scrivere la funzione che, data una lista di numeri reali, stampa in uscita gli scarti dei valori dalla media, uno per linea, considerando solo gli elementi della lista di valore maggiore o uguale al valore della media stessa.

--printVariance :: [Float] -> IO()

{-
Esempio:  printVariance [5.0, 7.0, 6.0, 9.0, 1.0, 2.0]
Stampa:
2.0
1.0
4.0
-}
-- Dati di appoggio

array :: [Elem]
array = [Square, Square]

array1 :: [Elem]
array1 = [Dot, Square, Square, Dot, Dot, Dot, Square, Dot, Dot]

tree1 = (Node Dot Empty Empty)
tree2 = (Node Dot (Node Dot (Node Square Empty Empty) (Node Dot (Node Dot Empty Empty) (Node Dot Empty Empty))) (Node Square (Node Square Empty (Node Dot Empty Empty))Empty))

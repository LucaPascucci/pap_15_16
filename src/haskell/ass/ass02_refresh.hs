import Screen

-- 1) Siano dati i seguenti tipi:

data Sym = Dot | Dash
data Code = Single Sym | Comp Sym Code

-- Implementare la funzione:
-- longestDashSeq :: Code -> Maybe Int
-- La funzione deve determinare la lunghezza della sequenza di Dash più lunga nel Code specificato.
-- Una sequenza di Dash può essere definita come o Single Dash (lunghezza 1) oppure da un (Comp Dash s), dove s è una sequenza di Dash, la cui lunghezza è pari a 1 + lunghezza s.
-- Se Code non contiene Dash deve essere restituito Nothing.

longestDashSeq :: Code -> Maybe Int -- può restituire anche 'Nothing'
longestDashSeq c = worker c 0 0 -- primo valore -> sequenza massima | secondo valore -> sequenza corrente
    where
        worker (Single Dot) maxSeq currSeq
            | maxSeq == 0 && currSeq ==0 = Nothing
            | currSeq /= 0 = Just currSeq
            | otherwise = Just maxSeq
        worker (Single Dash) maxSeq currSeq
            | maxSeq >= (currSeq + 1) = Just maxSeq
            | otherwise = Just (currSeq + 1)
        worker (Comp Dot c) maxSeq currSeq
            | maxSeq < (currSeq) = worker c currSeq 0
            | otherwise = worker c maxSeq 0
        worker (Comp Dash c) maxSeq currSeq = worker c maxSeq (currSeq + 1)


test1 = longestDashSeq (Single Dot) -- Nothing
test2 = longestDashSeq (Single Dash) -- Just 1
test3 = longestDashSeq (Comp Dash (Comp Dot (Comp Dash (Comp Dash (Single Dot))))) -- Just 2
test4 = longestDashSeq (Comp Dash (Comp Dot (Comp Dash (Comp Dash (Comp Dot (Comp Dash (Comp Dash (Single Dash)))))))) -- Just 3

-- 2) Implementare la funzione
-- findMax :: [Int] -> Maybe Int
-- che data una lista di interi l, determina - se esiste - il valore massimo utilizzando esclusivamente funzioni high-order di mapping o folding.

findMax :: [Int] -> Maybe Int
findMax [] = Nothing
findMax (x:xs) = Just (foldr (\curr max -> if (curr > max) then curr else max) x xs) -- tramite una foldr scorro la lista passando inizialmente la testa come valore massimo

test5 = findMax [] -- Nothing
test6 = findMax [1,2,4,3] -- Just 4

--3) Sono dati i tipi

type BuyerId = String
type City = String
type Year = Int
type Amount = Int

data Buyer = Buyer BuyerId City deriving (Show)
data Transaction = Trans BuyerId Year Amount deriving (Show)

data DBase = DBase [Buyer] [Transaction]

-- Un Buyer rappresenta un utente che ha effettuato acquisti (ad esempio di un certo store online), caratterizzato da un certo identificatore e città di provenienza.
-- Transaction rappresenta una transazione di acquisto, effettuata da un certo buyer, un certo anno, per un certo ammontare.

buyers = [Buyer "maria" "Milano", Buyer "stefano" "Roma", Buyer "laura" "Cesena", Buyer "alice" "Cesena"]
transactions = [Trans "alice" 2011 300, Trans "maria" 2012 1000, Trans "maria" 2011 400, Trans "laura" 2012 710, Trans "stefano" 2011 700, Trans "stefano" 2012 950, Trans "alice" 2015 1000, Trans "laura" 2016 2000]
db = DBase buyers transactions
dbb = DBase buyers []
dbt = DBase [] transactions

-- Definire le seguenti funzioni di query, utilizzando funzioni high-order e composizioni di funzioni:

-- querySortedTransList :: DBase -> Year -> [Transaction]
-- Ottiene la lista delle transazioni riferite all’anno specificato, ordinate per amount

-- funzione di quicksort generica (è necessario passargli una funzione lambda per specificare in base e come (crescente o decrescente) ordinare la lista
quicksort :: (a -> a -> Bool) -> [a] -> [a]
quicksort _ [] = []
quicksort f (x:xs) = quicksort f l ++ [x] ++ quicksort f r
  where
    l = [ y | y <- xs, f y x]
    r = [ z | z <- xs, not (f z x)]

querySortedTransList :: DBase -> Year -> [Transaction]
querySortedTransList (DBase _ transactions ) year = (quicksort c . filter(\(Trans _ currYear _) -> currYear == year)) transactions
    where
        c = \(Trans _ _ amount1) (Trans _ _ amount2) -> amount1 < amount2 -- funzione lambda per ordinare la lista di

test7 = querySortedTransList db 2011 -- [Trans "alice" 2011 300,Trans "maria" 2011 400,Trans "stefano" 2011 700]


-- queryBuyerCities :: DBase -> [City]
-- Ottiene l’elenco delle città distinte dei buyer

quicksort' :: Ord a => [a] -> [a]
quicksort' []     = []
quicksort' (p:xs) = (quicksort' lesser) ++ [p] ++ (quicksort' greater)
    where
        lesser  = filter (< p) xs
        greater = filter (>= p) xs

queryBuyerCities :: DBase -> [City]
queryBuyerCities (DBase buyers _) = (quicksort' . foldr (\(Buyer _ city) l -> if (not (member city l)) then (city:l) else l) []) buyers
    where
        member _ [] = False
        member v (x:xs)
            | v == x = True
            | otherwise = member v xs

test8 = queryBuyerCities db -- ["Milano","Roma","Cesena"]

-- queryExistBuyerFrom :: DBase -> City -> Bool
-- Verifica se esistono buyer di una certa città
queryExistBuyerFrom :: DBase -> City -> Bool
queryExistBuyerFrom (DBase buyers _) city = not ((checkEmpty . foldr (\(Buyer _ city') l -> if (city == city') then (city':l) else l) []) buyers)
    where
        checkEmpty [] = True
        checkEmpty _ = False

test9 = queryExistBuyerFrom db "Ancona" -- False
test10 = queryExistBuyerFrom db "Cesena" -- True

-- queryAmountsFromCity :: DBase -> City -> Amount
-- Determina la somma delle transazioni eseguite da buyer di una certa città

queryAmountsFromCity :: DBase -> City -> Amount
queryAmountsFromCity (DBase buyers transactions) city = foldr (\(Trans buID _ amount) tot -> if (checkBuyer buyers buID city) then tot+amount else tot) 0 transactions
    where
        checkBuyer [] _ _ = False
        checkBuyer (Buyer id c :tail) buID city
            | id == buID && c == city = True
            | otherwise = checkBuyer tail buID city

test11 = queryAmountsFromCity db "Cesena" -- 4010
test12 = queryAmountsFromCity db "Milano" -- 1400

-- EX. 4
-- A partire da questi tipi, definire il tipo TObj (che sta per “textual object” o “terminal object”) che può essere :
-- una stringa di testo s di colore c posizionata in una certa posizione p
-- una linea orizzontale di n caratteri ‘-’, a partire da una posizione p, di colore c
-- una linea verticale di n caratteri ‘|’, a partire da una posizione p, di colore c
-- un rettangolo, specificando vertice top-left, larghezza w e altezza h e colore
-- un box - come rettangolo pieno - specificando vertice top-left, larghezza w e altezza h e colore

data TObj = Text Stri Color Pos
    | OLine Int Color Pos
    | VLine Int Color Pos
    | Rect Int Int Color Pos
    | Box Int Int Color Pos
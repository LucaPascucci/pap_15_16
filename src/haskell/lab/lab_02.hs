--1) Implementare la funzione mapLen che data una lista di parole, computi la lista di (p,l), dove l è la lunghezza della parola p, usando la funzione map.

mapLen :: [String] -> [(String,Int)]
mapLen l = map (\w -> (w, len w)) l -- map + funzione lambda che per ogni parola nella lista crea una coppia (w, len w)
  where
    len w = foldr (\_ c -> c + 1) 0 w -- foldr + funzione lambda che scorre ogni carattere di una parola e incrementa il contatore c a partire da 0

--mapLen con List comprehension
mapLen' :: [String] -> [(String,Int)]
mapLen' l = [ (w, length w) | w <- l ]

mapLen'' :: [String] -> [(String,Int)]
mapLen'' l = map (\w -> (w, length w)) l

test1 = mapLen ["Prova","Test","Luca"] -- [("Prova",5),("Test",4),("Luca",4)]
test2 = mapLen [] -- []
test3 = mapLen' ["Prova","Luca"] -- [("Prova",5),("Luca",4)]
test4 = mapLen'' ["Prova","Luca"] -- [("Prova",5),("Luca",4)]

-- 2) Implementare la funzione selectedLen che data una lista di parole e un intero w, computi la lista di (p,l) delle parole la cui lunghezza l sia > di w, usando la funzione map e filter.

selectedLen :: [String] -> Int -> [(String,Int)]
selectedLen l w = filter (\(_, l') -> l' > w) (mapLen l)
--filter (predicato da verificare lista da passare) in questo caso il predicato è una lambda exp

-- Anche questa è fattibile con list comprehension. (Notare che la signature non sarebbe richiesta, in quanto inferita da Haskell. Di solito la si mette per documentazione e sicurezza.)
selectedLen' :: [String] -> Int -> [(String,Int)]
selectedLen' l v = [ (w, length w) | w <- l, length w > v] -- Notare che qui non c'è lazy evaluation:
-- Haskell non sa che la stessa espressione è presente due volte: in alternativa si può creare un ambiente locale in un linguaggio a vincoli tipo Prolog.
-- Qui non si riesce a fare senza eliminare la list comprehension.

-- versione come composizione di funzioni (ordine degli Input molto importante)
selectedLen'' :: Int -> [String] -> [(String,Int)]
selectedLen'' v = (filter (\(_, l) -> l > v) . mapLen) -- prima esegue "mapLen" e poi "filter" sul risultato

test5 = selectedLen ["Prova","Test","Luca"] 4 -- [("Prova",5)]
test6 = selectedLen' ["Prova","Test","Ok"] 3 -- [("Prova",5),("Test,4")]
test7 = selectedLen'' 1 ["Prova","Test","Ok"] -- [("Prova",5),("Test,4"),("Ok",2)]

--3) Implementare una funzione wordOcc che data una lista di parole e una parola, restituisce il numero di occorrenze della parola nella lista, usando una funzione di folding
wordOcc :: [String] -> String -> Int
wordOcc l v = foldr (\w c -> if (w == v) then c + 1 else c) 0 l -- foldr parte da destra e scorre la lista ed attraverso la funzione lambda unita all'if effettua il check
-- In questo caso la foldr ha un utilizzo simile al mantenimento di uno stato (si porta dietro un valore aggregando 0 -> 'c')

test8 = wordOcc ["Prova","Luca","Test","Luca"] "Luca" -- 2
test9 = wordOcc [] "Luca" -- 0

--4) Implementare una funzione wordsOcc che data una lista di parole, determini la lista delle occorrenze (occ,ws) - dove occ è il numero di occorrenze e ws è la lista delle parole di quella occorrenza, ordinate in modo crescente secondo le occorrenze

-- funzione per verificare se un valore appartiene ad una lista
member _ [] = False
member v (x:xs)
  | v == x = True
  | otherwise = member v xs

wordsOcc :: [String] -> [(Int, [String])]
wordsOcc l = (sortByOcc . myfold . getOccs) l  -- prima esegue "getOccs" successivamente "myfold" e poi "sortByOcc"
  where
    getOccs l = map (\w ->(wordOcc l w, w)) l -- mappa (occ,parola) utilizzando la funzione "wordOcc")
    myfold l = foldr (\occ l' -> updateList occ l') [] l -- unisce le parole con lo stessa occorrenza utilizzando foldr
      where
        updateList (o, w) [] = [(o ,[w])] -- prima coppia (occorrenze ,parola)
        updateList (o, w) ((o',w'):xs)
          | o == o' && not (member w w') = (o, w : w') : xs -- caso stesse occorrenze e parola non ancora presente nella lista w' -- w' inizialmente è una parola che poi diventerà una lista di parole
          | o == o' = (o, w') : xs -- caso stesse occorrenze e parola già presente
          | otherwise = (o', w') : updateList (o,w) xs -- caso coppia (occorrenze ,parola)
    sortByOcc [] = []
    sortByOcc ((o,l):xs) = sortByOcc le ++ [(o,l)] ++ sortByOcc ri -- prende la testa e la utilizza come elemento di divisione per ordinare ricorsivamente la coda dividendola in due sottoliste
      where
        le = [ (o',l') | (o',l') <- xs, o' < o] -- tutte le coppie (occorrenza,parole) prese dalla coda con occorrenze MINORI dell'elemento di divisione
        ri = [ (o'',l'') | (o'',l'') <- xs, o'' >= o] -- tutte le coppie (occorrenza,parole) prese dalla coda con occorrenze MAGGIORI dell'elemento di divisione

test10 = wordsOcc ["Prova","Luca","Test","Luca"] -- [(1,["Prova","Test"]),(2,["Luca"])]
test11 = wordsOcc [] -- []
test12 = wordsOcc ["Luca","Prova","Luca","Test","Prova","Luca"] -- [(1,["Test"]),(2,["Prova"]),(3,["Luca"])]


--Esercizi su IO
--1) Implementare una funzione che data una lista di elementi, restituisce un’azione il cui effetto è di stampare in uscita tutti gli elementi, uno per linea, indentati di 4 posizioni.
printElems :: [String] -> IO()
printElems [] = return ()
printElems (x:xs) = (putStrLn ("    " ++ x)) >> (printElems xs)

-- utilizzando una funzione
printElems' :: [String] -> IO()
printElems' l = foldr (\s full -> putStrLn ("    " ++ s) >> full) (return ()) l


test13 = printElems ["Prova","Test","Luca"]
test14 = printElems' ["Prova","Luca"]
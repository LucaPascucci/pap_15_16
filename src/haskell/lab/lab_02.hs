--type Word = String

--1) Implementare la funzione mapLen che data una lista di parole, computi la lista di (p,l), dove l è la lunghezza della parola p, usando la funzione map.

mapLen :: [String] -> [(String,Int)]
mapLen l = map (\w -> (w, len w)) l
  where
    len w = foldr (\_ c -> c + 1) 0 w

--mapLen con List comprehension
mapLen' :: [String] -> [(String,Int)]
mapLen' l = [ (w, length w) | w <- l ]


-- 2) Implementare la funzione selectedLen che data una lista di parole e un intero w, computi la lista di (p,l) delle parole la cui lunghezza l sia > di w, usando la funzione map e filter.

selectedLen :: [String] -> Int -> [(String,Int)]
selectedLen l v = filter (\(_, l') -> l' > v) (mapLen l)
--filter (predicato da verificare lista da passare) in questo caso il predicato è una lambda exp

--versione con List comprehension
selectedLen' :: [String] -> Int -> [(String,Int)]
selectedLen' l v = [ (w, length w) | w <- l, length w > v]

-- versione come composizione di funzioni
selectedLen'' :: Int -> [String] -> [(String,Int)]
selectedLen'' v = (filter (\(_, l) -> l > v) . mapLen)


--3) Implementare una funzione wordOcc che data una lista di parole e una parola, restituisce il numero di occorrenze della parola nella lista, usando una funzione di folding
wordOcc :: [String] -> String -> Int
wordOcc l v = foldr (\w c -> if (w == v) then c + 1 else c) 0 l
--foldr parte da destra e scorre la lista ed attraverso l'if effettua il check


--4) Implementare una funzione wordsOcc che data una lista di parole, determini la lista delle occorrenze (occ,ws) - dove occ è il numero di occorrenze e ws è la lista delle parole di quella occorrenza, ordinate in modo crescente secondo le occorrenze

--member
member _ [] = False
member v (x:xs)
  | v == x = True
  | otherwise = member v xs

wordsOcc :: [String] -> [(Int, [String])]
wordsOcc l = (sortByOcc . myfold . getOccs) l
  where
    getOccs l = map (\w ->(wordOcc l w, w)) l
    myfold l = foldr (\occ l' -> updateList occ l') [] l
      where
        updateList (o, w) [] = [(o ,[w])]
        updateList (o, w) ((o',lw):xs)
          | o == o' && not (member w lw)= (o, w:lw) : xs
          | o == o' = (o, lw) : xs
          | otherwise = (o', lw) : updateList (o,w) xs
    sortByOcc [] = []
    sortByOcc ((o,l):xs) = sortByOcc le ++ [(o,l)] ++ sortByOcc ri
      where
        le = [ (o',l') | (o',l') <- xs, o' < o]
        ri = [ (o'',l'') | (o'',l'') <- xs, o'' >= o]


--Esercizi su IO
--1) Implementare una funzione che data una lista di elementi, restituisce un’azione il cui effetto è di stampare in uscita tutti gli elementi, uno per linea,  indentati di 4 posizioni.
printElems :: [String] -> IO()
printElems [] = return ()
printElems (x:xs) = (putStrLn (" " ++ x)) >> (printElems xs)


printElems' :: [String] -> IO()
printElems' l = foldr (\s full -> putStrLn ("    " ++ s) >> full) (return ()) l

data BSTree a = Nil | Node a (BSTree a) (BSTree a)

-- Scorro ricorsivamente l'albero ed applico la funzione ad ogni nodo
bstMap :: (a -> b) -> BSTree a -> BSTree b
bstMap _ Nil = Nil
bstMap f (Node v l r) = Node (f v) (bstMap f l) (bstMap f r)

-- Foldl
{- Eseguo la fold prima sul subtree di destra
 ed il risultato viene poi passato al subtree di sinistra.
 Alla fine viene aggiunta al raggruppamento anche la radice dell'albero -}
bstFold :: (b -> a -> b) -> b -> BSTree a -> b
bstFold _ res Nil = res
bstFold f v (Node v1 l r) = f sbl v1
  where
    sbl = bstFold f sbr l
    sbr = bstFold f v r

bstFilter :: (a -> Bool) -> BSTree a -> BSTree a
bstFilter _ Nil = Nil
bstFilter f (Node v Nil Nil)
  | f v == True = Node v Nil Nil
  | otherwise = Nil
bstFilter f (Node v l Nil)
  | f v == True = Node v (bstFilter f l) Nil
  | otherwise = bstFilter f l
bstFilter f (Node v Nil r)
  | f v == True = Node v Nil (bstFilter f r)
  | otherwise = bstFilter f r
bstFilter f (Node v l r)
  | f v == True = Node v (bstFilter f l) (bstFilter f r)
  {- Prelevo il nodo con valore minore nel sotto albero di destra
  e lo sostituisco al nodo eliminato ed allo stesso tempo elmino
  il nodo prelevato dal subtree di destra -}
  | otherwise = bstFilter f (Node (minSuccessor r) l (deleteMin r))
  where
    -- Preleva il nodo più a sinistra che quindi ha valore minore
    minSuccessor (Node x Nil _) = x
    minSuccessor (Node x l _) = minSuccessor l

    {- Elimina il nodo più a sinistra quindi se trovo un nodo
    che ha solo un subtree a destra vuol dire che è il nodo
    con valore minore e quindi da eliminare -}
    deleteMin (Node x Nil Nil) = Nil
    deleteMin (Node x Nil r) = r -- eliminazione del nodo più a sinistra
    deleteMin (Node x l r) = Node x (deleteMin l) r

-- Scorro ricorsivamente l'albero ed ad ogni nodo applico l'azione passata
bstForEach :: (a -> IO ()) -> BSTree a -> IO ()
bstForEach _ Nil = return()
bstForEach f (Node v l r) = (bstForEach f l) >> (f v) >> (bstForEach f r)

--Test
testTreeInt = (Node 10 (Node 4 (Node 3 Nil Nil) (Node 7 Nil (Node 8 Nil Nil)))(Node 13 (Node 11 Nil Nil) (Node 15 Nil (Node 18 (Node 16 Nil Nil) Nil))))

testBstMap = (bstForEach out . bstMap mapFun) testTreeInt
  where
    out = \x -> print x
    mapFun = \x -> x + 1

testBstFold = print (bstFold foldFun 0 testTreeInt)
  where
    foldFun = \x v -> x + v

testBstFilter = (bstForEach out . bstFilter filtFun) testTreeInt
  where
    out = \x -> print x
    filtFun = \x -> x >= 10

type Age = Int
data Person = Person String Age

printPers :: BSTree Person -> Int -> Int -> IO()
printPers Nil _ _ = return()
printPers bst minAge maxAge = (bstForEach out . bstFilter check) bst
  where
    out = \(Person n _ ) -> putStrLn n
    check = \(Person _ a) -> minAge <= a && a <= maxAge

--Test
luca = Person "Luca" 23
andrea = Person "Andrea" 18
luciana = Person "Luciana" 54
leonardo = Person "Leonardo" 58
lucky = Person "Lucky" 1
alessio = Person "Alessio" 23
vittoria = Person "Vittoria" 18
lorenzo = Person "Lorenzo" 50
paola = Person "Paola" 84
ilario = Person "Ilario" 90

testTree = Node ilario (Node alessio Nil (Node andrea Nil Nil)) (Node paola (Node lorenzo (Node leonardo Nil Nil) (Node lucky (Node luciana (Node luca Nil Nil) Nil) Nil)) (Node vittoria Nil Nil))

testPrintPers = printPers testTree 10 50

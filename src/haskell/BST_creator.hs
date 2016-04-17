data BSTree a = Nil | Node a (BSTree a) (BSTree a) deriving (Show)

insertInBST :: (Ord a) => a -> BSTree a -> BSTree a
insertInBST s Nil = (Node s Nil Nil)
insertInBST s (Node v l r)
  | s <= v = (Node v (insertInBST s l) r)
  | otherwise = (Node v l (insertInBST s r))

--buildBST
buildBST :: (Ord a) => [a] -> BSTree a
buildBST [] = Nil
buildBST (x:xs) = insertInBST x (buildBST xs)

test = buildBST ["Luca","Andrea","Luciana","Leonardo","Lucky","Alessio","Vittoria","Lorenzo","Paola","Ilario"]

test2 = buildBST [10,5,8,13,19,3,16,2,1]

{-
  PAP 2015-2016
  Assignment #1 - Soluzioni
-}

-- Ex. 1

isSorted :: [Int] -> Bool
isSorted [] = True
isSorted [_] = True
isSorted (x:y:xs)
  | x <= y = isSorted (y:xs)
  | otherwise = False

-- Ex. 2

occSimple :: [String] -> String -> [Int]
occSimple l s = occSimpleWithIndex l s 0
  where
    occSimpleWithIndex [] _ _ = []
    occSimpleWithIndex (x:xs) s i --i = indice
      | x == s = i : occs
      | otherwise = occs
        where
          occs = occSimpleWithIndex xs s (i+1)

-- Ex. 3

occFull :: [String] -> [(String,[Int])]
occFull l = occFull' l l []
  where
    occFull' [] _ _ = []
    occFull' (x:xs) fl l
      | not (member x l) = (x,occSimple fl x) : occFull' xs fl (x:l)
      | otherwise = occFull' xs fl l
      where
        member _ [] = False
        member v (x:xs)
          | v == x = True
          | otherwise = member v xs

-- Ex. 4

data Sym = Dot | Dash
data Code = Single Sym | Comp Sym Code

countDash :: Code -> Int
countDash (Single Dot) = 0
countDash (Single Dash) = 1
countDash (Comp Dot c) = countDash  c
countDash (Comp Dash c) = 1 + countDash c

-- Ex. 5

showCode :: Code -> String
showCode (Single Dot) = "."
showCode (Single Dash) = "-"
showCode (Comp Dot c) = "." ++ showCode c
showCode (Comp Dash c) = "-" ++ showCode c

-- Ex. 6

data Digit = Zero | One
type BNum = [Digit]

equalsBNum :: BNum -> BNum -> Bool
equalsBNum [] [] = True
equalsBNum [] _ = False
equalsBNum _ [] = False
equalsBNum (x:xs) (y:ys)
  | equalsDigit x y = equalsBNum xs ys
  | otherwise = False
  where
    equalsDigit Zero Zero = True
    equalsDigit Zero _ = False
    equalsDigit One One = True
    equalsDigit One _ = False

-- Ex. 7

rev [] = []
rev (x:xs) = rev xs ++ [x]

convBNum :: BNum -> Int
convBNum b = convBNum' (rev b) 1
  where
    convBNum' [] _ = 0
    convBNum' (Zero:xs) nd = convBNum' xs (nd*2)
    convBNum' (One:xs) nd = nd + convBNum' xs (nd*2)

-- Ex. 8

sumBNum :: BNum -> BNum -> BNum
sumBNum b1 b2 = rev (sumBNum' (rev b1) (rev b2) 0)
  where
    sumBNum' [] b 0 = b
    sumBNum' [] [] 1 = [One]
    sumBNum' [] (Zero:xs) 1 = One : xs
    sumBNum' [] (One:xs) 1 = Zero : sumBNum' [] xs 1
    sumBNum' b [] 0 = b
    sumBNum' (Zero:xs) [] 1 = One : xs
    sumBNum' (One:xs) [] 1 = Zero : sumBNum' xs [] 1
    sumBNum' (Zero:xs) (Zero:ys) 0 = Zero : sumBNum' xs ys 0
    sumBNum' (Zero:xs) (One:ys) 0 = One : sumBNum' xs ys 0
    sumBNum' (One:xs) (Zero:ys) 0 = One : sumBNum' xs ys 0
    sumBNum' (One:xs) (One:ys) 0 = Zero : sumBNum' xs ys 1
    sumBNum' (Zero:xs) (Zero:ys) 1 = One : sumBNum' xs ys 0
    sumBNum' (Zero:xs) (One:ys) 1 = Zero : sumBNum' xs ys 1
    sumBNum' (One:xs) (Zero:ys) 1 = Zero : sumBNum' xs ys 1
    sumBNum' (One:xs) (One:ys) 1 = One : sumBNum' xs ys 1

-- Ex. 9

data BTree a = Nil | Node a (BTree a) (BTree a)

countZeroInTree :: BTree Digit -> Int
countZeroInTree Nil = 0
countZeroInTree (Node Zero l r) = 1 + countZeroInTree l + countZeroInTree r
countZeroInTree (Node _ l r) = countZeroInTree l + countZeroInTree r

-- Ex. 10

getValuesLessThan :: BTree Int -> Int -> [Int]
getValuesLessThan Nil _ = []
getValuesLessThan (Node x l r) v
  | x >= v = getValuesLessThan l v
  | otherwise = toList l ++ [x] ++ getValuesLessThan r v
  where
    toList Nil = []
    toList (Node x l r) = toList l ++ [x] ++ toList r

-- aux

showBNum :: BNum -> String
showBNum [] = ""
showBNum (x:xs) = showDigit x ++ showBNum xs
  where
    showDigit Zero = "0"
    showDigit One = "1"

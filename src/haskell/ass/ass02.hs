import Screen

--1) Siano dati i seguenti tipi

data Sym = Dot | Dash
data Code = Single Sym | Comp Sym Code

--Implementare la funzione che deve determinare la lunghezza della sequenza di Dash più lunga nel Code specificato. Una sequenza di Dash può essere definita come o Single Dash (lunghezza 1) oppure da un (Comp Dash s), dove s è una sequenza di Dash, la cui lunghezza è pari a 1 + lunghezza s. Se Code non contiene Dash deve essere restituito Nothing.

longestDashSeq :: Code -> Maybe Int
longestDashSeq c = worker c 0 0 -- primo valore = sequenza massima, secondo valore = sequenza corrente
  where
    worker (Single Dot) maxSeq currSeq
      | maxSeq == 0 = Nothing
      | otherwise = Just maxSeq
    worker (Single Dash) maxSeq currSeq
      | maxSeq >= (currSeq + 1) = Just maxSeq
      | otherwise = Just (currSeq + 1)
    worker (Comp Dash c) maxSeq currSeq
      | maxSeq >= (currSeq + 1) = worker c maxSeq (currSeq + 1)
      | otherwise = worker c (currSeq + 1) (currSeq + 1)
    worker (Comp Dot c) maxSeq currSeq = worker c maxSeq 0  --azzero la sequenza

test1 = longestDashSeq (Single Dot) --Nothing
test2 = longestDashSeq (Single Dash) --Just 1
test3 = longestDashSeq (Comp Dash (Comp Dot (Comp Dash (Comp Dash (Single Dot))))) --Just 2

-- 2) Implementare la funzione che data una lista di interi l, determina - se esiste - il valore massimo utilizzando esclusivamente funzioni high-order di mapping o folding.

findMax :: [Int] -> Maybe Int
findMax [] = Nothing
--tramite una foldr scorro la lista passando inizialmente la testa come valore massimo
findMax (x:xs) = Just (foldr (\val m -> if (val >= m) then val else m) x xs)

test4 = findMax [] --Nothing
test5 = findMax [1,2,4,3,0] --Just 4

--3) Sono dati i tipi

type BuyerId = String
type City = String
type Year = Int
type Amount = Int

data Buyer = Buyer BuyerId City deriving (Show)
data Transaction = Trans BuyerId Year Amount deriving (Show)

data DBase = DBase [Buyer] [Transaction]

buyers = [Buyer "maria" "Milano", Buyer "stefano" "Roma", Buyer "laura" "Cesena", Buyer "alice" "Cesena"]
transactions = [Trans "alice" 2011 300, Trans "maria" 2012 1000, Trans "maria" 2011 400, Trans "laura" 2012 710, Trans "stefano" 2011 700, Trans "stefano" 2012 950, Trans "alice" 2015 1000, Trans "laura" 2016 2000]
db = DBase buyers transactions
dbb = DBase buyers []
dbt = DBase [] transactions

--Ottiene la lista delle transazioni riferite all’anno specificato, ordinate per amount
querySortedTransList :: DBase -> Year -> [Transaction]
querySortedTransList (DBase _ trans) y = (quicksort . filter (\(Trans _ y' _) -> y' == y)) trans
  where
    quicksort [] = []
    quicksort (Trans bID y a:xs) = quicksort ([(Trans fbID fy fa) | (Trans fbID fy fa) <- xs , fa < a]) ++
      [Trans bID y a] ++
      quicksort ([(Trans fbID fy fa) | (Trans fbID fy fa) <- xs , fa >= a])

--Ottiene l’elenco delle città distinte dei buyers
queryBuyerCities :: DBase -> [City]
queryBuyerCities (DBase buyers _ ) = removeDuplicate(worker buyers)
  where
    --prelevo tutte le città dei compratori
    worker [] = []
    worker (Buyer _ c:cs) = c : worker (cs)
    --rimuovo di duplicati inserendo la testa e la coda filtrata dal valore della testa appena inserita
    removeDuplicate [] = []
    removeDuplicate (x:xs) = x : removeDuplicate (filter(\y -> x /= y) xs)

--Verifica se esistono buyer di una certa città
--rimuovo tramite filter i compratori che non appartengono alla città inserita
queryExistBuyerFrom :: DBase -> City -> Bool
queryExistBuyerFrom (DBase buyers _ ) v = check(filter(\(Buyer _ c) -> c == v) buyers )
  where
    check l
      | null l = False --Lista vuota
      | otherwise = True --Lista con almeno un elemento

--Determina la somma delle transazioni eseguite da buyers di una certa città
--filtro i compratori in base alla città ed il risultato viene dato a join
queryAmountsFromCity :: DBase -> City -> Amount
queryAmountsFromCity (DBase buyers trans) v = (join trans . filter(\(Buyer _ c) -> c == v)) buyers
  where
    --esegue una lettura delle transazioni e se il compratore compare nella lista filtrata dei compratori aggiungo la somma da lui spesa
    join trans buyers = foldr (\(Trans bID y a) tot -> if (member bID buyers) then tot + a else tot) 0 trans
      where
        --controllo se il compratore v è presente nella lista dei buyers filtrati
        member _ [] = False
        member v (Buyer bID _:xs) | v == bID = True
        member v (_:xs) = member v xs

test6 = querySortedTransList db 2011 -- [ Trans "alice" 2011 300, Trans "maria" 2011 400, Trans "stefano" 2011 700]
test7 = queryBuyerCities db --[ "Milano", "Roma", "Cesena"]
test8 = queryExistBuyerFrom db "Ancona" --False
test9 = queryExistBuyerFrom db "Cesena" --True
test10 = queryAmountsFromCity db "Cesena" --4010

--4)

--Definire il tipo TObj (che sta per “textual object” o “terminal object”)
data TObj = Text String Color Pos | OLine Int Pos Color | VLine Int Pos Color | Rect Pos Int Int Color | Box Pos Int Int Color deriving (Show)

viewport = Viewport (1,1) 20 20
viewportClip = Viewport (1,1) 7 7
text = Text "prova" RED (1,1)
orizLine = OLine 5 (1,2) BLUE
vertLine = VLine 5 (1,4) GREEN
rect = Rect (3,3) 3 4 WHITE
box = Box (9,4) 3 4 CYAN

objs = [text, orizLine, vertLine, rect, box]

--classe Viewable o, a che supporta le seguenti funzioni
class Viewable o where
  {- dato una Viewport e un elemento Viewable determina l’azione che ne esegue il rendering
   su terminale, considerando  come viewport quella specificata (per cui le posizioni degli
   elementi sono da considerare relativi alla viewport) -}
  render :: Viewport -> o ->  IO ()
  -- come render, ma con clipping
  renderWithClipping :: Viewport -> o ->  IO ()
  {- dato una Viewport e una lista di Viewable determina l’azione che esegue il rendering
  di tutte gli elementi sulla view port -}
  renderAll :: Viewport -> [o] ->  IO ()

--crea una stringa di lunghezza l ripetendo il carattere c
createString l c
  | l == 0 = []
  | otherwise = [c] ++ createString (l-1) c

--controlla se un punto si trova all'interno della viewport
checkPos (Viewport (x,y) w h) (x',y')
  | (x <= x') && (y <= y') && ((x + w) > x') && ((y + h) > y') = True
  | otherwise = False

instance Viewable TObj where

  render (Viewport (x,y) w h) (Text s c (x',y')) = setFColor c >> writeAt (x+x',y+y') s >> setFColor WHITE >> goto(w,h)

  --riutilizzo la funzione precedente passandogli una stringa lunga n formata da '-' creata con createString
  render wp (OLine n p c) = render wp (Text (createString n '-') c p)

  render (Viewport (x,y) w h) (VLine n (x',y') c) = do
    setFColor c
    printPipes n (x+x') (y+y')
    setFColor WHITE
    goto(w,h)
      where
        printPipes 0 _ _ = return ()
        printPipes n x y = writeAt (x,y) "|" >> printPipes (n-1) x (y+1)

  {- composto da 2 linee orizzontali e 2 linee verticali con opportune
  correzioni di posizionamento per sistemare il disegno -}
  render wp (Rect (x,y) w h c) = do
    render wp (VLine h (x,y) c)
    render wp (OLine w (x+1,y) c)
    render wp (OLine w (x+1,y+h-1) c)
    render wp (VLine h (x+w+1,y) c)

  --composto da h linee di testo contenente spazi
  render wp (Box p w h c) = do
    setBColor c --setto il background color cosi quando renderdizzo spazi risulterano pieni
    fillBox h (createString w ' ') p
    setBColor BLACK
      where
        fillBox 0 _ _ = return ()
        --renderdizzo una stringa vuota lunga w e ricorsivamente passo alla posizione sottostante
        fillBox n s (x',y') = render wp (Text s c (x',y')) >> fillBox (n-1) s (x',y'+1)

  renderWithClipping (Viewport (x,y) w h) (Text s c p) = do
    setFColor c
    printChar s p
    setFColor WHITE
    goto(w,h)
      where
        --controllo carattere per carattere se si trova all'interno della view
        printChar [] _ = return()
        printChar (c:cs) (x',y')
          --se si trova lo stampo e procedo ricorsivamente al successivo, altrimenti procedo solamente al successivo
          | checkPos (Viewport (x,y) w h) (x',y') = writeAt (x+x',y+y') [c] >> printChar cs ((x'+1),y')
          | otherwise = printChar cs ((x'+1),y')

  --riutilizzo la funione precedente passandogli una stringa lunga n formata da '-' creata con createString
  renderWithClipping wp (OLine n p c) = renderWithClipping wp (Text (createString n '-') c p)

  renderWithClipping (Viewport (x,y) w h) (VLine n p c) = do
    setFColor c
    printPipesClip n p
    setFColor WHITE
    goto(w,h)
      where
        --controllo carattere per carattere se si trova all'interno della view
        printPipesClip 0 _ = return ()
        printPipesClip n (x',y')
          --se si trova lo stampo e procedo ricorsivamente al successivo, altrimenti procedo solamente al successivo
          | checkPos (Viewport (x,y) w h) (x',y') = writeAt (x+x',y+y') "|" >> printPipesClip (n-1) (x',(y'+1))
          | otherwise = printPipesClip (n-1) (x',y'+1)

  {- composto da 2 linee orizzontali e 2 linee verticali con opportune
  correzioni di posizionamento per sistemare il disegno -}
  renderWithClipping wp (Rect (x,y) w h c) = do
    renderWithClipping wp (VLine h (x,y) c)
    renderWithClipping wp (OLine w (x+1,y) c)
    renderWithClipping wp (OLine w (x+1,y+h-1) c)
    renderWithClipping wp (VLine h (x+w+1,y) c)

  --composto da h linee di testo contenente spazi
  renderWithClipping wp (Box p w h c) = do
    setBColor c --setto il background color cosi quando renderdizzo spazi risulterano pieni
    fillBoxClip h (createString w ' ') p
    setBColor BLACK
      where
        fillBoxClip 0 _ _ = return ()
        --renderdizzo una stringa vuota lunga w e ricorsivamente passo alla posizione sottostante
        fillBoxClip n s (x,y) = renderWithClipping wp (Text s c (x,y)) >> fillBoxClip (n-1) s (x,y+1)

  renderAll _ [] = return ()
  renderAll wp (x:xs) = render wp x >> renderAll wp xs

test11 = renderAll viewport objs
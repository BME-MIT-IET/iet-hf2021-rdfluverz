# Manuális tesztek

## Karakterkezelés teszt

Az input .csv fájlban ékezetes (á, é, ő, ű) betűk szerepelnek, a teszt célja megvizsgálni, hogy az alkalmazás ezeket megfelelően kezeli és az outputban is helyesen szerepelnek.

Az input file: `karakterkezeles.csv`

Kiadott parancs:

     java -jar dist/lib/csv2rdf.jar manual-tests/template/cars-template.ttl manual-tests/input/karakterkezeles.csv karakterkezeles.ttl

Az output fájl UTF-8 kódolású, de semmilyen ékezetes betűt nem tud kezelni:
![](karakterkezeles.png)

## Üres input file teszt

A teszt célja hogy megvizsgálja, hogy akkor is lefut-e a program ha az input csv file üres.
Az alkalmazás helyesen kivételt dobott és jelezte, hogy üres az input file, .ttl file-t nem hozott létre

Input: `empty.csv`

Kiadott parancs: 

    java -jar dist/lib/csv2rdf.jar manual-tests/template/cars-template.ttl manual-tests/input/empty.csv empty.ttl


![](empty-input.png)

## 3-nál több argumentum teszt

A teszt célja, hogy megvizsgálja, hogy hogyan kezeli a program ha túl sok argumentumot ad meg a felhasználó
A program helyesen kezelte, Too many arguments kivételt dobott, nem jött létre .ttl file

Kiadott parancs: 

    java -jar dist/lib/csv2rdf.jar manual-tests/template/cars-template.ttl manual-tests/input/karakterkezeles.csv karakterkezeles.ttl karakterkezeles.ttl

![](too-many-arguments.png)

## Escape karakter teszt
A programalapértelmezett escape karaktere a `/`, ezért ahelyett `#`-el teszteltük.
A program helyesen kezeli az escape karaktereket, két `#` volt egymás mellett az inputban, az outputban csak egy

Input: `escape.csv`

Kiadott parancs: 

    java -jar dist/lib/csv2rdf.jar manual-tests/template/cars-template.ttl manual-tests/input/escape.csv escape.ttl -e #

## Szeparátor karakter teszt

A programban az alapértelmezett szeparátor a csv input file-nál a `,` ezért `;`-t használtunk.
A program megfelelően kezelte az új szeparátor beállítást.

Input: `separator.csv`

Kiadott parancs:

    java -jar dist/lib/csv2rdf.jar manual-tests/template/cars-template.ttl manual-tests/input/separator.csv separator.ttl -s ;

## Quote karakter teszt
A program alapértelmezett quote karakter a `"` ezért, mi `!`-t használtunk, megfelelően kezelte.

Input: `quote.csv`

Kiadott parancs: 

     java -jar dist/lib/csv2rdf.jar manual-tests/template/cars-template.ttl manual-tests/input/quote.csv quote.ttl --quote !




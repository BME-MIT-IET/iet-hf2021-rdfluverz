# Unit tesztelés JUnit5-el és kódlefedettség vizsgálata

A kiinduló projekt nem tartalmazott unit teszteket, ezért első lépésként a be kellet importálni a JUnit keretrendszert.

A kiinduló projekt egyetlen fájlból állt, a külső osztályban sok private láthatóságú belső osztály és függvény volt, ezért a tesztelhetőség miatt bizonyos metódusok és osztályok láthatóságát public-ra módosítottuk.

A következő teszteseteket valósítottuk meg:

## CSV2RDF toChar függvénye

A toChar függvényhez 2 tesztet készítettünk. Az első teszt megvizsgálja, hogy helyes paraméter esetén a függvény helyes ereményt ad-e. A teszt sikeresen lefutott, nem találtunk hibát.

A második teszt azt vizsgálja, hogy ha a függvény nem az elvártnak megfelelő paramétert kap (1 karakter helyett többet), akkor dob-e exception-t. A teszt sikeresen lefutott, nem találtunk hibát.

## CSV2RDF getParserConfig függvénye

A getParserConfig függvény hozza létre és állítja be a config-ot, a különböző beállítások ellenőrzéséhez 5 tesztet készítettünk, ezek mind sikeresen lefutottak, nem találtak hibát.

## RowNumberProvider provideValue függvénye

A RowNumeberProvider osztály egyetlen függvényből áll, ami paraméterként egy sor indexet és egy sort kap, visszatérési értéke az index string típusként. A teszt leellenőrzi, hogy a megadott indexet valóban string-ként adja-e vissza függvény. A teszt nem talált hibát.

## RowValueProvider provideValue függvénye

A RowValueProvider provideValue függvénye egy index alapján visszadaja a paraméterként kapott sorban és az index által jelölt oszlopban lévő elemet. A teszt leellenőrizte, hogy ez teljesül-e. A teszt nem talált hibát, sikeresen lefutott.

## UUIDProvider különböző sor indexekkel




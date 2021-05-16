# CSV2RDF

## **A programról**

A program egy egyszerű, konzolos megoldást jelent `csv` (egyszerű adatállomány) fájlok átkonvertálására RDF által elfogadott `ttl` (*turtle*) struktúrára, amiben az adatok szemantikus hármasokban vannak tárolva és összekötve.

A konverzióhoz a csv fájlon kívül szükség van még egy minta - template - ttl fájlra is, ami alapján a program a megfelelő struktúrába tudja rendezni a csv adatait.

**Használati példa**

```
java -jar dist/lib/csv2rdf.jar {template file (.ttl)} {input file (.csv)} {output file (.ttl)}
```

**Futtatási opciókat listázó parancs**
```
java -jar dist/lib/csv2rdf.jar help convert
```
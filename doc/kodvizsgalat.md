# Kódvizsgálat:
## Osztályok vizsgálata

A program egyetlen fő osztályból áll, a többi osztály ennek belső osztályaként van jelen.
Habár ez megakadályozza a többi osztály használatát a fő oszályon kívül, a kódot szükségtelenül komplexszé és nehezen olvashatóvá teszi.
A továbbiakban az eredeti kódról készített elemzés található.

Úgy döntöttünk, hogy az olvashatóság érdekében a kód belső osztályait kiszervezzük külön fájlokba, önálló osztályokra.
Ehhez szükség volt az osztályok illetve néhány változó láthatóságának módosítására, de ezen felül lényegi változtatás nem történt.
Az osztályok legmegfelelőbb láthatóság beállítása package-private lenne, de a unit tesztek miatt szükséges a public szint.

##  1. CSV2RDF

A program fő osztálya, annotációk használatával kerül benne elhelyezésre az osztályhoz tartozó parancs, opciók és argumentumok, utóbbiak a ki és bemeneti fájlok.

A további osztályok ennek belső osztályai.

##  2. Template

- insertPlaceholders

    A template fájl alapján az oszlopváltozók nevei/indexei helyére létrehozza a ValueProvidereket, és behelyettesíti a hozzájuk tartozó placeholdereket.

- valueProviderFor

    Az oszlopváltozók nevei/indexei alapján megfelelő típusú ValueProvidereket hoz létre.

- parseTemplate

    Ez a függvény dolgozza fel a template fájlt a Template osztály létrehozásakor az insertPlaceholders segítségével.
    Továbbá létrehoz egy RDFHandler-t is, ami értelmezi és kezeli a névtereket, illetvekésőbb kezeki a létrehozott állításokat és azokból rdf fájlt készít.

##  3. ValueGenerator

A sorhoz tartozó értékek generálására, ill. kinyerésére szolgál. Egyetlen `generate(...)` függvényt tartalmazó interfész.

### BNodeGenerator

A csv sorainként új BNodeokat készít.

### ConstantValueGenerator

Egy konstans tárolására és visszaadására szolgál.

##  3.1 TemplateValueGenerator

A template-ben levő ValueProviderekhez tartozó placeholdereket cseréli le az adott sorból a ValueProviderek által kinyert adatokkal. Absztrakt osztály.

### TemplateLiteralGenerator

A sorokhoz literalokat készít.

### TemplateURIGenerator

A sorokhoz URI-ket készít.

##  4.  ValueProvider

Segítségével a sorokból nyerhetők ki az értékek, vagy az érték hash-elt változatai.

### RowValueProvider

A sor megfelelő oszlopának értékét adja vissza.

### UUIDProvider

A sorból kinyert adat helyett ez egy univerzálisan egyedi azonosítót szolgáltat.

### RowNumberProvider

A sorból kinyert adat helyett a sor sorszámát adja meg.

## 5. StatementGenerator

A ValueGeneratorok segítségével összeállítja az rdf adatbázis 3-as állításait.

# Statikus Analízis

A statikus analízishez sonarlintet használtunk, a sonarcloudot engedély hiányában nem tudtuk beállítani.

## System.out és System.err lecserélése loggerre
A sonarlint több helyen jelzete, hogy ```System.out``` és ```System.err``` használata helyett inkább logger-t kéne használni, ezeket lecseréltük.

## Charsets.UTF_8 lecserélése StandardCharsets.UTF_8-ra
Két fájlban is javasolta a sonarlint a ```com.google.common.base.Charsets.UTF_8``` import lecserélését a ```StandardCharsets.UTF_8``` importra, ezeket lecseréltük.

## Függvényszignatúrák throws Exception része
A sonarlint több helyen hibajelzést adott, hogy a generikus `throws Exception` helyett a függvényszignatúrákba érdemes lenne beépíteni az ott dobott kivételek specifikus típusát.
Ez olyan esetben, ahol a függvény saját maga állítja elő a kivételeket és nem a benne hívott függvények kivételeit dobja tovább érdemes lenne, illetve, ha az osztályt olyan környezetben használnánk, ahol a kivételeket kezeljük.
Mivel a program kivételkezelése néhány funkcionális eset kivételével annyiban kimerül, hogy a kivétel StackTrace-ét kiírja, illetve a függvények közül volt olyan, amelyikben 10 féle kivétel keletkezhet, úgy döntöttünk, hogy a kód olvashatósága érdekében ezt a figyelmeztetést figyelmen kívül hagyjuk.

Egy példa arra, hogyha a hívott függvényekben keletkező összes kivételt felsorolnánk: 

```java
public void parseTemplate(List<String> cols, File templateFile, final RDFWriter writer) throws IOException, IllegalStateException, IndexOutOfBoundsException, IllegalArgumentException, UnsupportedRDFormatException, RDFHandlerException, ClassCastException, NullPointerException, UnsupportedOperationException, RDFParseException
{
    //...
}
```

## A "var" változónév lecserélése

A sonarlint nem javasolta, hogy a változó elnevezése egyezzen a változók jelölésére használt `var` kóddal, így ezt átneveztük `variable`-re, mivel a rövidített elnevezés logikus volt, hiszen az template fájlon belül egy változót jelöl.

## Unit Tesztek frissítése, korregálása
Miután külön osztályokra bontottuk a `CSV2RDF` osztályt, a teszteket is frissíteni kellett ennek megfelelően. Kikerültek fölösleges változók és a statikus függvények már nem egy példányon, hanem az osztályon hívódnak meg.

Emellett néhány függvény nevet is változtattunk, hogy a Java-s szokásoknak megfelelően kisbetűvel kezdődjenek.

## Láthatóság változtatása, setter bevezetése
Szükségtelenül `public` láthatóságú tagváltozót `private`-re cseréltünk, és kapott egy setter fügvényt (`ValueProdiver` class: `setIsHash(boolean isHash)`)

## Nehezen olvasható logika felbontása
**TemplateLiteralGenerator**

Eredeti:
```java
return datatype == null ? lang == null ? FACTORY.createLiteral(value) : FACTORY.createLiteral(value, lang) : FACTORY.createLiteral(value, datatype);
```
Frissített:
```java
Literal literal;
if(datatype == null){
    if(lang == null)
        literal = FACTORY.createLiteral(value);
    else
        literal = FACTORY.createLiteral(value, lang);
    }
else
    literal = FACTORY.createLiteral(value, datatype);
return literal;
```
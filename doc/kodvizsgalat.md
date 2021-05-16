# Kódvizsgálat:
## Osztályok vizsgálata

A program egyetlen fő osztályból áll, a többi osztály ennek belső osztályaként van jelen. Habár ez megakadályozza a többi osztály használatát a fő oszályon kívül, a kódot szükségtelenül komplexszé és nehezen olvashatóvá teszi.

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
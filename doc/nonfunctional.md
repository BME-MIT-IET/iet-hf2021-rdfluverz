# Nemfunkcionális jellemzők vizsgálata

## Használhatóság

A vizsgálatok és tesztek során arra a megállapításra jutottunk, hogy a program használata nem bonyolult, viszont nem is kényelmes. A program parancssorból működik, így a különböző paraméterekkel való indítása viszonylag kényelmetlen. A futásához szükség van a template fájlra, aminek az előállítása szintén időigényes lehet, főleg egy bonyolultabb adatbázis esetében.

Az alkalmazás cserében jól automatizálható, ha sok, hasonló felépítésű .csv fájlt szeretnénk konvertálni. (Az oszlopok neveinek meg kell egyezni, de a sorrend eltérhet, ha a csv fejlécet tartalmaz).

## Stresszteszt nagyméretű fájllal

A program stressztesztje gyanánt egy többmillió soros .csv fájlt használtunk, aminek a konvertálása során nem ütközött problémába. Erre a magyarázat, hogy a program nem tárolja a memóriában sem a bemenet, sem a kimenet egészét, hanem a bemenetnek az aktuálisan feldolgozott sorát, a kimenetnek meg a nagyjából tempplat nagyságú eredményét. Az egyetlen fájl, amit a memóriában tárol teljes egészében a template.

A futás során a java környezet nagyjából 250MB memóriát használt, ez az érték az idő előrehaladtával jelentősen nem változott. A konverzió időtartama nagyjából 20 perc volt, de ez az érték a hardverkonfigurációtól, a használt templateben található kapcsolatok számától és egyéb változóktól is függ. 

A program futtatása és kimenete itt látható:
![](nonfunctional-img/big-input-file-run.png)

Ez pedig a futás mappájának tartalma:
![](nonfunctional-img/big-input-file-files.png)

A fájlokat mméretük miatt githubra nem töltöttük föl.
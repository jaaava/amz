Amazon Product Advertising API REST request
============================================

<b>Puudused:</b><br>
1) Üleliigsed päringud Amazoni<br>
2) Üks üleliigne List<br>
2) XML-st otsimine liiga hard-coded, aga ajab asja ära.<br>

<b>Võimalikud lahendused:</b><br>
1)  Kasutada olemasolevaid vasteid; Lihtne indeksitega mässamine, kui View [Made in Scala] oskaks nested-Liste renderdada...! 
<br>
Võimalik, et annab teha Modelisse accessor, mida View siis kasutaks, saates getterisse $page muutuja, mis tähistab indekseid, ja saame sealt vajalikud väljad kätte. That might even work... :)
<br>
2) Kohe kui eelmine puudus on lahendatud, saame sellestki lahti - see on eelmise puuduse work-around
<br>
3) Meil on suuremaidki probleeme, kui see!
<br>
<br>
<br>

<b>Parendused, uuendused:</b><br>
1) Tegin Modelisse helper-klassi, millega on võrdlemisi mugav View-st andmeid pärida.<br>
2) "üleliigne" list on ja jääb sisse. See grupeerib kenasti elemente per-page(Titles, Images, Asins)<br>
3) XML-st otsimine on tehtud normaalsemaks, aga koledus on jäänud - if'de rägastik.<br>
<br><br><br>

Tahaks veel palju muuta...
====

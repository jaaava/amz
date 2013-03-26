Amazon Product Advertising API REST request
============================================

Puudused:
1) Üleliigsed päringud Amazoni
2) Üks üleliigne List
2) XML-st otsimine liiga hard-coded, aga ajab asja ära.

Võimalikud lahendused:
1)  Kasutada olemasolevaid vasteid; Lihtne indeksitega mässamine, kui View [Made in Scala] oskaks nested Liste renderdada...! 
Võimalik, et annab teha Modelisse accessor, mida View siis kasutaks, saates getterisse $page muutuja, mis tähistab indekseid, ja saame sealt vajalikud väljad kätte. That might even work... :)
2) Kohe kui eelmine puudus on lahendatud, saame sellestki lahti - see on eelmise puuduse work-around
3) Meil on suuremaidki probleeme, kui see!

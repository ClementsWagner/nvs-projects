# Food Dairy
## Beschreibung
Das Food dairy soll es seinen Benutzern ermöglich Zutaten mit deren Nährwerten zu speicher 
und Rezepte aus diesen zu erstellen um im Endeffekt eine Nährwert tabelle für seine Rezepte zu erhalten. 

## Benutzer Workflow
Zuerst wird der Benutzer gefragt ob er seine Daten Lokal oder aus der Cloud beziehen will. Der Cloud Button ist deaktiviert, da diese Funktion erst in einem Zukünftigen Release erfolgt.

<img src="images/ChooseDatasource.png" alt="drawing" width="300"/>


In der App findet man nun eine leere Liste auf sowie in anderen Masken kann man mit dem "Plus"-Knopf ein Element hinzufügen.

<img src="images/IngredientListEmpty.png" alt="drawing" width="300"/>

Hier kann man nun einige Werte einfüllen. Dabei ist es so ausgelegt das man die Nährwerte so eingeben kann wie man sie im Internet findet.

<img src="images/nährwertTabelleBsp.png" alt="drawing" width="400"/>
<img src="images/IngredientEmpty2.png" alt="drawing" width="300"/>

<img src="images/IngredientFilled1.png" alt="drawing" width="300"/>

Beim einfügen werden alle Werte die leer gelassen werden mit 0 aufgefüllt ausser bei verpflichtenden Feldern wie Name und Einheit wird darauf aufmerksamgemacht

<img src="images/IngredientNameError.png" alt="drawing" width="300"/>
<img src="images/IngredientUnitError.png" alt="drawing" width="300"/>

Nach dem einfügen sieht die Zutaten liste so aus:

(Mit dem Mülleimer auf der Seite des Kärtchens kann man elemente wieder entfernen)
<img src="images/IngredientListFilled1.png" alt="drawing" width="300"/>

Die Detailansicht einer Zutat sieht so aus:

(Von dort kann man sie auch editieren)

<img src="images/IngredientDetailFilled.png" alt="drawing" width="300"/>

Rezept hinzufügen:

<img src="images/AddRecipeEmpty.png" alt="drawing" width="300"/>

Rezept details angeben: 
Zutat zum Rezept hinzufügen mit Plus

<img src="images/RecipedAdded.png" alt="drawing" width="300"/>

Zutat zum Rezept hinzufügen:

<img src="images/AddRecipeIngredient.png" alt="drawing" width="300"/>


Fehler beim Zutat hinzufügen:

<img src="images/RecipeIngredientAmountError.png" alt="drawing" width="300"/>


Rezept Nährwerttabelle:

<img src="images/RecipeDetail.png" alt="drawing" width="300"/>


## DatenModel
<img src="images/DatenModel.png" alt="drawing" width="1000"/>
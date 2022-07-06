Symulacja zachowania się tłumu w galerii w przypadku gwałtownego zagrożenia

Zachowanie się tłumu w przypadku gwałtownego zagrożenia w galerii lub w innym wielkogabarytowym pomieszczeniu zamkniętym jest w dużej mierze nieprzewidywalne. W  wyniku zagrożenia, wszystkie osoby chcą jak najszybciej opuścić budynek, niemniej niektóre osoby kierują się rozsądkiem i drogowskazami, a inni panikują. 

Celem symulacji jest wskazanie strategii postępowania, która daje największe szanse na szybkie opuszczenie budynku. 

W tym projekcie badamy trzy proste strategie i pokazujemy, która z nich jest najskuteczniejsza. 

Symulacja odbywa się w środowisku graficznym i na bieżąco generowane są wykresy średniej odległości osób od najbliższego wyjścia w zależności od wybranej strategii zachowania się.

Mapa do symulacji jest w pełni edytowalna oraz jest w postaci pliku tekstowego, w którym litery odpowiadają następującym obiektom:
a) nieruchome:
- "W" - ściana,
- "O" - wyjście,
b) ruchome (reprezentujące osoby):
- "P" - znające najkrótszą drogę do wyjścia oraz poruszające się tylko wtedy gdy ruch przybliży je do wyjścia,
- "U" - znające najkrótszą drogę do wyjścia oraz poruszające się zawsze gdy mają możliwość, nawet wtedy gdy ruch oddali je od wyjścia,
- "C" - nie znające najkrótszej drogi do wyjścia, w początkowych fazach symulacji poruszają się losowo, natomiast z czasem, nabywają pewną wiedzę i z coraz większym prawdopodobieństwem wybierą najbardziej korzystną drogę.


Do projektu załączone są trzy przykładowe mapy:
- map4x2.txt,
- map5.txt.

Wybór mapy oraz prędkość symulacji określa się przez wpisanie parametrów w pliku "configuration.txt". W katalogu "images" znajdują się screen shoty z działającej aplikacji oraz wykresy średniej odległości.

Aplikacja była napisana w środowisku "IntelliJ IDEA", skompilowana pod najnowszą wersję Java 18. Dodatkowo został utworzony projekt dla środowiska automatycznego budowania opartego na Gradle 7.4.2. Działanie aplikacji zostało przetestowane w zintegrowanym środowisku IntelliJ IDEA, wynik budowania aplikacji przez system Gradle został sprawdzony przez uruchomienie odpowiednich poleceń Gradle. Wynikiem budowania Gradle jest między innymi plik "jar", którego poprawność sprawdzono uruchamiając poleceniem "java -jar (nazwa_pliku).jar".

Uwaga!
Do poprawnego działania aplikacji wymagana jest obecność następujących plików w katalogu src\hello\app:
 - plik tekstowy "configuration.txt",
 - plik tekstowy zawierający mapę,
 - imgC.png,
 - imgO.png,
 - imgP.png,
 - imgU.png,
 - imgW.png.
Brak, któregokolwiek z plików tekstowych sygnalizowany jest w konsoli odpowiednim komunikatem. Podczas edycji zawartości pliku "configuration.txt", należy uważać aby nie zmienić wartości poza nazwą pliku mapowego lub ilości klatek na sekundę wyświetlanej prezentacji. W przypadku braku pliku "configuration.txt" program zostanie uruchomiony z następującymi parametrami:
 - File - "map5",
 - FPS - 10.




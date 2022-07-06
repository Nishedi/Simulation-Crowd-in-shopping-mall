package hello;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CSimulator {//klasa opisująca symulactor
	public CShowObj ShowObj = new CShowObj();//obiekt typu CShowObj
	Map<String, String> parameters = new HashMap<>();
	CMap map = new CMap();//obiekt typu CMap
	ArrayList<CMoveObject> L = new ArrayList<CMoveObject>();//Lista zawierająca obiekty typu CMoveObject(ruchome)
	public void add(CMoveObject obj) {
		L.add(obj);
	}//dodaje obiekty ruchume do listy obiektów ruchomych

	CStatistics stat = new CStatistics();//obiekt typu CStatistics
	
	public static ArrayList<String> read_data(String name) throws Exception {//wczytuje dane z plikut tekstowego, tworzy listę stringów, powszechnie dostępna funckja
		BufferedReader ReadF = null;
		ArrayList<String> L = new ArrayList<String>();
		try {
			String currentDir = System.getProperty("user.dir");
			name=currentDir+"\\"+name;
			ReadF = new BufferedReader(new FileReader(name));
			String numstring = ReadF.readLine();
			while(numstring != null) {					
				L.add (numstring);
				numstring = ReadF.readLine();	
			}
			
		} catch (Exception er) {

			System.out.println("Brak pliku: ");
			System.out.println(name);
			return null;
		}
		ReadF.close();
		return L;
		
	}

	public boolean load_data() throws Exception {//wczytuje dane, inicjuje na ich podstawie obiekty
		String name = parameters.get("File");//nazwa pliku, z którego są pobierane dane
		ArrayList<String> Lp = read_data(name);//wczytywanie zawartości pliku do listy Stringów
		if (Lp==null) return false;//Jeżeli wczytanie zakończy się niepowodzeniem wychodzi z false
		for(int w =0; w<=Lp.size()-1;w++){//iteruje po wierszach w pliku(liście)
			String Lk=Lp.get(w);//do Lk przypisuje String z wiersza W
			for(int k = 0; k <= Lk.length()-1;k++) { //Iteruje po znakach w stringu

				if(Lk.charAt(k)=='W') { //Jeżeli k-ty znak jest "W"
					CWall obj = new CWall(w,k); //tworzy obiekt typu CWall
					ShowObj.add(obj);//Dodaje obiekt do listy obiektow wyswietlanych
					map.add(obj);//Dodaje do listy obiektów nieruchomych
				}

				if(Lk.charAt(k)=='U') { //Jeżeli k-ty znak jest "U"
					CUndecided obj = new CUndecided(w,k);//tworzy obiekt typu CUndecided
					ShowObj.add(obj);//Dodaje obiekt do listy obiektow wyswietlanych
					add(obj);//Dodaje do listy obiektów ruchomych
				}

				if(Lk.charAt(k)=='P') { //Jeżeli k-ty znak jest "P"
					CPerfect obj = new CPerfect(w,k);//tworzy obiekt typu CPerfect
					ShowObj.add(obj);//Dodaje obiekt do listy obiektow wyswietlanych
					add(obj);//Dodaje do listy obiektów ruchomych
				}

				if(Lk.charAt(k)=='O') { //Jeżeli k-ty znak jest "O"
					CExit obj = new CExit(w,k);//tworzy obiekt typu CExit
					ShowObj.add(obj);//Dodaje obiekt do listy obiektow wyswietlanych
					map.add(obj);//Dodaje do listy obiektów nieruchomych
				}

				if(Lk.charAt(k)=='C') { //Jeżeli k-ty znak jest "C"
					CChaotic obj = new CChaotic(w,k);//tworzy obiekt typu CChaotic
					ShowObj.add(obj);//Dodaje obiekt do listy obiektow wyswietlanych
					add(obj);//Dodaje do listy obiektów ruchomych
				}
			}
		}
		return true;
	}

	public void executestep(CShowObj ShowObj, CMap map) {

		Random gen = new Random();
		if(L.size()>=2) {//zmienia kolojeność obiektów na liście w sposób losowy, symuluje to losowy moment podejmowania decyzji przez autonomiczne obiekty
			for (int i = 0; i <= 200; i++) {//wykonywane jest 200 zmian
				int r1 = gen.nextInt(0, L.size() - 1);//losuje pozycje na liście
				CMoveObject o = L.get(r1);
				L.remove(r1);//usuwa element z wylosowanej pozycji
				L.add(o);//wstawia na koniec
			}
		}



		for(int j = L.size()-1; j>=0; j--) {//dla wszystkich elementów listy wykonuje
			CMoveObject o = L.get(j);//pobiera element z listy
			CPossition px = o.getPossition();//zapamiętuje pozycje
			if(map.getdistance(px)==1) {//sprawdza czy odległość jest równa 1
				o.gotoOut();//przesuwa do wyjścia
				L.remove(j);//usuwa obiekt z listy obiektów ruchomych
				continue;
			}
			o.MakeMove(map, ShowObj);//wykonuje autonomiczny ruch obiektu
		}
		stat.compute(L, map);//obliczanie wiersza statystyk
	}

	public boolean start() throws Exception {//startuje symulacje
		String name = "configuration.txt";//nazwa pliku, z którego są pobierane dane
		ArrayList<String> Lp = read_data(name);//wczytywanie zawartości pliku do listy Stringów
		if (Lp==null) {
			parameters.put("File", "map5.txt");
			parameters.put("FPS", "10");
			}else {
			for (String s : Lp) {
				String[] result = s.split("-");
				parameters.put(result[0].trim(), result[1].trim());
			}
		}
		if (load_data()==false) return false;//wczytuj dane, jeśli nastąpi niepowodzenie, symulacja jest przerywana
		map.create();//wyznacza odległości pól od najbliższych wyjść
		return true;
	}
}

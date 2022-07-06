package hello;

import java.util.ArrayList;

//wyswietlanie obiektów w czasie symulacji w formie tekstowej w konsili
public class CShowObj {	//wyswietlanie obiektow
	ArrayList<CObj> L = new ArrayList<>();//lista obiektów ruchomych i nieruchomych

	public void add(CObj obj){//dodaje obiekt do listy obiektów ruchomych i nieruchomych
		L.add(obj);
	}
	public void Show(CMap map) {//wyświetla CharMapę w konsoli
		String star="";
		for(int i = 0; i <= map.maxK; i++) {//tworzenie stringów składających się z maxK spacji
			star=star+" ";
		}
		ArrayList<String> charMap = new ArrayList<String>();
		for(int i = 0; i<=map.maxW;i++) {//tworzenie listy składającej się z maxW stringów star
			charMap.add(star);
		}
		
		for(CObj num: L) {//nadpisanie spacji symbolem obiektu
			num.ShowInCharMap(charMap);
		}

		for(String num: charMap) {
			System.out.println(num);//wyswietlenie w konsoli aktualnego stanu mapy
		}	
	}
}
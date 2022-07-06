package hello;

import java.util.ArrayList;

public class CMap {
	private int[][] distance = new int[0][]; // Odległość pozycji Y, X od najbliższego wyjścia
	public int maxW = -1;
	public int maxK = -1;
	ArrayList<CObj> L = new ArrayList<CObj>();// lista obiektów nieruchomych

	public int getdistance(CPossition pos){
		return distance[pos.Y][pos.X];
	}// zwraca odległość pozycji pos od najbliższego wyjścia

	public void add(CObj obj) {
		L.add(obj);
	}// dodanie obiektu obj do listy
	
	public void create() {
		for(CObj num: L) {// Wyszukanie maksymalnego wiersza i kolumny na mapie
			
			if(num.getPossition().Y>maxW) {
				maxW=num.getPossition().Y;
			}
			if(num.getPossition().X>maxK) {
				maxK=num.getPossition().X;
			}
		}
		
		distance= new int[maxW+1][maxK+1];// alokacja pamięci dla tablicy distance
		for(int i = 0; i<=maxW; i++ ) {
			for(int j = 0; j <= maxK; j++) {
				distance[i][j]=10000;
			}
		}
		ArrayList<CPossition> PAIR = new ArrayList<CPossition>();// lista pozycji o ustalonej odległości
		for(CObj S: L) {
			if(S.typ=='O') {// Gdy obiekt jest typu "O"
				distance[S.getPossition().Y][S.getPossition().X]=0;// odległość od wyjścia jest 0
				PAIR.add(new CPossition(S.getPossition().Y,S.getPossition().X));// jego pozycja jest wstawiana do listy
			}
		}
		for(int i = 0; i < PAIR.size();i++) {
			CPossition r = PAIR.get(i);// pozyskanie i-tego elementu z listy
			CObj o = new CObj(r.Y, r.X);//tworzenie obiekt osadzony w punkcie Y,X
			for(CPossition p: o.explore(L)) {//dla każdego punktu osiągalnego przez o
				if((distance[r.Y][r.X]+1)<distance[p.Y][p.X]) {//sprawdzanie czy jest oddalony bardziej niż o 1
					distance[p.Y][p.X]=distance[r.Y][r.X]+1;//podstawianie distance + 1
					PAIR.add(p);//dodanie punktu do listy
				}
			}
		}
	}
	
	void SHOW() {//wyswietlanie mapy z odlgłościami tzn. mapa z cyframi, na każdym miejscu odległośc od najbliższego wyjścia
		
		for(int i = 0; i<=maxW; i++ ) {
			for(int j = 0; j <= maxK; j++) {
				System.out.print(distance[i][j]+" ");
			}
			System.out.println();
		}
	}
}
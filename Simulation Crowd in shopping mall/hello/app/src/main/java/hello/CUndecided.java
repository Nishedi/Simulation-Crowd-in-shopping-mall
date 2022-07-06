package hello;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CUndecided extends CMoveObject{

	public CUndecided(int Y, int X) {//konstruktor inicjujący o pozycji
		super(Y, X);//wywołanie konstruktora ojca
		typ = 'U';//przypisanie znaku charakterysttycznego (U- undecided)
		img = new ImageIcon("imgU.png").getImage();//przypisanie bitmapy
	}

	public void MakeMove(CMap map, CShowObj ShowObj) {//wykonuje ruch obiektów
		//spośród miejsc wolnych wybiera miejsca, które są najbliżej wyjścia, w przypadku gdy najlepsze miejsce jest zajęte wybrane zostanie najlepsze z pozostałych
		ArrayList<CPossition> neighbours = explore(ShowObj.L);//zwraca listę dostępnych pól
		if(neighbours.size()==0) //gdy nie ma miejsc gdzie potencjalnie można się przemieścić obiekt stoi w miejscu
			return;
		//mn - najmniejsza odległość dostępnych pól od nabliższego wyjścia
		int mn = map.getdistance(neighbours.get(0));//wstępnie inicjowana odległością pierwszego miejsca
		for(CPossition j: neighbours) {
			if(map.getdistance(j)<mn)//jeżeli mn jest większe od odległości dla danego miejsca
				mn=map.getdistance(j);//wartośc mn jest aktualizowana
		}
		for(int i = neighbours.size()-1; i >= 0; i--) {//usuwa pola o odległości większej od mn
			if(map.getdistance(neighbours.get(i))>mn)//jezeli odległość jest większa od mn
				neighbours.remove(i);//usuwanie potencjalnego miejsca
		}
		int r = gen.nextInt(0, neighbours.size());//losuje liczbę od 0 do ilości potencjalnych pozycji
		setPossition(neighbours.get(r));//obiekt przemieszcza się na wylosowaną ^^ pozycję
	}
}

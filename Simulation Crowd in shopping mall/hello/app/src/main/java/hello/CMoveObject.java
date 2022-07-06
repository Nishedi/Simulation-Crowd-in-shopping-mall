package hello;

import java.util.ArrayList;
import java.util.Random;

public class CMoveObject extends CObj {
	Random gen = new Random();

	public CMoveObject(int W, int X) {
		super(W,X);
	}

	public void MakeMove(CMap map, CShowObj ShowObj) {//wykonuje ruch obiektów
		//domyślnie obiekty poruszają się tylko wtedy gdy przejście przybliża je do wyjścia
		ArrayList<CPossition> neighbours = explore(ShowObj.L);//zwraca listę dostępnych pól
		if(neighbours.size()==0)//gdy nie ma miejsc gdzie potencjalnie można się przemieścić obiekt stoi w miejscu
			return;
		int mn = map.getdistance(getPossition())-1;//obliczenie odległości o jeden mniejszej
		for(int i = neighbours.size()-1; i >= 0; i--) {
			if(map.getdistance(neighbours.get(i))>mn)//Jezeli potencjalne miejsce jest dalej niż miejsce obietu
				neighbours.remove(i);// jest usuwane
		}
		if(neighbours.size()==0) return;// gdy lista potencjalnych miejsc jest pusta obiekt stoi
		int r=gen.nextInt(0, neighbours.size());//losowana jest pozycja z listy
		setPossition(neighbours.get(r));//obiekt przemieszcza się na wylosowaną ^^ pozycję
	}
}

package hello;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CChaotic extends CMoveObject{
	int counter = 0;//Zlicza liczbę ruchów obiektu

	public CChaotic(int Y, int X) {//konstruktor inicjujący o pozycji
		super(Y, X);//wywołanie konstruktora ojca
		typ = 'C';//przypisanie znaku charakterysttycznego (C- chaotic)
		img = new ImageIcon("imgC.png").getImage();//przypisanie bitmapy
	}
	public void MakeMove(CMap map, CShowObj ShowObj) {//wykonuje ruch obiektów
		ArrayList<CPossition> neighbours = explore(ShowObj.L);//wyznacza listę wlonych miejsc wkoło obiektu
		if (neighbours.size() == 0)//jezeli lista jest pusta
			return;//konczy funkcje, obiekt pozostaje na pozyji poczatkowej

		counter++;//zwiększa licznik wykonanych ruchów o 1
		int r = gen.nextInt(0,400); //losuje liczbę z podanego zakresu
		if(r<counter) //sprawdza czy wylosowana liczba jest mniejsza od liczby wykonanych ruchów
			super.MakeMove(map, ShowObj);//wykonuje ruch metodą ojca
		else {//wykonuje ruch na losową, dostępną pozycje
			r = gen.nextInt(0, neighbours.size());//losuje liczbę od 0 do ilości potencjalnych pozycji
			setPossition(neighbours.get(r));//obiekt przemieszcza się na wylosowaną ^^ pozycję
		}
	}
}

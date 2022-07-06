package hello;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class CObj implements IObj {
	char typ=' ';//Określa typ obiektu, domyślnie " "
	private CPossition pos = null;//pozycja obiektu na mapie w formie klasy CPossition
	Image img = null;//przechowuje odpowiednią bitmapę odpowiadającą typowi obiektu
	
	CObj(int Y, int X){//konstruktor inicjujący położenie
		pos = new CPossition(Y, X);
	}

	@Override
	public void gotoOut() {//funkcja interface'u implementująca wyjście, tutaj ustawienie pozycji obiektu na 0,0
		pos.Y=0;
		pos.X=0;
	}

	@Override
	public CPossition getPossition() {//funkcja interface'u zwracająca pozycję
		return pos;
	}

	public void setPossition(CPossition pos){//funkcja ustawia pozycję
		this.pos.X=pos.X;
		this.pos.Y=pos.Y;
	}

	public boolean yourpositionis(CPossition pos) {//funkcja interface' sprawdzająca czy na danej pozycji jest obiekt
		if((this.pos.Y==pos.Y)&&(this.pos.X==pos.X))
			return true;
		return false;
	}

	public void ShowInCharMap(ArrayList<String> charMap){//funkcja interface'u podmieniająca znak na mapie odpowiadający typowi obiektu na pozycji pos w mapie charMap
			String row = charMap.get(pos.Y);//zapamietanie w row Y-kowy wiersz charMap
			String C = String.valueOf(typ);//zapamiętanie w C znak indentyfikujący typ
			String newrow = row.substring(0, pos.X);//skopiowanie do newrow pierwszych X-1 znaków z row

			newrow=newrow+C+row.substring(pos.X+1);//dodanie do newrow znaku C i pozostałych znaków z row
			charMap.remove(pos.Y);//usunięcie starego wiersza z pozycji Y
			charMap.add(pos.Y, newrow);//dodanie zaktualizowanego wiersza na pozycji Y
	}

	public ArrayList<CPossition> explore(ArrayList<CObj> L) {//Zwraca listę pozycji wolnych obok obiektu(nieblokowanych przez inne obiekty)
		ArrayList<CPossition> neighbourhood = new ArrayList<CPossition>();//Lista potencjalnych pozycji
		neighbourhood.add(new CPossition(pos.Y-1, pos.X));//dodanie pozycji o jeden w górę
		neighbourhood.add(new CPossition(pos.Y+1, pos.X));//dodanie pozycji o jeden w dół
		neighbourhood.add(new CPossition(pos.Y, pos.X-1));//dodanie pozycji o jeden w lewo
		neighbourhood.add(new CPossition(pos.Y, pos.X+1));//dodanie pozycji o jeden w prawo
		//neighbourhood.add(new CPossition(pos.Y+1, pos.X+1));
		//neighbourhood.add(new CPossition(pos.Y-1, pos.X+1));
		//neighbourhood.add(new CPossition(pos.Y-1, pos.X-1));
		//neighbourhood.add(new CPossition(pos.Y+1, pos.X-1));
		for(CObj x: L) {//i-terowanie po wszystkich obiektach (ruchimych i nieruchomych)
		for(int i = neighbourhood.size()-1; i>=0; i--) {//iterowanie po liście potencjalnych wolnych miejsc
			if((x.yourpositionis(neighbourhood.get(i)))==true)//Jeżeli obiekt znajduje się na potencjalnie wolnej pozycji
				neighbourhood.remove(i);//potencjalna pozycja zostaje usunięta z listy potencjalnych miejsc
			}
		}
		return neighbourhood;
	}

	public void paint (Graphics G2D) {
		G2D.drawImage(img,  pos.X*10,  pos.Y*10, null);
	}//Wyświetla bitmapę przypisaną do obiektu w oknie aplikacji


}

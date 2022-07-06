package hello;

import java.awt.*;
import java.util.ArrayList;

public interface IObj {
   public void paint(Graphics G2D);//wyswietla obiekt na mapie w oknie aplikacji
   public void ShowInCharMap(ArrayList<String> charMap);//nadpisuje kodem typu obiektu znak na pozycji X Y w mapie charMap
   public boolean yourpositionis(CPossition pos);//Zwraca true gdy obiekt znajduje się na pozycji pos
   public CPossition getPossition();//Zwraca pozycję w formie klasy CPossition obiektu
   public void gotoOut();//podejmuje działanie polegające na usunięciu obiektu
   public void setPossition(CPossition pos);//funkcja ustawia pozycję obiektu
}

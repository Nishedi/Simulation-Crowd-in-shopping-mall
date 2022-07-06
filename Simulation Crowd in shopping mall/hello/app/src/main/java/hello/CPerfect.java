package hello;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CPerfect extends CMoveObject{
	public CPerfect(int Y, int X) {//konstruktor inicjujący o pozycji
		super(Y, X);//wywołanie konstruktora ojca
		typ = 'P';//przypisanie znaku charakterysttycznego (P-perfect)
		img = new ImageIcon("imgP.png").getImage();//Przypisanie odpowiedniego obrazka
	}
}

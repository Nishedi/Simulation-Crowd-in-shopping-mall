package hello;

import javax.swing.*;
import java.awt.*;

public class CWall extends CObj {

	public CWall(int Y, int X) {//konstruktor inicjujący o pozycji
		super(Y, X);//wywołanie konstruktora ojca
		typ = 'W';//przypisanie znaku charakterysttycznego (W- Wall)
		img = new ImageIcon("imgW.png").getImage();//przypisanie bitmapy
	}
}

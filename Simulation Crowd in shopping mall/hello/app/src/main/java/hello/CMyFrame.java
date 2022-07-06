package hello;

import java.awt.*;
import javax.swing.*;

public class CMyFrame extends JFrame {
	//okno symulacji do utworzenia wymaga obiekt typu CSimulator
	CMyPanel panel;//panel symulacji
	CMyFrame(CSimulator sim) throws Exception{
		panel = new CMyPanel(sim);//inicjuje panel symulacji
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ustawia domyślną akcję zamknięcia okna
		this.add(panel);//dodaje do kontenera okna obiekty typu panel
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);//ustawienie widzialnosci okna

	}

}

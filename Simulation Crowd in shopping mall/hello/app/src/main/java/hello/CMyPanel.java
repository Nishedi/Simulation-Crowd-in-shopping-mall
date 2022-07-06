package hello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CMyPanel extends JPanel implements ActionListener {
	//słuzy do graficznego wyświetlania stanu symulacji. Konsttrukcja obiektu klasy CMyPanel wymaga podania obiektu CSimulator jako argumentu
	Timer timer;//wyzwala wykonanie kroku symulacyjnego, odświeża okno aplikacji oraz wyświetla stan mapy w formie tekstowej w konsoli
	CSimulator sim = null;//obiekt typu CSimulator
	final int Panel_width = 1920;//szerokość okna
	final int Panel_height = 1080;//wysokość okna
	int counter=0;//licznik kroków symulacji, wykorzystywany do zapisania wyniku symulacji po zadanej liczbie kroków
	CMyPanel(CSimulator sim) throws Exception{//kreator obiektu CMyPanel
		this.sim=sim;//zapamiętanie referencji do obiektu CSimulator
		this.setPreferredSize(new Dimension(sim.map.maxK*10+10, sim.map.maxW*10+10));//utworzenie okna o zadanej wielkości
		this.setBackground(Color.black);//ustawienie czarnego tła
		String FPS = sim.parameters.get("FPS");
		Integer IFPS = Integer.parseInt(FPS);
		timer = new Timer(1000/IFPS, this);//ustawienie wyzwalania (100ms)
		timer.start();//uruchomienie timera
	}
	
	public void paint (Graphics G) {//wyświetla wyszystkie obiekty (ruchome i nieruchome) w oknie aplikacji
		super.paint(G);
		Graphics2D G2D = (Graphics2D) G;
		for(CObj i: sim.ShowObj.L) {
			i.paint(G2D);//wywołanie metody rysującej ikonę obiektu w miejscu zdefiniowanym przez pozycję obiektu
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {//fukncja wywoływana przez timer, wywołuje krok symulacji
		sim.executestep(sim.ShowObj, sim.map);//wywołanie kroku symulatora
		sim.ShowObj.Show(sim.map);//wyswietlenie w konsoli aktualnego stanu mapy z obiektami
		counter++;//zwiekszanie wartości countera
		if(counter==100) {//jeżeli counter jest równy zadanej liczbie
			try {
				sim.stat.savetofile();//następuje zapis zawartości listy do pliku
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		repaint();//odświeża okno aplikacja, wywołuje funkcje paint
	}
}
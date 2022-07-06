package hello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CStatisticPanel extends JPanel implements ActionListener {
    //słuzy do graficznego wyświetlania statystyk symulacji tj. średniej odległości danego typu od najbliższego wyjścia
    //Konstrukcja obiektu klasy CStatiticPanel wymaga podania obiektu CSimulator jako argumentu
    Timer timer;//wyzwala wykonanie aktualizacji statystyk, odświeża okno statystyk
    CSimulator sim = null;//obiekt typu CSimulator
    final int Panel_width = 500;//szerokość okna
    final int Panel_height = 400;//wysokość okna
    CStatisticPanel(CSimulator sim) {
        this.sim=sim;//zapamiętanie referencji do obiektu CSimulator
        this.setPreferredSize(new Dimension(Panel_width, Panel_height));//utworzenie okna o zadanej wielkości
        this.setBackground(Color.gray);//ustawienie szarego tła
        timer = new Timer(1000, this);//ustawienie wyzwalania (1s)
        timer.start();//uruchomienie timera
    }
    public void paint (Graphics G) {//wyświetla wykresy średnich odległosci obiektów od wyjścia w oknie statystyk
        super.paint(G);
        if(sim.stat.stats==null) return;//jeżeli lista statystyk jest pusta fukncja zostaje przerwana
        if(sim.stat.stats.size()==0) return;
        Graphics2D G2D = (Graphics2D) G;
        String[] result = sim.stat.stats.get(0).split(";");//rozdziela wiersz listy na pojedyncze stringi względem znaku ";"
        int lp[] = new int[3];//tablica począktowej ilosci obiektów poszczególnych typów
        lp[0]= Integer.parseInt(result[0]);//liczba CChaotic
        lp[1]= Integer.parseInt(result[2]);//liczba CPerfect
        lp[2]= Integer.parseInt(result[4]);//liczba CUndecide
        int sump[] = new int[3];//tablica początkowych sum odledłości od wyjścia
        sump[0]= Integer.parseInt(result[1]);//suma CChaotic
        sump[1]= Integer.parseInt(result[3]);//suma CPerfect
        sump[2]= Integer.parseInt(result[5]);//suma CUndecided
        int sr = sump[0] / lp[0];
        for(int i = 1; i <=2 ; i++) {
            int sr2=sump[i] / lp[i];
            if(sr2>sr)
                sr=sr2;
        }

        int ofsx=50;
        int ofsy=50;
        double sky = getHeight()/sr; //skalowanie wykresu
        G2D.drawLine(ofsx, getHeight()-ofsy, 5000+ofsx,getHeight()-ofsy);//rysuje oś OX
        G2D.drawLine(ofsx, getHeight()-(ofsy), ofsx,getHeight()-(ofsy+1000));//rysuje oś
        for(int k = 1; k <=20; k++){
            G2D.drawLine(ofsx + k*100, getHeight() - (ofsy - 5), ofsx + k*100, getHeight() - (ofsy + 5));//rysuje oś OY
            Integer x = k*100;
            G2D.drawString(x.toString(),ofsx-10+ k*100,getHeight() - (ofsy - 20) );
        }
        for(int k = 1; k <=40; k++) {
            G2D.drawLine(ofsx - 5, getHeight() - (ofsy + (int)(10*k * sky)), ofsx + 5, getHeight() - (ofsy + (int)(10*k * sky)));
            Integer x = k*10;
            G2D.drawString(x.toString(),5,getHeight() - (ofsy + (int)(10*k * sky))+4);
        }
        G2D.setFont(G2D.getFont().deriveFont(15.0f));
        G2D.drawString("liczba iteracji",getWidth()-90,getHeight()-10);
        G2D.drawString("średnia odległość",70,15);
        G2D.setColor(Color.blue);
        G2D.drawString("Perfect",getWidth()-70,20);
        G2D.setColor(Color.green);
        G2D.drawString("Undefined",getWidth()-70,35);
        G2D.setColor(Color.YELLOW);
        G2D.drawString("Chaotic",getWidth()-70,50);




        for(int i =1; i< sim.stat.stats.size();i++){
          result = sim.stat.stats.get(i).split(";");//pobranie wiersza statystyk dla i-tego kroku
            int sumb[] = new int[3];//suma odległości w i-tym kroku
            sumb[0]= Integer.parseInt(result[1]);//suma CChaotic
            sumb[1]= Integer.parseInt(result[3]);//suma CPerfect
            sumb[2]= Integer.parseInt(result[5]);//suma CUndecided
            G2D.setColor(Color.YELLOW);//zmiana koloru kreski na CChaotic(żółty)
            G2D.drawLine(i-1+ofsx, getHeight()-ofsy-(int)(sky*sump[0]/lp[0]), i+ofsx,getHeight()-ofsy-(int)(sky*sumb[0]/lp[0]));//rysuje linie pomiędzy średnimi
            G2D.setColor(Color.blue);//zmiana koloru kreski na CPerfect(niebieski)
            //sump[0]/lp[0] - średnia z poprzedniej iteracji, sumb[0]/lp[0] - średnia z aktualnej iteracji
            G2D.drawLine(i-1+ofsx, getHeight()-ofsy-(int)(sky*sump[1]/lp[1]), i+ofsx,getHeight()-ofsy-(int)(sky*sumb[1]/lp[1]));
            G2D.setColor(Color.green);//zmiana koloru kreski na CUndecided(zielony)
            G2D.drawLine(i-1+ofsx, getHeight()-ofsy-(int)(sky*sump[2]/lp[2]), i+ofsx,getHeight()-ofsy-(int)(sky*sumb[2]/lp[2]));
            for(int j = 0; j <=2 ; j++){
                sump[j]=sumb[j];//podmiana sum obiektów z poprzedniej iteracji na aktualną
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

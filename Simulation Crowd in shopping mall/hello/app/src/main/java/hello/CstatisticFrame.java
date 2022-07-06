package hello;

import javax.swing.*;

public class CstatisticFrame extends JFrame{
    //okno statystyk do utworzenia wymaga obiekt typu CSimulator
    CStatisticPanel panelstat;//panel statystyk

    CstatisticFrame(CSimulator sim) throws Exception{
        panelstat = new CStatisticPanel(sim);//inicjuje panel statystyk
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ustawia domyślną akcję zamknięcia okna
        this.add(panelstat);//dodaje do kontenera okna obiekty typu panelstat
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);//ustawienie widzialnosci okna

    }
}

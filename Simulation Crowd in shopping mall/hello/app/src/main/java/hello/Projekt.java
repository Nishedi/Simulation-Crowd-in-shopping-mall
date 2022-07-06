package hello;

import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Projekt {
    static CSimulator sim = new CSimulator();//obiekt typu CSimulator
    public static void main(String[] args) throws Exception  {
       if (sim.start()==true)//jeżeli uruchomienie symulacji przebiegło pomyślnie
        {
            CstatisticFrame StatFrame = new CstatisticFrame(sim);//tworzony jest obiekt typu CStatisticFrame
            CMyFrame MainFrame = new CMyFrame(sim);//tworzony obiekt typu MyFrame
                //konstruktory uruchamiają timery, które inicjują kroki symulacji i odświeżenia statystyk
        }

    }
}
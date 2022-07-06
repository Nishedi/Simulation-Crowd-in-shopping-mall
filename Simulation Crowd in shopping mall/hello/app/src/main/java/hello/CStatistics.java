package hello;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CStatistics {
    ArrayList<String> stats = new ArrayList<String>();//lista stringów w formacie: l1;s1;l2;s2;l3;d3, gdzie l1 - liczba ruchomych CChaotic, s1 suma odległosci CChaoticów od najbliższuych wyjść...
    public void savetofile() throws IOException {//zapisanie w pliku listę stats
        FileWriter fWriter = null;
        try {
            fWriter = new FileWriter("statistics.csv");//nazwa pliku

            for(String i: stats)
            fWriter.write(i+"\n");

        } finally {
            if (fWriter != null) {
                fWriter.close();
            }
        }
    }

    public void compute(ArrayList<CMoveObject> List, CMap map){//wyznacza liczbę aktywnych obiektow ruchomych dla każdego typu oraz wylicza sumę odległości

        int[] sumob = new int[3];//suma obiektów
        int[] sumdis = new int[3];//suma odległości
        for(int i =0; i <3; i++){
            sumdis[i]=0;
            sumob[i]=0;
        }
        for(CMoveObject i: List){
            if(i.typ=='C') sumob[0]++;//zwiększenie liczby obiektów dla CChaotic
            if(i.typ=='P') sumob[1]++;//zwiększenie liczby obiektów dla CPerfect
            if(i.typ=='U') sumob[2]++;//zwiększenie liczby obiektów dla CUndecided
        }
        for(CMoveObject i: List){
            if(i.typ=='C') sumdis[0]=sumdis[0]+map.getdistance(i.getPossition());//zwiększenie sumy dystansów obiektów dla CChaotic
            if(i.typ=='P') sumdis[1]=sumdis[1]+map.getdistance(i.getPossition());//zwiększenie sumy dystansów obiektów dla CPerfect
            if(i.typ=='U') sumdis[2]=sumdis[2]+map.getdistance(i.getPossition());//zwiększenie sumy dystansów obiektów dla CUndecided
        }
        String S="";//utworzenie stringu w podanym wyżej formacie

        for(int i =0; i<3;i++){
            S =S+sumob[i]+";";
            if(sumob[i]>0) {
                S = S + sumdis[i] +";";
            }else{
                S= S+"0;";
            }
        }
        stats.add(S);
        System.out.println(S);

    }

}

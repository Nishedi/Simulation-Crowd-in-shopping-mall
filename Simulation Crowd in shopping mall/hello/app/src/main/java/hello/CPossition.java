package hello;

public class CPossition {//opisuje pozycje o
    public int Y;//pozycja X
    public int X;//pozycja Y
    public CPossition(int Y, int X){//konstruktor inicjujący pozycję X,Y
        this.Y=Y;
        this.X=X;
    }

    public CPossition(CPossition pos){//konstruktor kopiujący
        this.Y= pos.Y;
        this.X= pos.X;
    }
}

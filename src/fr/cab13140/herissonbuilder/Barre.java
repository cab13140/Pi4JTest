package fr.cab13140.herissonbuilder;

public class Barre {
    public void setM(int m) {
        this.m = m;
    }

    public void setCm(int cm) {
        this.cm = cm;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    private int m;
    private int cm;
    private int diameter;

    public Barre(int m, int cm, int diameter){

        this.m = m;
        this.cm = cm;
        this.diameter = diameter;
    }

    public int getM() {
        return m;
    }

    public int getCm() {
        return cm;
    }

    public int getDiameter() {
        return diameter;
    }

    public String getString(){

    }
}

package com;

public class Salaire {
    public double getMin() {
        return Min;
    }

    public void setMin(double min) {
        Min = min;
    }

    public double getMax() {
        return Max;
    }

    public void setMax(double max) {
        Max = max;
    }

    public double getSomme() {
        return Somme;
    }

    public void setSomme(double somme) {
        Somme = somme;
    }

    public Salaire(double min, double max, double somme) {
        Min = min;
        Max = max;
        Somme = somme;
    }

    private double Min;
    private double Max;
    private double Somme;
}

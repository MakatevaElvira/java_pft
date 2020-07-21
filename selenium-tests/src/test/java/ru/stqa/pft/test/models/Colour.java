package ru.stqa.pft.test.models;

public class Colour {
    private int r;
    private  int g;
    private int b;
    private int a;

    public int getR() {
        return r;
    }

    public Colour withR(int r) {
        this.r = r;
        return this;
    }

    public int getG() {
        return g;
    }

    public Colour withG(int g) {
        this.g = g;
        return this;
    }

    public int getB() {
        return b;
    }

    public Colour withB(int b) {
        this.b = b;
        return this;
    }

    public int getA() {
        return a;
    }

    public Colour withA(int a) {
        this.a = a;
        return this;
    }
}

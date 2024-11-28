package Problema1;

import java.util.List;
import java.util.ArrayList;

public class Parabola {
    private int a, b, c;

    // Constructor
    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Metoda care calculează și returnează coordonatele vârfului parabolei
    public double[] getVarf() {
        double x = -b / (2.0 * a); // X-ul vârfului
        double y = a * x * x + b * x + c; // Y-ul vârfului
        return new double[] {x, y};
    }

    // Redefinirea metodei toString() pentru a returna parabola sub formă de funcție
    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }

    // Metoda care primește ca parametru o altă parabolă și returnează mijlocul segmentului între vârfuri
    public double[] mijloc(Parabola p) {
        double[] varf1 = this.getVarf();
        double[] varf2 = p.getVarf();
        double mijlocX = (varf1[0] + varf2[0]) / 2;
        double mijlocY = (varf1[1] + varf2[1]) / 2;
        return new double[] {mijlocX, mijlocY};
    }

    // Metoda statică pentru a calcula mijlocul segmentului dintre două parabole
    public static double[] mijlocStatic(Parabola p1, Parabola p2) {
        double[] varf1 = p1.getVarf();
        double[] varf2 = p2.getVarf();
        double mijlocX = (varf1[0] + varf2[0]) / 2;
        double mijlocY = (varf1[1] + varf2[1]) / 2;
        return new double[] {mijlocX, mijlocY};
    }

    // Metoda care primește ca parametru o altă parabolă și returnează lungimea segmentului între vârfuri
    public double distanta(Parabola p) {
        double[] varf1 = this.getVarf();
        double[] varf2 = p.getVarf();
        return Math.hypot(varf2[0] - varf1[0], varf2[1] - varf1[1]);
    }

    // Metoda statică pentru a calcula lungimea segmentului dintre două parabole
    public static double distantaStatic(Parabola p1, Parabola p2) {
        double[] varf1 = p1.getVarf();
        double[] varf2 = p2.getVarf();
        return Math.hypot(varf2[0] - varf1[0], varf2[1] - varf1[1]);
    }
}


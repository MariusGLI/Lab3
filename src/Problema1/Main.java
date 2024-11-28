package Problema1;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Listă pentru a stoca parabolele
        List<Parabola> parabole = new ArrayList<>();

        // Citirea fișierului in.txt
        try (BufferedReader br = new BufferedReader(new FileReader("in.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coef = line.split("\\s+");
                int a = Integer.parseInt(coef[0]);
                int b = Integer.parseInt(coef[1]);
                int c = Integer.parseInt(coef[2]);

                // Crearea obiectului Parabola și adăugarea acestuia în listă
                parabole.add(new Parabola(a, b, c));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului.");
        }

        // Afișarea parabolelor și vârfurilor
        for (Parabola p : parabole) {
            System.out.println(p);
            double[] varf = p.getVarf();
            System.out.println("Vârful parabolei este: (" + varf[0] + ", " + varf[1] + ")");
        }

        // Calcularea mijlocului și distanței între vârfuri pentru două parabole
        if (parabole.size() > 1) {
            Parabola p1 = parabole.get(0);
            Parabola p2 = parabole.get(1);

            // Mijlocul segmentului dintre cele două parabole
            double[] mijloc = p1.mijloc(p2);
            System.out.println("Mijlocul segmentului dintre cele două parabole: (" + mijloc[0] + ", " + mijloc[1] + ")");

            // Lungimea segmentului dintre cele două parabole
            double dist = p1.distanta(p2);
            System.out.println("Lungimea segmentului dintre cele două parabole: " + dist);
        }
    }
}

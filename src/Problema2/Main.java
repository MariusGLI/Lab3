package Problema2;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Produs> produse = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Citirea fișierului produse.csv și crearea obiectelor de tip Produs
        citireProduseDinFisier(produse);

        // Meniu pentru utilizator
        int optiune;
        do {
            System.out.println("\nMeniu:");
            System.out.println("1. Afișează toate produsele");
            System.out.println("2. Afișează produsele expirate");
            System.out.println("3. Vinde un produs");
            System.out.println("4. Afișează produsele cu prețul minim");
            System.out.println("5. Salvează produsele cu cantitatea mică într-un fișier");
            System.out.println("6. Ieși");
            System.out.print("Alege o opțiune: ");
            optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    afiseazaProduse(produse);
                    break;
                case 2:
                    afiseazaProduseExpirate(produse);
                    break;
                case 3:
                    vindeProdus(produse);
                    break;
                case 4:
                    afiseazaProduseCuPretMinim(produse);
                    break;
                case 5:
                    salveazaProduseCuCantitateMica(produse);
                    break;
                case 6:
                    System.out.println("Ieșire...");
                    break;
                default:
                    System.out.println("Opțiune invalidă.");
                    break;
            }
        } while (optiune != 6);
    }

    // Citirea produselor din fișier
    private static void citireProduseDinFisier(List<Produs> produse) {
        try (BufferedReader reader = new BufferedReader(new FileReader("produse.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String denumire = parts[0];
                double pret = Double.parseDouble(parts[1]);
                int cantitate = Integer.parseInt(parts[2]);
                LocalDate dataExpirarii = LocalDate.parse(parts[3]);
                produse.add(new Produs(denumire, pret, cantitate, dataExpirarii));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }

    // Afișarea tuturor produselor
    private static void afiseazaProduse(List<Produs> produse) {
        for (Produs produs : produse) {
            System.out.println(produs);
        }
    }

    // Afișarea produselor expirate
    private static void afiseazaProduseExpirate(List<Produs> produse) {
        LocalDate dataCurenta = LocalDate.now();
        for (Produs produs : produse) {
            if (produs.getDataExpirarii().isBefore(dataCurenta)) {
                System.out.println(produs);
            }
        }
    }

    // Vânzarea unui produs
    private static void vindeProdus(List<Produs> produse) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți denumirea produsului: ");
        String denumire = scanner.nextLine();
        Produs produsDeVandut = null;

        // Căutăm produsul
        for (Produs produs : produse) {
            if (produs.getDenumire().equalsIgnoreCase(denumire)) {
                produsDeVandut = produs;
                break;
            }
        }

        if (produsDeVandut == null) {
            System.out.println("Produsul nu a fost găsit.");
            return;
        }

        System.out.print("Introduceți cantitatea de vândut: ");
        int cantitateVanduta = scanner.nextInt();

        // Vânzarea produsului
        if (produsDeVandut.vindeProdus(cantitateVanduta)) {
            produse.remove(produsDeVandut); // Eliminăm produsul dacă cantitatea ajunge la 0
            System.out.println("Produsul a fost vândut. Încasări totale: " + Produs.incasari);
        } else {
            System.out.println("Cantitate insuficientă.");
        }
    }

    // Afișarea produselor cu prețul minim
    private static void afiseazaProduseCuPretMinim(List<Produs> produse) {
        if (produse.isEmpty()) {
            System.out.println("Lista de produse este goală.");
            return;
        }

        double pretMinim = Double.MAX_VALUE;
        for (Produs produs : produse) {
            if (produs.getPret() < pretMinim) {
                pretMinim = produs.getPret();
            }
        }

        System.out.println("Produse cu prețul minim (" + pretMinim + "):");
        for (Produs produs : produse) {
            if (produs.getPret() == pretMinim) {
                System.out.println(produs);
            }
        }
    }

    // Salvarea produselor cu cantitate mică într-un fișier
    private static void salveazaProduseCuCantitateMica(List<Produs> produse) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți valoarea cantității minime: ");
        int cantitateMinima = scanner.nextInt();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("produse_cantitate_mica.txt"))) {
            for (Produs produs : produse) {
                if (produs.getCantitate() < cantitateMinima) {
                    writer.write(produs.toString());
                    writer.newLine();
                }
            }
            System.out.println("Produsele cu cantitate mică au fost salvate în fișier.");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea fișierului: " + e.getMessage());
        }
    }
}

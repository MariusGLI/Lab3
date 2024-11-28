package Problema2;

import java.time.LocalDate;

public class Produs {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirarii;
    public static double incasari = 0.0;

    // Constructor
    public Produs(String denumire, double pret, int cantitate, LocalDate dataExpirarii) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirarii = dataExpirarii;
    }

    // Gettere și settere
    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public LocalDate getDataExpirarii() {
        return dataExpirarii;
    }

    public void setDataExpirarii(LocalDate dataExpirarii) {
        this.dataExpirarii = dataExpirarii;
    }

    // Redefinirea metodei toString()
    @Override
    public String toString() {
        return "Produs{" +
                "denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", dataExpirarii=" + dataExpirarii +
                '}';
    }

    // Vânzarea unui produs
    public boolean vindeProdus(int cantitateVanduta) {
        if (cantitate >= cantitateVanduta) {
            cantitate -= cantitateVanduta;
            incasari += cantitateVanduta * pret;
            if (cantitate == 0) {
                return true; // Produsul se elimină din listă
            }
        }
        return false; // Cantitate insuficientă
    }
}


package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int ALOITUSKOKO = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        taulukko = new int[ALOITUSKOKO];
        alustaTaulukko();
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        taulukko = new int[kapasiteetti];
        if (kapasiteetti < 0) {
            return;
        }
        alustaTaulukko();
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    private void alustaTaulukko() {
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        taulukko = new int[kapasiteetti];
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Ei saa olla pienempi kuin nolla!");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Ei saa olla pienempi kuin nolla!");//heitin vaan jotain :D
        }
        alustaTaulukko();
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {        
        if (!kuuluu(luku)) {
            lisaaTaulukkoon(luku);
            return true;
        }
        return false;
    }

    public void lisaaTaulukkoon(int luku) {
        taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == taulukko.length) {
                int[] vanhaTaulukko = taulukko;
                taulukko = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(vanhaTaulukko, taulukko);
            }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                siirraLuvut(i);
                return true;
            }
        }
        return false;
    }

    private void siirraLuvut(int aloitusindeksi) {
        for (int j = aloitusindeksi; j < alkioidenLkm - 1; j++) {
            int apu = taulukko[j];
            taulukko[j] = taulukko[j + 1];
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            return luoMerkkijono();
        }
    }

    public String luoMerkkijono() {
        String merkkijono = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            merkkijono += taulukko[i];
            merkkijono += ", ";
        }
        merkkijono += taulukko[alkioidenLkm - 1];
        merkkijono += "}";
        return merkkijono;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {        
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();        
        return luoYhdistejoukko(aTaulu, bTaulu);
    }
    
    public static IntJoukko luoYhdistejoukko(int[] aTaulu, int[] bTaulu) {
        IntJoukko yhdistejoukko = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdistejoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdistejoukko.lisaa(bTaulu[i]);
        }
        return yhdistejoukko;        
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {        
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();        
        return luoLeikkausjoukko(aTaulu, bTaulu);
    }
    
    public static IntJoukko luoLeikkausjoukko(int[] aTaulu, int[] bTaulu) {
        IntJoukko leikkausjoukko = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausjoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausjoukko;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {        
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        return luoErotusjoukko(aTaulu, bTaulu);        
    }
    
    public static IntJoukko luoErotusjoukko(int[] aTaulu, int[] bTaulu) {
        IntJoukko erotusjoukko = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusjoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusjoukko.poista(i);
        }
        return erotusjoukko;        
    }

}

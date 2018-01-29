package fi.academy;

import java.util.Scanner;

public class Arvauspeli {

    public static void main(String[] args) {

        Kysely kysely = new Kysely();
        kysely.begin();

    }
}

class Kysely {

    void begin() {
        System.out.println("Mikäs sun nimes on?");
        Scanner lukija = new Scanner(System.in);
        String kayttajaNimi= lukija.nextLine();

        Kysymys k1 = new Kysymys("Mikä on maailman yleisin kivilaji?", "Graniitti", "Basaltti", "Hiekkakivi", "Kvartsi", new Vastaus("Graniitti"));
        Kysymys k2 = new Kysymys("Mikä on Suomen yleisin puulaji?", "Koivu", "Kuusi", "Mänty", "Paju", new Vastaus("Mänty"));
        Kysymys k3 = new Kysymys("Mikä on Suomen kansalliseläin?", "Susi", "Laulujoutsen", "Ilves", "Karhu", new Vastaus("Karhu"));
        Kysymys k4 = new Kysymys("Mikä on Suomen yleisin koirarotu?", "Mäyräkoira", "Kultainennoutaja", "Suomen pystykorva", "Labradorinnoutaja", new Vastaus("Labradorinnoutaja"));
        Kysymys k5 = new Kysymys("Mihin ilmansuuntaan aurinko laskee?", "Länteen", "Pohjoiseen", "Itään", "Etelään", new Vastaus("Länteen"));

        Kysymys kysymyksetLista[] = {k1,k2,k3,k4,k5};
        int laskeMaara = 0;
        int laskeOikeidenSumma = 0;
        int laskeVaarienSumma = 0;

        ////////////////// Käydään läpi vastausvaihtoehdot //////////////////
        for(Kysymys k:kysymyksetLista)
        {
            System.out.println(k.kysymys);
            System.out.println("A. "+k.vaihtoehto1);
            System.out.println("B. "+k.vaihtoehto2);
            System.out.println("C. "+k.vaihtoehto3);
            System.out.println("D. "+k.vaihtoehto4);
            System.out.println("\nAnna Vastaus!");

            ////////////////// Otetaan lukijan syöttämä kirjain ylös //////////////////
            char vaihtoehto = lukija.next().charAt(0);
            String vastaukset="";

            switch(vaihtoehto) {

                case 'A':
                    vastaukset=k.vaihtoehto1;
                    break;
                case 'B':
                    vastaukset=k.vaihtoehto2;
                    break;
                case 'C':
                    vastaukset=k.vaihtoehto3;
                    break;
                case 'D':
                    vastaukset=k.vaihtoehto4;
                    break;
                default:break;

            }

            ////////////////// Annetaan hienot tulosteet vastauksesta riippuen ja pidetään lukua vastauksista //////////////////
            if(vastaukset.equals(k.vastaus.getKysymys())) {
                System.out.println("********************************************");
                System.out.println("Voi jehna, sehän oli oikein!");
                System.out.println("********************************************");
                laskeOikeidenSumma++;
            } else {
                System.out.println("********************************************");
                System.out.println("No mutta, eihän se menny ollenkaan oikein!");
                System.out.println("********************************************");
                laskeVaarienSumma++;
            }

            System.out.println("\n=============================================================================================\n");

            laskeMaara++;
        }

        ITulos tulos = new Tulos(kayttajaNimi,laskeMaara,laskeOikeidenSumma,laskeVaarienSumma);
        tulos.naytaTulos();

    }
}

class Kysymys {

    String kysymys;
    String vaihtoehto1;
    String vaihtoehto2;
    String vaihtoehto3;
    String vaihtoehto4;
    Vastaus vastaus;

    public Kysymys(String kysymys, String vaihtoehto1, String vaihtoehto2, String vaihtoehto3, String vaihtoehto4, Vastaus vastaus) {
        super();
        this.kysymys = kysymys;
        this.vaihtoehto1 = vaihtoehto1;
        this.vaihtoehto2 = vaihtoehto2;
        this.vaihtoehto3 = vaihtoehto3;
        this.vaihtoehto4 = vaihtoehto4;
        this.vastaus = vastaus;
    }

    public String getKysymys() {
        return kysymys;
    }
    public String getVaihtoehto1() {
        return vaihtoehto1;
    }
    public String getVaihtoehto2() {
        return vaihtoehto2;
    }
    public String getVaihtoehto3() {
        return vaihtoehto3;
    }
    public String getVaihtoehto4() {
        return vaihtoehto4;
    }
    public Vastaus getVastaus() {
        return vastaus;
    }

}

class Vastaus {
    String kysymys;

    public Vastaus(String kysymys) {
        super();
        this.kysymys = kysymys;
    }

    public String getKysymys() {
        return kysymys;
    }

}

interface ITulos {
    void naytaTulos();
    double naytaProsenttiMaara(int oikeatVastaukset, int kysymystenMaara);
    String naytaTulos(double prosentti);
}

class Tulos implements ITulos {
    String kayttajaNimi;
    int kysymystenMaara;
    int oikeatVastaukset;
    int vaaratVastaukset;

    public Tulos(String kayttajaNimi, int kysymystenMaara, int oikeatVastaukset, int vaaratVastaukset) {
        super();
        this.kayttajaNimi = kayttajaNimi;
        this.kysymystenMaara = kysymystenMaara;
        this.oikeatVastaukset = oikeatVastaukset;
        this.vaaratVastaukset = vaaratVastaukset;
    }

    public void naytaTulos() {
        System.out.println("Sinun tuloksesi oli:");
        System.out.println("Nimesi:                "+ kayttajaNimi);
        System.out.println("Kysymysten määrä:      "+ kysymystenMaara);
        System.out.println("Oikeat vastaukset:     "+ oikeatVastaukset);
        System.out.println("Väärät vastaukset:     "+ vaaratVastaukset);
        System.out.println("Prosenttia oikein:     "+ naytaProsenttiMaara(oikeatVastaukset, kysymystenMaara));
    }

    public double naytaProsenttiMaara(int oikeatVastaukset, int kysymystenMaara) {
        return ((double)oikeatVastaukset/kysymystenMaara)*100;
    }

    public String naytaTulos(double prosentti) {
        return null;
        // Tänne vielä toteuttaa arvosanat prosenttimäärien mukaan...sitten joskus
    }

}



import java.util.Scanner;

// Interfaccia Riproducibile
interface Riproducibile {
    void play();
}

// Classe astratta ElementoMultimediale
abstract class ElementoMultimediale {
    protected String titolo;
    protected int luminosita;

    public ElementoMultimediale(String titolo, int luminosita) {
        this.titolo = titolo;
        this.luminosita = luminosita;
    }

    public abstract void show();
}

// Classe Immagine
class Immagine extends ElementoMultimediale {
    public Immagine(String titolo, int luminosita) {
        super(titolo, luminosita);
    }

    @Override
    public void show() {
        System.out.println(titolo + ": " + "*".repeat(luminosita));
    }
}

// Classe Video
class Video extends ElementoMultimediale implements Riproducibile {
    private int volume;

    public Video(String titolo, int luminosita, int volume) {
        super(titolo, luminosita);
        this.volume = volume;
    }

    @Override
    public void show() {
        for (int i = 0; i < volume; i++) {
            System.out.println(titolo + ": " + "!".repeat(volume) + "*" + "*".repeat(luminosita));
        }
    }

    @Override
    public void play() {
        for (int i = 0; i < volume; i++) {
            System.out.println(titolo + ": " + "!".repeat(volume) + "*" + "*".repeat(luminosita));
        }
    }

    public void aumentaLuminosita() {
        luminosita++;
    }

    public void diminuisciLuminosita() {
        if (luminosita > 0) {
            luminosita--;
        }
    }

    public void aumentaVolume() {
        volume++;
    }

    public void diminuisciVolume() {
        if (volume > 0) {
            volume--;
        }
    }
}

// Classe LettoreMultimediale
public class LettoreMultimediale {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElementoMultimediale[] playlist = new ElementoMultimediale[5];

        // Lettura dei valori da tastiera e creazione degli elementi
        for (int i = 0; i < playlist.length; i++) {
            System.out.println("Inserisci il titolo per l'elemento " + (i + 1) + ": ");
            String titolo = scanner.nextLine();
            System.out.println("Inserisci la luminosita per l'elemento " + (i + 1) + ": ");
            int luminosita = Integer.parseInt(scanner.nextLine());
            System.out.println("Inserisci il volume per l'elemento " + (i + 1) + ": ");
            int volume = Integer.parseInt(scanner.nextLine());

            playlist[i] = new Video(titolo, luminosita, volume);
        }

        // Menu interattivo per l'esecuzione degli elementi
        int scelta;
        do {
            System.out.println("Scegli l'elemento da eseguire (1-5) o 0 per uscire: ");
            scelta = Integer.parseInt(scanner.nextLine());
            if (scelta >= 1 && scelta <= 5) {
                ElementoMultimediale elementoScelto = playlist[scelta - 1];
                if (elementoScelto instanceof Riproducibile) {
                    ((Riproducibile) elementoScelto).play();
                } else {
                    elementoScelto.show();
                }
            } else if (scelta != 0) {
                System.out.println("Scelta non valida. Riprova.");
            }
        } while (scelta != 0);
    }
}

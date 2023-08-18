import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Musician {
    private String name;
    private List<Band> bands;

    public Musician(String name) {
        this.name = name;
        this.bands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void joinBand(Band band) {
        if (!bands.contains(band)) {
            bands.add(band);
            band.addMusician(this);
        }
    }

    public void leaveBand(Band band) {
        if (bands.contains(band)) {
            bands.remove(band);
            band.removeMusician(this);
        }
    }

    public List<Band> getBands() {
        return bands;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Band {
    private String name;
    private List<Musician> musicians;

    public Band(String name) {
        this.name = name;
        this.musicians = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMusician(Musician musician) {
        if (!musicians.contains(musician)) {
            musicians.add(musician);
            musician.joinBand(this);
        }
    }

    public void removeMusician(Musician musician) {
        if (musicians.contains(musician)) {
            musicians.remove(musician);
            musician.leaveBand(this);
        }
    }

    public List<Musician> getMusicians() {
        return musicians;
    }

    @Override
    public String toString() {
        return name;
    }
}

 class MusicianBandExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of musicians: ");
        int numMusicians = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        List<Musician> musicians = new ArrayList<>();
        for (int i = 0; i < numMusicians; i++) {
            System.out.print("Enter the name of musician " + (i + 1) + ": ");
            String musicianName = scanner.nextLine();
            musicians.add(new Musician(musicianName));
        }

        System.out.print("Enter the number of bands: ");
        int numBands = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        List<Band> bands = new ArrayList<>();
        for (int i = 0; i < numBands; i++) {
            System.out.print("Enter the name of band " + (i + 1) + ": ");
            String bandName = scanner.nextLine();
            bands.add(new Band(bandName));
        }

        for (Musician musician : musicians) {
            System.out.println("Adding " + musician.getName() + " to bands:");
            for (int i = 0; i < bands.size(); i++) {
                System.out.print("Do you want to add " + musician.getName() + " to band " + bands.get(i).getName() + "? (y/n): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("y")) {
                    musician.joinBand(bands.get(i));
                }
            }
        }

        System.out.println("\nBands and their Musicians:");
        for (Band band : bands) {
            System.out.println(band.getName() + " has musicians: " + band.getMusicians());
        }

        scanner.close();
    }
}

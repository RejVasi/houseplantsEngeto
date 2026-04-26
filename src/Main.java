import java.io.*;
import java.lang.invoke.StringConcatFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Path GOODFLOWERTXT = Path.of("data", "kvetiny.txt");
    private static final Path BADFLOWERDATE = Path.of("data", "kvetiny-spatne-datum.txt");
    private static final Path BADFLOWERFREQUENCY = Path.of("data", "kvetiny-spatne-frekvence.txt");
    private static final String DELIMITER = "\t";

    public static void main(String[] args) throws PlantException {

        PlantManager plantManager = new PlantManager();

        // 7.1)
        plantManager.readFile(GOODFLOWERTXT, DELIMITER);

        // 7.2)
        System.out.println("7.2) Watering info of read plant list");
        for (Plant plant : plantManager.getPlantListCopy()) {
            System.out.println(plant.getWateringInfo());
        }

        // 7.3)
        System.out.println("7.3) Adding plant");
        plantManager.addPlant(new Plant("Dandelion"));


        // 7.4)
        System.out.println("7.4) Adding 10 plants");
        for (int i = 1; i <= 10; i++) {
            plantManager.addPlant(new Plant("Tulipán na prodej " + i, 14));
        }

        // 7.5)
        plantManager.removePlantFromIndex(3);
        System.out.println("7.5) Plant index 3 removed, currently contained: ");

        for (Plant plant : plantManager.getPlantListCopy()) {
            System.out.println(plant.getWateringInfo());
        }

        // 7.6)
        System.out.println("7.6) Saving to file");
        plantManager.saveToFile(Path.of("data", "savedList.txt"), DELIMITER);


        PlantManager plantManager1 = new PlantManager();
        // 7.7)
        System.out.println("Reading saved file: ");
        plantManager1.readFile(Path.of("data", "savedList.txt"), DELIMITER);
        for(Plant plant : plantManager1.getPlantListCopy())
        {
            System.out.println(plant.toString());
        }

        // 7.8) Sorting by name
        System.out.println("Sorted by name: ");
        plantManager.sortByName();
        for(Plant plant : plantManager.getPlantListCopy())
        {
            System.out.println(plant.toString());
        }

        System.out.println("Sorted by watering date: ");
        plantManager.sortByLastWateringDate();
        for(Plant plant : plantManager.getPlantListCopy())
        {
            System.out.println(plant.toString());
        }


    }


}
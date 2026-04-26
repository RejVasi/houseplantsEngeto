import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantManager
{
    List<Plant> plantList = new ArrayList<>();

    // 4.1)
    public void addPlant(Plant plant)
    {
        this.plantList.add(plant);
    }

    public void clearList()
    {
        this.plantList.clear();
    }

    public void addPlantList(List<Plant> plantList)
    {
        this.plantList.addAll(plantList);
    }

    // 4.2)
    public Plant getPlantFromIndex(int index)
    {
        return this.plantList.get(index);
    }

    // 4.3)
    public void removePlantFromList(Plant plant)
    {
        this.plantList.remove(plant);
    }

    public void removePlantFromIndex(int index)
    {
        this.plantList.remove(index);
    }

    // 4.4)
    public List<Plant> getPlantListCopy()
    {
        return new ArrayList<>(this.plantList);
    }

    // 4.4,5)
    /**
     * Returns copy of list of plants, that require watering, last watering + frequency <= today's date
     * @return List<Plant>
     */
    public List<Plant> getPlantsRequireWateringList()
    {
        return  getPlantListCopy()
                .stream()
                .filter(Plant::getRequireWatering)
                .toList();
    }



    // 5.0)
    public void sortByName()
    {
        Collections.sort(this.plantList);
    }

    // 5.0)
    public void sortByLastWateringDate()
    {
        this.plantList.sort(Comparator.comparing(Plant::getLastWateringDate));
    }

    // 6.1)
    public void readFile(Path filePath, String delimiter)
    {
        try (Scanner scanner = new Scanner(
                new BufferedReader(
                        new FileReader(filePath.toString())
                )
        )) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int expectedSplitLength = 5;

                String[] stringSplit = line.split(delimiter);
                if (stringSplit.length != expectedSplitLength) {
                    throw new PlantException("Invalid data count on line: " + line + "\nExpected: " + expectedSplitLength + "\nReceived: " + stringSplit.length);
                }

                String name = stringSplit[0];
                String description = stringSplit[1];
                int wateringFrequency;
                try {
                    wateringFrequency = Integer.parseInt(stringSplit[2]);
                } catch (NumberFormatException e) {
                    throw new PlantException("Parsing value to Integer failed: " + stringSplit[2] + "\nLine: " + line, e);
                }

                LocalDate lastWateringDate;
                LocalDate plantedDate;
                try {
                    lastWateringDate = LocalDate.parse(stringSplit[3]);
                } catch (DateTimeParseException e) {
                    throw new PlantException("Parsing lastWateringDate value to LocalDate failed: " + stringSplit[3] + "\nLine: " + line, e);
                }
                try {
                    plantedDate = LocalDate.parse(stringSplit[4]);
                } catch (DateTimeParseException e) {
                    throw new PlantException("Parsing plantedDate value to LocalDate failed: " + stringSplit[4] + "\nLine: " + line, e);
                }

                this.addPlant(new Plant(name, description, wateringFrequency, plantedDate, lastWateringDate));
            }

        } catch (IOException e) {
            System.err.println("Failed to load data from file " + e.getMessage());
        } catch (PlantException e) {
            this.clearList();

            // "Aplikace by neměla havarovat, ale vypsat chybu a řádně pokračovat s prázdným seznamem."
            System.err.println(e.getMessage());
        }
    }

    // 6.2)
    public void saveToFile(Path path, String delimiter) {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8))) {
            for (Plant plant : this.getPlantListCopy())
            {
                writer.println(plant.getPlantToString(delimiter));
            }
        }
        catch (Exception e)
        {
            System.err.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

}

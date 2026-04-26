import java.lang.reflect.Array;
import java.time.LocalDate;

public class Plant
        implements Comparable<Plant>
{
    // 1.1 - 1.5)
    private String name;
    private String notes;
    private LocalDate plantedDate;
    private LocalDate lastWateringDate;
    private int wateringFrequency;

    @Override
    public int compareTo(Plant second)
    {
        return this.getName().compareTo(second.getName());
    }


    @Override
    public String toString() {
        return "\nPlant{" +
                "\nname= " + this.getName() +
                "\nnotes= " + this.getNotes() +
                "\nwateringFrequency= " + this.getWateringFrequency() +
                "\nplantedDate= " + this.getPlantedDate() +
                "\nlastWateringDate= " + this.getLastWateringDate() +
                "\n}";
    }

    // 1.11)
    public Plant(String name, String notes, int wateringFrequency, LocalDate plantedDate, LocalDate lastWateringDate)
            throws PlantException
    {
        this.name = name;
        this.notes = notes;
        setWateringFrequency(wateringFrequency);
        this.plantedDate = plantedDate;
        setLastWateringDate(lastWateringDate);
    }

    // 1.12)
    public Plant(String name, int wateringFrequency)
            throws PlantException
    {
        this(name, "", wateringFrequency, LocalDate.now(), LocalDate.now());
    }

    // 1.13)
    public Plant(String name)
            throws PlantException
    {
        this(name, "", 7, LocalDate.now(), LocalDate.now());
    }

    // 2.1)
    public String getWateringInfo()
    {
        return getName() + "\nLast watering date: " + getLastWateringDate() + "\nnext recommended watering date: " + getNextRecommendedWateringDate() + "\n";
    }

    // 2.2)
    public void doWateringNow()
    {
        this.lastWateringDate = LocalDate.now();
    }

    private LocalDate getNextRecommendedWateringDate()
    {
        return getLastWateringDate().plusDays(getWateringFrequency());
    }

    public boolean getRequireWatering()
    {
        // If next watering date (last watering + frequency) is before or today
        return getNextRecommendedWateringDate().isBefore(LocalDate.now().plusDays(1));
    }

    /**
     * Returns plant string in saving file format
     * */
    public String getPlantToString(String delimiter)
    {
        return String.join(delimiter,
                this.getName(),
                this.getNotes(),
                Integer.toString(this.getWateringFrequency()),
                this.getLastWateringDate().toString(),
                this.getPlantedDate().toString()
        );
    }



    //region Getters and Setters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public LocalDate getPlantedDate()
    {
        return plantedDate;
    }

    public void setPlantedDate(LocalDate plantedDate)
    {
        this.plantedDate = plantedDate;
    }

    public LocalDate getLastWateringDate()
    {
        return lastWateringDate;
    }

    // 3.2)
    public void setLastWateringDate(LocalDate lastWateringDate)
            throws PlantException
    {
        if(lastWateringDate.isBefore(getPlantedDate()))
        {
            throw new PlantException("Last watering date cannot be before planted date\nEntered last watering date: " + lastWateringDate + "\nPlanted date is: " + this.getPlantedDate());
        }

        this.lastWateringDate = lastWateringDate;
    }

    public int getWateringFrequency()
    {
        return wateringFrequency;
    }

    // 3.1)
    public void setWateringFrequency(int wateringFrequency)
            throws PlantException
    {
        if(wateringFrequency < 1)
        {
                throw new PlantException("Watering frequency cannot be less than 1\nEntered: " + wateringFrequency);
        }

        this.wateringFrequency = wateringFrequency;
    }
    //endregion

}

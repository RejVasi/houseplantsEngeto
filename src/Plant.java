import java.lang.reflect.Array;
import java.time.LocalDate;

public class Plant
        implements Comparable<Plant>
{
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

    public Plant(String name, String notes, LocalDate plantedDate, LocalDate lastWateringDate, int wateringFrequency)
            throws PlantException
    {
        this.name = name;
        this.notes = notes;
        this.plantedDate = plantedDate;
        setLastWateringDate(lastWateringDate);
        this.wateringFrequency = wateringFrequency;
    }

    public Plant(String name, int wateringFrequency)
            throws PlantException
    {
        this(name, "", LocalDate.now(), LocalDate.now(), wateringFrequency);
    }


    public Plant(String name)
            throws PlantException
    {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }


    public String getWateringInfo()
    {
        return getName() + "\nlast watering date: " + getLastWateringDate() + "next recommended watering date:" + getNextRecommendedWateringDate();
    }

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
        if(getNextRecommendedWateringDate().isBefore(LocalDate.now().plusDays(1)))
        {
            return true;
        }

        return false;
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

    public void setLastWateringDate(LocalDate lastWateringDate)
            throws PlantException
    {
        if(lastWateringDate.isBefore(getPlantedDate()))
        {
            throw new PlantException("Last watering date cannot be before planted date\nEntered: " + lastWateringDate);
        }

        this.lastWateringDate = lastWateringDate;
    }

    public int getWateringFrequency()
    {
        return wateringFrequency;
    }

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

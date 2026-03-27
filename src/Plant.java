import java.lang.reflect.Array;
import java.time.LocalDate;

public class Plant
{

    private String name;
    private String notes;
    private LocalDate plantedDate;
    private LocalDate lastWateringDate;
    private int wateringFrequency;

    public Plant(String _name, String _notes, LocalDate _plantedDate, LocalDate _lastWateringDate, int _wateringFrequency)
    {
        this.name = _name;
        this.notes = _notes;
        this.plantedDate = _plantedDate;
        this.lastWateringDate = _lastWateringDate;
        this.wateringFrequency = _wateringFrequency;
    }

    public Plant(String _name, int _wateringFrequency)
    {
        this.name = _name;
        this.notes = "";
        this.plantedDate = this.lastWateringDate = LocalDate.now();
        this.wateringFrequency = _wateringFrequency;
    }


    public Plant(String _name)
    {
        this.name = _name;
        this.notes = "";
        this.plantedDate = this.lastWateringDate = LocalDate.now();
        this.wateringFrequency = 7;
    }

    public Object[] getWateringInfo()
    {
        return new Object[] {getName(), getLastWateringDate(), getNextRecommendedWateringDate()};
    }

    public void doWateringNow()
    {
        this.lastWateringDate = LocalDate.now();
    }

    private LocalDate getNextRecommendedWateringDate()
    {
        return getLastWateringDate().plusDays(getWateringFrequency());
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
    {
        this.lastWateringDate = lastWateringDate;
    }

    public int getWateringFrequency()
    {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency)
    {
        this.wateringFrequency = wateringFrequency;
    }
    //endregion

}

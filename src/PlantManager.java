import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlantManager
{


    List<Plant> plantList = new ArrayList<>();

    public void addPlant(Plant plant)
    {
        this.plantList.add(plant);
    }

    public Plant getPlantFromIndex(int index)
            throws IndexOutOfBoundsException
    {
        if(index > -1 && index < this.plantList.size())
        {
            return this.plantList.get(index);
        }

        throw new IndexOutOfBoundsException("Cannot retrieve plant, index is out of bounds\n"
                + "used index: " + index);
    }

    public void removePlantFromList(Plant plant)
    {
        this.plantList.remove(plant);
    }

    public List<Plant> getPlantListCopy()
    {
        return new ArrayList<>(this.plantList);
    }

    /**
     * Returns copy of list of plants, that require watering, last watering + frequency <= today's date
     * @return List<Plant>
     */
    public List<Plant> getPlantsRequireWatering()
    {
        return  getPlantListCopy()
                .stream()
                .filter(Plant::getRequireWatering)
                .toList();
    }

    public void sortByName()
    {
        this.plantList.sort(Comparator.comparing(Plant::getName));
    }

    public void sortByLastWateringDate()
    {
        this.plantList.sort(Comparator.comparing(Plant::getLastWateringDate));
    }

}

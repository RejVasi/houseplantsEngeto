import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws PlantException {
        Plant plant1 = new Plant("Tulipan1", "test1", LocalDate.now(), LocalDate.now(), 10);

        //System.out.println(plant1.getWateringInfo()[0] + "\n" + plant1.getWateringInfo()[1] + "\n" +  plant1.getWateringInfo()[2]);


    }
}
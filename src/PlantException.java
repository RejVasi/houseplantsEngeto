// 3.0)
public class PlantException extends Exception
{
    public PlantException(String message)
    {
        super(message);
    }
    //  If further tracking is required
    public PlantException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

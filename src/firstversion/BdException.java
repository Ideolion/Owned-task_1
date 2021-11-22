package firstversion;

/**
 * Класс представляет общее исключение, возникающее при работе с базой объектов/субъектов.
 * @author Уфилин Д.В.
 */
public class BdException extends Exception {

    public BdException() {
    }
   
     public BdException(String string) {
        super(string);
    }
       
    BdException(Exception e) {
        System.out.println("");
        System.out.println("Возникла ошибка :"+e);
    }
}

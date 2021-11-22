package firstversion;

/**
 * Класс субъект,может быть способным владеть некоторым подмножеством из
 * объектов
 *
 * @author Денис Уфилин
 * @version 0_о
 */
public class Subject {
 
    
    /**
     * Поле имя субъекта
     */
    int subjectName;

    /**
     * Поле массив имен объектов, принадлежащих субъекту
     */
    int[] objectsOwned;

    public Subject(int subjectName, int[] objectsOwned) {
        this.subjectName = subjectName;
        this.objectsOwned = objectsOwned;
    }

    public Subject(int subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(int subjectName) {
        this.subjectName = subjectName;
    }

    public int[] getObjectsOwned() {
        return objectsOwned;
    }

    public void setObjectsOwned(int[] objectsOwned) {
        this.objectsOwned = objectsOwned;
    }
   
    

}

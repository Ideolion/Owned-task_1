
package firstversion;

/** 
 * Класс объект,может принадлежать только одному из субъектов
 * @author Денис Уфилин
 * 
*/
public class Object {
    /** Поле имя объекта, */ 
    int objectName;
    /** Поле владелец объекта, в каждый момент времени у объекта один владелец*/
    int subjectName;

    public Object(int objectName, int subjectName) {
        this.objectName = objectName;
        this.subjectName = subjectName;
    }

    public int getObjectName() {
        return objectName;
    }

    public void setObjectName(int objectName) {
        this.objectName = objectName;
    }

    public int getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(int subjectName) {
        this.subjectName = subjectName;
    }

    

    
}

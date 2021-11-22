package firstversion;

import java.util.ArrayList;

/**
 *
 * @author kiry
 */
public class OwnedTask {

    public static void OwnedTask() throws BdException {

        ArrayList<Object> objectsList = new ArrayList<Object>();
        ArrayList<Subject> subjectsList = new ArrayList<Subject>();
        DbService ownersTask = new DbService();
        DbService.connection();
        //сохраняем все имеющиеся объекты в ArrayList
        objectsList = ownersTask.findAllObjects();
        //получаем имена субъектов с нормальным приоритетом
        subjectsList = ownersTask.findSubjectsNormPriority();
        //для этих субъектов получаем массив принадлежащих объектов
        for (int i = 0; i < subjectsList.size(); i++) {
            subjectsList.get(i).setObjectsOwned(ownersTask.findSubjectSubset(subjectsList.get(i).getSubjectName()));
        }
        //распределяем субъекты с нормальным приоритетом.
        objectsList = AllocationNormPrioritySubjects(objectsList, subjectsList);
        //отмечаем в базе отработанные субъекты с нормальным приоритетом
        ownersTask.savingProcessingmark(subjectsList);
        //получаем имена субъектов с низким приоритетом
        subjectsList = ownersTask.findSubjectsLoPriority();
        //для этих субъектов получаем массив принадлежащих объектов
        for (int i = 0; i < subjectsList.size(); i++) {
            subjectsList.get(i).setObjectsOwned(ownersTask.findSubjectSubset(subjectsList.get(i).getSubjectName()));
        }
        //распределяем субъекты с низким приоритетом, они займут только "свободные места", 
        //либо займут места субъектов с признаком избегания владения, если таковые имеются 
        objectsList = AllocationLoPrioritySubjects(objectsList, subjectsList, ownersTask);
        //отмечаем в базе отработанные субъекты с низким приоритетом
        ownersTask.savingProcessingmark(subjectsList);

        //получаем имена субъектов с признаком избегания владения объектами
        subjectsList = ownersTask.findSubjectsAvoidance();
        //для этих субъектов получаем массив принадлежащих объектов
        for (int i = 0; i < subjectsList.size(); i++) {
            subjectsList.get(i).setObjectsOwned(ownersTask.findSubjectSubset(subjectsList.get(i).getSubjectName()));
        }
        //распределяем субъекты с избеганием владения, они займут только "свободные места". 
        objectsList = AllocationSubjectsAvoidance(objectsList, subjectsList);
        //отмечаем в базе отработанные субъекты с избеганием владения
        ownersTask.savingProcessingmark(subjectsList);
        //получаем имена субъектов с пометкой на удаление
        subjectsList = ownersTask.findSubjectsDeletion();
        //для этих субъектов получаем массив принадлежащих объектов
        for (int i = 0; i < subjectsList.size(); i++) {
            subjectsList.get(i).setObjectsOwned(ownersTask.findSubjectSubset(subjectsList.get(i).getSubjectName()));
            }
        //распределяем субъекты с рометкой на удаление. 
        objectsList = AllocationSubjectsDeletion(objectsList, subjectsList);
        //отмечаем в базе отработанные субъекты с пометкой на удаление
        ownersTask.savingProcessingmark(subjectsList);
        // вносим информацию о распределении субъектов в базу данных
        ownersTask.savingToDatabase(objectsList);
        ownersTask.close();
        
//        System.out.println("");
//        System.out.println("измененный список - ");
//        for (int i = 0; i < objectsList.size(); i++) {
//            System.out.print("Имя объекта - " + objectsList.get(i).getObjectName());
//            if (objectsList.get(i).getSubjectName() == 0) {
//                System.out.println(" нет");
//            } else {
//                System.out.println("имя субъекта" + objectsList.get(i).getSubjectName());
//            }
//        }
    }

    /**
     * Метод осуществляет распределение субъектов с нормальным приоритетом в
     * массив объектов
     *
     * @param objectsList полученный ранее из базы данных массив объектов<br>
     * (не содержащий данных о субъекте при первом прогоне).
     * @param subjectsList полученный ранее из базы массив субъектов.
     * @return возвращает массив объектов objectsList в который внесены данные
     * о<br>
     * субъектах с нормальным приоритетом.
     *
     */
    public static ArrayList<Object> AllocationNormPrioritySubjects(ArrayList<Object> objectsList, ArrayList<Subject> subjectsList) {

        for (int i = 0; i < subjectsList.size(); i++) {
            int subjectName = subjectsList.get(i).getSubjectName();
            int[] objectsOwned = subjectsList.get(i).getObjectsOwned();
            for (int j = 0; j < objectsOwned.length; j++) {
                for (Object Object : objectsList) {
                    if (Object.getObjectName() == objectsOwned[j]) {
                        Object.setSubjectName(subjectName);
                    }

                }
            }
        }
        return objectsList;
    }

    /**
     * Метод осуществляет распределение субъектов с низким приоритетом в
     * массив<br>
     * объектов, таким образом что они занимают только свободные объекты или<br>
     * объекты с признаком "избегания владения"
     *
     * @param objectsList массив объектов с внесенными данными о субъектах с
     * высоким приоритетом<br>
     * @param subjectsList полученный ранее из базы массив субъектов с низким
     * приоритетом.
     * @param ownersTask экземпляр класса DbService, используется<br>
     * для запроса приоритета субъекта
     * @return возвращает массив объектов objectsList в который внесены данные
     * о<br>
     * субъектах с низким приоритетом
     */
    public static ArrayList<Object> AllocationLoPrioritySubjects(ArrayList<Object> objectsList, ArrayList<Subject> subjectsList, DbService ownersTask) throws BdException {
        try {
            for (int i = 0; i < subjectsList.size(); i++) {
                int subjectName = subjectsList.get(i).getSubjectName();
                int[] objectsOwned = subjectsList.get(i).getObjectsOwned();
                for (int j = 0; j < objectsOwned.length; j++) {
                }
                for (int j = 0; j < objectsOwned.length; j++) {
                }
                for (int j = 0; j < objectsOwned.length; j++) {
                    for (Object Object : objectsList) {
                        if (Object.getObjectName() == objectsOwned[j]) {
                            if (Object.getSubjectName() == 0) {
                                Object.setSubjectName(subjectName);
                            } else {
                                int avoidance = ownersTask.findSubjectsAvoidanceMark(Object.getSubjectName());
                                switch (ownersTask.findSubjectsAvoidanceMark(Object.getSubjectName())) {
                                    case (1):
                                        Object.setSubjectName(subjectName);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new BdException(e);
        }
        return objectsList;

    }

    /**
     * Метод осуществляет распределение субъектов с отметкой об избегании
     * владения<br>
     * этим субъектам могут принадлежать только свободные объекты
     *
     * @param objectsList полученный ранее из базы данных массив объектов<br>
     * @param subjectsList полученный ранее из базы массив субъектов.
     * @return возвращает массив объектов objectsList в который внесены данные
     * о<br>
     * субъектах с нормальным приоритетом.
     *
     */
    public static ArrayList<Object> AllocationSubjectsAvoidance(ArrayList<Object> objectsList, ArrayList<Subject> subjectsList) {

        for (int i = 0; i < subjectsList.size(); i++) {
            int subjectName = subjectsList.get(i).getSubjectName();
            int[] objectsOwned = subjectsList.get(i).getObjectsOwned();
            for (int j = 0; j < objectsOwned.length; j++) {
                for (Object Object : objectsList) {
                    if (Object.getObjectName() == objectsOwned[j]) {
                        if (Object.getSubjectName() == 0) {
                            Object.setSubjectName(subjectName);
                        }
                    }
                }
            }
        }
        return objectsList;
    }

    /**
     * Метод осуществляет удаление субъектов с отметкой на удаление<br>
     *
     * @param objectsList полученный ранее из базы данных массив объектов<br>
     * @param subjectsList полученный ранее из базы массив субъектов.
     * @return возвращает массив объектов objectsList из которого<br>
     * удалена информация о субъектах с пометкой на удаление
     */
    private static ArrayList<Object> AllocationSubjectsDeletion(ArrayList<Object> objectsList, ArrayList<Subject> subjectsList) {
        for (int i = 0; i < subjectsList.size(); i++) {
            int subjectName = subjectsList.get(i).getSubjectName();
            int[] objectsOwned = subjectsList.get(i).getObjectsOwned();
            for (int j = 0; j < objectsOwned.length; j++) {
                for (Object Object : objectsList) {
                    if (Object.getObjectName() == objectsOwned[j]) {
                        if (Object.getSubjectName() != 0) {
                            Object.setSubjectName(0);
                        }
                    }
                }
            }
        }
        return objectsList;
    }

}

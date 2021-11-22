/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package firstversion;

import java.util.ArrayList;

/**
 *
 * @author kiry
 */
public interface IDbService extends AutoCloseable {
  
/**
* Метод производит поиск объектов в базе данных.
*
* @return возвращает массив найденных объектов.
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
ArrayList<Object> findAllObjects() throws BdException;    
 
/**
* Метод производит поиск субъектов с нормальным приоритетом в базе данных у которых:<br>
* - нет пометки на удаление<br>
* - нет признака того, что субъект уже был распределен ранее<br>
* @return возвращает массив найденных субъектов.
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
ArrayList<Subject> findSubjectsNormPriority() throws BdException;    

/**
* метод осуществляет поиск объектов принадлежащих субъекту
* @param subjectName имя субъекта, необходимо для выборки подмножества объектов,<br>
* принадлежащих субъекту
* @return возвращает подмножество объектов принадлежащих субъекту.
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
int[] findSubjectSubset(int subjectName)throws BdException;

/**
* Метод производит поиск субъектов с низким приоритетом в базе данных у которых:<br>
* - нет пометки на удаление<br>
* - нет признака того, что субъект уже был распределен ранее<br>
* @return возвращает массив найденных субъектов.
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
ArrayList<Subject> findSubjectsLoPriority() throws BdException;

/**
* Метод производит запрашивает значение Avoidance у субъекта<br>
* если у него:<br>
* - нет пометки на удаление<br>
* - нет признака того, что субъект уже был распределен ранее<br>
* @return возвращает значение 1 если субъект имеет свойство избегания владения.
* @param subjectName имя субъекта
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
int findSubjectsAvoidanceMark(int subjectName) throws BdException;

/**
* Метод производит поиск субъектов с пометкой избегания владения у которых:<br>
* - нет пометки на удаление<br>
* - нет признака того, что субъект уже был распределен ранее<br>
* @return возвращает массив найденных субъектов.
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
ArrayList<Subject> findSubjectsAvoidance() throws BdException;

/**
* Метод производит поиск субъектов с пометкой на удаление у которых:<br>
* - нет признака того, что субъект уже был распределен ранее<br>
* @return возвращает массив найденных субъектов.
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
ArrayList<Subject> findSubjectsDeletion() throws BdException;

/**
* Метод осуществляет запись в базу данных сведений о распреелении субъектов<br>
//* @return возвращает массив найденных субъектов.
* @param objectsList массив объектов с данными распределения субъектов
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
void savingToDatabase(ArrayList<Object> objectsList) throws BdException;

/**
* Метод вносит отметку об обработке субъекта<br>
* @param subjectsList массив объектов с данными распределения субъектов
* @throws BdException выбрасывается в
* случае ошибки доступа к базе данных
*/
void savingProcessingmark(ArrayList<Subject> sujectsList) throws BdException;
    
   
    
    
}

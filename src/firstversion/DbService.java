package firstversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kiry
 */
public class DbService implements IDbService {

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private PreparedStatement ps;

    public static void connection() throws BdException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbobjectssubjects", "root", "gfhjkmgfhjkm");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (con != null) {
                System.out.println("Connection created successfully");
            } else {
                System.out.println("Connection is not created");
            }
        } catch (Exception e) {
            System.out.println("Problem with creating connection");
            throw new BdException(e);
        }
    }

    @Override
    public ArrayList<Object> findAllObjects() throws BdException {
        ArrayList<Object> objectsList = new ArrayList<Object>();
        try {
            //  stmt = con.createStatement();
            String query = "SELECT * FROM objects";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Object object = new Object(rs.getInt(1), rs.getInt(2));
                objectsList.add(object);
            }

        } catch (Exception e) {
            throw new BdException(e);
        }
        return objectsList;
    }

    @Override
    public ArrayList<Subject> findSubjectsNormPriority() throws BdException {
        ArrayList<Subject> subjectsList = new ArrayList<Subject>();
        try {
            String query = "SELECT SubjectName FROM subjects WHERE LOWPRIO = 0 AND markForDeletion = 0 AND distributed = 0 AND avoidance = 0";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt(1));
                subjectsList.add(subject);
            }
        } catch (Exception e) {
            throw new BdException(e);
        }
        return subjectsList;
    }

    @Override
    public int[] findSubjectSubset(int subjectName) throws BdException {
        int[] objectsOwned;
        int m = 0;
        try {
            String query = "SELECT Objectname FROM Ownership where SubjectName = " + subjectName + "";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                m++;
            }
            objectsOwned = new int[m];
            rs.beforeFirst();
            int counter = 0;
            while (rs.next()) {
                objectsOwned[counter++] = rs.getInt(1);

            }
        } catch (Exception e) {
            throw new BdException(e);
        }

        return objectsOwned;
    }

    @Override
    public ArrayList<Subject> findSubjectsLoPriority() throws BdException {
        ArrayList<Subject> subjectsList = new ArrayList<Subject>();
        try {
            String query = "SELECT SubjectName FROM subjects WHERE LOWPRIO = 1 AND markForDeletion = 0 AND distributed = 0 AND Avoidance = 0";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt(1));
                subjectsList.add(subject);
            }
        } catch (Exception e) {
            throw new BdException(e);
        }
        return subjectsList;
    }

    @Override
    public int findSubjectsAvoidanceMark(int subjectName) throws BdException {
        int avoidance = 0;
        try {
            String query = "SELECT SubjectName FROM subjects WHERE markForDeletion = 0 AND distributed = 0 AND Avoidance = 1";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                avoidance = rs.getInt(1);
            }
        } catch (Exception e) {
            throw new BdException(e);
        }
        return avoidance;
    }

    @Override
    public ArrayList<Subject> findSubjectsAvoidance() throws BdException {
        ArrayList<Subject> subjectsList = new ArrayList<Subject>();
        try {
            String query = "SELECT SubjectName FROM subjects WHERE markForDeletion = 0 AND distributed = 0 AND avoidance = 1";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt(1));
                subjectsList.add(subject);
            }
        } catch (Exception e) {
            throw new BdException(e);
        }
        return subjectsList;
    }

    @Override
    public ArrayList<Subject> findSubjectsDeletion() throws BdException {
        ArrayList<Subject> subjectsList = new ArrayList<Subject>();
        try {
            String query = "SELECT SubjectName FROM subjects WHERE markForDeletion = 1 AND distributed = 0";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt(1));
                subjectsList.add(subject);
            }
        } catch (Exception e) {
            throw new BdException(e);
        }
        return subjectsList;
    }

    @Override
    public void savingToDatabase(ArrayList<Object> objectsList) throws BdException {
        try {
            int result = 0;
            for (int i = 0; i < objectsList.size(); i++) {
                if (objectsList.get(i).getSubjectName() != 0) {
                    String query = "UPDATE objects SET Subjectname = " + objectsList.get(i).getSubjectName() + " where ObjectName = " + objectsList.get(i).getObjectName() + "";
                    result = stmt.executeUpdate(query);
                }else{
                String query = "UPDATE objects SET Subjectname = null where ObjectName = " + objectsList.get(i).getObjectName() + "";
                result = stmt.executeUpdate(query);}
            }
        } catch (Exception e) {
            throw new BdException(e);
        }

    }

    @Override
    public void savingProcessingmark(ArrayList<Subject> sujectsList) throws BdException {
        try {
            int result = 0;
            for (int i = 0; i < sujectsList.size(); i++) {
                String query = "UPDATE subjects SET distributed = 1 where Subjectname = "+sujectsList.get(i).getSubjectName()+"";
                result = stmt.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new BdException(e);
        }

    }

    @Override
    public void close() throws BdException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Connection to BD is closed.");
            }
        } catch (Exception e) {
            throw new BdException(e);
        }
    }

}

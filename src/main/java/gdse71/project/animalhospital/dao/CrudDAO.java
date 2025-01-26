package gdse71.project.animalhospital.dao;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll() throws Exception,ClassNotFoundException;
    boolean save(T dto) throws Exception, ClassNotFoundException;
    boolean delete(String id) throws Exception , ClassNotFoundException;
    boolean update(T dto) throws Exception, ClassNotFoundException;
    String generateId() throws Exception, ClassNotFoundException;
    String loadId() throws Exception, ClassNotFoundException;
}

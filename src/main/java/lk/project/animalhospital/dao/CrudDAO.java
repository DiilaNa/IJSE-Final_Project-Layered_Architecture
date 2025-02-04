package lk.project.animalhospital.dao;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll() throws Exception;
    boolean save(T dto) throws Exception;
    boolean delete(String id) throws Exception;
    boolean update(T dto) throws Exception;
    String generateId() throws Exception;

}

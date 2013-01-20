package cz.cvut.warehouse.dao;

import java.io.Serializable;

public interface GenericDao <T, PK extends Serializable> {

    /** Persist the newInstance object into database */
    void create(T entity);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** Save changes made to a persistent object.  */
    void update(T entity);

    /** Remove an object from persistent storage in the database */
    void delete(T entity);
}


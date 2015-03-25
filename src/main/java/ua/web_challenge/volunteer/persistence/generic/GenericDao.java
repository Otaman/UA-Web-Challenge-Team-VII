package ua.web_challenge.volunteer.persistence.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 18.03.2015
 *
 * @author Bohdan Vanchuhov
 */
public interface GenericDao<T, PK extends Serializable> {
    T findById(PK id);
    T add(T object);
    T update(T object);
    void delete(T object);
    void delete(PK id);
    List<T> findRange(int startPosition, int maxResults);
}

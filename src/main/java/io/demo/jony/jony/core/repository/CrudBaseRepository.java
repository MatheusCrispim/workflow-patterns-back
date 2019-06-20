package io.demo.jony.jony.core.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

import io.demo.jony.jony.core.model.Model;

/**
 * Base Repository for CRUD operations.
 * 
 * @author Virtus
 *
 * @param <M> Model type.
 * @param <T> ID type.
 */
@NoRepositoryBean
public interface CrudBaseRepository<M extends Model<T>, T extends Serializable> extends SearchBaseRepository<M, T> {

}

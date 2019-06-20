package io.demo.jony.jony.core.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Base Repository for only JDBC operations.
 * 
 * @author Virtus
 */
public abstract class BaseJdbcRepository extends JdbcDaoSupport {

	/**
	 * Sets the data source.
	 * 
	 * @param dataSource
	 * 		Data Source.
	 */
	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
}

package com.example.daoImpl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.RoleDAO;
import com.example.entity.Role;


@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO{
	
	private static Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);
	
	@Autowired
	private EntityManager entityManager;
	
	

	@Override
	public List<String> getRoleNames(Integer user_id) {
		logger.info("getRoleNames id: " + user_id);
		
		try {
			String sql = "SELECT ar.role_name FROM " + Role.class.getName() + " ar " //  
					+ " WHERE ar.role_id IN " 
					+ " ( "
					+ " Select ur.role_id from " + Role.class.getName() + " ur " //
					+ " where ur.user_id = :userId "
					+ " ) ";

			Query query = this.entityManager.createQuery(sql, String.class);
			query.setParameter("userId", user_id);
			return query.getResultList();
		} catch (Exception e) {
			logger.error("Query get roles name failed !");
			return null;
		}
		
	}
}

package com.example.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDAO;
import com.example.entity.User;
import com.example.utils.DataContants;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{
	
	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User findUserByUsername(String username) {
		logger.info("Start findUserByUsername >>");
		logger.info("Table name: "+User.class.getName());
		try {
			String sql = " select * from app_user where user_name = :userName ";
			
			Query query = entityManager.createNativeQuery(sql, User.class);
			query.setParameter("userName", username);
			
			return (User) query.getSingleResult();
		} catch (Exception e) {
			logger.error("Query findUserByUsername error: ", e);
			return null;
		}
		
	}

	@Override
	public User findUserById(int id) {
		try {
			String sql = " from " + User.class.getName() + " where user_id = :userId";
			
			Query query = entityManager.createNativeQuery(sql, User.class);
			query.setParameter("userId", id);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			logger.error("Query findUserByUsername error: ", e);
			return null;
		}
	}

	@Override
	public List<User> getListUser(Integer page, Integer pageSize) {
		int fromRow = (page - 1) * pageSize;
		
		logger.info("page: " + page + " ,pageSize: " + pageSize );
		List<User> listUser = new ArrayList<User>();
		try {
			
			String sql = "from " + User.class.getName() + " a where a.status = 1 ORDER BY a.created_date ASC ";
			TypedQuery<User> query = this.entityManager.createQuery(sql, User.class);
			
			query.setFirstResult(fromRow);
			query.setMaxResults(pageSize);
			
			listUser = query.getResultList();
			
			
		} catch (Exception e) {
			logger.error("Query findUserByUsername error: ", e);
			return listUser =  null;
		}
		
		return listUser;
	}

	@Override
	public int save(User user) {
		logger.info("Call to save: =====>>>>>>");
		int result = -1;
		try {
			String sql = " INSERT INTO user (user_name,password,status,created_date,updated_date, full_name,mobile_phone,email,address) "
					+ " values (:user_name, :password, :status, NOW(), NOW(), :full_name, :mobile_phone, :email, :address) ";

			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("user_name", user.getUser_name());
			query.setParameter("password", user.getPassword());
			query.setParameter("status", user.getStatus());
			query.setParameter("full_name", user.getFull_name());
			query.setParameter("mobile_phone", user.getMobile_phone());
			query.setParameter("email", user.getEmail());
			query.setParameter("address", user.getAddress());
			result = query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Query findUserByUsername error: ", e);
			
		}
		
		return result;
	}

	@Override
	public int delete(int id) {
		logger.info("Call to delete: ");
		int result = -1;
		try {
			String sql = "DELETE FROM user WHERE user_id = :user_id";
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("user_id", id);

			result = query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR when delete app usser", e);
		}
		return result;
	}

	@Override
	public int updatePassword(User user) {
		logger.info("Call to updatePassword: ");
		int result = DataContants.ERROR;
		try {
			String sql = "update user set password = :password where user_id = :user_id ";
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("password", user.getPassword());
			query.setParameter("user_id", user.getUser_id());
			query.executeUpdate();
			result = DataContants.SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("EROR when update Password :  ", e);
		}
		return result;
	}

	@Override
	public List<User> getListUser() {
		logger.info("call to getListUser non parameter: ");
		List<User> listUser = new ArrayList<User>();
		try {
			
			String sql = "from " + User.class.getName() + " a where a.status = 1 ORDER BY a.created_date ASC ";
			TypedQuery<User> query = this.entityManager.createQuery(sql, User.class);
			listUser = query.getResultList();
			
			
		} catch (Exception e) {
			logger.error("Query findUserByUsername error: ", e);
			return listUser =  null;
		}
		
		return listUser;
	}

	@Override
	public List<String> getAllPermission(Integer user_id) {
		logger.info("Start getAllPermission >>>");
		try {
			String sql = "SELECT action_name FROM app_action_object WHERE allow = 1 AND object_id = :object_id ";
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("object_id", user_id);
			
			List<String> permissions = query.getResultList();
			
			return permissions;
			
		} catch (Exception e) {
			return null;
		}
	}

}

package com.example.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dao.CategoryParentDAO;
import com.example.entity.CategoryParent;
import com.example.utils.DataContants;

@Repository
@Transactional
public class CategoryParentDAOImpl implements CategoryParentDAO {

	private static Logger logger = LoggerFactory.getLogger(CategoryParentDAOImpl.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public int insert(CategoryParent categoryParent) {
		logger.info("insert called >>");
		int result = DataContants.SYSTEM_ERROR;
		String sql = "insert into app_category_parent (category_name,status,created_date,updated_date) "
					+ "values (:category_name, :status,NOW(),NOW())";
		try {
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("category_name", categoryParent.getCategory_name());
			query.setParameter("status", categoryParent.getStatus());
			
			 query.executeUpdate();
			 result = DataContants.SUCCESS;
		} catch (Exception e) {
			logger.error("insert CategoryParent failed >>");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(CategoryParent categoryParent) {
		logger.info("update category start >>");
		int result = DataContants.SYSTEM_ERROR;
		try {
			String sql = "update app_category_parent set category_name =:category_name,status =:status,"
					+ "updated_date = NOW() where category_id = :category_id ";
			
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("category_name", categoryParent.getCategory_name());
			query.setParameter("status", categoryParent.getStatus());
			query.setParameter("category_id", categoryParent.getId());
			
			 query.executeUpdate();
			 result = DataContants.SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(int idCategory) {
		int result = DataContants.SYSTEM_ERROR;
		try {
			String sql = "update app_category_parent set status =:status updated_date = NOW() where category_id = :category_id ";
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("status", DataContants.DELETED_STATUS);
			query.setParameter("category_id", idCategory);
			query.executeUpdate();
			result = DataContants.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CategoryParent> categoryParents() {
		logger.info("Get categoryparent start >> ");
		List<CategoryParent> categoryParents = new ArrayList<>();
		try {
			
			 categoryParents = this.entityManager.createQuery("from "+CategoryParent.class.getName(), CategoryParent.class).getResultList();
			//String sql = " from app_category_parent ";
			//TypedQuery<CategoryParent> query = this.entityManager.createQuery(sql,CategoryParent.class);
			//categoryParents = query.getResultList();
		} catch (Exception e) {
			categoryParents = null;
			logger.error("Get categoryparent failed ",e);
			e.printStackTrace();
		}
		return categoryParents;
	}

	@Override
	public CategoryParent categoryParentById(int idCategory) {
		logger.info("Get categoryparent start >> ");
		CategoryParent categoryParent = new CategoryParent();
		 try {
			String sql = "from app_category_parent where category_id =:category_id";
			TypedQuery<CategoryParent> query = this.entityManager.createQuery(sql,CategoryParent.class);
			query.setParameter("category_id", idCategory);
			
			categoryParent = query.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryParent;
	}

}

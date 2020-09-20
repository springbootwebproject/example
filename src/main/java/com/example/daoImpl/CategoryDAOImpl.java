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

import com.example.dao.CategoryDAO;
import com.example.entity.Category;
import com.example.entity.CategoryParent;
import com.example.utils.DataContants;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public int insert(Category category) {
		logger.info("insert category >>");
		int result = DataContants.SYSTEM_ERROR;
		try {
			String sql = "insert into app_category(category_name,status,created_date,updated_dated,updated_by)"
					+ "values(:category_name,:status,NOW(),NOW(),:updated_by)";
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("category_name", category.getCategory_name());
			query.setParameter("status", category.getStatus());
			query.setParameter("updated_by", category.getUpdated_by());
			
			query.executeUpdate();
			
			result = DataContants.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int update(Category category) {
		
		logger.info("update category >>");
		int result = DataContants.SYSTEM_ERROR;
		try {
			String sql = "update app_category set category_name = :category_name, status = :status, updated_by = :updated_by "
					+ "updated_dated = NOW() where category_id = :category_id ";
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("category_name", category.getCategory_name());
			query.setParameter("status", category.getStatus());
			query.setParameter("category_id",category.getCategory_id());
			query.executeUpdate();

			result = DataContants.SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(int idCategory) {
		logger.info("Delete category >>");
		int result = DataContants.SYSTEM_ERROR; 
	
		result = DataContants.SUCCESS;
		try {
			String sql = "update app_category set status = :status where  category_id = :category_id";
			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter("status", DataContants.DELETED_STATUS);
			query.setParameter("category_id",idCategory);
			query.executeUpdate();

			result = DataContants.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Category> categories() {
		logger.info("Get list category >>");
		
		List<Category> categories = new ArrayList<>();
		
		try {
			String sql = "from "+Category.class.getName()+" where status = :status ";
			TypedQuery<Category> query = this.entityManager.createQuery(sql, Category.class);
			query.setParameter("status", DataContants.PUBLISH_STATUS);
			
			categories = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public Category categoryById(int idCategory) {
		logger.info("Get category By Id >>");
		
		Category category = new Category();
		
		try {
			String sql = "from "+Category.class.getName()+" where status = :status and  category_id = :category_id ";
			TypedQuery<Category> query = this.entityManager.createQuery(sql, Category.class);
			query.setParameter("status", DataContants.PUBLISH_STATUS);
			query.setParameter("category_id", idCategory);
			
			category = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public Category getCategoryByParentId(int parentId) {
		logger.info("get Category By ParentId>>");
		
		Category category = new Category();
		
		try {
			String sql = "from "+Category.class.getName()+" where status = :status and  parent_category_id = :parent_category_id ";
			TypedQuery<Category> query = this.entityManager.createQuery(sql, Category.class);
			query.setParameter("status", DataContants.PUBLISH_STATUS);
			query.setParameter("parent_category_id", parentId);
			
			category = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Category> categories(int parentId) {
		logger.info("Get list category by parent Id  >>");
		
		List<Category> categories = new ArrayList<>();
		
		try {
			String sql = " from "+Category.class.getName()+"as  t1 left join "+ CategoryParent.class.getName()+"as t2"
					+ "on t1.parent_category_id = t2.parent_category_id  where t1.status = :status and t2.parent_category_id = :parent_category_id ";
			TypedQuery<Category> query = this.entityManager.createQuery(sql, Category.class);
			query.setParameter("status", DataContants.PUBLISH_STATUS);
			query.setParameter("parent_category_id", parentId);
			
			categories = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
}

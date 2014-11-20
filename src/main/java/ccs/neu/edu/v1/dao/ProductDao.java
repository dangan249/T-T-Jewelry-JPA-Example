package ccs.neu.edu.v1.dao;

import ccs.neu.edu.v1.model.Product;
import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by andang on 11/18/14.
 */
public class ProductDao {

	private final EntityManager em;

	@Inject
	public ProductDao(EntityManager em) {
		this.em = em;
	}

	public List<Product> getAllProducts() {
		return em.createQuery(
				"SELECT p FROM PRODUCT p").getResultList();
	}
}
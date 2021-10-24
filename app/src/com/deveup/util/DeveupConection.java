package com.deveup.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class DeveupConection<T> {
	
	private Class<T> c;
	private static EntityManager em = null;

	@SuppressWarnings("static-access")
	public DeveupConection() {
		em = this.getEm();
	}

	@SuppressWarnings("static-access")
	public DeveupConection(Class<T> c) {
		em = this.getEm();
		this.c = c;
	}

	public void setC(Class<T> c) {
		this.c = c;
	}

	public static EntityManager getEm() {
		if (em == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeveupVariable.DEVEUP_DATABASE_NAME);  
			em = emf.createEntityManager();
		}
		return em;
	}

	public <E> T find(E id) {
		T object = (T) em.find(c, id);
		return object;
	}

	public List<T> list() {
		TypedQuery<T> consulta = em.createNamedQuery(c.getSimpleName() + ".findAll", c);
		List<T> lista = (List<T>) consulta.getResultList();
		return lista;
	}

	public void insert(T o) {
		try {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 //em.close();
		}
	}

	public void update(T o) {
		try {
			em.getTransaction().begin();
			em.merge(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 //em.close();
		}
	}

	public void delete(T o) {
		try {
			em.getTransaction().begin();
			em.remove(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//em.close();
		}
	}

	public <E> T findByField(String fieldName, E fieldValue) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
		Root<T> root = criteriaQuery.from(c);
		criteriaQuery.select(root);

		@SuppressWarnings("unchecked")
		ParameterExpression<E> params = criteriaBuilder.parameter((Class<E>) Object.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), params));

		TypedQuery<T> query = (TypedQuery<T>) em.createQuery(criteriaQuery);
		query.setParameter(params, (E) fieldValue);

		List<T> queryResult = query.getResultList();

		T returnObject = null;

		if (!queryResult.isEmpty()) {
			returnObject = queryResult.get(0);
		}
		return returnObject;
	}

	public <E> List<T> findByFieldList(String fieldName, E fieldValue) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);
		Root<T> root = criteriaQuery.from(c);
		criteriaQuery.select(root);

		@SuppressWarnings("unchecked")
		ParameterExpression<E> params = criteriaBuilder.parameter((Class<E>) Object.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), params));

		TypedQuery<T> query = (TypedQuery<T>) em.createQuery(criteriaQuery);
		query.setParameter(params, (E) fieldValue);

		List<T> queryResult = query.getResultList();
		return queryResult;
	}
}

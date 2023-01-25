package com.lawencon.ticketing.application.dao.impl.nativequerry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoImpl {
	public EntityManager em;
	
	@PersistenceContext
	public void setEm(final EntityManager em) {
		this.em = em;
	}
	
	
}

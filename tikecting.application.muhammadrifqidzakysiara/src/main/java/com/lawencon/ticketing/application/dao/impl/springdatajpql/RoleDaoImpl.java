package com.lawencon.ticketing.application.dao.impl.springdatajpql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.RoleDao;
import com.lawencon.ticketing.application.model.RoleUsers;
import com.lawencon.ticketing.application.repository.jpql.RoleUsersRepositoryJpql;
@Profile("SpringDataJpql")
@Repository
public class RoleDaoImpl implements RoleDao{
	private final RoleUsersRepositoryJpql roleUsersRepository;
	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	RoleDaoImpl(RoleUsersRepositoryJpql roleUsersRepository){
		this.roleUsersRepository = roleUsersRepository;
	}
	
	@Override
	public RoleUsers insert(final RoleUsers data) {
		return roleUsersRepository.save(data);
	}

	@Override
	public RoleUsers update(final RoleUsers data) {
		return roleUsersRepository.saveAndFlush(data);
	}

	@Override
	public Optional<RoleUsers> getById(final Long id) {
		final RoleUsers roleUsers = roleUsersRepository.findById(id).get();
		em.detach(roleUsers);
		
		return Optional.ofNullable(roleUsers);
	}

	@Override
	public List<RoleUsers> getAll() {
		return roleUsersRepository.findAll();
	}

	@Override
	public boolean deleteById(final Long id) {
		final int result = roleUsersRepository.removeById(id);
		return result > 0;
	}

}

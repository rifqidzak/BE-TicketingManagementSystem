package com.lawencon.ticketing.application.dao.impl.springdatanativequerry;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.model.RoleUsers;
import com.lawencon.ticketing.application.model.Users;
import com.lawencon.ticketing.application.repository.nativequerry.UsersRepository;

@Profile("SpringDataNativeQuerry")
@Repository
public class UsersDaoImpl implements UsersDao {
	private final UsersRepository usersRepository;
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	UsersDaoImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public Users insert(final Users data) {
		return usersRepository.save(data);
	}

	@Override
	public Users update(final Users data) {
		return usersRepository.saveAndFlush(data);
	}

	@Override
	public Optional<Users> getById(final Long id) {
		final Users user = usersRepository.findById(id).get();
		em.detach(user);
		
		return Optional.ofNullable(user);
	}

	@Override
	public List<Users> getAll() {
		return usersRepository.findAll();
	}

	@Override
	public boolean deleteById(final Long id) {
		final int result = usersRepository.removeById(id);
		return result > 0;
	}

	@Override
	public Optional<Users> getUserByEmail(final String email) {
		Optional<Users> userOptional = Optional.ofNullable(null);
		final Object userObj = usersRepository.auth(email);
		if (userObj != null) {
			final Users user = new Users();
			final Object[] objArr = (Object[]) userObj;
			user.setId(Long.valueOf(objArr[0].toString()));
			user.setFullName(objArr[1].toString());
			final Files file = new Files();
			file.setId(Long.valueOf(objArr[4].toString()));
			user.setFilesId(file);
			user.setVer(Integer.valueOf(objArr[5].toString()));
			final RoleUsers roleUsers = new RoleUsers();
			roleUsers.setRoleUserCode(objArr[2].toString());
			user.setRoleUserId(roleUsers);
			user.setPassword(objArr[3].toString());
			userOptional = Optional.ofNullable(user);
		}
		return userOptional;
	}

	@Override
	public List<Users> getUsersByRole(final String roleCode) {
		return usersRepository.getUsersByRole(roleCode);
	}

	@Override
	public List<Users> getPicCustomers(final Long id) {
		return usersRepository.getPicCustomers(id);
	}

}

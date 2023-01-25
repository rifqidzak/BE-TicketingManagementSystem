package com.lawencon.ticketing.application.repository.jpql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Comments;

@Repository
public interface CommentsRepositoryJpql extends JpaRepository<Comments, Long>{
	@Query(value =  "SELECT c FROM Comments c " + "INNER JOIN FETCH c.ticketsId " + "INNER JOIN FETCH c.userId "
			+ "WHERE c.ticketsId.id  = :ticketsId " + "ORDER BY c.createdAt DESC")
	List<Comments> getAll(@Param("ticketsId") Long ticketsId);
}

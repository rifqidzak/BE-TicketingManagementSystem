package com.lawencon.ticketing.application.repository.nativequerry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long>{
	@Query(value = "SELECT * FROM comments as c "
			+ " INNER JOIN tickets t on c.tickets_id = t.id " + " INNER JOIN users u on c.users_id  = u.id "
			+ " WHERE c.tickets_id  = :ticketsId " + " ORDER BY c.created_at DESC", nativeQuery = true)
	List<Comments> getAll(@Param("ticketsId") Long ticketsId);
}

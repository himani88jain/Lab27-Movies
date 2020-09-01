package co.grandcircus.Lab27;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MovieDao extends JpaRepository<Movie, Long> {

	List<Movie> findByTitleContainsIgnoreCase(String titleMatch);
	List<Movie> findByCategoryContainsIgnoreCase(String categoryMatch);

	 
}

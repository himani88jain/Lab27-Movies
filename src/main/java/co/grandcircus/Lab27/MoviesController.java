package co.grandcircus.Lab27;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {
	
	@Autowired
	private MovieDao dao;
	
	@GetMapping("/movies")
	public List<Movie> listMovies(@RequestParam(required=false) String title,
									@RequestParam(required=false) String category){
		if(!(title==null||title.isEmpty()))
			return dao.findByTitleContainsIgnoreCase(title);
		if(!(category==null||category.isEmpty()))
			return dao.findByCategoryContainsIgnoreCase(category);
		return dao.findAll();
	}
	
	@GetMapping("/movies/{id}")
	public Movie findMovieById(@PathVariable("id") Long id) {
		return dao.findById(id).get();
	}
	
	@GetMapping("/random-movie")
	public Movie findMovieByRandomId(@RequestParam(required=false) String category) {
		
		double rand= Math.random();
		if(category==null||category.isEmpty())
		{
		rand=rand*(dao.findAll().size())+1;
		int num=(int)rand;
		Long randomNum=Long.valueOf(num);
				return dao.findById(randomNum).get();
		}
		else {
		List<Movie> movie=dao.findByCategoryContainsIgnoreCase(category);
		     int lengthOfList=movie.size();
		     rand=rand*lengthOfList;
		     int randomNum=(int)rand;
		     return movie.get(randomNum);
		
	}
	}
	
	@GetMapping("/random-movies")
	public List<Movie> getRandomMovies(@RequestParam(required=true) int size){
		List<Movie> movies=new ArrayList<>();
		for(int i=0;i<size;i++) {
		double rand= Math.random();
		rand=rand*(dao.findAll().size())+1;
		int num=(int)rand;
		Long randomNum=Long.valueOf(num);
		Movie movie=dao.findById(randomNum).get();
		movies.add(movie);
	  }
	  return movies;
	  }
		
		
		
		@GetMapping("/category")
		public Set<String> listCategory(){
			List<Movie> movie=dao.findAll();
			String category="";
			Set<String> distinctCategory=new HashSet<>();
			for(int i=0;i<movie.size();i++) {
			category=movie.get(i).getCategory();
			distinctCategory.add(category);
			}
			return distinctCategory;
		}
	
	

}

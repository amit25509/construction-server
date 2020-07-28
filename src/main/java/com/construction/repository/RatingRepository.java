package com.construction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.construction.models.Rating;



@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> 
{
	


}
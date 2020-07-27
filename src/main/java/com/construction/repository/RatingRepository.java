package com.construction.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.construction.models.Address;
import com.construction.models.Rating;



@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> 
{
	


}
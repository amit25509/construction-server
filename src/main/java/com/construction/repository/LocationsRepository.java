package com.construction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.construction.models.Locations;



@Repository
public interface LocationsRepository extends JpaRepository<Locations, Integer> 
{

}
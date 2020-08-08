package com.construction.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.construction.models.Address;



@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> 
{
	@Query(value="select * from address where user=(select id from users where username=:username)",nativeQuery = true)
	List<Address> findAddressByUsername(@Param("username")String username);
	
	@Query(value="select * from address where address_id=(select address_id from users where username=:username)",nativeQuery = true)
	Optional<Address> findByUsername(@Param("username")String username);


}
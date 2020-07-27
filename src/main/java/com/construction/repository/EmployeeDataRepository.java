package com.construction.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.construction.models.EmployeeData;

 

@Repository
@Transactional
public interface EmployeeDataRepository extends JpaRepository<EmployeeData, Integer> {
	@Query(value="select * from employee_data where id=(select employee_data from users where username=:username)",nativeQuery = true)
	List<EmployeeData> findEmployeeDataByUsername(@Param("username")String username);
	
	@Modifying
	@Query(value="update employee_data set rating =:rating where id =:id",nativeQuery = true)
	void updateEmployeeRating(@Param("rating") double rating,@Param("id") Integer id);
	
	@Modifying
	@Query(value="update employee_data set availability=:availability where id=(select employee_data from users where username=:username)",nativeQuery = true)
	void updateEmployeeAvailability(@Param("username") String username, @Param("availability") boolean availability);
}
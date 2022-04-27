package com.example.FetchingData.DataBase;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentRepository extends CrudRepository<ClgStudent, Integer> {

    public List<ClgStudent> findAll (Pageable pageable);

    public List<ClgStudent> findByNameContaining (String name);


}

package com.biolense.lab.ms.service.repository;

import com.biolense.lab.ms.service.model.TestMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestManagementRepo extends CrudRepository<TestMaster, Long>, PagingAndSortingRepository<TestMaster, Long> {

}

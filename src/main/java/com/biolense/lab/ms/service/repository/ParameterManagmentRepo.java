package com.biolense.lab.ms.service.repository;

import com.biolense.lab.ms.service.model.ParameterMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterManagmentRepo extends CrudRepository<ParameterMaster, Long> {
}

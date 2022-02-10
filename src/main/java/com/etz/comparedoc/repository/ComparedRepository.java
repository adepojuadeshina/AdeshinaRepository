package com.etz.comparedoc.repository;

import com.etz.comparedoc.domain.response.CompareResponse;
import com.etz.comparedoc.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComparedRepository extends JpaRepository<CompareResponse, Integer> {
}

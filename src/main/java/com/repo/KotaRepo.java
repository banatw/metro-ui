package com.repo;

import com.entity.Kota;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KotaRepo extends PagingAndSortingRepository<Kota,Integer> {
}

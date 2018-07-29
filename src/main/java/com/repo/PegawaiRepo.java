package com.repo;

import com.entity.Pegawai;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiRepo extends PagingAndSortingRepository<Pegawai,Integer> {
}

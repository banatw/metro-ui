package com.repo;

import com.entity.Pegawai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiRepo extends PagingAndSortingRepository<Pegawai,Integer> {
    Page<Pegawai> findByNamaContaining(String s, Pageable p);
}

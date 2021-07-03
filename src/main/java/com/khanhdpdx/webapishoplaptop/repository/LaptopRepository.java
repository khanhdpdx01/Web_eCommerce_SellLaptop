package com.khanhdpdx.webapishoplaptop.repository;

import com.khanhdpdx.webapishoplaptop.entity.Laptop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    @Query("SELECT l FROM Laptop l WHERE l.name LIKE %?1%")
    Page<Laptop> search(String keyword, Pageable pageable);

    @Query("SELECT l FROM Laptop l WHERE l.category.categoryId = ?1")
    Page<Laptop> getLaptopsByCategoryId(Long categoryId, Pageable pageable);
}

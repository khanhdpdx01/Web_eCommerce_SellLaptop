package com.khanhdpdx.webapishoplaptop.repository;

import com.khanhdpdx.webapishoplaptop.entity.Laptop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    @Query("SELECT l FROM Laptop l WHERE l.name LIKE %?1%")
    Page<Laptop> search(String keyword, Pageable pageable);

    @Query("SELECT l FROM Laptop l WHERE l.category.categoryId = ?1")
    Page<Laptop> getLaptopsByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT l FROM Laptop l WHERE l.slug = :slug")
    Optional<Laptop> findBySlug(@Param("slug") String slug);

    Optional<Laptop> findByLaptopId(Long laptopId);
}

package com.furkan.homey.repository;

import com.furkan.homey.model.entity.Apart;
import com.furkan.homey.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartRepository extends JpaRepository<Apart, Long> {

    List<Apart> findByUserId(Long userId);

    Apart findApartByUuid(String uuid);

    @Query("select a from Apart a where a.user.id =:userId")
    Apart findApartByUserId(Long userId);

}

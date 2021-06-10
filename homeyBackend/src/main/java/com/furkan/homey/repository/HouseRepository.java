package com.furkan.homey.repository;

import com.furkan.homey.model.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {

    House findHouseByApartIdAndHouseNumber(Long apartId, Integer houseNumber);

}

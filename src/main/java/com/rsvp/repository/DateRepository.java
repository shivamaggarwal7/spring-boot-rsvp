package com.rsvp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsvp.entity.RsvpDate;

@Repository
public interface DateRepository extends JpaRepository<RsvpDate, Long> {

	List<RsvpDate> findByCityIdAndActive(Long cityId,String active);
}

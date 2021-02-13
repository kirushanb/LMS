package com.lms.app.com.lms.app.repo;

import com.lms.app.com.lms.app.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface eventRepo extends JpaRepository<Event,Integer> {
}

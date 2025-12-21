package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.OrdemItem;
import com.educandoweb.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrdemItem, Long> {
}

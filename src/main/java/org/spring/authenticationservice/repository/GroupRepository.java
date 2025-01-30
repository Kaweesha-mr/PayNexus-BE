package org.spring.authenticationservice.repository;

import jakarta.persistence.Entity;
import org.spring.authenticationservice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository <Group, Long> {

}

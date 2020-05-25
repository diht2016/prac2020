package ru.sbt.mipt.services.rbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DBRepo extends CrudRepository<DBItem, Long> {
    Optional<DBItem> findByDate(String date);
    Optional<DBItem> findById(String date);
}
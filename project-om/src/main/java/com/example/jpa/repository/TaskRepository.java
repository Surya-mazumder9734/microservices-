package com.example.jpa.repository;

import com.example.jpa.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByProjectId(Long postId, Pageable pageable);
    Optional<Task> findByIdAndProjectId(Long id, Long projectId);

}

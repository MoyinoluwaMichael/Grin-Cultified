package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.models.FarmProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmProjectRepository extends JpaRepository<FarmProject, Long> {

    List<FarmProject> findAllByStatus(FarmProjectStatus status);
}

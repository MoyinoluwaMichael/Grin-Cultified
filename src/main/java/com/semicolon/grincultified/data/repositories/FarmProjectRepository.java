package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.models.FarmProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FarmProjectRepository extends JpaRepository<FarmProject, Long> {

    List<FarmProject> findAllByStatus(FarmProjectStatus status);

    @Query("SELECT f FROM FarmProject f WHERE f.investmentPlan.maturityDate > :currentDate")
    List<FarmProject> findAllNotMaturedProjects(LocalDate currentDate);
}

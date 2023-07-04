package com.semicolon.grincultified.services.farmProjectService;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.repositories.FarmProjectRepository;
import com.semicolon.grincultified.dtos.requests.FarmProjectRequest;
import com.semicolon.grincultified.dtos.responses.FarmProjectResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FarmProjectServiceImplementation implements FarmProjectService {

    private final FarmProjectRepository farmProjectRepository;

    @Override
    public FarmProjectResponse createFarmProject(FarmProjectRequest farmProjectRequest) {
        FarmProject farmProject = new FarmProject();
        farmProjectRepository.save(farmProject);
        return null;
    }
}

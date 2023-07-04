package com.semicolon.grincultified.services.farmProjectService;

import com.semicolon.grincultified.dtos.requests.FarmProjectRequest;
import com.semicolon.grincultified.dtos.responses.FarmProjectResponse;

public interface FarmProjectService {
    FarmProjectResponse createFarmProject(FarmProjectRequest farmProjectRequest);

}

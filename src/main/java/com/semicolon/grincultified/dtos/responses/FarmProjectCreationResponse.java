package com.semicolon.grincultified.dtos.responses;

import com.semicolon.grincultified.data.models.FarmProjectStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FarmProjectCreationResponse {
    private Long id;
    private String farmProduceSummary;
    private String description;
    private Long numberOfInvestors;
    private FarmProjectStatus status;
    private String pictures;
    private LocalDateTime uploadedAt;

    @Override
    public String toString() {
        return "FarmProjectResponse{" +
                "farmProduceSummary='" + farmProduceSummary + '\'' +
                ", description='" + description + '\'' +
                ", numberOfInvestors=" + numberOfInvestors +
                ", status=" + status +
                ", pictures='" + pictures + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
}

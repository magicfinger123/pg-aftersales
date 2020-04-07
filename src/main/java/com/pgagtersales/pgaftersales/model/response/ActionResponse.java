package com.pgagtersales.pgaftersales.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionResponse {
    private String action;
    private String actionStatus;
}

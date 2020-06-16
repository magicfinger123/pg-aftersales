/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.resquest.TeamCreationRequest;
import com.pgagtersales.pgaftersales.shared.dto.SiteInspectionDto;
import com.pgagtersales.pgaftersales.shared.dto.TeamDto;

public interface TeamService {
    ApiResponse createTeam(TeamCreationRequest teamCreationRequest);
    ApiResponse getAllTeams(int page, int size);
    ApiResponse getByUserId(int page, int size);
    ApiResponse getTeamById(String teamId);
    ApiResponse updateTeam(TeamDto teamDto);
    ApiResponse deleteTeam(String teamId);
}

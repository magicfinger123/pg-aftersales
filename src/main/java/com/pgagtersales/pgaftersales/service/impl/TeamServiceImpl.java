/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;


import com.pgagtersales.pgaftersales.controller.NotificationController;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.*;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.model.resquest.TeamCreationRequest;
import com.pgagtersales.pgaftersales.repository.TeamRepository;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.TeamService;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.NotificationRequestDto;
import com.pgagtersales.pgaftersales.shared.dto.TeamDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private Utils utils;

    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();


    @Override
    public ApiResponse createTeam(TeamCreationRequest teamCreationRequest) {
        System.out.println("teamcreation: "+teamCreationRequest);
        ModelMapper modelMapper = new ModelMapper();
        TeamDto teamDto = new TeamDto();
        BeanUtils.copyProperties(teamCreationRequest,teamDto);
        if (teamRepository.findByTeamTitle(teamCreationRequest.getTeamTitle())!=null) {
            throw new UserServiceException("Team Already Exist","Team already exist");
        }
        teamDto.setTeamId(utils.generateId(20));
        System.out.println("teamdto: "+teamDto.getTeamLead());
        TeamEntity teamEntity = new TeamEntity();
        BeanUtils.copyProperties(teamDto,teamEntity);
        System.out.println("teamEntity: "+teamEntity);
        try {
            TeamEntity createTeam = teamRepository.save(teamEntity);
            ApiResponse apiResponse = utils.sucessApiResponse(teamCreationRequest.getTeamTitle()+ "Schedule has been created");
            notificationRequestDto.setTarget(createTeam.getTeamLead());
            notificationRequestDto.setTitle("PEL TEAMS");
            notificationRequestDto.setBody("You have been assigned to head a team, click for more details");
            notificationController.sendPnsToTopic(notificationRequestDto);
            if (createTeam.getTeamMember1().length()>1){
                notificationRequestDto.setTarget(createTeam.getTeamLead());
                notificationRequestDto.setTitle("PEL TEAMS");
                notificationRequestDto.setBody("You have been assigned to head a team, click for more details");
                notificationController.sendPnsToTopic(notificationRequestDto);
            }
            if (createTeam.getTeamMember2().length()>1){
                notificationRequestDto.setTarget(createTeam.getTeamLead());
                notificationRequestDto.setTitle("PEL TEAMS");
                notificationRequestDto.setBody("You have been assigned to a team \nYour team lead is "+createTeam.getTeamLead());
                notificationController.sendPnsToTopic(notificationRequestDto);
            }
            if (createTeam.getTeamMember3().length()>1){
                notificationRequestDto.setTarget(createTeam.getTeamLead());
                notificationRequestDto.setTitle("PEL TEAMS");
                notificationRequestDto.setBody("You have been assigned to a team \nYour team lead is "+createTeam.getTeamLead());
                notificationController.sendPnsToTopic(notificationRequestDto);
            }
            if (createTeam.getTeamMember4().length()>1){
                notificationRequestDto.setTarget(createTeam.getTeamLead());
                notificationRequestDto.setTitle("PEL TEAMS");
                notificationRequestDto.setBody("You have been assigned to a team \nYour team lead is "+createTeam.getTeamLead());
                notificationController.sendPnsToTopic(notificationRequestDto);
            }
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }
    @Override
    public ApiResponse getAllTeams(int page, int size) {
        List<TeamDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page-1, size);
            Page<TeamEntity> teamPage = teamRepository.findAll(pageable);
            List<TeamEntity> teamList = teamPage.getContent();
            for (TeamEntity team : teamList) {
                TeamDto teamDto = new TeamDto();
                BeanUtils.copyProperties(team, teamDto);
                try {
                    String member1 = userRepository.findByUserId(team.getTeamMember1()).getUsername();
                    teamDto.setTeamMember1(member1);
                }
                catch (Exception e){

                }
                try {
                    String member2 = userRepository.findByUserId(team.getTeamMember2()).getUsername();
                    teamDto.setTeamMember2(member2);
                }
                catch (Exception e){

                }
                try {
                    String member3 = userRepository.findByUserId(team.getTeamMember3()).getUsername();
                    teamDto.setTeamMember3(member3);
                }
                catch (Exception e){

                }
                try {
                    String member4 = userRepository.findByUserId(team.getTeamMember4()).getUsername();
                    teamDto.setTeamMember4(member4);
                }
                catch (Exception e){

                }
                try {
                    String tL = userRepository.findByUserId(team.getTeamLead()).getUsername();
                    teamDto.setTeamLead(tL);
                }
                catch (Exception e){

                }
                returnedValue.add(teamDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse getByUserId(int page, int size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getPrincipal().toString();
        System.out.println(username);
        String userId = userRepository.findByUsername(username).getUserId();

        List<TeamDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page-1, size);
            Page<TeamEntity> teamPage = teamRepository.findByUserId(userId,pageable);
            List<TeamEntity> teamList = teamPage.getContent();
            for (TeamEntity team : teamList) {
                TeamDto teamDto = new TeamDto();
                BeanUtils.copyProperties(team, teamDto);
                try {
                    String member1 = userRepository.findByUserId(team.getTeamMember1()).getUsername();
                    teamDto.setTeamMember1(member1);
                }
                catch (Exception e){

                }
                try {
                    String member2 = userRepository.findByUserId(team.getTeamMember2()).getUsername();
                    teamDto.setTeamMember2(member2);
                }
                catch (Exception e){

                }
                try {
                    String member3 = userRepository.findByUserId(team.getTeamMember3()).getUsername();
                    teamDto.setTeamMember3(member3);
                }
                catch (Exception e){

                }
                try {
                    String member4 = userRepository.findByUserId(team.getTeamMember4()).getUsername();
                    teamDto.setTeamMember4(member4);
                }
                catch (Exception e){

                }
                try {
                    String tL = userRepository.findByUserId(team.getTeamLead()).getUsername();
                    teamDto.setTeamLead(tL);
                }
                catch (Exception e){

                }
                returnedValue.add(teamDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse getTeamById(String teamId) {
        TeamDto returnValue = new TeamDto();
        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) {
            throw new UserServiceException("something went wrong","generator id not found");
        } else {
            BeanUtils.copyProperties(teamEntity, returnValue);
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse updateTeam(TeamDto teamDto) {
        return null;
    }

    @Override
    public ApiResponse deleteTeam(String teamId) {
        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) {
            throw new UserServiceException("something went wrong","team id not found");
        } else {
            teamRepository.delete(teamEntity);
            return utils.sucessApiResponse("Team "+teamEntity.getTeamTitle()+" has been deleted");
        }
    }
}

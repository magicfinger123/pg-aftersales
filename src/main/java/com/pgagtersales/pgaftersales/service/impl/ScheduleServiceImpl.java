/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.InventoryEntity;
import com.pgagtersales.pgaftersales.io.entity.ScheduleEntity;
import com.pgagtersales.pgaftersales.io.entity.ScheduleTaskEntity;
import com.pgagtersales.pgaftersales.io.entity.TeamEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.model.resquest.ScheduleCreationDto;
import com.pgagtersales.pgaftersales.model.resquest.ScheduleTaskCreationDto;
import com.pgagtersales.pgaftersales.repository.InventoryRepository;
import com.pgagtersales.pgaftersales.repository.ScheduleRepository;
import com.pgagtersales.pgaftersales.repository.ScheduleTasKRepository;
import com.pgagtersales.pgaftersales.repository.TeamRepository;
import com.pgagtersales.pgaftersales.service.ScheduleService;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ScheduleTasKRepository scheduleTasKRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    private Utils utils;
    @Override
    public ApiResponse createSchedule(ScheduleCreationDto scheduleCreationDto) {
        if (scheduleRepository.findByScheduleName(scheduleCreationDto.getScheduleName())!=null) {
            throw new UserServiceException("This Schedule Already Exist","Schedule already exist");
        }
        ScheduleDto dto = new ScheduleDto();
        BeanUtils.copyProperties(scheduleCreationDto,dto);
        //ScheduleDto dto = modelMapper.map(scheduleCreationDto, ScheduleDto.class);
        dto.setScheduleId(utils.generateId(20));
        ScheduleEntity entity = new ScheduleEntity();
        BeanUtils.copyProperties(dto,entity);
        System.out.println("scheduleEntity: "+entity);
        try {
            ScheduleEntity createTeam = scheduleRepository.save(entity);
            ApiResponse apiResponse = utils.sucessApiResponse("Schedule created successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse getAllSchedule(int page, int size) {
        List<ScheduleDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ScheduleEntity> scheduleEntityPage = scheduleRepository.findAll(pageable);
            List<ScheduleEntity> scheduleEntities = scheduleEntityPage.getContent();
            for (ScheduleEntity scheduleEntity : scheduleEntities) {
                ScheduleDto scheduleDto = new ScheduleDto();
                BeanUtils.copyProperties(scheduleEntity, scheduleDto);
                returnedValue.add(scheduleDto);
            }
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to get schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse getScheduleByTeamId(String teamId) {
        ScheduleByTeamId returnValue = new ScheduleByTeamId();
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        List<InventoryDto> inventoryDtos = new ArrayList<>();

        TeamDto teamDto = new TeamDto();
        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null){
            throw new UserServiceException("something went wrong","team id not found");
        }
        BeanUtils.copyProperties(teamEntity,teamDto);
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<ScheduleEntity> entity = scheduleRepository.findByTeamId(teamId, pageable);
        List<ScheduleEntity> entities;
        if (entity != null ){
            entities  = entity.getContent();
            for (ScheduleEntity ent :entities) {
                ScheduleDto scheduleDto = new ScheduleDto();
                BeanUtils.copyProperties(ent, scheduleDto);
                scheduleDtos.add(scheduleDto);
            }
        }
        Page<InventoryEntity> inventoryEntityPage = inventoryRepository.findByTeamId(teamId, pageable);
        List<InventoryEntity> inventoryEntities = new ArrayList<>();
        if (inventoryEntityPage != null ){
            inventoryEntities  = inventoryEntityPage.getContent();
            for (InventoryEntity ent :inventoryEntities) {
                InventoryDto inventoryDto = new InventoryDto();
                BeanUtils.copyProperties(ent, inventoryDto);
                inventoryDtos.add(inventoryDto);
            }
        }
            //ScheduleTaskEntity m = scheduleTasKRepository.findByScheduleId(entity.getScheduleId());
            returnValue.setTeamDto(teamDto);
            returnValue.setScheduleDtos(scheduleDtos);
            returnValue.setInventoryDtos(inventoryDtos);
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;

    }

    @Override
    public ApiResponse getScheduleByScheduleId(String scheduleId) {
        ScheduleDto returnValue = new ScheduleDto();
        ScheduleEntity entity = scheduleRepository.findByScheduleId(scheduleId);
        if (entity == null) {
            throw new UserServiceException("something went wrong","schedule id not found");
        } else {
            BeanUtils.copyProperties(entity, returnValue);
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse updateSchedule(String scheduleId) {
        return null;
    }

    @Override
    public ApiResponse deleteSchedule(String scheduleId) {
        return null;
    }

    @Override
    public ApiResponse addTask(ScheduleTaskCreationDto scheduleTaskCreationDto) {
        if (scheduleRepository.findByScheduleId(scheduleTaskCreationDto.getScheduleId())==null) {
            throw new UserServiceException("Schedule does not exist","Schedule does not exist");
        }
        if (scheduleTasKRepository.findByTaskName(scheduleTaskCreationDto.getTaskName())!=null) {
            throw new UserServiceException("A name with this task already exist ","A name with this task already exist");
        }
        ScheduleTaskDto dto = new ScheduleTaskDto();
        BeanUtils.copyProperties(scheduleTaskCreationDto,dto);
        //ScheduleDto dto = modelMapper.map(scheduleCreationDto, ScheduleDto.class);
        dto.setTaskId(utils.generateId(20));
        ScheduleTaskEntity entity = new ScheduleTaskEntity();
        BeanUtils.copyProperties(dto,entity);
        System.out.println("scheduleTaskENtity: "+entity);
        try {
            ScheduleTaskEntity createTeam = scheduleTasKRepository.save(entity);
            ApiResponse apiResponse = utils.sucessApiResponse("Task Added Successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create task","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse deleteTask(String taskId) {
        ScheduleTaskEntity getTaskEntity = scheduleTasKRepository.findByTaskId(taskId);
        if (getTaskEntity==null) {
            throw new UserServiceException("Task with id "+taskId+ " does not exist","Task with id "+taskId+ " does not exist");
        }

        //ScheduleDto dto = modelMapper.map(scheduleCreationDto, ScheduleDto.class);
        System.out.println("scheduleTaskENtity: "+getTaskEntity);
        try {
            scheduleTasKRepository.delete(getTaskEntity);
            ApiResponse apiResponse = utils.sucessApiResponse("Task deleted successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to update task","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse updateTask(ScheduleTaskDto scheduleTaskDto) {
        ScheduleTaskEntity getTaskEntity = scheduleTasKRepository.findByTaskId(scheduleTaskDto.getTaskId());
        if (getTaskEntity==null) {
            throw new UserServiceException("Task with id "+scheduleTaskDto.getTaskId()+ " does not exist","Task with id "+scheduleTaskDto.getTaskId()+ " does not exist");
        }
        if (scheduleRepository.findByScheduleId(scheduleTaskDto.getScheduleId())==null) {
            throw new UserServiceException("Schedule does not exist","Schedule does not exist");
        }
        BeanUtils.copyProperties(scheduleTaskDto,getTaskEntity);
        //ScheduleDto dto = modelMapper.map(scheduleCreationDto, ScheduleDto.class);
        System.out.println("scheduleTaskENtity: "+getTaskEntity);
        try {
            ScheduleTaskEntity createTeam = scheduleTasKRepository.save(getTaskEntity);
            ApiResponse apiResponse = utils.sucessApiResponse("Task Updated Successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to update task","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse getTaskByScheduleId(String scheduleId) {
        List<ScheduleTaskDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<ScheduleTaskEntity> pageEntities = scheduleTasKRepository.findByScheduleId(scheduleId, pageable);

        if (pageEntities == null) {
            throw new UserServiceException("something went wrong","schedule id not found");
        } else {
            List<ScheduleTaskEntity> scheduleTaskEntities = pageEntities.getContent();
            for (ScheduleTaskEntity ent:scheduleTaskEntities) {
                ScheduleTaskDto scheduleTaskDto = new ScheduleTaskDto();
                BeanUtils.copyProperties(ent,scheduleTaskDto);
                returnValue.add(scheduleTaskDto);
            }
            ApiResponse apiResponse = responseBuilder.successfullResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnValue);
            return apiResponse;
        }
    }
}

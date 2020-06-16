/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

    package com.pgagtersales.pgaftersales.service;

    import com.pgagtersales.pgaftersales.model.response.ApiResponse;
    import com.pgagtersales.pgaftersales.model.resquest.ScheduleCreationDto;
    import com.pgagtersales.pgaftersales.model.resquest.ScheduleTaskCreationDto;
    import com.pgagtersales.pgaftersales.model.resquest.TeamCreationRequest;
    import com.pgagtersales.pgaftersales.shared.dto.ScheduleTaskDto;
    import com.pgagtersales.pgaftersales.shared.dto.TeamDto;

    public interface ScheduleService {
        ApiResponse createSchedule(ScheduleCreationDto scheduleCreationDto);
        ApiResponse getAllSchedule(int page, int size);
        ApiResponse getScheduleByTeamId(String teamId);
        ApiResponse getScheduleByScheduleId(String scheduleId);
        ApiResponse updateSchedule(String scheduleId);
        ApiResponse deleteSchedule(String scheduleId);
        ApiResponse addTask(ScheduleTaskCreationDto scheduleTaskCreationDto);
        ApiResponse deleteTask(String taskId);
        ApiResponse updateTask(ScheduleTaskDto scheduleTaskDto);
        ApiResponse getTaskByScheduleId(String scheduleId);
    }

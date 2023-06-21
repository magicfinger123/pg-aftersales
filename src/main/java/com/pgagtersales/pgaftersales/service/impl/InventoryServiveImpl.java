/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.controller.NotificationController;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.InventoryEntity;
import com.pgagtersales.pgaftersales.io.entity.InventoryItemEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.model.resquest.InventoryCreationDto;
import com.pgagtersales.pgaftersales.repository.*;
import com.pgagtersales.pgaftersales.service.InventoryService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.*;
import lombok.SneakyThrows;
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
public class InventoryServiveImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private Utils utils;

    @Autowired
    SendMail sendMail;

    @Autowired
    NotificationMessages message;

    @Autowired
    private UserRepository userRepository;

    String[] recipent = {"powergenltd@gmail.com"};


    @Autowired
    private NotificationController notificationController;

    NotificationRequestDto notificationRequestDto = new NotificationRequestDto();

    @Override
    public ApiResponse createInventory(InventoryCreationDto inventoryCreationDto) {
        String ccRecipent = utils.getEmails("default").getEmailAddress();
        if (inventoryRepository.findByInventoryName(inventoryCreationDto.getInventoryName())!=null) {
            throw new UserServiceException("Inventory name already exist, use a different name","inventory name already exist");
        }
        if (teamRepository.findByTeamId(inventoryCreationDto.getTeamId())==null) {
            throw new UserServiceException("No team Selected","Innvalid team Id");
        }
        InventoryDto dto = new InventoryDto();
        BeanUtils.copyProperties(inventoryCreationDto,dto);
        //ScheduleDto dto = modelMapper.map(scheduleCreationDto, ScheduleDto.class);
        dto.setInventoryId(utils.generateId(20));
        InventoryEntity entity = new InventoryEntity();
        BeanUtils.copyProperties(dto,entity);
        System.out.println("inventory Entity: "+entity);
        InventoryEntity createInventory = inventoryRepository.save(entity);
        try {
            sendMail.sendEmailWithAttachment(message.newInventory(inventoryCreationDto), ccRecipent, AppConstants.AFTERSALES_RECIPENTS, "New Inventory Manager Created");
        } catch (Exception e){

        }
            ApiResponse apiResponse = utils.sucessApiResponse("Inventory created successfully");
        try{
            notificationRequestDto.setTarget("admin");
            notificationRequestDto.setTitle("PEL");
            notificationRequestDto.setBody("New Inventory Created by "+message.getUserDetails().getFirst_name()+" for "+inventoryCreationDto.getInventoryManager());
            notificationController.sendPnsToTopic(notificationRequestDto);
        } catch (Exception e) {

        }
          return apiResponse;
    }

    @Override
    public ApiResponse getAllInventory(int page, int size) {
        List<InventoryDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            Page<InventoryEntity> inventoryEntityPage = inventoryRepository.findAll(pageable);
            List<InventoryEntity> inventoryEntities = inventoryEntityPage.getContent();
            for (InventoryEntity inventoryEntity : inventoryEntities) {
                InventoryDto inventoryDto = new InventoryDto();
                BeanUtils.copyProperties(inventoryEntities, inventoryDto);
                returnedValue.add(inventoryDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to get schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse getInventoryByTeamId(String teamId) {
        List<InventoryDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            Page<InventoryEntity> inventoryEntityPage = inventoryRepository.findByTeamId(teamId,pageable);
            List<InventoryEntity> inventoryEntities = inventoryEntityPage.getContent();
            for (InventoryEntity inventoryEntity : inventoryEntities) {
                InventoryDto inventoryDto = new InventoryDto();
                BeanUtils.copyProperties(inventoryEntities, inventoryDto);
                returnedValue.add(inventoryDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to get schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }
    @Override
    public ApiResponse getInventoryByScheduleId(String scheduleId) {
        return getApiResponse(scheduleId);
    }

    private ApiResponse getApiResponse(String scheduleId) {
        List<InventoryDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            Page<InventoryEntity> inventoryEntityPage = inventoryRepository.findByTeamId(scheduleId, pageable);
            List<InventoryEntity> inventoryEntities = inventoryEntityPage.getContent();
            for (InventoryEntity inventoryEntity : inventoryEntities) {
                InventoryDto inventoryDto = new InventoryDto();
                BeanUtils.copyProperties(inventoryEntities, inventoryDto);
                returnedValue.add(inventoryDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to get schedule", "something went wrong: " + e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse updateInventory(InventoryDto inventoryDto) {
        if (inventoryRepository.findByInventoryName(inventoryDto.getInventoryId())==null) {
            throw new UserServiceException("Inventory does not exist","Inventory does not exist");
        }
        if (teamRepository.findByTeamId(inventoryDto.getTeamId())==null) {
            throw new UserServiceException("No team Selected","Invalid team Id");
        }
        //ScheduleDto dto = modelMapper.map(scheduleCreationDto, ScheduleDto.class);
        InventoryEntity entity = new InventoryEntity();
        BeanUtils.copyProperties(inventoryDto,entity);
        System.out.println("inventory Entity: "+entity);
        try {
            InventoryEntity createInventory = inventoryRepository.save(entity);
            ApiResponse apiResponse = utils.sucessApiResponse("Inventory updated successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to update Inventory","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse addItemToInventory(InventoryItemDto inventoryItemDto) {
        String ccRecipent = utils.getEmails("default").getEmailAddress();
        InventoryEntity inv = inventoryRepository.findByInventoryId(inventoryItemDto.getInventoryId());
        if (inv==null) {
            throw new UserServiceException("No inventory selected","No inventory selected");
        }
        inventoryItemDto.setInventoryManager(inv.getInventoryManager());
        inventoryItemDto.setInventoryItemId(utils.generateId(20));
        InventoryItemEntity entity = new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItemDto,entity);
        System.out.println("inventory Entity: "+entity);
        try {
            InventoryItemEntity createInventoryItem = inventoryItemRepository.save(entity);
            sendMail.sendEmailWithAttachment(message.addInventoryItem(inventoryItemDto), ccRecipent, AppConstants.AFTERSALES_RECIPENTS, "Item Added to Inventory mannaged by "+inv.getInventoryManager());
            ApiResponse apiResponse = utils.sucessApiResponse("Inventory item created successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create Inventory item","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse upDateInventoryItem(InventoryItemDto inventoryItemDto) {
        if (inventoryRepository.findByInventoryId(inventoryItemDto.getInventoryId())==null) {
            throw new UserServiceException("No inventory Selected","Invalid inventory Id");
        }
        if (inventoryItemRepository.findByInventoryItemId(inventoryItemDto.getInventoryItemId())==null) {
            throw new UserServiceException("Item does not exist","Invalid inventory item Id");
        }
        InventoryItemEntity entity = inventoryItemRepository.findByInventoryItemId(inventoryItemDto.getInventoryItemId());
        //InventoryItemEntity entity = new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItemDto,entity);
        System.out.println("inventory Entity: "+entity);
        try {
            InventoryItemEntity createInventoryItem = inventoryItemRepository.save(entity);
            ApiResponse apiResponse = utils.sucessApiResponse(inventoryItemDto.getItemName()+"  has been updated");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable update item","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse deleteInventoryItem(String inventoryItemId) {
        InventoryItemEntity getItemEntity = inventoryItemRepository.findByInventoryItemId(inventoryItemId);
        if (getItemEntity==null) {
            throw new UserServiceException("item with id "+inventoryItemId+ " does not exist","item with id "+inventoryItemId+ " does not exist");
        }
        System.out.println("itemEntity to delete: "+getItemEntity);
        try {
            inventoryItemRepository.delete(getItemEntity);
            ApiResponse apiResponse = utils.sucessApiResponse("item deleted successfully");
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to delete item","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @SneakyThrows
    @Override
    public ApiResponse getItemsByInventoryIds(UpdateInventoryItemDto inventoryId) {
        String ccRecipent = utils.getEmails("default").getEmailAddress();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getPrincipal().toString();
        System.out.println(username);
        UserEntity userDto = userRepository.findByUsername(username);
        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(userDto,userDto1);

        List<InventoryItemDto> returnedValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        List<InventoryNotification> inventoryNotifications = new ArrayList<>();
        Page<InventoryItemEntity> entityPage = inventoryItemRepository.findByInventoryId(inventoryId.getInventoryId(),pageable);
        List<InventoryItemEntity> inventoryEntities = entityPage.getContent();
        for (InventoryItemEntity inventoryEntity : inventoryEntities) {
            InventoryItemDto inventoryDto = new InventoryItemDto();
            BeanUtils.copyProperties(inventoryEntity, inventoryDto);
            returnedValue.add(inventoryDto);
        }
        returnedValue.retainAll(inventoryId.getInventoryItemDtos());
        List<InventoryItemDto> inventoryItemDtos = inventoryId.getInventoryItemDtos();
        inventoryItemDtos.removeAll(returnedValue);
        for (InventoryItemDto dto: inventoryItemDtos) {

            InventoryItemEntity ent = inventoryItemRepository.findByInventoryItemId(dto.getInventoryItemId());
            int previousQty = ent.getItemQty();
            if (ent != null){
                BeanUtils.copyProperties(dto,ent);
                InventoryItemEntity nn = inventoryItemRepository.save(ent);
                int newQty = nn.getItemQty();
                String updatedQty = "";
                if (previousQty > newQty) {
                    int used = previousQty - newQty;
                    updatedQty = "Used up "+used+" "+nn.getItemUnits()+" "+nn.getItemName();
                }else{
                    int added = newQty - previousQty;
                    updatedQty = "Added "+added+" "+nn.getItemUnits()+" "+nn.getItemName();
                }
                System.out.println("updated qty "+updatedQty);
                InventoryNotification notificationItem = new InventoryNotification(nn.getItemName(),previousQty,newQty,
                        updatedQty);
                inventoryNotifications.add(notificationItem);
            }else
            {
                System.out.println("unable to save"+dto.getInventoryItemId());
            }
        }

        sendMail.sendEmailWithAttachment(message.inventoryNotification(inventoryNotifications, userDto1), ccRecipent,
                AppConstants.AFTERSALES_RECIPENTS, userDto1.getUsername() + " Inventory Update");
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Update Successful").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);

        return apiResponse;
    }

    @Override
    public ApiResponse getItemsByInventoryId(String inventoryId) {
        List<InventoryItemDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            Page<InventoryItemEntity> entityPage = inventoryItemRepository.findByInventoryId(inventoryId,pageable);
            List<InventoryItemEntity> inventoryEntities = entityPage.getContent();
            for (InventoryItemEntity inventoryEntity : inventoryEntities) {
                InventoryItemDto inventoryDto = new InventoryItemDto();
                BeanUtils.copyProperties(inventoryEntity, inventoryDto);
                returnedValue.add(inventoryDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to get schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }
}

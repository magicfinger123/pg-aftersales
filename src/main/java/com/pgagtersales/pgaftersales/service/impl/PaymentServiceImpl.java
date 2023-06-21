/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.*;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.GeneratorRepository;
import com.pgagtersales.pgaftersales.repository.OutstandingRepository;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.service.PaymentService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.PaymentAdviseDto;
import com.pgagtersales.pgaftersales.shared.dto.PaymentNotificationBuilderDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaPriceListDto;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ResponseBuilder responseBuilder;
    @Autowired
    private SendMail sendMail;
    @Autowired
    OutstandingRepository outstandingRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    GeneratorRepository generatorRepository;
    @Autowired
    NotificationMessages message;
    String[] recipent = {"powergenltd@gmail.com"};
    @Autowired
    private Utils utils;

    @Autowired
    private ReportLogRepo reportLogRepo;
    @SneakyThrows
    @Override
    public ApiResponse makePayment(PaymentAdviseDto paymentAdviseDto) {
        int amountPaid= Integer.parseInt(paymentAdviseDto.getAmountPaid());
        int amountInvoiced= Integer.parseInt(paymentAdviseDto.getAmountInvoiced());
        PaymentNotificationBuilderDto returnValue = new PaymentNotificationBuilderDto();
        returnValue.setAmountPaid(amountPaid);
        returnValue.setBalance(amountInvoiced-amountPaid);
        returnValue.setPaymentType(paymentAdviseDto.getPaymentType());
        ClientsEntity client = clientRepository.findById(Integer.parseInt(paymentAdviseDto.getClientId()));

        if (client == null){
            throw new UserServiceException("client does not exist in the database","client does not exist in the database");
        }
        String new_recipent = client.getEmail();
        returnValue.setClientName(client.getFirst_name());
        if (client.getOutstandingDtos()!=null) {
            int totalOut = 0;

            for (OutstandingEntity out: client.getOutstandingDtos()) {
              int x =   Integer.parseInt(out.getInvoicedAmount()) - Integer.parseInt(out.getAmountPaid());
              totalOut = totalOut+x;
            }
            returnValue.setCurrentOutstandingOwed(totalOut);
        }
        if (paymentAdviseDto.getGeneratorMaintenance()){
           returnValue.setSlaType(paymentAdviseDto.getSlaType());
            GeneratorEntity gen = generatorRepository.findById(Integer.parseInt(paymentAdviseDto.getGenId()));
            returnValue.setGenSize(gen.getSize());
            returnValue.setSlaStartDate(paymentAdviseDto.getSlaStartDate());
            returnValue.setServicesAlreadyDone(paymentAdviseDto.getServicesAlreadyDone());
            returnValue.setPaymentDescription(paymentAdviseDto.getPaymentItem());
        }else{
            returnValue.setSlaType(null);
            returnValue.setPaymentDescription(paymentAdviseDto.getPaymentDescription());
        }
        sendMail.sendEmailWithAttachment(message.paymentAdviseNotification(returnValue),new_recipent, AppConstants.FINANCE_AND_AFTERSALES, "PAYMENT ADVICE");
        try {
            ReportLogDto reportLogDto = new ReportLogDto();
            reportLogDto.setUserId(message.getUserDetails().getUserId());
            reportLogDto.setDescription("Sent payment advice to "+client.getCompany()+" of payment of "+returnValue.getAmountPaid()+" for "+returnValue.getPaymentDescription());
            reportLogDto.setAction("SLA renewal notice");
            reportLogDto.setStatus("completed");
            reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
            reportLogDto.setTime(utils.getTime());
            ReportLogEntity ent = new ReportLogEntity();
            BeanUtils.copyProperties(reportLogDto,ent);
            reportLogRepo.save(ent);
        } catch (BeansException e) {
            System.out.println("reportlog error: "+e.getLocalizedMessage());
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Payment notification sent successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }

    @Override
    public ApiResponse getPaymentsByClients(String clientId) {
        return null;
    }
}

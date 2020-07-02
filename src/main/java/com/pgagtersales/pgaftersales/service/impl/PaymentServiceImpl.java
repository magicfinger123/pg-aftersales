/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SendMail;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ClientsEntity;
import com.pgagtersales.pgaftersales.io.entity.GeneratorEntity;
import com.pgagtersales.pgaftersales.io.entity.OutstandingEntity;
import com.pgagtersales.pgaftersales.io.entity.SlaPriceListEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ClientRepository;
import com.pgagtersales.pgaftersales.repository.GeneratorRepository;
import com.pgagtersales.pgaftersales.repository.OutstandingRepository;
import com.pgagtersales.pgaftersales.service.PaymentService;
import com.pgagtersales.pgaftersales.shared.dto.PaymentAdviseDto;
import com.pgagtersales.pgaftersales.shared.dto.PaymentNotificationBuilderDto;
import com.pgagtersales.pgaftersales.shared.dto.SlaPriceListDto;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
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
    String[] recipent = {"ossaimike8@gmail.com","exc.easey@gmail.com"};
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
        returnValue.setClientName(client.getFirst_name());
        if (client.getOutstandingDtos()!=null) {
            int totalOut = 0;

            for (OutstandingEntity out: client.getOutstandingDtos()) {
              int x =   Integer.parseInt(out.getInvoicedAmount()) - Integer.parseInt(out.getAmountPaid());
              totalOut = totalOut+x;
            }
            returnValue.setCurrentOutstandingOwed(totalOut);
            //returnValue.setOutStandingDescription(outstanding.getDescription());
           // OutstandingEntity outstanding = outstandingRepository.findByClientId(paymentAdviseDto.getClientId());
           /* if (outstanding != null) {
                returnValue.setCurrentOutstandingOwed(Integer.parseInt(outstanding.getInvoicedAmount()) - Integer.parseInt(outstanding.getAmountPaid()));
                returnValue.setOutStandingDescription(outstanding.getDescription());
            }*/
        }
        if (paymentAdviseDto.getGeneratorMaintenance()){
           returnValue.setSlaType(paymentAdviseDto.getSlaType());
            GeneratorEntity gen = generatorRepository.findById(Integer.parseInt(paymentAdviseDto.getGenId()));
            returnValue.setGenSize(gen.getSize());
            returnValue.setSlaStartDate(paymentAdviseDto.getSlaStartDate());
            returnValue.setServicesAlreadyDone(paymentAdviseDto.getServicesAlreadyDone());
            returnValue.setPaymentDescription(paymentAdviseDto.getPaymentItem());
        }else{
            returnValue.setPaymentDescription(paymentAdviseDto.getPaymentDescription());
        }
        sendMail.sendEmailWithAttachment(message.paymentAdviseNotification(returnValue),recipent,recipent, "Sla notice");
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Site Inspection Logged Successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }
}

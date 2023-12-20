/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.pgagtersales.pgaftersales.shared.dto.NotificationRequestDto;
import com.pgagtersales.pgaftersales.shared.dto.SubscriptionRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class StorageService {

    @Value("${google-cloud-config}")
    private String gcsConfig;

    @PostConstruct
    private void initialize() {
//        try {
//            Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource(gcsConfig).getInputStream());
//            Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
//                    .setProjectId("sound-works-387423").build().getService();
//            Bucket bucket = storage.create(BucketInfo.of("powergen"));
//
//            String value = "Hello, World!";
//            byte[] bytes = value.getBytes(UTF_8);
//            Blob blob = bucket.create("my-first-blob", bytes);
//        } catch (IOException e) {
//            log.error("Create FirebaseApp Error", e);
//        }
    }
}

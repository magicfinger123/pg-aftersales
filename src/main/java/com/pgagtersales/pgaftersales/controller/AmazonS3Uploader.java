package com.pgagtersales.pgaftersales.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pgagtersales.pgaftersales.config.AWSConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
@Component
public class AmazonS3Uploader {
    private final AmazonS3 s3Client = new AWSConfig().amazonS3();
    public  void upload(String base64EncodedImage, String bucketName, String objectKey) {
        try {
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64EncodedImage);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, inputStream, null);
            s3Client.putObject(request);
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}
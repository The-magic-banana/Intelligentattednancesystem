package cn.cachalot.intelligentattendancesystem.common;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class SMSUtils {
    public static String sendMessage(String signName, String templateCode, String phoneNumbers,String code) {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder().accessKeyId(
                "LTAI5tPYZkrsRUdnoKgfGzTM").accessKeySecret("1UJpM5HdyRlR4FPgLG0pDqtfxGZbuU").build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder().region("cn" + "-zhangjiakou") // Region ID
                .credentialsProvider(provider).overrideConfiguration(ClientOverrideConfiguration.create().setEndpointOverride("dysmsapi.aliyuncs.com")).build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest =
//                SendSmsRequest.builder().signName("阿里云短信测试").templateCode("SMS_154950909").phoneNumbers
//                ("15268260059").templateParam("{\"code\":\"1234\"}").build();
                SendSmsRequest.builder().signName(signName).templateCode(templateCode).phoneNumbers(phoneNumbers).templateParam("{\"code\":\"" + code + "\"}").build();

        // Asynchronously get the return value of the API
        // request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API
        // request
        SendSmsResponse resp;
        try {
            resp = response.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new Gson().toJson(resp));
        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling
        exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/

        // Finally, close the client
        log.info("短信发送成功!");
        client.close();
        return code;
    }


}

package org.nypl.mediaingest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;
import org.nypl.mediaingest.config.EnvironmentConfig;
import org.nypl.mediaingest.constants.Constants;
import org.nypl.mediaingest.models.Asset;
import org.nypl.mediaingest.models.AssetDAO;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestProcessorUtils {


  public Map<String, List<String>> downloadMediaFiles(List<String> mediaFilenames,
      AssetDAO assetDAO) {
    Map<String, List<String>> resultOfDownload = new HashMap<>();
    List<String> successfulFiles = new ArrayList<>();
    List<String> unsuccessfulFiles = new ArrayList<>();
    for (String fileName : mediaFilenames) {
      try {
        Asset asset = assetDAO.findByName(fileName);
        String locationUUID = asset.getUuid();

        if (new File(getFullPath(EnvironmentConfig.DESTINATION_PATH, asset.getName())).exists())
          continue;

        FileCopyUtils.copy(
            new File(
                getFullPath(EnvironmentConfig.BASE_PATH_TO_REPO, RepoUtils.getPath(locationUUID))),
            new File(getFullPath(EnvironmentConfig.DESTINATION_PATH, asset.getName())));

        System.out.println("Copied file - " + asset.getName());
        String checksumAfterCopying =
            findChecksum(getFullPath(EnvironmentConfig.DESTINATION_PATH, asset.getName()));
        if (!asset.getChecksum().equals(checksumAfterCopying))
          throw new Exception("Checksum is not the same for file - " + asset.getName());
        successfulFiles.add(fileName);

      } catch (Exception e) {
        System.out.println("Failed to successfully complete download of file - " + fileName);
        unsuccessfulFiles.add(fileName);
        e.printStackTrace();
      }
      resultOfDownload.put(Constants.SUCCESSFUL_FILES, successfulFiles);
      resultOfDownload.put(Constants.UNSUCCESSFUL_FILES, unsuccessfulFiles);
    }
    return resultOfDownload;
  }

  public boolean sendEmail(Map<String, List<String>> results, JavaMailSender emailSender,
      String email, String requestId) throws JsonProcessingException, MessagingException {
    System.out.println(results);
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setTo(email);
    helper.setSubject(Constants.SUBJECT_EMAIL + requestId);
    helper.setText(new ObjectMapper().writeValueAsString(results));
    emailSender.send(message);
    System.out.println("Email sent - requestId - " + requestId);
    return true;
  }

  public static String getFullPath(String begin, String end) {
    StringBuilder fullPath = new StringBuilder(begin);
    if (!begin.endsWith("/")) {
      fullPath.append("/");
      fullPath.append(end);
    } else
      fullPath.append(end);

    return fullPath.toString();
  }

  public static String findChecksum(String filePath) throws Exception {
    String md5 = "";
    try {
      FileInputStream fileInputStream = new FileInputStream(new File(filePath));
      md5 = DigestUtils.md5Hex(fileInputStream);
    } catch (Exception e) {
      System.out.println(e);
      System.out.println(e.getMessage());
    }
    return md5;
  }

}

package org.nypl.mediaingest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.mail.MessagingException;

import org.nypl.mediaingest.config.EnvironmentConfig;
import org.nypl.mediaingest.constants.Constants;
import org.nypl.mediaingest.models.Asset;
import org.nypl.mediaingest.models.AssetDAO;
import org.nypl.mediaingest.models.Source;
import org.nypl.mediaingest.models.SourceDAO;
import org.nypl.mediaingest.utils.RequestProcessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;


@Service
public class RequestProcessor {

  @Autowired
  private SourceDAO sourceDAO;

  @Autowired
  private AssetDAO assetDAO;

  @Autowired
  private JavaMailSender emailSender;

  public Optional<List<Asset>> getVideoFilesOfBag(String bagName) {
    Optional<Source> optionalSource = Optional.ofNullable(sourceDAO.findByName(bagName));
    if (!optionalSource.isPresent())
      return Optional.empty();
    else {
      Source source = optionalSource.get();
      int sourceId = source.getId();
      System.out.println(sourceId);
      List<Asset> assets = assetDAO.findBySourceId(sourceId);
      return Optional.of(assets);
    }
  }

  public String reqDownloadMediaFiles(List<String> mediaFilenames, String email) {
    String requestId = UUID.randomUUID().toString().toUpperCase();
    CompletableFuture.supplyAsync(() -> {
      return new RequestProcessorUtils().downloadMediaFiles(mediaFilenames, assetDAO);
    }).thenAccept(results -> {
      try {
        new RequestProcessorUtils().sendEmail(results, emailSender, email, requestId);
      } catch (JsonProcessingException | MessagingException e) {
        System.out.println("Error occurred while sending email");
        e.printStackTrace();
      }
    });

    return Constants.MESSAGE_START_DOWNLOADING_FILES + requestId;
  }

}

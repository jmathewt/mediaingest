package org.nypl.mediaingest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MediaingestApplication {

  public static void main(String[] args) {
    SpringApplication.run(MediaingestApplication.class, args);
  }
}

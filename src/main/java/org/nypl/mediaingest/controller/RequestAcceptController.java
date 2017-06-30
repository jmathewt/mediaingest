package org.nypl.mediaingest.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.nypl.mediaingest.models.Asset;
import org.nypl.mediaingest.service.RequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mediaDrop")
public class RequestAcceptController {

  @Autowired
  private RequestProcessor requestProcessor;

  @RequestMapping(value = "/bag/{bagName}", method = RequestMethod.GET)
  public ResponseEntity<List<Asset>> listMediaFiles(@PathVariable String bagName) {
    System.out.println("Going to retrieve assets for bag - " + bagName);
    Optional<List<Asset>> optionalAssets = requestProcessor.getVideoFilesOfBag(bagName);
    if (!optionalAssets.isPresent()) {
      return new ResponseEntity<List<Asset>>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<List<Asset>>(optionalAssets.get(), HttpStatus.OK);
    }
  }

  @RequestMapping(value = "/getMedia", method = RequestMethod.GET)
  public ResponseEntity<String> requestMediaFiles(@RequestParam(value = "names") List<String> names,
      @RequestParam(value = "email") String email) {
    return new ResponseEntity<String>(requestProcessor.reqDownloadMediaFiles(names, email),
        HttpStatus.OK);
  }

}

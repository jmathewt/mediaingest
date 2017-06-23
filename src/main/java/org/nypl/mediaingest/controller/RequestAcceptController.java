package org.nypl.mediaingest.controller;

import java.util.List;

import org.nypl.mediaingest.models.Asset;
import org.nypl.mediaingest.service.RequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mediaDrop")
public class RequestAcceptController {
  
  @Autowired
  private RequestProcessor requestProcessor;
  
  @RequestMapping(value = "/bag/{bagName}", method = RequestMethod.GET)
  public List<Asset> listMediaFiles(@PathVariable String bagName){ 
    System.out.println("Going to retrieve assets for bag - " + bagName);
    return requestProcessor.getVideoFilesOfBag(bagName);
  }

}

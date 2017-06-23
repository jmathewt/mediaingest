package org.nypl.mediaingest.service;

import java.util.List;

import org.nypl.mediaingest.models.Asset;
import org.nypl.mediaingest.models.AssetDAO;
import org.nypl.mediaingest.models.Source;
import org.nypl.mediaingest.models.SourceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessor {
  
  @Autowired
  private SourceDAO sourceDAO;
  
  @Autowired
  private AssetDAO assetDAO;
  
  public List<Asset> getVideoFilesOfBag(String bagName){
    Source source = sourceDAO.findByName(bagName);
    int sourceId = source.getId();
    System.out.println(sourceId);
    List<Asset> assets = assetDAO.findBySourceId(sourceId);
    return assets;
  }

}

package org.nypl.mediaingest.service;

import java.util.List;
import java.util.Optional;

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
  
  public Optional<List<Asset>> getVideoFilesOfBag(String bagName){
    Optional<Source> optionalSource = Optional.ofNullable(sourceDAO.findByName(bagName));
    if(!optionalSource.isPresent())
      return Optional.empty();
    else{
      Source source = optionalSource.get();
      int sourceId = source.getId();
      System.out.println(sourceId);
      List<Asset> assets = assetDAO.findBySourceId(sourceId);
      return Optional.of(assets);
    }
  }

}

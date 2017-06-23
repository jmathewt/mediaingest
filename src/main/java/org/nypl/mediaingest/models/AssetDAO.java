package org.nypl.mediaingest.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AssetDAO extends CrudRepository<Asset, Integer>{
  
  public List<Asset> findBySourceId(int sourceId);
  
}

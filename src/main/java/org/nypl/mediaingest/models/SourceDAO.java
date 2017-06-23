package org.nypl.mediaingest.models;

import org.springframework.data.repository.CrudRepository;

public interface SourceDAO extends CrudRepository<Source, Integer>{
  
  public Source findByName(String name);

}

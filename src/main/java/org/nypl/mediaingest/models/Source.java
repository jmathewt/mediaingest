package org.nypl.mediaingest.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "sources")
public class Source {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @NotNull
  private String uuid;
  
  @NotNull
  private String name;
  
  @NotNull
  private Date created_at;
  
  private boolean is_processed;
  
  @NotNull
  private String source;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public boolean isIs_processed() {
    return is_processed;
  }

  public void setIs_processed(boolean is_processed) {
    this.is_processed = is_processed;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

}

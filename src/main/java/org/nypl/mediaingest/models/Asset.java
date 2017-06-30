package org.nypl.mediaingest.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "assets")
public class Asset {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  private String name;

  @NotNull
  private String checksum;

  @NotNull
  private String capture_uuid;

  @NotNull
  private Date created_at;

  private String type;

  private float width;

  private float height;

  @NotNull
  private boolean is_processed;

  @NotNull
  private String uuid;

  @NotNull
  private long size;

  @NotNull
  @Column(name = "source_id")
  private int sourceId;

  private String derivative_type;

  @Transient
  @Column(name = "preservation_master_id")
  private int preservationMasterId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public String getCapture_uuid() {
    return capture_uuid;
  }

  public void setCapture_uuid(String capture_uuid) {
    this.capture_uuid = capture_uuid;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public boolean isIs_processed() {
    return is_processed;
  }

  public void setIs_processed(boolean is_processed) {
    this.is_processed = is_processed;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }


  public int getSourceId() {
    return sourceId;
  }

  public void setSourceId(int sourceId) {
    this.sourceId = sourceId;
  }

  public String getDerivative_type() {
    return derivative_type;
  }

  public void setDerivative_type(String derivative_type) {
    this.derivative_type = derivative_type;
  }

  public int getPreservationMasterId() {
    return preservationMasterId;
  }

  public void setPreservationMasterId(int preservationMasterId) {
    this.preservationMasterId = preservationMasterId;
  }

}

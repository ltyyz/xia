package com.github.loutai.xia.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class XiaRoleMenu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NonNull
  private Integer roleId;
  @NonNull
  private Integer menuId;

}

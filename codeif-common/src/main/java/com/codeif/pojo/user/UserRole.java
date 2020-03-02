//package com.codeif.pojo.user;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@ApiModel(value = "UserRole", description = "用户_角色中间表")
//@Getter
//@Setter
//@Entity
//@Table(name = "us_user_role")
//public class UserRole {
//
//    @Id
//    @GeneratedValue(generator = "idGenerator")
//    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
//    @ApiModelProperty("用户_角色中间表主键")
//    private String id;
//    @ApiModelProperty("用户表id")
//    private String usUserId;
//    @ApiModelProperty("角色表id")
//    private String usRoleId;
//}

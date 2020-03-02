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
//@ApiModel(value = "RoleResource", description = "角色_资源中间表")
//@Getter
//@Setter
//@Entity
//@Table(name = "us_role_resource")
//public class RoleResource {
//
//    @Id
//    @GeneratedValue(generator = "idGenerator")
//    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
//    @ApiModelProperty("角色_资源中间表主键")
//    private String id;
//    @ApiModelProperty("角色表_id")
//    private String usRoleId;
//    @ApiModelProperty("资源表_id")
//    private String usResourceId;
//}
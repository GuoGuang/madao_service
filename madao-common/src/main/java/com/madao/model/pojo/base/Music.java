package com.madao.model.pojo.base;

import com.madao.model.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
//@Entity
//@Table(name = "")
public class Music extends BasePojo implements Serializable {

    //  @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id", unique=true, nullable=false, updatable=false, length = 20)
    private String id;

    private String parentId;

    /**
     * 编码
     */
    @NotNull(message = "编码不能为空")
    private String code;

    /**
     * 名称
     */
    private String name;

    private String description;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 类型
     */
    private String type;

    private static final long serialVersionUID = 1L;

}
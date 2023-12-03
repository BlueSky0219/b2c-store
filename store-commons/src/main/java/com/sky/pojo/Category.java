package com.sky.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author bluesky
 * @create 2022-11-14-17:22
 */
@Data
@TableName("category")
public class Category implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("category_id")
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    @JsonProperty("category_name")
    @NotBlank
    private String categoryName;

}

package com.sky.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bluesky
 * @create 2022-11-14-15:31
 */
@Data
public class Carousel implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("carousel_id")
    @TableId(value = "carousel_id", type = IdType.AUTO)
    private Integer carouselId;

    @TableField("img_path")
    private String imgPath;

    private String describes;

    @JsonProperty("product_id ")
    @TableField("product_id")
    private Integer productId;

    private Integer priority;

}

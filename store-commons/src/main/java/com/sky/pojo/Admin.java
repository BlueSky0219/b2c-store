package com.sky.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bluesky
 * @create 2022-11-22-15:46
 */
@Data
@TableName("admin_user")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    @TableId(type = IdType.AUTO)
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_account")
    private String userAccount;
    @JsonProperty("user_password")
    private String userPassword;
    @JsonProperty("user_phone")
    private String userPhone;
    @JsonProperty("create_time")
    private Date createTime;
    @JsonProperty("user_role")
    private Integer userRole;


}

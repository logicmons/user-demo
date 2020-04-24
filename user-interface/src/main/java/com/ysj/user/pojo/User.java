package com.ysj.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Length(min = 6,max = 12,message = "用户名需要6-12个字符")
    private String username;

    @JsonIgnore
    @Length(min = 8,max = 12,message = "密码长度为8-12个字符")
    private String password;

    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "手机号不合法")
    private String phone;

    private Date created; //创建时间

    @JsonIgnore
    private String salt; //密码的盐值
}

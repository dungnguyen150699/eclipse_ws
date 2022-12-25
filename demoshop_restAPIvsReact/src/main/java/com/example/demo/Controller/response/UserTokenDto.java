package com.example.demo.Controller.response;

import java.io.Serializable;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.Impl.UserDetailImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTokenDto {
	  private String token;
	  private UserDetailImpl user;
}

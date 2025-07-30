package com.julspringsecurity.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.julspringsecurity.entity.Users;


public class SecurityUser extends User{
    private static final long serialVersionUID=1L;

    private Users users;

    public SecurityUser(Users users){
        super(users.getUsername(),users.getPassword(),
        AuthorityUtils.createAuthorityList(users.getRole().toString()));
        this.users=users;
    }
   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   public Users getUsers() {
      return users;
   }
   
}

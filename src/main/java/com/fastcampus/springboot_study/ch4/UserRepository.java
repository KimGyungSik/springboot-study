package com.fastcampus.springboot_study.ch4;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {

}

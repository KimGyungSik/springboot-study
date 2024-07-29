package com.fastcampus.springboot_study.ch4;

import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board,Long> {
}

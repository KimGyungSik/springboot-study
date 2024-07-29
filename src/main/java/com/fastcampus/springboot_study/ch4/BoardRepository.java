package com.fastcampus.springboot_study.ch4;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board,Long> {
    @Query("SELECT b FROM Board b") // JPQL은 명칭을 대소문자 구분에 주의
    List<Board> findAllByBoard(); // 메서드 이름은 아무거나 괜찮

    @Query("select b from Board b where b.title=?1 and b.writer=?2")
            List<Board> findByTitleAndWriter2(String title,String writer);

    @Query("select b from Board b where b.title=:title and b.writer=:writer")
    List<Board> findByTitleAndWriter3(String title,String writer);

    @Query(value = "select * from Board",nativeQuery = true) // SQL
            List<Board> findAllBoardBySQL();

    @Query(value = "select TITLE,WRITER from Board",nativeQuery = true) // SQL
    List<Object[]> findAllBoardBySQL2();

    // select count(*) from board where writer = :writer
    int countAllByWriter(String writer);
    // select * from board where writer = :writer
    List<Board> findByWriter(String writer);
    // select * from board where title = :title and writer = :writer
    List<Board> findByTitleAndWriter(String title,String writer);
    // delete from board where writer = :writer
    @Transactional // delete의 경우, 여러 건을 delete할 수 있기 때문에 Tx처리 필수
    int deleteByWriter(String writer);
}

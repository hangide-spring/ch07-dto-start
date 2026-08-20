package com.metacoding.blog.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public List<Board> findAll() {
        List<Board> boardList = em
                .createQuery("select b from Board b order by b.id desc", Board.class)
                .getResultList();
        return boardList;
    }

    public Board findById(Integer id) {
        Board board = em.find(Board.class, id);
        return board;
    }

    public void delete(Board board) {
        em.remove(board);
    }
}

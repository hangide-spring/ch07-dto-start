package com.metacoding.blog.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Board findById(Integer id) {
        Board board = boardRepository.findById(id);
        return board;
    }

    @Transactional
    public Board save(Board request) {
        Board board = boardRepository.save(request);
        return board;
    }

    @Transactional
    public Board update(Integer id, Board request) {
        Board board = getBoard(id);
        board.update(request.getTitle(), request.getContent()); // 더티체킹
        return board;
    }

    @Transactional
    public void delete(Integer id) {
        boardRepository.delete(getBoard(id));
    }

    private Board getBoard(Integer id) {
        Board board = boardRepository.findById(id);
        if (board == null)
            throw new RuntimeException("게시글을 찾을 수 없습니다 : " + id);
        return board;
    }
}

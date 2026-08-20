package com.metacoding.blog.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<?> list() {
        List<Board> response = boardService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") int id) {
        Board response = boardService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/boards")
    public ResponseEntity<?> save(@RequestBody Board request) {
        Board response = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Board request) {
        Board response = boardService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        boardService.delete(id);
        return ResponseEntity.ok(null);
    }
}

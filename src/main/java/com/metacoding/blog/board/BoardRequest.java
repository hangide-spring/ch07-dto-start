package com.metacoding.blog.board;

public record BoardRequest(
        String title,
        String content) {

    public Board toEntity() {
        return null;
    }
}

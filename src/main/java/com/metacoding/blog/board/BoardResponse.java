package com.metacoding.blog.board;

import java.sql.Timestamp;

public record BoardResponse(Integer id, String title, String content, Timestamp createdAt) {

    public static BoardResponse from(Board board) {
        return null;
    }
}

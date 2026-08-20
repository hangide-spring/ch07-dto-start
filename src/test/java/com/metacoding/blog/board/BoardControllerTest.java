package com.metacoding.blog.board;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest; // 스프링 부트 4의 새 패키지 위치
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean // 컨트롤러가 의존하는 서비스를 가짜 객체로 대체한다
    private BoardService boardService;

    @Test
    public void list_test() throws Exception {
        Board board1 = Board.builder().id(1).title("제목1").content("내용1").build();
        Board board2 = Board.builder().id(2).title("제목2").content("내용2").build();
        List<Board> boardList = List.of(board1, board2);

        given(boardService.findAll()).willReturn(boardList);

        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("제목1"));
    }

    @Test
    public void save_test() throws Exception {
        // given
        Board board = Board.builder().id(1).title("제목1").content("내용1").build();
        given(boardService.save(any(Board.class))).willReturn(board);

        // when & then
        mvc.perform(post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"title":"새글제목","content":"새글내용"}
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4));
    }

    @Test
    public void save_valid_fail_test() throws Exception {

    }
}

package com.juyeon.board.controller;

import com.juyeon.board.common.ResponseMsg;
import com.juyeon.board.domain.dto.CommentDTO;
import com.juyeon.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Spring Boot Swagger 연동 API (Comments)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    // 게시글 작성
    @Operation(summary = "게시글 작성", description = "게시판에 업로드할 새로운 게시글 작성")
    @PostMapping("/comments")
    public ResponseEntity<ResponseMsg> createNewPost(@RequestBody CommentDTO newComment) {
        commentService.registerComment(newComment);

        String successMsg = "댓글 등록에 성공하였습니다.";

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", successMsg);

        return ResponseEntity
                .ok()
                .body(new ResponseMsg(201, "댓글 추가 성공", responseMap));
    }
}

package com.juyeon.board.controller;

import com.juyeon.board.common.ResponseMsg;
import com.juyeon.board.domain.dto.CommentDTO;
import com.juyeon.board.domain.dto.PostDTO;
import com.juyeon.board.domain.entity.Comment;
import com.juyeon.board.global.CommentNotFoundException;
import com.juyeon.board.global.PostNotFoundException;
import com.juyeon.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Spring Boot Swagger 연동 API (Comments)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @Operation(summary = "댓글 작성", description = "게시글에 추가할 새로운 댓글 작성")
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

    // 댓글 전체 조회
    @Operation(summary = "댓글 전체 조회", description = "사이트의 댓글 전체 조회")
    @GetMapping("/comments")
    public ResponseEntity<ResponseMsg> findAllComments() {
        List<Comment> comments = commentService.findAllComments();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("comments", comments);

        return ResponseEntity
                .ok()
                .body(new ResponseMsg(200, "댓글 전체 조회 성공", responseMap));
    }

    // 댓글 단일 조회
    @Operation(
            summary = "댓글 번호로 특정 게시글 조회",
            description = "댓글 번호를 통해 특정 게시글을 조회한다.",
            parameters = {
                    @Parameter(
                            name = "commentId",
                            description = "comment의 pk"
                    )
            })
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ResponseMsg> findCommentByCommentId(@PathVariable long commentId) throws CommentNotFoundException {
        Comment foundComment = commentService.getCommentByCommentId(commentId);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("comment", foundComment);

        return ResponseEntity
                .ok()
                .body(new ResponseMsg(200, "댓글 단일 조회 성공", responseMap));
    }
}

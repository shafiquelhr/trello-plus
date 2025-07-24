package com.trelloplus.controller;

import com.trelloplus.dto.BoardDto;
import com.trelloplus.mapper.BoardMapper;
import com.trelloplus.model.Board;
import com.trelloplus.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    // POST a board
    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto) {
        Board board = boardMapper.toEntity(boardDto);
        Board savedBoard = boardService.createBoard(board);
        return new ResponseEntity<>(boardMapper.toDto(savedBoard), HttpStatus.CREATED);
    }

    // GET all boards
    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        List<BoardDto> dtos = boardService.getAllBoards().stream().map(boardMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}

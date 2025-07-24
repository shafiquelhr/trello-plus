package com.trelloplus.service;

import com.trelloplus.model.Board;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board);

    List<Board> getAllBoards();

    Board getBoardById(Long id);
}

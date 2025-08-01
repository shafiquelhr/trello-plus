package com.trelloplus.service.impl;

import com.trelloplus.exception.BoardNotFoundException;
import com.trelloplus.model.Board;
import com.trelloplus.repository.BoardRepository;
import com.trelloplus.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));
    }
}

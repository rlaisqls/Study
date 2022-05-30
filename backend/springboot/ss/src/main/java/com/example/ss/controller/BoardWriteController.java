package com.example.ss.controller;


import com.example.ss.payload.request.BoardRequest;
import com.example.ss.payload.response.BoardWriteResponse;
import com.example.ss.service.create.BoardCreationServiceImpl;
import com.example.ss.service.delete.BoardDeletionServiceImpl;
import com.example.ss.service.modify.BoardModificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
@CrossOrigin(origins = "*")
public class BoardWriteController {
    private final BoardCreationServiceImpl boardCreationService;
    private final BoardModificationServiceImpl boardModifyService;
    private final BoardDeletionServiceImpl boardDeleteService;
    @PostMapping
    public BoardWriteResponse write(@RequestBody BoardRequest request){
        return boardCreationService.createBoard(request.getTitle(),request.getContent());
    }

    @PatchMapping("/{feed_id}")
    public BoardWriteResponse modefy(@PathVariable(name = "feed_id") Long id, @RequestBody BoardRequest request){
        return boardModifyService.modifyBoard(request, id);
    }
    @DeleteMapping("/{feed_id}")
    public BoardWriteResponse delete(@PathVariable(name = "feed_id") long id){
        return boardDeleteService.deleteBoard(id);
    }
}
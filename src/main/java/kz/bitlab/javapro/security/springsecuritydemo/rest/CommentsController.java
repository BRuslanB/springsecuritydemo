package kz.bitlab.javapro.security.springsecuritydemo.rest;

import kz.bitlab.javapro.security.springsecuritydemo.dto.AuthorDTO;
import kz.bitlab.javapro.security.springsecuritydemo.dto.CommentDTO;
import kz.bitlab.javapro.security.springsecuritydemo.mapper.UserMapper;
import kz.bitlab.javapro.security.springsecuritydemo.model.User;
import kz.bitlab.javapro.security.springsecuritydemo.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comments")
@CrossOrigin
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;
    private final UserMapper userMapper;

    @GetMapping(value = "{newsId}")
    private ResponseEntity<List<CommentDTO>> getCommentsByNewsId(@PathVariable(name = "newsId") Long newsId){
        return new ResponseEntity<>(commentsService.getAllComments(newsId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO comment){
        AuthorDTO author = userMapper.toDto(getCurrentUser());
        comment.setAuthor(author);
        return new ResponseEntity<>(commentsService.addComment(comment), HttpStatus.CREATED);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}

package kz.bitlab.javapro.security.springsecuritydemo.services;

import kz.bitlab.javapro.security.springsecuritydemo.dto.CommentDTO;
import kz.bitlab.javapro.security.springsecuritydemo.mapper.CommentMapper;
import kz.bitlab.javapro.security.springsecuritydemo.model.Comment;
import kz.bitlab.javapro.security.springsecuritydemo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public List<CommentDTO> getAllComments(Long id){
        List<CommentDTO> commentDTOList = commentMapper.toList(commentRepository.findAllByNews_Id(id));
        return commentDTOList;
    }

    public CommentDTO addComment(CommentDTO commentDTO){
        Comment comment = commentMapper.toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

}

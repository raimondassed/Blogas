package com.raimis.Blogas.service;

import com.raimis.Blogas.entities.Comment;
import com.raimis.Blogas.entities.Topic;
import com.raimis.Blogas.entities.User;
import com.raimis.Blogas.repo.CommentRepository;
import com.raimis.Blogas.repo.TopicRepository;
import com.raimis.Blogas.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public Topic addNewTopic(Topic newTopic) {
        return topicRepository.save(newTopic);
    }

    public Optional<Topic> getTopic(Long id) {
        return topicRepository.findById(id);
    }

    public List<Topic> filterTopicsByKeyword(String keyword) {
        return topicRepository.findTopicsByKeyword(keyword);
    }
    public Page<Topic> findPaginated(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

    public Topic save(Topic topicFound) {
        return topicRepository.save(topicFound);
    }

    public void addComment(long topicId, Comment comment, User user) {
        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with id %d does not exist"));
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Topics with id %d does not exist"));

        topic.addComment(comment);
        user.addComment(comment);

        commentRepository.save(comment);
    }

}

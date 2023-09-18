package com.raimis.Blogas.rest;

import com.raimis.Blogas.entities.Topic;
import com.raimis.Blogas.rest.dto.TopicDto;
import com.raimis.Blogas.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicEdgeService {

    private final TopicService topicService;

    public TopicEdgeService(TopicService topicService) {
        this.topicService = topicService;
    }

    public Page<TopicDto> findPaginated(Pageable pageable) {
        return topicService.findPaginated(pageable)
                .map(this::toTopicDto);
    }

    public Optional<TopicDto> getTopic(long topidId) {
        return topicService.getTopic(topidId).map(this::toTopicDto);
    }

    public TopicDto addTopic(TopicDto topicDto) {
        var topic = topicService.addNewTopic(toDomainTopic(topicDto));
        return toTopicDto(topic);
    }

    public void deleteTopicById(long id) {
        topicService.deleteTopic(id);
    }

    public TopicDto updateTopic(TopicDto topicToUpdate) {
        Long id = topicToUpdate.getId();
        Topic topicFound = topicService.getTopic(id)
                .orElseThrow(() -> new IllegalArgumentException("Topic with id %d not found".formatted(id)));
        topicFound.setTitle(topicToUpdate.getTitle());
        topicFound.setHeader(topicToUpdate.getHeader());
        return toTopicDto(topicService.save(topicFound));
    }

    private Topic toDomainTopic(TopicDto topicDto) {
        Topic topic = new Topic();
        topic.setHeader(topicDto.getHeader());
        topic.setTitle(topicDto.getTitle());
        return topic;
    }

    private TopicDto toTopicDto(Topic topic) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setTitle(topic.getTitle());
        topicDto.setHeader(topic.getHeader());

        List<Long> comments = topic.getComments().stream()
                .map(Comment::getId)
                .toList();
        topicDto.setCommentIds(comments);
        return topicDto;
    }
}

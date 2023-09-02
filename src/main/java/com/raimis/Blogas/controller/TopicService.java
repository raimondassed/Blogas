package com.raimis.Blogas.controller;

import com.raimis.Blogas.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class TopicService {
    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }



    public Topic addNewTopic(Topic newTopic) {

        return topicRepository.save(newTopic);
    }

    public List<Topic> findTopicsByTitle(String topicTitle) {
        return null;
    }
    public Topic getTopic(Long id) {
        return topicRepository.findById(id).get();
    }

    public List<Topic> filterTopicsByKeyword(String keyword) {
        return topicRepository.findTopicsByKeyword(keyword);
        }

    public Page<Topic> findPaginated(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }
}








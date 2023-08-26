package com.raimis.Blogas.controller;

import com.raimis.Blogas.entity.Topic;
import org.springframework.stereotype.Service;

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


    public Topic getTopic(Long id) {
        return topicRepository.findById(id).get();
    }

    public Topic addNewTopic(Topic newTopic) {

        return topicRepository.save(newTopic);
    }
}



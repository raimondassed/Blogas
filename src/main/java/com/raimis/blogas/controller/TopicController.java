package com.raimis.blogas.controller;

import com.raimis.blogas.entities.Comment;
import com.raimis.blogas.entities.Topic;
import com.raimis.blogas.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Slf4j
@RequestMapping("/topics")
public class TopicController {


    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("comment", new Comment());
        Topic topic = topicService.getTopic(id).orElse(null);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return "redirect:/topics";
    }

    @GetMapping("/add")
    public String getAddTopicForm(Topic topic) {
        return "addTopic";
    }

    @PostMapping("/add")
    public String addNewTopic(@Valid Topic topic, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addTopic";
        }
        log.info("New topic={} added");
        Topic savedTopic = topicService.addNewTopic(topic);
        log.info("New topic={} saved");
        return "redirect:/topics";
    }

    @GetMapping("/filter")
    public String filterTopics(@RequestParam String keyword, Model model) {
        List<Topic> topics = topicService.filterTopicsByKeyword(keyword);
        model.addAttribute("topics", topics);
        return "topics";
    }

    /**
     * List topics using pageable
     * Request example:
     *  http://localhost:8080/topics?size=3&page=0&sort=title,asc
     */
    @GetMapping
    public String listTopics(Model model,
                             @PageableDefault(sort = { "title"}, direction = Sort.Direction.DESC, size = 2, page = 1)
                             Pageable pageable)
    {
        Page<Topic> topicsPage = topicService.findPaginated(pageable);
        model.addAttribute("topics", topicsPage);

        int totalPages = topicsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "topics";
    }

}

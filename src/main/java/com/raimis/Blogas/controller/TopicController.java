package com.raimis.Blogas.controller;


import com.raimis.Blogas.entity.Comment;
import com.raimis.Blogas.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;
    private final CommentService commentService;

    public TopicController(TopicService topicService, CommentService commentService) {
        this.topicService = topicService;
        this.commentService = commentService;
    }


//    @GetMapping
//    public String getTopics(Model model) {
//        List<Topic> topics = topicService.getAllTopics();
//        model.addAttribute("topics", topics);
//        return "topics";
//    }

    @GetMapping("/{id}")
    public String getTopic(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("comment", new Comment());
        Topic topic = topicService.getTopic(id);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @PostMapping("/{id}")
    public String addCommentToTopic(@PathVariable Long id, Comment comment, Model model) {
        Topic topic = topicService.getTopic(id);
        comment.setTopic(topic);
        commentService.addCommentToTopic(comment);
        return "redirect:/topics/" + id;
    }

//
//    @GetMapping("/add")
//    public String getAddTopicForm(Model model) {
//        model.addAttribute("newTopic", new Topic());
//        return "addTopic";
//    }
//
//
//    @PostMapping
//    public String addNewTopic(Topic newTopic, Model model) {
//        topicService.addNewTopic(newTopic);
//        return "redirect:/topics";
//    }

    @GetMapping("/add")
    public String getAddTopicForm(Model model) {

        model.addAttribute("newTopic", new Topic());
        return "addTopics";
    }

    @PostMapping
    public String addNewTopic(Topic newTopic, Model model) {
        topicService.addNewTopic(newTopic);
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
     * http://localhost:8080/topics/list?size=3&page=0&sort=title,asc
     */
    @GetMapping
    public String listTopics(Model model,
                             @PageableDefault(sort = {"title"}, direction = Sort.Direction.DESC, size = 2, page = 1)
                             Pageable pageable) {
        Page<Topic> bookPage = topicService.findPaginated(pageable);

        model.addAttribute("topicPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "listTopics";
    }
    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}


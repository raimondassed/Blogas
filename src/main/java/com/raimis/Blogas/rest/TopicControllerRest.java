package com.raimis.Blogas.rest;


import com.raimis.Blogas.rest.dto.TopicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Api(tags = "RestController for Topics")
@RequestMapping("/rest/topics")
@RestController
public class TopicControllerRest {

    private final TopicEdgeService topicService;

    public TopicControllerRest(TopicEdgeService topicService) {
        this.topicService = topicService;
    }

    @ApiOperation(value = "Get page of topics", tags = "read", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @GetMapping
    public ResponseEntity<Page<TopicDto>> listTopics(
            @PageableDefault(sort = { "title"}, direction = Sort.Direction.DESC, size = 2, page = 1) Pageable pageable) {
        Page<TopicDto> topics = topicService.findPaginated(pageable);
        return ResponseEntity.ok().body(topics);
    }

    @ApiOperation(value = "Get topic by Id", tags = "read", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code=404, message = "Not found")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable long id) {
        return ResponseEntity.of(topicService.getTopic(id));
    }

    @ApiOperation(value = "Add new topic", tags = "create", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New topic created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<TopicDto> addTopic(@RequestBody TopicDto topic, HttpServletRequest request) {
        TopicDto topicDto = topicService.addTopic(topic);
        URI uri = URI.create(request.getRequestURL().toString().concat("/%d".formatted(topicDto.getId())));
        return ResponseEntity.created(uri)
                .body(topicDto);
    }

    @ApiOperation(value = "Delete a topic", tags = "delete", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Topic does not exist anymore")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<TopicDto> deleteTopic(@PathVariable long id) {
        topicService.deleteTopicById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update topic", tags = "update", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Topic updated successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PutMapping
    public ResponseEntity<TopicDto> updateTopic(@RequestBody TopicDto topicToUpdate) {
        TopicDto updatedTopic = topicService.updateTopic(topicToUpdate);
        return ResponseEntity.ok().body(updatedTopic);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ErrorResponse> handleErrorResponse(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }
}

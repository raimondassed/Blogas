package com.raimis.blogas.rest.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicDto {

    private Long id;
    @ApiModelProperty(notes = "Topic title")
    private String title;
    private String header;
    private List<Long> commentIds;

}

package com.raimis.Blogas.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable=false)
    private Topic topic;

    @ManyToOne
    private User createdBy;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", topic=" + (topic == null? "null": topic.getId()) +
                ", createdBy=" + createdBy +
                '}';
    }
}



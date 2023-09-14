package com.raimis.Blogas.repo;

import com.raimis.Blogas.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

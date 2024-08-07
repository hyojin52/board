package dev.be.board.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import dev.be.board.domain.ArticleComment;
import dev.be.board.domain.QArticle;
import dev.be.board.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {
  
  List<ArticleComment> findByArticle_Id(Long articleId);
  
  @Override
  default void customize(QuerydslBindings bindings, QArticleComment root){
    bindings.excludeUnlistedProperties(true);
    bindings.including(root.createdAt, root.createdBy, root.content);
    bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
    bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    bindings.bind(root.createdAt).first(DateTimeExpression::eq);
  }
}
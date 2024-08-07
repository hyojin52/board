package dev.be.board.controller;

import dev.be.board.domain.type.SearchType;
import dev.be.board.dto.response.ArticleCommentResponse;
import dev.be.board.dto.response.ArticleResponse;
import dev.be.board.dto.response.ArticleWithCommentResponse;
import dev.be.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/articles")
@Controller
@RequiredArgsConstructor
public class ArticleController {
  
  private final ArticleService articleService;
  
  @GetMapping
  public String articles(
          @RequestParam(required = false) SearchType searchType,
          @RequestParam(required = false) String searchValue,
          @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
          ModelMap map) {
    map.addAttribute("articles",
            articleService.searchArticles(searchType, searchValue, pageable)
            .map(ArticleResponse::from));
    return "articles/index";
  }
  
  @GetMapping("/{articleId}")
  public String article(@PathVariable Long articleId, ModelMap modelMap) {
    ArticleWithCommentResponse article = ArticleWithCommentResponse.from(articleService.getArticle(articleId));
    modelMap.addAttribute("article", article);
    modelMap.addAttribute("articleComments", article.articleCommentResponses());
    
    return "articles/detail";
  }
}
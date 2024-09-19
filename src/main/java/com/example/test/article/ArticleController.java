package com.example.test.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String articleList(Model model){
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/create")
    public String articleCreate(){
        return "article_form";
    }

    @PostMapping("/create")
    public String articleCreate(@RequestParam(value="title") String title,
                                @RequestParam(value="content") String content){
        this.articleService.create(title, content);
        return "redirect:/article/list";
    }

    @GetMapping (value = "/detail/{id}")
    public String create(Model model, @PathVariable("id") Integer id,
                         @Valid ArticleForm articleForm, BindingResult bindingResult){
        Article article = this.articleService.getArticle(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("articleForm", new ArticleForm());
            return "article_detail";
        }
        this.articleService.create(articleForm.getTitle(), articleForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }
}

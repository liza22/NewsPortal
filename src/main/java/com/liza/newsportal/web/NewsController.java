package com.liza.newsportal.web;

import java.beans.PropertyEditorSupport;
import java.util.Locale;
import java.util.Map;

import com.liza.newsportal.domain.Category;
import com.liza.newsportal.domain.News;
import com.liza.newsportal.domain.Query;
import com.liza.newsportal.service.NewsService;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listNews(Model model) {

        model.addAttribute("query", new Query());
        model.addAttribute("newsList", newsService.listNews());
        model.addAttribute("categoriesList", newsService.listCategories());

        return "listnews";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String listNews(@ModelAttribute("query") Query query, @RequestParam String type, Model model, Locale locale) {

        String msg_reset = messageSource.getMessage("label.reset",
                new String[]{locale.getDisplayName(locale)}, locale);
        String msg_search = messageSource.getMessage("label.searchnews",
                new String[]{locale.getDisplayName(locale)}, locale);

        if (type.equals(msg_search))
            model.addAttribute("newsList", newsService.filterListNews(query));

        if (type.equals(msg_reset)) {
            model.addAttribute("query", new Query());
            model.addAttribute("newsList", newsService.listNews());
        }

        model.addAttribute("categoriesList", newsService.listCategories());

        return "listnews";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") News news) {

        newsService.addNews(news);

        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNews(Model model) {

        model.addAttribute("news", new News());
        model.addAttribute("categoriesList", newsService.listCategories());

        return "addnews";
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") Category category, Model model, Locale locale) {

        try {
            newsService.addCategory(category);
            return "redirect:/index";
        } catch (ConstraintViolationException e) {
            String msg_exist = messageSource.getMessage("label.exist",
                    new String[]{locale.getDisplayName(locale)}, locale);
            model.addAttribute("exception", msg_exist);
            return "addcategory";
        }
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String addCategory(Model model) {

        model.addAttribute("category", new Category());

        return "addcategory";
    }

    @RequestMapping(value = "/edit/{newsId}", method = RequestMethod.POST)
    public String editNews(@ModelAttribute("news") News news, @PathVariable("newsId") Integer newsId) {

        News oldNews = newsService.findNews(newsId);

        oldNews.setTitle(news.getTitle());
        oldNews.setBody(news.getBody());
        oldNews.setCategory(news.getCategory());

        newsService.editNews(oldNews);

        return "redirect:/index";
    }

    @RequestMapping(value = "/edit/{newsId}", method = RequestMethod.GET)
    public String editNews(@PathVariable("newsId") Integer newsId, Model model) {
        News news = newsService.findNews(newsId);
        model.addAttribute("news", news);

        return "editnews";
    }

    @RequestMapping("/delete/{newsId}")
    public String deleteNews(@PathVariable("newsId") Integer newsId) {

        newsService.removeNews(newsId);

        return "redirect:/index";
    }

    class CategoryEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text){
            Integer id = Integer.parseInt(text);
            Category category = newsService.findCategories(id);
            setValue(category);
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEditor());
    }
}

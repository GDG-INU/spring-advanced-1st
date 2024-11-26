package com.gdg.ms.controller;

import com.gdg.ms.entity.Book;
import com.gdg.ms.entity.Manager;
import com.gdg.ms.service.BookService;
import com.gdg.ms.service.ManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/*")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private BookService bookService;

    // 로그인페이지
    @RequestMapping("loginPage")
    public String loginPage() {

        return "manager/loginPage";
    }

    // 로그인 프로세스
    @RequestMapping("loginProcess")
    public String loginProcess(@ModelAttribute Manager manager, Model model, HttpSession session) {
        boolean isValid = managerService.validateManager(manager);


        if (isValid) {
            Manager managerInfo = managerService.getManagerInfo(manager);
            session.setAttribute("sessionManagerInfo", managerInfo);
            return "redirect:./mainPage";
        } else {
            model.addAttribute("error", "Invalid ID or Password");
            return "manager/loginPage";
        }
    }

    // 메인페이지
    @RequestMapping("mainPage")
    public String mainPage(HttpSession session, Model model) {

        Manager manager = (Manager) session.getAttribute("sessionManagerInfo");
        List<Book> bookList = bookService.getBookList();

        model.addAttribute("managerName", manager.getName());
        model.addAttribute("bookList", bookList);

        return "manager/mainPage";
    }

    // 도서 등록 페이지
    @RequestMapping("registerBookPage")
    public String registerBookPage() {
        return "manager/registerBookPage";
    }

    // 도서 등록 프로세스
    @RequestMapping("registerBookProcess")
    public String registerBookProcess(@ModelAttribute Book book) {
        bookService.registerBook(book);
        return "redirect:./mainPage";
    }

    // 도서 삭제 프로세스
    @RequestMapping("deleteBookProcess")
    public String deleteBookProcess(@ModelAttribute("bookPk") int bookPk) {
        bookService.deleteBook(bookPk);
        return "redirect:./mainPage";
    }




}

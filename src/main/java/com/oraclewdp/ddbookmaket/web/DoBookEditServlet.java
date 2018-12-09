package com.oraclewdp.ddbookmaket.web;

import com.oraclewdp.ddbookmaket.biz.BookBiz;
import com.oraclewdp.ddbookmaket.biz.impl.BookBizImpl;
import com.oraclewdp.ddbookmaket.model.Book;
import com.oraclewdp.ddbookmaket.util.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DoBookEditServlet", value = "/doBookEdit")
@MultipartConfig
public class DoBookEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getSession().getAttribute("hasLongined")==null||!(Boolean)request.getSession().getAttribute("hasLongined")){
//            response.sendRedirect("login.jsp");
//            return;
//        }
        Book book = new Book();
        MyBeanUtils.populate(book, request.getParameterMap(), "yyyy-MM-dd");
        String newFile = null;
        Part part = request.getPart("photo");
        if (part.getHeader("Content-Disposition").contains("; filename=")) {
            if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
                String es = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf(".") + 1);
                newFile = UUID.randomUUID() + "." + es;
                part.write(request.getServletContext().getRealPath("/upload/" + newFile));
            }
        }
        book.setPhoto(newFile);
        BookBiz bookBiz = new BookBizImpl();
        boolean ret = bookBiz.update(book);
        if (ret) {
            response.sendRedirect("bookList");
        } else {
            request.setAttribute("smallType", book);
            request.getRequestDispatcher("bookEdit.jsp").forward(request, response);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

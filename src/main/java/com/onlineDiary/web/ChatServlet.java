package com.onlineDiary.web;

import com.onlineDiary.logic.DBService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatServlet extends HttpServlet {
    private DBService dbService = new DBService();
    private String login;
    private String receiver;

    private static final Logger LOGGER = Logger.getLogger(AddMarkServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (RequestHandler.isAuthorized(request)) {
            RequestHandler.setRole(request);
            login = RequestHandler.getLogin(request);
            setUsers(request);
            request.setAttribute("sender", login);
            request.getRequestDispatcher("Chat.jsp").forward(request, response);
        } else {
            response.sendRedirect("/auth");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (RequestHandler.isAuthorized(request)) {
            RequestHandler.setRole(request);
            setChatForm(request, response);
        } else {
            response.sendRedirect("/auth");
        }
    }

    private void sendMessage(HttpServletRequest request) {
        if ((request.getParameter("messOk") != null)
                & (request.getParameter("newMessage") != null)) {
            String text = request.getParameter("newMessage");
            dbService.addMessage(login, receiver, text);
            LOGGER.info("Message from " + login + " to " + receiver + " was sent!");
        }
    }

    private void setUsers(HttpServletRequest request) {
        request.setAttribute("messages", dbService.getMessages(login, receiver));
        request.setAttribute("receivers", dbService.getReceivers(login));
    }

    private void setReceiver(HttpServletRequest request) {
        if (request.getParameter("receiver") != null) {
            receiver = request.getParameter("receiver");
        }
        request.setAttribute("selectedUser", receiver);
        setUsers(request);
    }

    private void setChatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendMessage(request);
        setReceiver(request);
        request.getRequestDispatcher("Chat.jsp").forward(request, response);
    }
}

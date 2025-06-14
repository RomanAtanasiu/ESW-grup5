package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Tweet;
import model.User;
import repository.TweetRepository;
import service.TweetService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Tweets
 */
@WebServlet("/Tweets")
public class Tweets extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tweets() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Tweet> tweets = null;
        User user = null;
        HttpSession session = request.getSession(false);


        if (session != null) {
            user = (User) session.getAttribute("user");
            if (user != null) {
                try(TweetRepository tweetRepository = new TweetRepository()) {
                    TweetService tweetService = new TweetService(tweetRepository);
                    tweets = tweetService.getTweetsByUser(user.getId(),0,4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        request.setAttribute("tweets",tweets);
        request.setAttribute("user",user);
        request.getRequestDispatcher("Tweets.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

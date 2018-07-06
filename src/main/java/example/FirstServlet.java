package example;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/servlet")
public class FirstServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FirstServlet.class);
    private static final String COUNTER = "COUNTER";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();


        //response.sendRedirect("https://onet.pl");

        //request.getRequestDispatcher("/servlet2").forward(request, response);

        HttpSession session = request.getSession(true);

        Object counter = session.getAttribute(COUNTER);
        if (counter == null) {
            session.setAttribute(COUNTER, 1);
            logger.info("counter created!");
        } else {
            Integer intCounter = (Integer) counter;
            intCounter++;
            session.setAttribute(COUNTER, intCounter);
            logger.info("counter incremented!");
        }

        writer.append("Hello from servlet! " + new Date() + " for: " + session.getAttribute(COUNTER));

    }
}

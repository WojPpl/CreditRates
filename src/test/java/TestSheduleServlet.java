import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestSheduleServlet extends Mockito {

    @Test
    public void dont_greet_if_name_is_null() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        main.java.servlets.SheduleServlet servlet = new main.java.servlets.SheduleServlet();

        when(request.getParameter("name")).thenReturn(null);

        servlet.doPost(request, response);

        verify(response).sendRedirect("/");
    }

    @Test
    public void servlet_should_not_greet_the_user_if_the_name_is_empty() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        main.java.servlets.SheduleServlet servlet = new main.java.servlets.SheduleServlet();

        when(request.getParameter("name")).thenReturn("");

        servlet.doPost(request, response);

        verify(response).sendRedirect("/");
    }

    @Test
    public void servlet_should_greet_the_user_when_the_name_is_provided() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getParameter("name")).thenReturn("jan");
        when(response.getWriter()).thenReturn(writer);

        new main.java.servlets.SheduleServlet().doPost(request, response);

        verify(writer).println("<h1>Hello jan</h1>");
    }
}

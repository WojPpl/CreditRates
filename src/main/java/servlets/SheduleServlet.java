package main.java.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@WebServlet("/shedule")
public class SheduleServlet extends HttpServlet {

    private double amount;
    private Integer ratesNumber;
    private Double percent;
    private Double constantPayment;
    private Integer ratesType;
    private Double singleRateStatic;
    private Double singleRateDesc;
    private String shedule;

    public static boolean isntNumeric(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*Validation*/
        request.getParameterMap().entrySet().forEach(entry -> {
            if (request.getParameter(entry.getKey()) == null || request.getParameter(entry.getKey()).equals("") || this.isntNumeric(request.getParameter(entry.getKey()))) {
                try {
                    this.doError(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        DecimalFormat decFormat = new DecimalFormat("#.##");

        this.amount = Integer.parseInt(request.getParameter("amount"));
        this.ratesNumber = Integer.parseInt(request.getParameter("rates"));
        this.percent = Double.parseDouble(request.getParameter("percent"));
        this.constantPayment = Double.parseDouble(request.getParameter("salary"));
        this.ratesType = Integer.parseInt(request.getParameter("type"));

        this.singleRateStatic = ((this.amount * (1 + ((this.percent / 100) / 12) * this.ratesNumber)) / this.ratesNumber) + this.constantPayment;
        this.singleRateDesc = (this.amount / this.ratesNumber) + this.constantPayment;

        this.shedule = "<!DOCTYPE html>\n" +
                "\n" +
                "        <html>\n" +
                "        <head>\n" +
                "                <title>Harmonogram splat</title>\n" +
                "<style>" +
                "span { width: 19.8%; max-width: 19.8%; display: block; float: left; border: 1px solid #cccccc;}" +
                "p { width: 60%; float: left; line-height: 1.5rem; margin: 0 auto;} p:nth-child(even) {background: #cccccc;}" +
                "</style>" +
                "        </head>";
        this.shedule += "<h1>Harmonogram splat</h1>";
        this.shedule += "<p><span>Nr raty</span><span>Kwota Kapitalu</span><span>Kwota odsetek</span><span>Oplaty stale</span><span>Calkowita kwota raty</span></p>";

        for (int i = 1; i < this.ratesNumber + 1; i++) {
            Double rate;
            if (this.ratesType == 2) {
                rate = this.amount * ((this.percent / 100) / 12) + this.singleRateDesc;
                this.amount = (this.amount - this.singleRateDesc);
            } else {
                rate = this.singleRateStatic;
            }
            this.shedule += "<p><span>" + i + "</span><span>" + decFormat.format((rate * this.ratesNumber) - (rate * i)) + "</span><span>" +
                    decFormat.format(((rate * this.ratesNumber) - (rate * i)) * (this.percent / 100)) + "</span><span>" +
                    decFormat.format(this.constantPayment) + "</span><span>" + decFormat.format(rate) + "</span><p>";
        }

        response.getWriter().println(this.shedule);
    }

    public void doError(HttpServletResponse response) throws IOException {
        response.sendRedirect("/?msg=err");
    }
}
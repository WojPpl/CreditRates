<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <!DOCTYPE html>

        <html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; UTF-8"/>
                <title>Wniosek kredytowy</title>
        </head>

        <body onload="errMsg()">
        <script>
                const errMsg = () => {
                let url = new URL(window.location.href);
                if (url.searchParams.get("msg")=="err") {
                        alert("Wypełnij prawidłowo wszystkie pola");
                }
        }
        </script>
        <style type="text/css">
        body {
        font-family: Tahoma, sans-serif;
        }
        .container {
        margin: 0 auto;
        padding: 20px 5%;
        max-width: 50%;
        background: #f1f1f1;
        }
        h3 {
        text-align: center;
        margin-bottom: 20px;
        }
        label {
        min-width: 25%;
        float: left;
        }
        hr {
        border-top: 1px solid #fcfcfc;
        height: 1px;
        color: #ccc;
        margin: 15px 0;
        }
        button {
        color: #ffffff;
        background: rgba(0,0,0,0.7);
        border-radius: 0;
        border: none;
        padding: 10px 15px;
        margin: 0 auto;
        cursor: pointer;
        transition: 0.4s linear;
        }
        button:hover {
        background: #000000;
        }
        </style>

        <div class="container">
                <h3>Wypełnij formularz aby zobaczyć harmonogram spłat</h3>
                <form action="schedule" method="post">
                        <label>Kwota kredytu[PLN]:</label><input type="number" id="amount" name="amount"/>
                        <hr />
                        <label>Ilość rat:</label><input type="number" id="rates" name="rates"/>
                        <hr />
                        <label>Oprocentowanie[%]:</label><input type="text" id="percent" name="percent"/>
                        <hr />
                        <label>Opłata stała:</label><input type="text" id="salary" name="salary"/>
                        <hr />
                        <label>Rodzaj rat:</label><select name="type"><option value="1">stała</option><option value="2">malejąca</option></select>
                        <hr />
                        <button>Wyślij</button>
                </form>
        </div>
        </body>

        </html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset='utf-8' content="Cory Sanoy" name="Author" />

    <script>
        $(document).ready(function() {
            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                defaultDate: '2019-06-01',
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: {
                    url: '/allevents',
                    type: 'GET',
                    error: function() {
                        alert('there was an error while fetching events!');
                    },
                    //color: 'blue',   // a non-ajax option
                    //textColor: 'white' // a non-ajax option
                }
            });

        });

    </script>

    <style>
        body {
            margin: 40px 10px;
            padding: 0;
            font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
            font-size: 14px;
        }
        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div id='calendar' th:id="calendar"></div>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OWASP Demo Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    </head>
    <body>
        
        <div class="container">
            <h3>Press Button to load your personal data</h3>
            <input type="text" id="id" value="<%= request.getUserPrincipal().getName() %>">
            <br/><br/>
            <button id="getDataBtn" class="bnt btn-default">Get Personal Data</button>
            <br/> <br/>
            <div style="font-size: large" id="data"></div>
        </div>
            
        <br/><br/>
        <br/><br/>
        <div class="container">
            <input type="text" id="id1" value="<%= request.getUserPrincipal().getName() %>">
            <br/><br/>
            <button id="getDataBtnV" class="bnt btn-default">Get Personal Data (Vulnerable)</button>
            <br/> <br/>
            <div style="font-size: large" id="data1"></div>
        </div>
    </body>

    <script>
        document.getElementById("getDataBtn").onclick = getData;
        function getData() {
            // The credentials property, ensures that HTTP Authentication Coockie is included with the request
            var parameters = {
                method: 'GET',
                credentials: 'same-origin',
                headers: {
                    'Accept': 'application/json'
                }
            };
            fetch("api/data/" + document.getElementById("id").value, parameters)
                    .then((r) => r.json())
                    .then(d => document.getElementById("data").innerHTML = d.data);
        }
        
        document.getElementById("getDataBtnV").onclick = getDataV;
        function getDataV() {
            // The credentials property, ensures that HTTP Authentication Coockie is included with the request
            var parameters = {
                method: 'GET',
                credentials: 'same-origin',
                headers: {
                    'Accept': 'application/json'
                }
            };
            fetch("api/data/v1/" + document.getElementById("id1").value, parameters)
                    .then((r) => r.json())
                    .then(d => document.getElementById("data1").innerHTML = d.data);
        }
    </script>
</html>

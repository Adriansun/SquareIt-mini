let numberIdResponse;

refresh = function() {
    countAllNumbers();
    getListOfAllNumbers();
}

saveNumber = function() {
    const numb = document.getElementById("postNumber").value;

    let numberJson = {
        "number": numb
    };

    const http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            const response = JSON.parse(http.responseText);
            document.getElementById("respDataId").value = numberIdResponse = response.id;
            refresh();
        }
    };

    // Using IDE to run the project with embedded database server on port 8082.
    http.open("POST", "http://localhost:8082/rest/square/number/v1/setNumber", true);

    // Using Tomcat with specific ip to the server and port 8080, and a database named squareit.
    // http.open("POST", "http://178.251.131.62:8080/squareit/rest/square/number/v1/setNumber", true);

    // Using Tomcat with localhost to the server and port 8080, and a database named squareit.
    // http.open("POST", "http://localhost:8080/squareit/rest/square/number/v1/setNumber", true);

    http.setRequestHeader("Content-Type", "application/json");
    http.send(JSON.stringify(numberJson));
}

getNumber = function() {
    const http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            const response = JSON.parse(http.responseText);
            document.getElementById("getNumberId").value = response.squareDto.number;
        }
    };

    // Using IDE to run the project with embedded database server on port 8082.
    http.open("GET", "http://localhost:8082/rest/square/number/v1/getNumber/" + numberIdResponse, true);

    // Using Tomcat with specific ip to the server and port 8080, and a database named squareit.
    // http.open("GET", "http://178.251.131.62:8080/squareit/rest/square/number/v1/getNumber/" + numberIdResponse, true);

    // Using Tomcat with localhost to the server and port 8080, and a database named squareit.
    // http.open("GET", "http://localhost:8080/squareit/rest/square/number/v1/getNumber/" + numberIdResponse, true);

    http.send();
}

deleteNumber = function() {
    const id = document.getElementById("idToDelete").value;

    let deleteIdJson = {
        "numberId": id
    };

    const http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            const response = JSON.parse(http.responseText);
            document.getElementById("numberDeleted").value = response.statusText;
            refresh();
        } else {
            document.getElementById("numberDeleted").value = "Error. ID not found";
        }
    };

    // Using IDE to run the project with embedded database server on port 8082.
    http.open("POST", "http://localhost:8082/rest/square/number/v1/deleteId", true);

    // Using Tomcat with specific ip to the server and port 8080, and a database named squareit.
    // http.open("POST", "http://178.251.131.62:8080/squareit/rest/square/number/v1/deleteId", true);

    // Using Tomcat with localhost to the server and port 8080, and a database named squareit.
    // http.open("POST", "http://localhost:8080/squareit/rest/square/number/v1/deleteId", true);

    http.setRequestHeader("Content-Type", "application/json");
    http.send(JSON.stringify(deleteIdJson));
}

countAllNumbers = function() {
    const http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            const response = JSON.parse(http.responseText);
            document.getElementById("getAllNumbers").value = response.countTot;
        }
    };

    // Using IDE to run the project with embedded database server on port 8082.
    http.open("GET", "http://localhost:8082/rest/square/number/v1/countNumbers", true);

    // Using Tomcat with specific ip to the server and port 8080, and a database named squareit.
    // http.open("GET", "http://178.251.131.62:8080/squareit/rest/square/number/v1/countNumbers", true);

    // Using Tomcat with localhost to the server and port 8080, and a database named squareit.
    // http.open("GET", "http://localhost:8080/squareit/rest/square/number/v1/countNumbers", true);

    http.send();
}

getListOfAllNumbers = function() {
    const http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            const response = JSON.parse(http.responseText);

            const select = document.getElementById("getListOfNumbers");
            select.options.length = 0;

            let convertedList = [];

            for (let i = 0; i < response.squareNumberDtoList.length; i++) {
                convertedList[i] = "id: " + response.squareNumberDtoList[i].id + ", number: "
                + response.squareNumberDtoList[i].number;
            }

            const fragment = document.createDocumentFragment();

            convertedList.forEach(function(item) {
                const opt = document.createElement('option');
                opt.innerHTML = item;
                fragment.appendChild(opt);
            });

            select.appendChild(fragment);
        }
    };
    // Using IDE to run the project with embedded database server on port 8082.
    http.open("GET", "http://localhost:8082/rest/square/number/v1/getListOfItems", true);

    // Using Tomcat with specific ip to the server and port 8080, and a database named squareit.
    // http.open("GET", "http://178.251.131.62:8080/squareit/rest/square/number/v1/getListOfItems", true);

    // Using Tomcat with localhost to the server and port 8080, and a database named squareit.
    // http.open("GET", "http://localhost:8080/squareit/rest/square/number/v1/getListOfItems", true);

    http.send();
}
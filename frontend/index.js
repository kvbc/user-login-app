const APIUrl = "http://localhost:8080/api/user";
const APIRegisterUrl = "http://localhost:8080/api/user/register";
const APILoginUrl = "http://localhost:8080/api/user/login";
const APIDeleteUrl = "http://localhost:8080/api/user/delete"

function APIRequest (method, url, json, callback) {
    var req = {
        "url": url,
        "method": method,
        "success": callback
    };

    // empty JSON
    if (Object.keys(json).length == 0) {
        return $.ajax(req);
    }

    req.dataType = "json";
    req.contentType = "application/json";
    req.data = JSON.stringify(json);

    return $.ajax(req);
}

function updateRegisteredUsersTable () {
    $("#users").html('<tr><th>ID</th><th>Login</th><th>Password Hash</th></tr>')
    APIRequest("GET", APIUrl, {}, (d) => {
        d.forEach(u => {
            $("#users").append(`<tr><td>${u.id}</td><td>${u.login}</td><td>${u.password}</td></tr>`)
        });
    });
}

function APICredentials (method, url, success_msg) {
    APIRequest(method, url, {
        "login": $("#i-login").val(),
        "password": $("#i-pwd").val()
    }, (d) => {
        if (d.user == null) {
            $("#out").css("color", "red");
            $("#out").html(d.msg);
        } else {
            $("#out").css("color", "green");
            $("#out").html(success_msg);
            updateRegisteredUsersTable();
        }
    })
}

$(document).ready(() => {
    updateRegisteredUsersTable();
    $("#btn-log").on("click", () => APICredentials("POST", APILoginUrl, "Logged in successfully"));
    $("#btn-reg").on("click", () => APICredentials("POST", APIRegisterUrl, "Your account has been registered"));
    $("#btn-del").on("click", () => APICredentials("DELETE", APIDeleteUrl, "Your account has been deleted"));
    $("#btn-delall").on("click", () => APIRequest("DELETE", APIUrl, {}, () => updateRegisteredUsersTable()));
})

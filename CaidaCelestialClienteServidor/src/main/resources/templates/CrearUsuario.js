function createUser(user) {
    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/registration',
        data: JSON.stringify(user),
        processData: false,
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (user) {
        console.log("User created: " + JSON.stringify(item));
    })
}

$("#registrarse-button").click(function () {

        var username = $('#user');
        var password = $('#password');
        
        var user = {
            username: username.val(),
            password: password.val()
        }

        createUser(user);
    })
function registerKunde() {
    let username = document.getElementById("username");
    let password = document.getElementById("password");

    let obj = new Object();
    obj.username = username.value;
    obj.password = password.value;

    console.log(obj);
    username.value = "";
    password.value = "";


    fetch("/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(obj)
    }).then(function (response) {
        // popups erstellen, die dem user feedback geben
        if (response.ok) {
            //console.log("Nutzer erstellt!");
            var registerSuccess = document.getElementById("registerSuccess");
            registerSuccess.style.opacity = "1";
            setTimeout(() => {
                registerSuccess.style.transition = "opacity 3s";
                registerSuccess.style.opacity = "0";
            }, 2000);
        } else {
            if(response.status === 422){
                var usernameOccupied = document.getElementById("usernameOccupied");
                usernameOccupied.style.opacity = "1";
                setTimeout(() => {
                    usernameOccupied.style.transition = "opacity 3s";
                    usernameOccupied.style.opacity = "0";
                }, 2000);
            } else {
                alert("Fehler bei erstellen des Nutzers " + response.status);
            }
        }
    })
        .catch(function (error) {
            alert("Fehler bei erstellen des Nutzers: " + error);
        });
}
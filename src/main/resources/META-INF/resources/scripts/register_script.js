function registerKunde() {
    let username = document.getElementById("username");
    let password = document.getElementById("password");

    let obj = new Object();
    obj.username = username.value;
    obj.password = password.value;

    console.log(obj);

    fetch("/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(obj)
    }).then(function (response) {
        // popups erstellen, die dem user feedback geben
        if (response.ok) {
            console.log("Nutzer erstellt!");
        } else {
            alert("Fehler bei erstellen des Nutzers " + response.status);
        }
    })
        .catch(function (error) {
            alert("Fehler bei erstellen des Nutzers: " + error);
        });
}
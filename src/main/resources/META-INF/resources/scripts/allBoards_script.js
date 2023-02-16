const LOGGED_COOKIE = "quarkus-credential";
function logout() {
    console.log("logging out")
    document.cookie = LOGGED_COOKIE + '=; Max-Age=0'
    window.location.href = "/";
}

console.log(location.hostname);
//https://stackoverflow.com/questions/10593013/delete-cookie-by-name
// //falls cookies nicht richtig gelöscht werden
//document.cookie = LOGGED_COOKIE +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';

function createKanbanBoard(titel, boardId) {
    // Create card element
    var card = document.createElement("div");
    card.classList.add("card");
    card.style.width = "25rem";
    card.id = "card" + boardId;

    // Create card body
    var cardBody = document.createElement("div");
    cardBody.classList.add("card-body");
    card.appendChild(cardBody);

    // Create delete button
    var deleteButton = document.createElement("button");
    deleteButton.classList.add("delete-kanban");
    deleteButton.id = "delete" + boardId;
    deleteButton.innerHTML = "X";
    deleteButton.addEventListener("click", function (e) {
        deleteKanban(boardId);
    });
    cardBody.appendChild(deleteButton);

    // Create card link
    var cardLink1 = document.createElement("a");
    cardLink1.classList.add("card-link");
    cardLink1.href = "#";
    cardLink1.innerHTML = "Another link";
    var paddingLeft = document.createElement("div");
    paddingLeft.classList.add("padding-left-xl");
    paddingLeft.appendChild(cardLink1);
    cardBody.appendChild(paddingLeft);

    // Create card title
    var cardTitle = document.createElement("h1");
    cardTitle.classList.add("card-title");
    cardTitle.innerHTML = titel;
    cardBody.appendChild(cardTitle);

    // Create card link
    var cardLink2 = document.createElement("a");
    cardLink2.classList.add("card-link");
    cardLink2.innerHTML = "Zum Kanbanboard";
    cardLink2.addEventListener("click", function (e) {
        e.preventDefault();
        window.location.href = "http://"+ location.hostname +":8080/kanban/board/" + boardId;
    });
    cardBody.appendChild(cardLink2);

    var existingDiv = document.getElementById("existing-div");
    existingDiv.appendChild(card);
}

var socket = new WebSocket("ws://" + location.hostname +":8080/kanban");
socket.onmessage = function (event) {
    var message = JSON.parse(event.data);
    console.log(message);
    if (message.type === "kanban_board_created") {
        createKanbanBoard(message.titel, message.boardId);
    }
    if (message.type === "kanban_board_deleted") {
        document.getElementById("card" + message.boardId).remove();
    }
};

function deleteKanban(boardId) {
    var request = new Request("http://"+ location.hostname +":8080/kanban", {
        method: "DELETE",
        body: boardId,
        headers: {"Content-Type": "application/json"}
    });
    // mit fetch zu quarkus markus senden
    fetch(request)
        .then(function (response) {
            // popups erstellen, die dem user feedback geben
            if (response.ok) {
                console.log("Kanban board wurde deleted!");

            } else {
                alert("Fehler beim löschen des Kanban boards " + response.status);
            }
        })
        .catch(function (error) {
            alert("Fehler beim löschen des Kanban boards: " + error);
        });
}

document.getElementById("submit-kanban").addEventListener("click", function (event) {
    event.preventDefault(); // prevent the form from submitting

    var inputField = document.getElementById("input-field");
    var kanbanBoardTitel = inputField.value;

    // neues Request object
    var request = new Request("http://"+ location.hostname +":8080/kanban", {
        method: "POST",
        body: kanbanBoardTitel,
        headers: {"Content-Type": "application/json"}
    });

    // mit fetch zu quarkus markus senden
    fetch(request)
        .then(function (response) {
            // popups erstellen, die dem user feedback geben
            if (response.ok) {
                console.log("Kanban board erstellt!");
            } else {
                alert("Fehler bei erstellen des Kanban boards " + response.status);
            }
        })
        .catch(function (error) {
            alert("Fehler bei erstellen des Kanban boards: " + error);
        });
    // Close the modal
    modal.style.display = "none";
});

modalAddBoard();

function modalAddBoard(){
    var modal = document.getElementById("modal");
    console.log(modal);

// Get the button that opens the modal
    var btn = document.getElementById("open-modal-btn");
    console.log(btn);
// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close-modal")[0];

// When the user clicks the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";
        //Für den autofocus, damit instant geschrieben werden kann
        document.getElementById("input-field").focus();
    }
// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
// Get the form element
    var form = document.getElementById("modal-form");
}




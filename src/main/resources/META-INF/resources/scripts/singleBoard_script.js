function hello(){
    alert("Hello, ich bin eine Nachricht!");
}
function dragzone() {
    /* events fired on the draggable target */
    const sources = document.querySelectorAll(".draggable");
    sources.forEach(source => {
        source.addEventListener("drag", (event) => {
            console.log("dragging");
        });
        source.addEventListener("dragstart", (event) => {
            // store a ref. on the dragged elem
            dragged = event.target;
            // make it half transparent
            event.target.classList.add("dragging");
        });
        source.addEventListener("dragend", (event) => {
            // reset the transparency
            event.target.classList.remove("dragging");
        });
    })
    /* events fired on the drop targets */
    //const target = document.getElementById("droptarget");
    const listTargets = document.querySelectorAll(".dropzone");
    listTargets.forEach(target => {
        target.addEventListener("dragover", (event) => {
            // prevent default to allow drop
            event.preventDefault();
        }, false);
        target.addEventListener("dragenter", (event) => {
            // highlight potential drop target when the draggable element enters it
            if (event.target.classList.contains("dropzone")) {
                event.target.classList.add("dragover");
            }
        });
        target.addEventListener("dragleave", (event) => {
            // reset background of potential drop target when the draggable element leaves it
            if (event.target.classList.contains("dropzone")) {
                event.target.classList.remove("dragover");
            }
        });
        target.addEventListener("drop", (event) => {
            // prevent default action (open as link for some elements)
            event.preventDefault();
            // move dragged element to the selected drop target
            if (event.target.classList.contains("dropzone")) {
                event.target.classList.remove("dragover");
                event.target.appendChild(dragged);
            }
        });
    })
}

var socket = new WebSocket("ws://localhost:8080/kanban/board");
socket.onmessage = function (event) {
    var message = JSON.parse(event.data);
    console.log(message);
    if (message.type === "liste_kanban_created") {
        createKanbanListElement(message.titel, message.listeId);
    }
    if(message.type === "liste_kanban_deleted"){
        document.getElementById("liste" + message.listeId).remove();
    }
};
//New List-Item
document.getElementById("add-button").addEventListener("click", function (event) {
    event.preventDefault(); // prevent the form from submitting

    var inputField = document.getElementById("input-field");
    var listKanbanTitel = inputField.value;
    var obj = new Object();
    var currentUrl = window.location.href;
    var boardId = currentUrl.substring(currentUrl.lastIndexOf('/') + 1);
    obj.boardId = boardId;
    obj.titel = listKanbanTitel;

    var jsonString = JSON.stringify(obj);
    console.log(jsonString);
    var request = new Request("http://localhost:8080/kanban/board/PostForList", {
        method: "POST",
        body: jsonString,
        headers: { "Content-Type": "application/json"}
    });
    // mit fetch zu quarkus markus senden
    fetch(request)
        .then(function (response) {
            // popups erstellen, die dem user feedback geben
            if (response.ok) {
                console.log("Liste erstellt!");
            } else {
                alert("Fehler bei erstellen der Liste" + response.status);
            }
        })
        .catch(function (error) {
            alert("Fehler bei erstellen der Liste: " + error);
        });
    modal.style.display = "none";
});

function deleteListe(listeId, boardId){

    var obj = new Object();
    obj.listeId = listeId;
    obj.boardId = boardId;
    var jsonString = JSON.stringify(obj);

    var request = new Request("http://localhost:8080/kanban/board", {
        method: "DELETE",
        body: jsonString,
        headers: { "Content-Type" : "application/json"}
    });
    // mit fetch zu quarkus markus senden
    fetch(request)
        .then(function (response) {
            // popups erstellen, die dem user feedback geben
            if (response.ok) {
                console.log("Die Liste wurde deleted")

            } else {
                alert("Fehler beim löschen der Liste " + response.status);
            }
        })
        .catch(function (error) {
            alert("Fehler beim löschen der Liste: " + error);
        });
}

function createKanbanListElement(titel, listeId) {
    // Create new dropzone element
    var newListKanbanDiv = document.createElement("div");
    newListKanbanDiv.classList.add("dropzone");
    newListKanbanDiv.setAttribute("id", "color-picker" + listeId);
    newListKanbanDiv.id = "liste" + listeId;

    // Create new h2 element for the title
    var newTitle = document.createElement("h2");
    newTitle.classList.add("list-title");
    newTitle.innerHTML = titel;

    // Create new label for color picker
    var colorPickerLabel = document.createElement("label");
    colorPickerLabel.setAttribute("for", "color-picker");
    colorPickerLabel.innerHTML = "Background color: ";

    // Create delete button
    var deleteButton = document.createElement("button");
    deleteButton.classList.add("delete-kanban");
    deleteButton.id = "delete" + listeId;
    deleteButton.innerHTML = "X";
    deleteButton.addEventListener("click", function (e) {
        var currentUrl = window.location.href;
        var boardId = currentUrl.substring(currentUrl.lastIndexOf('/') + 1);
        deleteListe(listeId, boardId);
    });
    newListKanbanDiv.appendChild(deleteButton);

    // Create new color picker input
    var colorPickerInput = document.createElement("input");
    colorPickerInput.setAttribute("type", "color");
    colorPickerInput.setAttribute("id", "color-picker");
    colorPickerInput.setAttribute("name", "color-picker");
    colorPickerInput.setAttribute("style", "width: 30px; height: 15px;");
    colorPickerInput.addEventListener("change", function () {
        var parent = this.parentElement.parentElement;
        parent.style.backgroundColor = this.value;
    });

    var addElementButton = document.createElement("button");
    addElementButton.innerHTML = "+ Element hinzufügen";
    addElementButton.classList.add("add-element-button");
    addElementButton.addEventListener("click", function (event){
        addElementToList(listeId);
    })

    var eButtonDiv = document.createElement("div");
    eButtonDiv.classList.add("element-button-div")
    eButtonDiv.appendChild(addElementButton);

    // Append elements to new dropzone element
    newListKanbanDiv.appendChild(newTitle);
    newListKanbanDiv.appendChild(eButtonDiv);
    newListKanbanDiv.appendChild(colorPickerLabel);
    colorPickerLabel.appendChild(colorPickerInput);

    // Append new dropzone element to boardKanban div
    document.getElementById("boardKanban").appendChild(newListKanbanDiv);
    dragzone();

    return newListKanbanDiv;
}

function createCard(title) {
    // Create card element
    var card = document.createElement("div");
    card.setAttribute("draggable", "true");
    card.classList.add("card", "draggable");
    card.innerHTML = title;
    return card;
}

function addElementToList(listeId){

}

modalAddListe();

function modalAddListe(){
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

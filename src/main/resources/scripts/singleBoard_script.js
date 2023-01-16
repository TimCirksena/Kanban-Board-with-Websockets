const listKanbans = document.querySelectorAll('.listKanban');
//jedes kanbanElement bekommt die DragDropListener
listKanbans.forEach(listKanban => {
    listKanban.addEventListener('dragstart', handleDragStart);
    listKanban.addEventListener('dragenter', handleDragEnter);
    listKanban.addEventListener('dragover', handleDragOver);
    listKanban.addEventListener('dragleave', handleDragLeave);
    listKanban.addEventListener('drop', handleDrop);
});
//dieses kann man dann für die zukunft verwenden
let draggedItem;

function handleDragStart(event) {
    draggedItem = event.target;

    //movement erlauben
    event.dataTransfer.effectAllowed = 'move';
    //dataTransfer is ein temporäres feld, wir können dort einfach
    //die id des html element zwischenspeichern,
    // um es später holen zu können
    event.dataTransfer.setData('text/plain', event.target.id);
}

function handleDragEnter(event) {
    //hiermit zeigen wir, dass ein User hier etwas Droppen kann
    event.target.classList.add('dragOver');
}

function handleDragOver(event) {
    // prevent default to allow dropd
    event.preventDefault();
    //hier werden die erlaubten drag and drop operationen zugewiesen
    //wir möchten nur moven
    event.dataTransfer.dropEffect = 'move';
}

function handleDragLeave(event) {
    event.target.classList.remove('dragOver');
}

function handleDrop(event) {
    //damit die parent elements nicht das event annehmen
    if (event.stopPropagation) {
        event.stopPropagation();
    }

    const id = event.dataTransfer.getData('text');

    const card = document.getElementById(id);
    //in die liste packen, wo das dragDrop event beendet wurde
    event.target.querySelector('ul').appendChild(card);

    event.target.classList.remove('dragOver');
}

const tdList = document.getElementById('tictactoe').getElementsByTagName('td');
const rowNum = document.getElementsByTagName('tr').length;
const colNum = document.querySelector("tr.row").getElementsByTagName('td').length;
const cancelBtn = document.getElementById('cancelBtn');
let playHistory;
const xhr = new XMLHttpRequest();
const sessionId = sessionStorage.getItem("sessionId");

for (let index = 0; index < tdList.length; index++) {
    tdList[index].addEventListener('click', (event) => {
        draw(event);
    });
}

document.getElementById('cancelBtn').addEventListener('click', () => {
    cancel();
});

window.onload = initialize;

function initialize() {
    console.log('initialize() start');

    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            playHistory = [];
            cancelBtn.disabled = "disabled";
            for (let i = 0; i < colNum; i++) {
                const col = document.getElementsByName(i);
                for (let j = 0; j < rowNum; j++) {
                    col[j].innerHTML = '';            
                }
            }
        }
    };
    xhr.onerror = function() {
        console.log('initialize GET error');
    }
    xhr.open('GET', 'initialize?sessionId=' + sessionId);
    xhr.send();
}

function draw(event) {
    console.log('draw() start');
    
    const rowIdx = Array.from(document.getElementsByTagName('tr')).indexOf(event.target.parentNode);
    const colIdx = event.target.getAttribute('name');
    
    if (event.target.innerHTML != ''){
        return;
    }
    
    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            cancelBtn.disabled = "";
            playHistory.push(event.target);
            event.target.innerHTML = xhr.responseText;
            checkEnd(event, xhr.responseText);
        }
    };
    xhr.onerror = function() {
        console.log('draw GET error');
    }
    xhr.open('GET', 'draw?rowIdx=' + rowIdx + '&colIdx=' + colIdx + "&sessionId=" + sessionId);
    xhr.send();
}

function cancel() {
    console.log('cancel() start');
    
    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            playHistory.pop().innerHTML = '';
            if (playHistory.length == 0) {
                cancelBtn.disabled = "disabled";
            }
        }
    };
    xhr.onerror = function() {
        console.log('cancel GET error');
    }
    xhr.open('GET', 'cancel?sessionId=' + sessionId);
    xhr.send();
}

function checkEnd(event, mark) {
    console.log('checkEnd() start');
    const rowIdx = Array.from(document.getElementsByTagName('tr')).indexOf(event.target.parentNode);
    const colIdx = event.target.getAttribute('name');

    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            if (xhr.responseText.trim() == '') {
                return;
            } else {
                setTimeout(() => {
                    alert(xhr.responseText);
                    initialize();
                }, 100);
            }
        }
    };
    xhr.onerror = function() {
        console.log('checkEnd GET error');
    }
    xhr.open('GET', 'checkEnd?rowIdx=' + rowIdx + 
                    '&colIdx=' + colIdx + 
                    '&length=' + rowNum + 
                    '&mark=' + mark + 
                    '&sessionId=' + sessionId);
    xhr.send();
}
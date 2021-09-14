const tdList = document.getElementById('tictactoe').getElementsByTagName('td');
const rowNum = document.getElementsByTagName('tr').length;
const colNum = document.querySelector("tr.row").getElementsByTagName('td').length;
const cancleBtn = document.getElementById('cancleBtn');
let playHistory;
const xhr = new XMLHttpRequest();

for (let index = 0; index < tdList.length; index++) {
    tdList[index].addEventListener('click', (event) => {
        draw(event);
    });
}

// let table = new Array(rowNum);

// for (let i = 0; i < rowNum; i++) {
//     for (let j = 0; j < colNum; j++) {
//         table[i][j] = '';
//     }    
// }

document.getElementById('cancleBtn').addEventListener('click', () => {
    cancle();
});

window.onload = initialize();

function initialize() {
    console.log('initialize() start');

    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            playHistory = [];
            cancleBtn.disabled = "disabled";
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
    xhr.open('GET', 'initialize');
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
            cancleBtn.disabled = "";
            playHistory.push(event.target);
            event.target.innerHTML = xhr.responseText;
            checkEnd(event, xhr.responseText);
        }
    };
    xhr.onerror = function() {
        console.log('draw GET error');
    }
    xhr.open('GET', 'draw?rowIdx=' + rowIdx + '&colIdx=' + colIdx);
    xhr.send();
}

// function cancle() {
//     console.log('cancle() start');
//     playHistory.pop().innerHTML = '';
//     count--;
//     if (playHistory.length == 0) {
//         cancleBtn.disabled = "disabled";
//     }
// }

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
    xhr.open('GET', 'checkEnd?rowIdx=' + rowIdx + '&colIdx=' + colIdx + '&length=' + rowNum + '&mark=' + mark);
    xhr.send();
}
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
        console.log('init GET error');
    }
    xhr.open('GET', 'init');
    xhr.send();
}

function draw(event) {
    console.log('draw() start');
    
    if (event.target.innerHTML != ''){
        return;
    }
    
    xhr.onload = function() {
        if (xhr.status === 200 || xhr.status === 201) {
            cancleBtn.disabled = "";
            playHistory.push(event.target);
            event.target.innerHTML = xhr.responseText;
        }
    };
    xhr.onerror = function() {
        console.log('draw GET error');
    }
    xhr.open('GET', 'draw');
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

// function checkEnd(event, player) {
//     console.log('checkEnd() start');
//     const eventRow = event.target.parentNode.getElementsByTagName('td');
//     const eventCol = document.getElementsByName(event.target.getAttribute('name'));
//     const rowIdx = Array.from(document.getElementsByTagName('tr')).indexOf(event.target.parentNode);
//     const colIdx = event.target.getAttribute('name');
    
//     if (count < 2 * rowNum - 1) {
//         console.log('checkEnd() end');
//         return;
//     }

//     // 가로줄 판정
//     for (let index = 0; index < colNum; index++) {
//         if (eventRow[index].innerHTML != player) {
//             break;
//         }
//         if (index == colNum - 1) {
//             printResult(player + ' 승리');
//             return;
//         }
//     }

//     // 세로줄 판정
//     for (let index = 0; index < rowNum; index++) {
//         if (eventCol[index].innerHTML != player) {
//             break;
//         }
//         if (index == rowNum - 1) {
//             printResult(player + ' 승리');
//             return;
//         }
//     }

//     // 대각선 판정
//     if (rowIdx == colIdx) {
//         for (let index = 0; index < rowNum; index++) {
//             if (document.getElementsByName(index)[index].innerHTML != player) {
//                 break;
//             } 
//             if (index == rowNum - 1) {
//                 printResult(player + ' 승리');
//                 return;
//             }
//         }
//     }
    
//     // 역대각선 판정
//     if (parseInt(rowIdx) + parseInt(colIdx) == rowNum - 1) {
//         for (let index = 0; index < rowNum; index++) {
//             if (document.getElementsByName(index)[rowNum - index - 1].innerHTML != player) {
//                 break;
//             }
//             if (index == rowNum - 1) {
//                 printResult(player + ' 승리');
//                 return;
//             }
//         }
//     }

//     // 무승부 판정
//     if (count == tdList.length) {
//         printResult('무승부');
//         return;
//     }
//     console.log('checkEnd() end');
// }

// function printResult(message) {
//     setTimeout(() => {
//         alert(message);
//         initialize();
//     }, 100);
// }
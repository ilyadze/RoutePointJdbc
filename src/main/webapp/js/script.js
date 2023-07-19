ymaps.ready(init);
function init() {
    var map = new ymaps.Map("map", {
        center: [53.895208372848124, 27.56030528038091],
        zoom: 17,
    });

}

const requestURL = '/points';
// function sendRequest(method, url, body = null) {
//     return fetch(url);
//         // .then(response => {
//         // return response.json();
//     // })
// }
function sendRequest(url){
    const request = new XMLHttpRequest();
    request.open('GET', url);
    request.setRequestHeader("Content-type", "application/json; charset=utf-8");
    request.send();
    request.addEventListener("load" , function ()  {
        if(request.status == 200 ) {
            let data = JSON.parse(request.response);
            // data.forEach(function(el) {
            //     lastTaskId++;
            //     addTaskFromJson(el, tasks);
            // })
            // let size = data.length;
            // for(let i = 0;i < size;i++) {
            //     addTaskFromJson(data[i], tasks);
            // }
            // addTaskFromJson(data, tasks);
            console.log(JSON);
            console.log(data)
        } else {
            console.error("Что-то пошло не так");
        }
    });
    // tasksRender(tasks);
    // console.log(tasks);
}
sendRequest( requestURL);


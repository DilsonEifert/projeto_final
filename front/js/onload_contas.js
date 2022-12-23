//UTIL
function getCookie(){
    return document.cookie
    .split(";")
    .map(cookie => cookie.split("="))
    .reduce((accumulator, [key, value]) => 
        ({ ...accumulator, [key.trim()]: decodeURIComponent(value) }),
    {});
}

var conta_form = null;
var c_list_table = null;
var contas_form = null
var transacao_form_form = null
window.onload = function(){
    c_list_table = document.getElementById("c-list-table");
    conta_form = document.getElementById("contas_form")
    transacao_form_form = document.getElementById("transacao_form_form")
    listar_contas()
    /*
    if(getCookie().JSESSIONID == null){
        window.location.href = '/login.html'
    }
    */
}
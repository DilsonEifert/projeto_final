//UTIL
function getCookie(){
    return document.cookie
    .split(";")
    .map(cookie => cookie.split("="))
    .reduce((accumulator, [key, value]) => 
        ({ ...accumulator, [key.trim()]: decodeURIComponent(value) }),
    {});
}

var c_list_table = null;
var transacao_form = null
var transacao_form_form = null
window.onload = function(){
    categoria_manager = document.getElementById("categorias_manager_table");
    c_list_table = document.getElementById("c-list-table");
    transacao_form = document.getElementById("transacao_form")
    transacao_form_form = document.getElementById("transacao_form_form")
    select_usuario = document.getElementById("usuario")
    listar_categorias()
    listar_roles()
    /*
    if(getCookie().JSESSIONID == null){
        window.location.href = '/login.html'
    }
    */
}
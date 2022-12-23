url="http://desktop-mab14l9:8080/"
//url="http://localhost:8080/"

//CATEGORIAS
function listar_categorias(){
    categoria_manager.removeChild(categoria_manager.lastChild);
    var lista = new Array;
    var tbody = document.createElement('tbody');
    fetch(url+'categoria')
    .then((response) => response.json())
    .then((data) => {
        lista = data
        for(i = 0; i < lista.length; i++){
            tr = document.createElement('tr')

            td_id = document.createElement('td')
            td_checkbox = document.createElement('td')
            td_desc = document.createElement('td')

            input_hidden = document.createElement('input')
            input_hidden.setAttribute('type', 'hidden')
            input_hidden.setAttribute('value', lista[i].id)
            td_id.appendChild(input_hidden)
            
            input_box = document.createElement('input')
            input_box.setAttribute('type', 'checkbox')
            td_checkbox.appendChild(input_box)

            td_desc.appendChild(document.createTextNode(lista[i].nome))

            tr.appendChild(td_checkbox)
            tr.appendChild(td_desc)
            tr.appendChild(td_id)
            
            tbody.appendChild(tr)
            categoria_manager.appendChild(tbody)
        }
    });
}
function retornar_categorias(){
    var lista = new Array;
    fetch(url+'categoria')
    .then((response) => response.json())
    .then((data) => {
        for(i = 0; i < data.length; i++){
            lista.push(data[1])
        }
    })
    return lista
}
function cadastrar_categoria(){
    var name = window.prompt("Informe a categoria")

    fetch(url+'categoria', {
        method: 'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({nome:name, usuario_id:"41e4824e-48a2-408d-af35-b50e9ca6fd93"})
    })
    .then(response => {response.json(); listar_categorias()})    
}
function remover_categoria(){
    for(i = 1; i < categoria_manager.rows.length; i++){
        if(categoria_manager.rows[i].cells[0].childNodes[0].checked){
            
            fetch(url+'categoria/'+categoria_manager.rows[i].cells[2].childNodes[0].getAttribute('value'), {
            method: 'DELETE',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
                body: JSON.stringify({})
        })
        .then(response => {response.json(); listar_categorias()})
        
        }
    }
    
}
function editar_categoria(){
    var name = window.prompt("Informe a categoria")

    for(i = 1; i < categoria_manager.rows.length; i++){
        if(categoria_manager.rows[i].cells[0].childNodes[0].checked){
            
            fetch(url+'categoria/'+categoria_manager.rows[i].cells[2].childNodes[0].getAttribute('value'), {
            method: 'PUT',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
                body: JSON.stringify({nome:name, usuario_id:"41e4824e-48a2-408d-af35-b50e9ca6fd93"})
        })
        .then(response => {response.json(); listar_categorias()})
        
        } 
    }

}

//TRANSAÇÕES
function cadastrar_transacao(id){
    if(id == null){
        const d = new Date();
        date = d.getFullYear() + 1 + "-"+d.getMonth()+"-"+d.getDate();
    
        fetch(url+'transacao', {
            method: 'POST',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                descricao:transacao_form_form[0].value,
                credito:transacao_form_form[1].value,
                valor:transacao_form_form[2].value,
                categoria_id:transacao_form_form[3].value,
                usuario_id:transacao_form_form[4].value,
                data:date
            })
        }).then(function(){
            listar_transacoes()
            limpar_transacao_form()
        })

    } else {
        const d = new Date();
        date = d.getFullYear() + 1 + "-"+d.getMonth()+"-"+d.getDate();
    
        fetch(url+'transacao/'+id, {
            method: 'PUT',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                descricao:transacao_form_form[0].value,
                credito:transacao_form_form[1].value,
                valor:transacao_form_form[2].value,
                categoria_id:transacao_form_form[3].value,
                usuario_id:transacao_form_form[4].value,
                data:date
            })
        }).then(function(){
            listar_transacoes()
            limpar_transacao_form()
        })
    }
    
    
    
}

function remover_transacao(event){
    fetch(url+'transacao/'+event.currentTarget.parentNode.parentNode.childNodes[6].childNodes[0].getAttribute("value"), {
        method: 'DELETE',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
            body: JSON.stringify({})
    })
    .then(response => {response.json(); listar_transacoes()})
}

function editar_transacao(event){
    fetch(url+'transacao/'+event.currentTarget.parentNode.parentNode.childNodes[6].childNodes[0].getAttribute("value"), {
        method: 'GET',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
    
    .then(response => {
        transacao_carregar_form_new();
        return response.json();
        
    })
    .then(data => {
        transacao_form_form[0].value = data.descricao
        transacao_form_form[2].value = data.valor
        transacao_form_form[1].value = data.credito
        transacao_form_form[3].value = data.categoria_id
        transacao_form_form[4].value = data.usuario_id
        btn_cadastrar_transacao.setAttribute("onclick", "cadastrar_transacao('"+data.id+"')")
    })
}

function limpar_transacao_form(){
    transacao_form.childNodes[1].reset()
    transacao_form.style.display = 'none'
    select_categorias.innerHTML = ''
    select_usuario.innerHTML = ''
    btn_cadastrar_transacao.setAttribute("onclick", "cadastrar_transacao()")
}

function transacao_carregar_form_new(){
    transacao_form.style.display='flex'
    var lista = new Array;

    fetch(url+'categoria')
    .then((response) => response.json())
    .then((data) => {
        for(i = 0; i < data.length; i++){
            lista.push(data[i])
        }
        select_categorias = document.getElementById("categoria")
        for(i = 0; i < lista.length; i++){
            option = document.createElement("option")
            option.setAttribute("value", lista[i].id)
            optionText = document.createTextNode(lista[i].nome)
            option.appendChild(optionText)
            select_categorias.appendChild(option)
        }
    })
    lista2 = new Array;

    fetch(url+'usuario')
    .then((response_u) => response_u.json())
    .then((data_u) => {
        for(i = 0; i < data_u.length; i++){
            lista2.push(data_u[i])
        }
        select_usuario = document.getElementById("usuario")
        for(i = 0; i < lista2.length; i++){
            option = document.createElement("option")
            option.setAttribute("value", lista2[i].id)
            optionText = document.createTextNode(lista2[i].nome)
            option.appendChild(optionText)
            select_usuario.appendChild(option)
        }
    })
    
}

//BANCOS

function listar_roles(){
    c_list_table.innerHTML = ''
    fetch(url+'role')
    .then((response) => response.json())
    .then((data) => {
        thead = document.createElement('thead')
        thead_tr = document.createElement('tr')

        td_input = document.createElement('td')
        input = document.createElement('input')
        input.setAttribute("type","checkbox");
        td_input.appendChild(input)
        
        thead_tr.appendChild(td_input)
        thead_tr.appendChild(document.createElement('td'))
        thead_tr.appendChild(document.createElement('td'))
        td = document.createElement('td')
        td.appendChild(document.createTextNode('Nome'))
        thead_tr.appendChild(td)
        
        thead_tr.appendChild(td)
        thead.appendChild(thead_tr)
        c_list_table.appendChild(thead)

        tbody = document.createElement('tbody')
        for(i = 0; i < data.length; i++){
        
            tr = document.createElement('tr')
        
            td_check = document.createElement('td')
            td_check_input = document.createElement('input')
            td_check_input.setAttribute('type', 'checkbox')
            td_check.appendChild(td_check_input)

            td_remove = document.createElement('td')
            td_remove_img = document.createElement('img')
            td_remove_img.setAttribute('src', '/img/remove.png')
            td_remove_img.setAttribute('onclick', "remover_transacao(event)")
            td_remove.appendChild(td_remove_img)

            td_edit = document.createElement('td')
            td_edit_img = document.createElement('img')
            td_edit_img.setAttribute('src', '/img/edit.png')
            td_edit_img.setAttribute('onclick', "editar_transacao(event)")
            td_edit.appendChild(td_edit_img)

            td_categoria = document.createElement('td')
            td_categoria.appendChild(document.createTextNode(data[i].roleName))


            td_hidden = document.createElement('td')
            td_hidden_input = document.createElement('input')
            td_hidden_input.setAttribute('type', 'hidden')
            td_hidden_input.setAttribute('value', data[i].id)
            td_hidden.appendChild(td_hidden_input)

            tr.appendChild(td_check)
            tr.appendChild(td_remove)
            tr.appendChild(td_edit)
            tr.appendChild(td_categoria)
            tr.appendChild(td_hidden)

            tbody.appendChild(tr)
            c_list_table.appendChild(tbody)
        }
        
    });
}
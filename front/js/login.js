url="http://desktop-mab14l9:8080/"
function login(){
    fetch(url, {
        method: 'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            client_id:"dilson",
            client_secret:"teste"
        })
    }).catch(error => {
        console.error(error);
    })
} 

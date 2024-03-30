const url = "http://localhost:8080";

function register(event) {
    event.preventDefault();
    let form = event.target;
    const params = {
        method: "POST",
        body: JSON.stringify({
            username: form[0].value,
            password: form[1].value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }
    fetch(url + "/api/users/registration", params)
        .then(data => {
            console.log(data);
            if (!data.ok) {
                return;
            }
            window.location.href = "/";
            return data.json();
        })
        .then(json => {
            console.log(json);
        });

}


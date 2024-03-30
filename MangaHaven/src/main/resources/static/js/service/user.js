const url = "http://localhost:8080";

function fetchuser() {
    return fetch(url + "/api/users/user", {method: "GET"});
}

function updateuser(id, user) {
    const params = {
        method: "PUT",
        body: JSON.stringify(user),
        headers: {
            "Content-Type": "application/json"
        }
    }
    return fetch(url + `/api/users/${id}`, params);
}
const url = "http://localhost:8080";

function fetchAllChapters(mangaId) {
    const params = {
        method: "GET"
    }
    return fetch(url + `/api/mangas/${mangaId}/chapters`, params);
}

function fetchChapter(id) {
    return fetch(url + `/api/chapters/${id}`, {method: "GET"});
}

function deleteChapter(id) {
    const params = {
        method: "DELETE"
    }
    return fetch(url + `/api/chapters/${id}`, params);
}


function updateChapter(id , chapter){
    const params = {
        method: "PUT",
        body: JSON.stringify(chapter),
        headers: {
            "Content-Type": "application/json"
        }
    }
    return fetch(url + `/api/chapters/${id}`, params);
}

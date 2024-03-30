const url = "http://localhost:8080";

function fetchManga(id) {
    return fetch(url + `/api/mangas/${id}`, {method: "GET"});
}

function updateManga(id, manga) {
    const params = {
        method: "PUT",
        body: JSON.stringify(manga),
        headers: {
            "Content-Type": "application/json"
        }
    }
    return fetch(url + `/api/mangas/${id}`, params);
}

function deleteManga(id) {
    const params = {
        method: "DELETE"
    }
    return fetch(url + `/api/mangas/${id}`, params);
}

function likeManga(id) {
    const params = {
        method: "PUT"
    }
    return fetch(url + `/api/mangas/${id}/likes`, params);
}

/*

function fetchAllChapters(mangaId) {
    return fetch(url + `/api/mangas/${mangaId}/chapters`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error fetching chapters: ${response.status}`);
            }
            return response.json();
        })
        .catch(error => {
            console.error('Error fetching chapters:', error);
        });
}*/


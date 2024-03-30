let manga;

window.addEventListener("load", () => {
    const urlParts = window.location.pathname.split('/');
    const mangaId = urlParts[urlParts.length - 1];

    fetchManga(mangaId)
        .then(handleResponse)
        .then(displayMangaDetails)
        .then(displayChapters)
        .catch(handleError);
});

function handleResponse(response) {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
}

function displayMangaDetails(mangaJson) {
    manga = mangaJson;
    document.getElementById("manga-title").innerText = mangaJson.title;
    document.getElementById("manga-author").innerText = mangaJson.author;
    document.getElementById("manga-description").innerText = mangaJson.description;
    document.getElementById("manga-cover-image").setAttribute("src" , mangaJson.coverImage.content);
    document.getElementById("manga-liked").innerText = mangaJson.liked ? "dislike" : "like";
    document.getElementById("manga-likes").innerText = "Likes: " + mangaJson.likes;
    return mangaJson.chapters;
}

function displayChapters(chapters) {
    let mangaContainer = document.getElementById("chapterList");
    chapters.forEach(chapter => {
        let div = document.createElement("div");
        div.innerHTML = `
            <div style="border: 1px solid black">
                <h3>${chapter.chapterNumber}</h3>
                <p>${chapter.title}</p>
                <a href="/chapter/${chapter.id}"><button>Read</button></a>
            </div>`;
        mangaContainer.appendChild(div);
    });
}

function handleError(error) {
    console.error('Error:', error);

}

function fillUpdateForm(manga) {
    let form = document.getElementById("manga-update-form");
    form[0].value = manga.title;
    form[1].value = manga.author;
    form[2].value = manga.description;
    return manga;
}

function update(event) {
    event.preventDefault();
    let form = event.target;
    const imageFile = form[3].files[0];
    if (!imageFile) {
        return;
    }
    const reader = new FileReader();
    reader.readAsDataURL(imageFile);
    reader.onload = function (e) {
        const base64String = e.target.result;
        const mangaBody = {
            title: form[0].value,
            author: form[1].value,
            description: form[2].value,
            coverImage: {
                name: imageFile.name,
                content: base64String
            }
        }
        updateManga(manga.id, mangaBody)
            .then(data => data.json())
            .then(mangaJson => {
                manga = mangaJson;
                fillUpdateForm(manga);
            })
            .catch(e => {
                fillUpdateForm(manga);
            });
    }
}

function deleteM(event) {
    deleteManga(manga.id).then(() => window.location.href = "/")
}

function like(event) {
    likeManga(manga.id)
        .then(handleResponse)
        .then(displayMangaDetails)
        .catch(handleError);
}

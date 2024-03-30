const url = "http://localhost:8080";

window.addEventListener("load", () => {
    fetchSingleManga();
});


function fetchSingleManga() {
    const urll = window.location.pathname;
    const parts = urll.split('/');
    const mangaId = BigInt(parts[parts.length - 1]);
    const id = parts[parts.length - 1];
    console.log(url)
    fetch(url + `/api/mangas/${id}`, {method: "GET"})
        .then(data => data.json())
        .then(manga => {
            console.log(manga);
            document.getElementById("manga-title").innerText = manga.title;
            document.getElementById("manga-author").innerText += " " + manga.author;
            document.getElementById("manga-description").innerText +=  manga.description;
            document.getElementById("manga-cover-image").setAttribute("src" , manga.coverImage.content);
        })
        .catch(error => {
            console.error("Error fetching manga:", error);
        });

}


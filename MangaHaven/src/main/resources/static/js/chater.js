let chapter

window.addEventListener("load", () => {
    const urlParts = window.location.pathname.split('/');
    const chapterId = urlParts[urlParts.length - 1];

    fetchChapter(chapterId)
        .then(handleResponse)
        .then(displayChapterDetails)
        .catch(handleError);

});

function handleResponse(response) {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
}

function displayChapterDetails(chapterJson) {
    chapter = chapterJson
    document.getElementById("manga-link").href = "/manga/" + chapterJson.idManga;
    let container = document.getElementById("chapter-images")
    chapterJson.images.forEach(image => {
        let img = document.createElement("img")
        img.setAttribute("src" , image.content)
        container.append(img)
    })
    // document.getElementById("").setAttribute("src" , manga.coverImage.content);
}


function addChapter(event) {
    event.preventDefault();
    let form = event.target;
    let filePromises = Array.from(form[2].files).map(file => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = e => resolve(e.target.result);
            reader.onerror = e => reject(e);
            reader.readAsDataURL(file);
        });
    });

    Promise.all(filePromises).then(imagesBase64 => {
        const urlParts = window.location.pathname.split('/');
        const mangaId = urlParts[urlParts.length - 1];
        imagesBase64 = imagesBase64.map(image => {
            return {content: image}
        })
        const params = {
            method: "POST",
            body: JSON.stringify({

                idManga: mangaId,
                title: form[0].value,
                chapterNumber: form[1].value,
                images: imagesBase64
            }),
            headers: {
                "Content-Type": "application/json"
            }
        };

        fetch(url + "/api/chapters", params)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(json => {
                console.log(json);
                location.reload();
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
    });
}

function deleteC(event) {
    const urlParts = window.location.pathname.split('/');
    const chapterId = urlParts[urlParts.length - 1];
    deleteChapter(chapterId).then(() => window.location.href = `/manga/${manga.id}`)
}




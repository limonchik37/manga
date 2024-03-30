const url = "http://localhost:8080";

window.addEventListener("load", () => {
    fetchAllMangas();
});

function addmanga(event) {
    event.preventDefault();
    let form = event.target;
    const imageFile = form[3].files[0];
    if (!imageFile) {
        return;
    }
    const reader = new FileReader();
    reader.readAsDataURL(imageFile);
    reader.onload = function (e) {
        const base64String = e.target.result; // Base64-encoded image string
        const params = {
            method: "POST",
            body: JSON.stringify({
                title: form[0].value,
                author: form[1].value,
                description: form[2].value,
                coverImage: {
                    name: imageFile.name,
                    content: base64String
                }
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }
        fetch(url + `/api/mangas`, params)
            .then(data => {
                console.log(data);
                if (!data.ok) {
                    // тут можно ошибки отображать
                    return;
                }
                location.reload();
                return data.json();
            })
            .then(json => {
                console.log(json);
            });
    }
}


// formData.set('coverImageBase64', base64String);


// event.preventDefault();
// let form = event.target;
// const params = {
//     method: "POST",
//     body: JSON.stringify({
//         title: form[0].value,
//         author: form[1].value,
//         description : form[2].value,
//         coverImage : form[3].value
//     }),
//     headers: {
//         "Content-Type": "application/json"
//     }
// }
// fetch(url + "/api/users/registration", params)
//     .then(data => {
//         console.log(data);
//         if (!data.ok) {
//             // тут можно ошибки отображать
//             return;
//         }
//         window.location.href = "http://localhost:8080";
//         return data.json();
//     })
//     .then(json => {
//         console.log(json);
//     });

function fetchAllMangas() {
    fetch(url + "/api/mangas", {method: "GET"})
        .then(data => data.json())
        .then(mangas => {
            let mangaContainer = document.getElementById("mangaList");
            mangas.forEach(manga => {
                let div = document.createElement("div");
                div.innerHTML = `
                    <div style="border: 1px solid black">
                        <h3>${manga.title}</h3>
                        <p>${manga.author}</p>
                        <img src="${manga.coverImage.content}">
                        <a href="/manga/${manga.id}"><button>Read</button></a>
                    </div>`
                mangaContainer.append(div);
            })
        });
}


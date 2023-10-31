<!doctype html>
<html>
<head>
    <title>${manga.title}</title>
</head>
<body>
<h1>
    Manga Haven
</h1>
<h4>
    Information about manga
</h4>
<b>Manga title: </b>${manga.title}<br>
<b>Manga summary: </b>${manga.summary}<br>
<b>Manga author: </b>${manga.author}<br>
<hr>
<form action="/manga/delete/${manga.id}" method="post">
    <input type="submit" value="Delete manga"/>
</form>
</body>
</html>
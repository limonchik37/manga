<!doctype html>
<html lang="en">
<head>
    <title>MangaHaven</title>
</head>
<body>
<h1>MANGAHAVEN</h1>
<h4>All mangas</h4>
<form action="/" method ="get">
    Search manga by its title: <input type="text" name="title"><br>
    <input type="submit" value = "Search">
</form>
<#list mangas as manga>
    <div>
        <p><b>${manga.title}</b> ${manga.author} | <a href="/manga/${manga.id}">Details...</a> </p>
    </div>
    <#else>
    <h3>manga with this title does not exist</h3>
</#list>
<hr>
<h3>
    Add new manga
</h3>
<form action ="/manga/create" method="post">
    Manga title: <input type="text" name="title"/><br><br>
    Manga author: <input type="text" name="author"/><br><br>
    Manga summary: <input type="text" name="summary"/><br><br>
    <input type="submit" value="Add manga">
</form>
</body>
</html>
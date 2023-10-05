@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@import javax.naming.Name
@param MainPage page


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<main>
    <h1>Привет, Хекслет!</h1>
    @if(page.getName() != null)
        Добро пожаловать, ${page.getName()}.
    <form action="${NamedRoutes.logoutPath()}" method="post">
        <input type="submit" value="Exit"  >
    </form>
    @else
        <a href="${NamedRoutes.loginPath()}/build">Enter</a>
    @endif
</main>
</body>
</html>
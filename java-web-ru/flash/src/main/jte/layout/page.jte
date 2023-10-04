@import gg.jte.Content
@import exercise.util.NamedRoutes
@import exercise.dto.BasePage

@param BasePage page = null
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
@if (page!=null && page.getFlash()!=null)
    <p> ${page.getFlash()} </p>
@endif
<p>${content}</p>
</body>

</html>


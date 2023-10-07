@import gg.jte.Content
@param Content content
@param Content footer = null
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<p>
    <a href="/users">Users</a>
</p>
${content}
@if (footer != null)
    <div>
        ${footer}
    </div>
    
@endif

</body>
</html>
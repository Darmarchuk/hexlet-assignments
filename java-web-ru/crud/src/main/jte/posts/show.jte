@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page


@template.layout.page(content = @`
<table>
   <a href="${NamedRoutes.postsPath()}">Back to Posts list</a>
    <br>
   <tr>${ page.getPost().getName()} </tr>
</table>
    <tr><textarea> ${page.getPost().getBody() }</textarea></tr>
`)
<!-- BEGIN -->

<!-- END -->

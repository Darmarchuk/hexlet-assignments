@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(page=page,
content = @`
    <div>
        <table>

            @for(var post: page.getPosts() )
                <tr>
                    <td>${post.getName()} </td>
                    <td>${post.getId()}</td>
                </tr>
            @endfor

        </table>
    </div>
`)
@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage  page



@template.layout.page(content = @`
    <table>
        @for(var post: page.getPosts() )
            <tr>
                <td><a href="${NamedRoutes.postById(post.getId())}">show info:</a></td>
                <td>${post.getId()} </td>
                <td>${post.getName()} </td>
            </tr>
        @endfor
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            @if(page.getPageNum()>1)
            <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNum()-1}">Previous</a></li>
            @endif
            <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=1">1</a></li>
            <li class="page-item active" aria-current="page">
                <span class="page-link">${page.getPageNum()}</span>
            </li>
            @if(page.getPosts().size()>0)
            <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getPageNum()+1}">Next</a></li>
            @endif;
        </ul>
    </nav>
`)
<!-- BEGIN -->

<!-- END -->
